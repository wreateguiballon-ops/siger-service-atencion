package sat.siger.service.atencion.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sat.siger.service.atencion.dto.*;
import sat.siger.service.atencion.exception.BusinessException;
import sat.siger.service.atencion.model.*;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class AtencionRepositoryCustomImpl implements AtencionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String BASE_DETALLE = "select r.iCodigo_Req, r.sdFecha_Req, r.vSumilla_Req, r.vDescripcion_Req, r.iCodigo_Uo, r.iCodigo_Per, r.siCodigo_Est, e.vNombre_Est, r.iCodigo_CSC, r.iCodigo_Pri from dbo.GRMaeRequerimiento r left join dbo.GRTabEstado e on e.siCodigo_Est=r.siCodigo_Est ";

    @Override
    public List<RequerimientoDetalle> pendientes() {
        String sql = BASE_DETALLE + " where r.bActivo_Req=1 and exists (select 1 from dbo.GRMovRequerimiento m where m.iCodigo_Req=r.iCodigo_Req and m.bActivo_ReqMov=1 and m.bVigencia_ReqMov=1 and m.iCodigo_PerDes_ReqMov is not null) order by r.sdFecha_Req";
        return detalles(sql);
    }

    @Override
    public RequerimientoDetalle obtenerDetalle(Integer id) {
        List<RequerimientoDetalle> l = detalles(BASE_DETALLE + " where r.iCodigo_Req=:id", id);
        return l.isEmpty() ? null : l.get(0);
    }

    @Override
    public ClasificacionResponse obtenerClasificacion(Integer id) {
        Query q = entityManager.createNativeQuery("select iCodigo_Req, iCodigo_CSC, iCodigo_Pri, iCodigo_TipEva, iCodigo_TipMod, iCodigo_TipAut from dbo.GRMaeRequerimiento where iCodigo_Req=:id");
        q.setParameter("id", id);
        List<?> rows = q.getResultList();
        if (rows.isEmpty()) return null;
        Object[] r=(Object[])rows.get(0);
        return new ClasificacionResponse(((Number)r[0]).intValue(), numInt(r[1]), numInt(r[2]), numInt(r[3]), numInt(r[4]), numInt(r[5]));
    }

    @Override public List<DocumentoAdjunto> obtenerAdjuntos(Integer id) {
        return entityManager.createQuery("select d from DocumentoAdjunto d where d.codigoReq=:id and (d.activo=true or d.activo is null) order by d.fechaCarga", DocumentoAdjunto.class).setParameter("id",id).getResultList();
    }
    @Override public List<PersonaAutorizacion> obtenerAutorizaciones(Integer id) {
        return entityManager.createQuery("select a from PersonaAutorizacion a where a.codigoReq=:id and (a.activo=true or a.activo is null) order by a.fechaSolicitud", PersonaAutorizacion.class).setParameter("id",id).getResultList();
    }
    @Override public EvaluacionImpacto obtenerEvaluacionImpacto(Integer id) {
        List<EvaluacionImpacto> l=entityManager.createQuery("select e from EvaluacionImpacto e where e.codigoReq=:id and (e.activo=true or e.activo is null) order by e.fechaEvalDat desc",EvaluacionImpacto.class).setParameter("id",id).getResultList(); return l.isEmpty()?null:l.get(0);
    }
    @Override public EvaluacionImpacto registrarEvaluacionImpacto(Integer id, EvaluacionImpactoRequest r) {
        EvaluacionImpacto e=new EvaluacionImpacto(); e.setCodigoReq(id); e.setFechaEvalDat(LocalDateTime.now()); e.setCodigoPriEvalDat(r.getCodigoPriEvalDat()); e.setMontoDeuda(r.getMontoDeuda()); e.setMontoPagos(r.getMontoPagos()); e.setNumeroRegistros(r.getNumeroRegistros()); e.setNumeroPersonas(r.getNumeroPersonas()); e.setActivo(true); e.setCodPerActualizacion(r.getCodPerActualizacion()); e.setFechaActualizacion(LocalDateTime.now()); e.setNombreTerminal(r.getNombreTerminal()); entityManager.persist(e); return e;
    }
    @Override public PersonaAutorizacion registrarAutorizacion(Integer id, AutorizacionRequest r) {
        PersonaAutorizacion a=new PersonaAutorizacion(); a.setCodigoReq(id); a.setCodigoPer(r.getCodigoPer()); a.setCodigoTipEva(r.getCodigoTipEva()); a.setCodigoPerSolicitante(r.getCodigoPerSolicitante()); a.setCodigoEst(r.getCodigoEst()); a.setFechaSolicitud(LocalDateTime.now()); a.setMigracion(false); a.setActivo(true); a.setCodPerActualizacion(r.getCodPerActualizacion()); a.setFechaActualizacion(LocalDateTime.now()); a.setNombreTerminal(r.getNombreTerminal()); entityManager.persist(a); return a;
    }
    @Override public List<PersonaAutorizacion> obtenerAutorizacionesPendientes(Integer id) { return entityManager.createQuery("select a from PersonaAutorizacion a where a.codigoReq=:id and a.activo=true and a.fechaAutorizacion is null and a.fechaAnulacion is null order by a.fechaSolicitud",PersonaAutorizacion.class).setParameter("id",id).getResultList(); }
    @Override public PersonaAutorizacion reasignarAutorizacion(Integer id, Integer autorizacionId, ReasignacionAutorizacionRequest r) { PersonaAutorizacion a=entityManager.find(PersonaAutorizacion.class, autorizacionId); if(a==null || !id.equals(a.getCodigoReq())) throw new BusinessException("No existe la autorización para el requerimiento indicado"); a.setCodigoPer(r.getNuevoCodigoPer()); a.setCodPerActualizacion(r.getCodPerActualizacion()); a.setNombreTerminal(r.getNombreTerminal()); a.setFechaActualizacion(LocalDateTime.now()); return a; }
    @Override public RequerimientoMovimiento registrarAtencion(Integer id, AtencionRequest r) { RequerimientoMovimiento m=new RequerimientoMovimiento(); m.setCodigoReq(id); m.setFechaReqMov(LocalDateTime.now()); m.setCodigoEst(codigoEstado("En Proceso")); m.setCodigoPerOrigen(r.getCodigoPerOrigen()); m.setCodigoPerDestino(r.getCodigoPerDestino()); m.setUoOrigen(r.getUoOrigen()); m.setUoDestino(r.getUoDestino()); m.setResponsablePrincipal(r.getResponsablePrincipal()); m.setVigencia(true); m.setInformeTecnico(r.getInformeTecnico()); m.setObservacion(r.getObservacion()==null?"":r.getObservacion()); m.setMigracion(false); m.setActivo(true); m.setCodPerActualizacion(r.getCodPerActualizacion()); m.setFechaActualizacion(LocalDateTime.now()); m.setTerminal(r.getNombreTerminal()); entityManager.persist(m); return m; }
    @Override public RequerimientoMovimiento actualizarAtencion(Integer id,Integer atencionId,AtencionRequest r){ RequerimientoMovimiento m=entityManager.find(RequerimientoMovimiento.class,atencionId); if(m==null||!id.equals(m.getCodigoReq()))throw new BusinessException("No existe la atención indicada"); m.setCodigoPerDestino(r.getCodigoPerDestino()); m.setUoDestino(r.getUoDestino()); m.setResponsablePrincipal(r.getResponsablePrincipal()); m.setInformeTecnico(r.getInformeTecnico()); m.setObservacion(r.getObservacion()); m.setCodPerActualizacion(r.getCodPerActualizacion()); m.setFechaActualizacion(LocalDateTime.now()); m.setTerminal(r.getNombreTerminal()); return m; }
    @Override public RequerimientoProblemaSolucion registrarSolucion(Integer id,SolucionRequest r){ RequerimientoProblemaSolucion x=new RequerimientoProblemaSolucion(); x.setCodigoReq(id); x.setCodigoPro(r.getCodigoProblema()); x.setCodigoSol(r.getCodigoSolucion()); x.setActivo(true); x.setCodPerActualizacion(r.getCodPerActualizacion()); x.setFechaActualizacion(LocalDateTime.now()); x.setNombreTerminal(r.getNombreTerminal()); entityManager.persist(x); return x; }
    @Override public List<RequerimientoProblemaSolucion> obtenerSoluciones(Integer id){return entityManager.createQuery("select x from RequerimientoProblemaSolucion x where x.codigoReq=:id and x.activo=true order by x.codigoReqProSol",RequerimientoProblemaSolucion.class).setParameter("id",id).getResultList();}
    @Override public DocumentoAdjunto registrarDocumento(Integer id,DocumentoAdjuntoRequest r){DocumentoAdjunto d=new DocumentoAdjunto(); d.setCodigoReq(id); d.setCodigoPerCar(r.getCodigoPerCar()); d.setFechaCarga(LocalDateTime.now()); d.setCodigoTipAdj(r.getCodigoTipAdj()); d.setNombre(r.getNombre()); d.setNombreOriginal(r.getNombreOriginal()); d.setDescripcion(r.getDescripcion()); d.setEjecucion(r.getEjecucion()); d.setCodigoPerEjec(r.getCodigoPerEjec()); d.setActivo(true); d.setCodPerActualizacion(r.getCodPerActualizacion()); d.setFechaActualizacion(LocalDateTime.now()); d.setNombreTerminal(r.getNombreTerminal()); entityManager.persist(d); return d;}
    @Override public List<HistorialItem> historial(Integer id){String sql="select iCodigo_ReqMov, sdFecha_ReqMov, siCodigo_Est, iCodigo_PerOri_ReqMov, iCodigo_PerDes_ReqMov, iCodUoOri_ReqMov, iCodUoDes_ReqMov, bResPrincipal_ReqMov, vObservacion_ReqMov from dbo.GRMovRequerimiento where iCodigo_Req=:id and bActivo_ReqMov=1 order by sdFecha_ReqMov"; List<?> rows=entityManager.createNativeQuery(sql).setParameter("id",id).getResultList(); return rows.stream().map(o->{Object[]r=(Object[])o;return new HistorialItem(numInt(r[0]), toDate(r[1]), numShort(r[2]),numInt(r[3]),numInt(r[4]),numInt(r[5]),numInt(r[6]),(Boolean)r[7],(String)r[8]);}).toList();}
    @Override public RequerimientoDetalle cambiarEstado(Integer id,String estado,String observacion,String usuario,String terminal){Short codigo=codigoEstado(estado); Requerimiento r=entityManager.find(Requerimiento.class,id); if(r==null)throw new BusinessException("No existe el requerimiento "+id); r.setCodigoEst(codigo); r.setObservacionReq(observacion); r.setCodPerActualizacion(usuario); r.setFechaActualizacionReq(LocalDateTime.now()); r.setNombreTerminalReq(terminal); if("Atendido".equalsIgnoreCase(estado)){r.setFechaCierreReq(LocalDateTime.now());} entityManager.flush(); return obtenerDetalle(id);}
    @Override public List<RequerimientoDetalle> listarPorResponsable(Integer id){String sql=BASE_DETALLE+" where r.bActivo_Req=1 and exists (select 1 from dbo.GRMovRequerimiento m where m.iCodigo_Req=r.iCodigo_Req and m.iCodigo_PerDes_ReqMov=:id and m.bActivo_ReqMov=1 and m.bVigencia_ReqMov=1) order by r.sdFecha_Req"; return detalles(sql,id);}
    @Override public List<RequerimientoDetalle> listarPorEstado(String estado){String sql=BASE_DETALLE+" where r.bActivo_Req=1 and upper(e.vNombre_Est)=upper(:estado) order by r.sdFecha_Req"; return detalles(sql,estado);}

    private Short codigoEstado(String nombre){Query q=entityManager.createNativeQuery("select top 1 siCodigo_Est from dbo.GRTabEstado where upper(vNombre_Est)=upper(:nombre) and bActivo_Est=1 order by siCodigo_Est").setParameter("nombre",nombre); List<?>l=q.getResultList();if(l.isEmpty())throw new BusinessException("No se encontró el estado '"+nombre+"' en GRTabEstado");return ((Number)l.get(0)).shortValue();}
    private List<RequerimientoDetalle> detalles(String sql,Object...params){Query q=entityManager.createNativeQuery(sql); if(params.length==2){q.setParameter("id",params[0]);} else if(params.length==1){String name=sql.contains(":estado")?"estado":"id";q.setParameter(name,params[0]);} return q.getResultList().stream().map(o->{Object[]r=(Object[])o;return new RequerimientoDetalle(numInt(r[0]),toDate(r[1]),(String)r[2],(String)r[3],numInt(r[4]),numInt(r[5]),numShort(r[6]),(String)r[7],numInt(r[8]),numInt(r[9]));}).toList();}
    private static Integer numInt(Object o){return o==null?null:((Number)o).intValue();}
    private static Short numShort(Object o){return o==null?null:((Number)o).shortValue();}
    private static LocalDateTime toDate(Object o){return o==null?null:((java.sql.Timestamp)o).toLocalDateTime();}
}
