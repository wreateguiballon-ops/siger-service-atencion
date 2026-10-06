package sat.siger.service.atencion.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name="GRMovDocumentoAdjunto", schema="dbo")
@Data
public class DocumentoAdjunto {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="iCodigo_DocAdj") private Integer codigoDocAdj;
    @Column(name="iCodigo_Req") private Integer codigoReq;
    @Column(name="iCodigo_PerCar_DocAdj") private Integer codigoPerCar;
    @Column(name="sdFechaCarga_DocAdj") private LocalDateTime fechaCarga;
    @Column(name="iCodigo_TipAdj") private Integer codigoTipAdj;
    @Column(name="vNombre_DocAdj") private String nombre;
    @Column(name="vNombreOriginal_DocAdj") private String nombreOriginal;
    @Column(name="vDescripcion_DocAdj") private String descripcion;
    @Column(name="siEjecucion_DocAdj") private Short ejecucion;
    @Column(name="iCodigo_PerEjec_DocAdj") private Integer codigoPerEjec;
    @Column(name="sdFechaEjecucion_DocAdj") private LocalDateTime fechaEjecucion;
    @Column(name="iCodigo_PerAnul_DocAdj") private Integer codigoPerAnul;
    @Column(name="sdFechaAnulacion_DocAdj") private LocalDateTime fechaAnulacion;
    @Column(name="bActivo_DocAdj") private Boolean activo;
    @Column(name="cCodPerActualizacion") private String codPerActualizacion;
    @Column(name="sdFechaActualizacion_DocAdj") private LocalDateTime fechaActualizacion;
    @Column(name="cNombreTerminal_DocAdj") private String nombreTerminal;
}
