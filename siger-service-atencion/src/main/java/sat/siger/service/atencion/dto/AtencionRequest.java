package sat.siger.service.atencion.dto;

import lombok.Data;

@Data
public class AtencionRequest {
    private Integer codigoPerOrigen;
    private Integer codigoPerDestino;
    private Integer uoOrigen;
    private Integer uoDestino;
    private Boolean responsablePrincipal;
    private Boolean informeTecnico;
    private String observacion;
    private String codPerActualizacion;
    private String nombreTerminal;
}
