package sat.siger.service.atencion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RequerimientoDetalle {
    private Integer codigoReq;
    private LocalDateTime fechaReq;
    private String sumillaReq;
    private String descripcionReq;
    private Integer codigoUo;
    private Integer codigoPer;
    private Short codigoEstado;
    private String nombreEstado;
    private Integer codigoCsc;
    private Integer codigoPri;
}
