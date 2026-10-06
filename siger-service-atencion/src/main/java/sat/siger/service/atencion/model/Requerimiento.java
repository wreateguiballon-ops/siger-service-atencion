package sat.siger.service.atencion.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name="GRMaeRequerimiento", schema="dbo")
@Data
public class Requerimiento {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="iCodigo_Req") private Integer codigoReq;
    @Column(name="sdFecha_Req") private LocalDateTime fechaReq;
    @Column(name="vDescripcion_Req") private String descripcionReq;
    @Column(name="vSumilla_Req") private String sumillaReq;
    @Column(name="iCodigoPadre_Req") private Integer codigoPadreReq;
    @Column(name="iCodigo_Uo") private Integer codigoUo;
    @Column(name="iCodUo_Per") private Integer codUoPer;
    @Column(name="iCodigo_Per") private Integer codigoPer;
    @Column(name="siCodigo_Est") private Short codigoEst;
    @Column(name="iCodigo_CSC") private Integer codigoCsc;
    @Column(name="iCodigo_Pri") private Integer codigoPri;
    @Column(name="iCodigo_TipEva") private Integer codigoTipEva;
    @Column(name="iCodigo_TipEnv") private Integer codigoTipEnv;
    @Column(name="iCodigo_TipDocSus") private Integer codigoTipDocSus;
    @Column(name="iCodigo_PriEvalDat") private Integer codigoPriEvalDat;
    @Column(name="iCodigo_TipMod") private Integer codigoTipMod;
    @Column(name="iCodigo_TipAut") private Integer codigoTipAut;
    @Column(name="iCodigo_TipNoCon") private Integer codigoTipNoCon;
    @Column(name="cNumero_DocSus") private String numeroDocSus;
    @Column(name="sdFecha_DocSus") private LocalDateTime fechaDocSus;
    @Column(name="vDocumentoAdjunto_Req") private String documentoAdjuntoReq;
    @Column(name="vObservacion_Req") private String observacionReq;
    @Column(name="bAvance_Req") private Boolean avanceReq;
    @Column(name="bFisico_Req") private Boolean fisicoReq;
    @Column(name="iCodPer") private Integer codPer;
    @Column(name="siCodigo_DId") private Short codigoDId;
    @Column(name="cDocIde") private String docIde;
    @Column(name="cNumDoc") private String numDoc;
    @Column(name="vCodInv") private String codInv;
    @Column(name="cCodSAF") private String codSaf;
    @Column(name="bVigencia_Req") private Boolean vigenciaReq;
    @Column(name="sdFechaCierre_Req") private LocalDateTime fechaCierreReq;
    @Column(name="bMigracion_Req") private Boolean migracionReq;
    @Column(name="bActivo_Req") private Boolean activoReq;
    @Column(name="cCodPerActualizacion") private String codPerActualizacion;
    @Column(name="sdFechaActualizacion_Req") private LocalDateTime fechaActualizacionReq;
    @Column(name="cNombreTerminal_Req") private String nombreTerminalReq;
}
