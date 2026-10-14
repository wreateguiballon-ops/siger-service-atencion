package sat.siger.service.atencion.repository;

import sat.siger.service.atencion.dto.*;
import sat.siger.service.atencion.model.*;
import java.util.List;

public interface AtencionRepositoryCustom {
    List<RequerimientoDetalle> pendientes(int page, int size);
    long contarPendientes();
    RequerimientoDetalle obtenerDetalle(Integer id);
    ClasificacionResponse obtenerClasificacion(Integer id);
    List<DocumentoAdjunto> obtenerAdjuntos(Integer id);
    List<PersonaAutorizacion> obtenerAutorizaciones(Integer id);
    EvaluacionImpacto obtenerEvaluacionImpacto(Integer id);
    EvaluacionImpacto registrarEvaluacionImpacto(Integer id, EvaluacionImpactoRequest request);
    PersonaAutorizacion registrarAutorizacion(Integer id, AutorizacionRequest request);
    List<PersonaAutorizacion> obtenerAutorizacionesPendientes(Integer id);
    PersonaAutorizacion reasignarAutorizacion(Integer id, Integer autorizacionId, ReasignacionAutorizacionRequest request);
    RequerimientoMovimiento registrarAtencion(Integer id, AtencionRequest request);
    RequerimientoMovimiento actualizarAtencion(Integer id, Integer atencionId, AtencionRequest request);
    RequerimientoProblemaSolucion registrarSolucion(Integer id, SolucionRequest request);
    List<RequerimientoProblemaSolucion> obtenerSoluciones(Integer id);
    DocumentoAdjunto registrarDocumento(Integer id, DocumentoAdjuntoRequest request);
    List<HistorialItem> historial(Integer id);
    RequerimientoDetalle cambiarEstado(Integer id, String estado, String observacion, String usuario, String terminal);
    List<RequerimientoDetalle> listarPorResponsable(Integer responsableId);
    List<RequerimientoDetalle> listarPorEstado(String estado);
}
