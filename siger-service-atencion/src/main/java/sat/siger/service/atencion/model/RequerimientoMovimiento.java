package sat.siger.service.atencion.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name="GRMovRequerimiento", schema="dbo")
@Data
public class RequerimientoMovimiento {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="iCodigo_ReqMov") private Integer codigoReqMov;
    @Column(name="iCodigo_Req") private Integer codigoReq;
    @Column(name="sdFecha_ReqMov") private LocalDateTime fechaReqMov;
    @Column(name="siCodigo_Est") private Short codigoEst;
    @Column(name="iCodigo_PerOri_ReqMov") private Integer codigoPerOrigen;
    @Column(name="iCodUoOri_ReqMov") private Integer uoOrigen;
    @Column(name="iCodigo_PerDes_ReqMov") private Integer codigoPerDestino;
    @Column(name="iCodUoDes_ReqMov") private Integer uoDestino;
    @Column(name="bResPrincipal_ReqMov") private Boolean responsablePrincipal;
    @Column(name="bVigencia_ReqMov") private Boolean vigencia;
    @Column(name="bInformeTecnico_ReqMov") private Boolean informeTecnico;
    @Column(name="vObservacion_ReqMov") private String observacion;
    @Column(name="bMigracion_ReqMov") private Boolean migracion;
    @Column(name="bActivo_ReqMov") private Boolean activo;
    @Column(name="cCodPerActualizacion") private String codPerActualizacion;
    @Column(name="sdFechaActualizacion_ReqMov") private LocalDateTime fechaActualizacion;
    @Column(name="cNombreTerminal_ReqMov") private String terminal;
}
