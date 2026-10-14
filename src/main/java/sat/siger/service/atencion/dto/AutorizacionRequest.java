package sat.siger.service.atencion.dto;

import lombok.Data;

@Data
public class AutorizacionRequest {
    private Integer codigoPer;
    private Integer codigoTipEva;
    private Integer codigoPerSolicitante;
    private Short codigoEst;
    private String codPerActualizacion;
    private String nombreTerminal;
}
