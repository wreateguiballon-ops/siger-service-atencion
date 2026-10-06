package sat.siger.service.atencion.dto;

import lombok.Data;

@Data
public class ReasignacionAutorizacionRequest {
    private Integer nuevoCodigoPer;
    private String codPerActualizacion;
    private String nombreTerminal;
}
