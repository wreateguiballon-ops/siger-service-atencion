package sat.siger.service.atencion.dto;

import lombok.Data;

@Data
public class DocumentoAdjuntoRequest {
    private Integer codigoPerCar;
    private Integer codigoTipAdj;
    private String nombre;
    private String nombreOriginal;
    private String descripcion;
    private Short ejecucion;
    private Integer codigoPerEjec;
    private String codPerActualizacion;
    private String nombreTerminal;
}
