package sat.siger.service.atencion.service;

import sat.siger.service.atencion.dto.*;
import sat.siger.service.atencion.model.*;
import java.util.List;

public interface AtencionService {
    List<RequerimientoDetalle> pendientes(int page, int size);
    long contarPendientes();
    RequerimientoDetalle obtenerPorId(Integer id);
    ClasificacionResponse obtenerClasificacion(Integer id);
    List<DocumentoAdjunto> obtenerAdjuntos(Integer id);
    List<PersonaAutorizacion> obtenerAutorizaciones(Integer id);
    RequerimientoDetalle iniciarAtencion(Integer id, String usuario, String terminal);
    EvaluacionImpacto registrarEvaluacionImpacto(Integer id, EvaluacionImpactoRequest request);
    EvaluacionImpacto obtenerEvaluacionImpacto(Integer id);
    EvaluacionImpacto actualizarEvaluacionImpacto(Integer id, EvaluacionImpactoRequest request);
    PersonaAutorizacion solicitarAutorizacion(Integer id, AutorizacionRequest request);
    List<PersonaAutorizacion> autorizacionesPendientes(Integer id);
    PersonaAutorizacion solicitarReasignacion(Integer id, Integer autorizacionId, ReasignacionAutorizacionRequest request);
    RequerimientoMovimiento registrarAtencion(Integer id, AtencionRequest request);
    RequerimientoMovimiento actualizarAtencion(Integer id, Integer atencionId, AtencionRequest request);
    RequerimientoProblemaSolucion registrarSolucion(Integer id, SolucionRequest request);
    List<RequerimientoProblemaSolucion> obtenerSoluciones(Integer id);
    DocumentoAdjunto registrarDocumento(Integer id, DocumentoAdjuntoRequest request);
    List<HistorialItem> historial(Integer id);
    RequerimientoDetalle finalizarAtencion(Integer id, FinalizarAtencionRequest request);
    List<RequerimientoDetalle> listarPorResponsable(Integer id);
    List<RequerimientoDetalle> listarPorEstado(String estado);
}
