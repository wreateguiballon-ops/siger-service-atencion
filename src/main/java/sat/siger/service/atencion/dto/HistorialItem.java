package sat.siger.service.atencion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HistorialItem {
    private Integer codigoReqMov;
    private LocalDateTime fecha;
    private Short codigoEstado;
    private Integer personaOrigen;
    private Integer personaDestino;
    private Integer unidadOrigen;
    private Integer unidadDestino;
    private Boolean responsablePrincipal;
    private String observacion;
}
