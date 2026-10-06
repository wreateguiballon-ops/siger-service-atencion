package sat.siger.service.atencion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClasificacionResponse {
    private Integer codigoReq;
    private Integer codigoCsc;
    private Integer codigoPrioridad;
    private Integer codigoTipoEvaluacion;
    private Integer codigoTipoModificacion;
    private Integer codigoTipoAutorizacion;
}
