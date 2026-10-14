package sat.siger.service.atencion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sat.siger.service.atencion.dto.*;
import sat.siger.service.atencion.model.*;
import sat.siger.service.atencion.service.AtencionService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class AtencionController {
    private final AtencionService atencionService;

    public AtencionController(AtencionService atencionService) { this.atencionService = atencionService; }

    // 1 GET /api/atenciones/pendientes
    @GetMapping("/atenciones/pendientes")
    public ResponseEntity<?> pendientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        if (page < 0 || size < 1) {
            throw new IllegalArgumentException(
                    "La página debe ser mayor o igual a 0 "
                            + "y el tamaño debe ser mayor que 0."
            );
        }

        size = Math.min(size, 100);

        List<RequerimientoDetalle> registros =
                atencionService.pendientes(page, size);

        long total = atencionService.contarPendientes();

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("content", registros);
        respuesta.put("page", page);
        respuesta.put("size", size);
        respuesta.put("totalElements", total);
        respuesta.put("totalPages", (total + size - 1) / size);

        return ResponseEntity.ok(respuesta);
    }

    // 2 GET /api/requerimientos/{id}
    @GetMapping("/requerimientos/{id}")
    public ResponseEntity<RequerimientoDetalle> obtenerPorId(@PathVariable Integer id) { return ResponseEntity.ok(atencionService.obtenerPorId(id)); }

    // 3 GET /api/requerimientos/{id}/clasificacion
    @GetMapping("/requerimientos/{id}/clasificacion")
    public ResponseEntity<ClasificacionResponse> clasificacion(@PathVariable Integer id) { return ResponseEntity.ok(atencionService.obtenerClasificacion(id)); }

    // 4 GET /api/requerimientos/{id}/adjuntos
    @GetMapping("/requerimientos/{id}/adjuntos")
    public ResponseEntity<List<DocumentoAdjunto>> adjuntos(@PathVariable Integer id) { return ResponseEntity.ok(atencionService.obtenerAdjuntos(id)); }

    // 5 GET /api/requerimientos/{id}/autorizaciones
    @GetMapping("/requerimientos/{id}/autorizaciones")
    public ResponseEntity<List<PersonaAutorizacion>> autorizaciones(@PathVariable Integer id) { return ResponseEntity.ok(atencionService.obtenerAutorizaciones(id)); }

    // 6 POST /api/requerimientos/{id}/iniciar-atencion
    @PostMapping("/requerimientos/{id}/iniciar-atencion")
    public ResponseEntity<RequerimientoDetalle> iniciarAtencion(@PathVariable Integer id,
            @RequestParam String usuario, @RequestParam(required=false, defaultValue="WEB") String terminal) {
        return ResponseEntity.ok(atencionService.iniciarAtencion(id,usuario,terminal));
    }

    // 7 POST /api/requerimientos/{id}/evaluacion-impacto
    @PostMapping("/requerimientos/{id}/evaluacion-impacto")
    public ResponseEntity<EvaluacionImpacto> registrarEvaluacion(@PathVariable Integer id,@RequestBody EvaluacionImpactoRequest request){ return ResponseEntity.status(HttpStatus.CREATED).body(atencionService.registrarEvaluacionImpacto(id,request)); }

    // 8 GET /api/requerimientos/{id}/evaluacion-impacto
    @GetMapping("/requerimientos/{id}/evaluacion-impacto")
    public ResponseEntity<EvaluacionImpacto> obtenerEvaluacion(@PathVariable Integer id){ return ResponseEntity.ok(atencionService.obtenerEvaluacionImpacto(id)); }

    // 9 PUT /api/requerimientos/{id}/evaluacion-impacto
    @PutMapping("/requerimientos/{id}/evaluacion-impacto")
    public ResponseEntity<EvaluacionImpacto> actualizarEvaluacion(@PathVariable Integer id,@RequestBody EvaluacionImpactoRequest request){ return ResponseEntity.ok(atencionService.actualizarEvaluacionImpacto(id,request)); }

    // 10 POST /api/requerimientos/{id}/autorizaciones
    @PostMapping("/requerimientos/{id}/autorizaciones")
    public ResponseEntity<PersonaAutorizacion> solicitarAutorizacion(@PathVariable Integer id,@RequestBody AutorizacionRequest request){ return ResponseEntity.status(HttpStatus.CREATED).body(atencionService.solicitarAutorizacion(id,request)); }

    // 11 GET /api/requerimientos/{id}/autorizaciones/pendientes
    @GetMapping("/requerimientos/{id}/autorizaciones/pendientes")
    public ResponseEntity<List<PersonaAutorizacion>> autorizacionesPendientes(@PathVariable Integer id){ return ResponseEntity.ok(atencionService.autorizacionesPendientes(id)); }

    // 12 POST /api/requerimientos/{id}/autorizaciones/{autorizacionId}/solicitar-reasignacion
    @PostMapping("/requerimientos/{id}/autorizaciones/{autorizacionId}/solicitar-reasignacion")
    public ResponseEntity<PersonaAutorizacion> reasignarAutorizacion(@PathVariable Integer id,@PathVariable Integer autorizacionId,@RequestBody ReasignacionAutorizacionRequest request){ return ResponseEntity.ok(atencionService.solicitarReasignacion(id,autorizacionId,request)); }

    // 13 POST /api/requerimientos/{id}/atenciones
    @PostMapping("/requerimientos/{id}/atenciones")
    public ResponseEntity<RequerimientoMovimiento> registrarAtencion(@PathVariable Integer id,@RequestBody AtencionRequest request){ return ResponseEntity.status(HttpStatus.CREATED).body(atencionService.registrarAtencion(id,request)); }

    // 14 PUT /api/requerimientos/{id}/atenciones/{atencionId}
    @PutMapping("/requerimientos/{id}/atenciones/{atencionId}")
    public ResponseEntity<RequerimientoMovimiento> actualizarAtencion(@PathVariable Integer id,@PathVariable Integer atencionId,@RequestBody AtencionRequest request){ return ResponseEntity.ok(atencionService.actualizarAtencion(id,atencionId,request)); }

    // 15 POST /api/requerimientos/{id}/soluciones
    @PostMapping("/requerimientos/{id}/soluciones")
    public ResponseEntity<RequerimientoProblemaSolucion> registrarSolucion(@PathVariable Integer id,@RequestBody SolucionRequest request){ return ResponseEntity.status(HttpStatus.CREATED).body(atencionService.registrarSolucion(id,request)); }

    // 16 GET /api/requerimientos/{id}/soluciones
    @GetMapping("/requerimientos/{id}/soluciones")
    public ResponseEntity<List<RequerimientoProblemaSolucion>> soluciones(@PathVariable Integer id){ return ResponseEntity.ok(atencionService.obtenerSoluciones(id)); }

    // 17 POST /api/requerimientos/{id}/adjuntos-atencion
    @PostMapping("/requerimientos/{id}/adjuntos-atencion")
    public ResponseEntity<DocumentoAdjunto> registrarDocumento(@PathVariable Integer id,@RequestBody DocumentoAdjuntoRequest request){ return ResponseEntity.status(HttpStatus.CREATED).body(atencionService.registrarDocumento(id,request)); }

    // 18 GET /api/requerimientos/{id}/historial-atencion
    @GetMapping("/requerimientos/{id}/historial-atencion")
    public ResponseEntity<List<HistorialItem>> historial(@PathVariable Integer id){ return ResponseEntity.ok(atencionService.historial(id)); }

    // 19 POST /api/requerimientos/{id}/finalizar-atencion
    @PostMapping("/requerimientos/{id}/finalizar-atencion")
    public ResponseEntity<RequerimientoDetalle> finalizar(@PathVariable Integer id,@RequestBody FinalizarAtencionRequest request){ return ResponseEntity.ok(atencionService.finalizarAtencion(id,request)); }

    // 20 GET /api/atenciones?responsableId={id}
    // 21 GET /api/atenciones?estado=EN_PROCESO
    @GetMapping("/atenciones")
    public ResponseEntity<List<RequerimientoDetalle>> listar(
            @RequestParam(required = false) Integer responsableId,
            @RequestParam(required = false) String estado,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        if (responsableId != null) {
            return ResponseEntity.ok(
                    atencionService.listarPorResponsable(responsableId));
        }

        if (estado != null && !estado.isBlank()) {
            return ResponseEntity.ok(
                    atencionService.listarPorEstado(estado));
        }

        if (page < 0 || size < 1) {
            return ResponseEntity.badRequest().build();
        }

        size = Math.min(size, 100);

        return ResponseEntity.ok(
                atencionService.pendientes(page, size));
    }
}
