package sat.siger.service.atencion.dto;

import lombok.Data;

@Data
public class FinalizarAtencionRequest {
    private String observacion;
    private Integer porcentajeAvance;
    private String codPerActualizacion;
    private String nombreTerminal;
}
