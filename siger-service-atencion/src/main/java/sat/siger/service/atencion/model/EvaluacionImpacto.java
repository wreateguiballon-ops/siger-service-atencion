package sat.siger.service.atencion.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="GRMovEvaluacionDatos", schema="dbo")
@Data
public class EvaluacionImpacto {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="iCodigo_EvalDat") private Integer codigoEvalDat;
    @Column(name="iCodigo_Req") private Integer codigoReq;
    @Column(name="sdFecha_EvalDat") private LocalDateTime fechaEvalDat;
    @Column(name="iCodigo_PriEvalDat") private Integer codigoPriEvalDat;
    @Column(name="nMontoDeuda_EvalDat") private BigDecimal montoDeuda;
    @Column(name="nMontoPagos_EvalDat") private BigDecimal montoPagos;
    @Column(name="iNumeroRegistros_EvalDat") private Integer numeroRegistros;
    @Column(name="iNumeroPersonas_EvalDat") private Integer numeroPersonas;
    @Column(name="bActivo_EvalDat") private Boolean activo;
    @Column(name="cCodPerActualizacion") private String codPerActualizacion;
    @Column(name="sdFechaActualizacion_EvalDat") private LocalDateTime fechaActualizacion;
    @Column(name="cNombreTerminal_EvalDat") private String nombreTerminal;
}
