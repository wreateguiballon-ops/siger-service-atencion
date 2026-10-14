package sat.siger.service.atencion.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sat.siger.service.atencion.dto.*;
import sat.siger.service.atencion.exception.BusinessException;
import sat.siger.service.atencion.model.*;
import sat.siger.service.atencion.repository.RequerimientoRepository;

import java.util.List;

@Service
@Transactional
public class AtencionServiceImpl implements AtencionService {
    private final RequerimientoRepository repository;

    public AtencionServiceImpl(RequerimientoRepository repository) { this.repository = repository; }

    @Override @Transactional(readOnly=true) public List<RequerimientoDetalle> pendientes(int page, int size){return repository.pendientes(page, size); }
    @Override @Transactional(readOnly=true) public long contarPendientes(){return repository.contarPendientes(); }
    @Override @Transactional(readOnly=true) public RequerimientoDetalle obtenerPorId(Integer id){ return require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); }
    @Override @Transactional(readOnly=true) public ClasificacionResponse obtenerClasificacion(Integer id){ return require(repository.obtenerClasificacion(id),"No existe el requerimiento "+id); }
    @Override @Transactional(readOnly=true) public List<DocumentoAdjunto> obtenerAdjuntos(Integer id){ require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); return repository.obtenerAdjuntos(id); }
    @Override @Transactional(readOnly=true) public List<PersonaAutorizacion> obtenerAutorizaciones(Integer id){ require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); return repository.obtenerAutorizaciones(id); }
    @Override public RequerimientoDetalle iniciarAtencion(Integer id,String usuario,String terminal){ require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); return repository.cambiarEstado(id,"En Proceso","Inicio de atención",usuario,terminal); }
    @Override public EvaluacionImpacto registrarEvaluacionImpacto(Integer id,EvaluacionImpactoRequest r){ require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); return repository.registrarEvaluacionImpacto(id,r); }
    @Override @Transactional(readOnly=true) public EvaluacionImpacto obtenerEvaluacionImpacto(Integer id){ require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); return repository.obtenerEvaluacionImpacto(id); }
    @Override public EvaluacionImpacto actualizarEvaluacionImpacto(Integer id,EvaluacionImpactoRequest r){ EvaluacionImpacto actual=repository.obtenerEvaluacionImpacto(id); if(actual==null) return repository.registrarEvaluacionImpacto(id,r); actual.setCodigoPriEvalDat(r.getCodigoPriEvalDat()); actual.setMontoDeuda(r.getMontoDeuda()); actual.setMontoPagos(r.getMontoPagos()); actual.setNumeroRegistros(r.getNumeroRegistros()); actual.setNumeroPersonas(r.getNumeroPersonas()); actual.setCodPerActualizacion(r.getCodPerActualizacion()); actual.setNombreTerminal(r.getNombreTerminal()); return actual; }
    @Override public PersonaAutorizacion solicitarAutorizacion(Integer id,AutorizacionRequest r){ require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); return repository.registrarAutorizacion(id,r); }
    @Override @Transactional(readOnly=true) public List<PersonaAutorizacion> autorizacionesPendientes(Integer id){ require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); return repository.obtenerAutorizacionesPendientes(id); }
    @Override public PersonaAutorizacion solicitarReasignacion(Integer id,Integer autorizacionId,ReasignacionAutorizacionRequest r){ return repository.reasignarAutorizacion(id,autorizacionId,r); }
    @Override public RequerimientoMovimiento registrarAtencion(Integer id,AtencionRequest r){ require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); return repository.registrarAtencion(id,r); }
    @Override public RequerimientoMovimiento actualizarAtencion(Integer id,Integer atencionId,AtencionRequest r){ return repository.actualizarAtencion(id,atencionId,r); }
    @Override public RequerimientoProblemaSolucion registrarSolucion(Integer id,SolucionRequest r){ require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); return repository.registrarSolucion(id,r); }
    @Override @Transactional(readOnly=true) public List<RequerimientoProblemaSolucion> obtenerSoluciones(Integer id){ require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); return repository.obtenerSoluciones(id); }
    @Override public DocumentoAdjunto registrarDocumento(Integer id,DocumentoAdjuntoRequest r){ require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); return repository.registrarDocumento(id,r); }
    @Override @Transactional(readOnly=true) public List<HistorialItem> historial(Integer id){ require(repository.obtenerDetalle(id),"No existe el requerimiento "+id); return repository.historial(id); }
    @Override public RequerimientoDetalle finalizarAtencion(Integer id,FinalizarAtencionRequest r){ return repository.cambiarEstado(id,"Atendido",r.getObservacion(),r.getCodPerActualizacion(),r.getNombreTerminal()); }
    @Override @Transactional(readOnly=true) public List<RequerimientoDetalle> listarPorResponsable(Integer id){ return repository.listarPorResponsable(id); }
    @Override @Transactional(readOnly=true) public List<RequerimientoDetalle> listarPorEstado(String estado){ return repository.listarPorEstado(estado); }
    private <T> T require(T obj,String msg){if(obj==null)throw new BusinessException(msg);return obj;}
}
