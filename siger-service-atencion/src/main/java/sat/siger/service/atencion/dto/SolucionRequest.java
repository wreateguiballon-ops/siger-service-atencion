package sat.siger.service.atencion.dto;

import lombok.Data;

@Data
public class SolucionRequest {
    private Integer codigoProblema;
    private Integer codigoSolucion;
    private String codPerActualizacion;
    private String nombreTerminal;
}
