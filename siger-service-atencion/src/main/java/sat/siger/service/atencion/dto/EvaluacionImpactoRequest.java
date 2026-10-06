package sat.siger.service.atencion.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class EvaluacionImpactoRequest {
    private Integer codigoPriEvalDat;
    private BigDecimal montoDeuda;
    private BigDecimal montoPagos;
    private Integer numeroRegistros;
    private Integer numeroPersonas;
    private String codPerActualizacion;
    private String nombreTerminal;
}
