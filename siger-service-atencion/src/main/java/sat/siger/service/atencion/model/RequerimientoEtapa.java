package sat.siger.service.atencion.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name="GRMovRequerimientoEtapa", schema="dbo")
@Data
public class RequerimientoEtapa {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="iCodigo_ReqEta") private Integer codigoReqEta;
    @Column(name="siCorrelativo_ReqEta") private Short correlativo;
    @Column(name="iCodigo_ReqMov") private Integer codigoReqMov;
    @Column(name="siCodigo_Eta") private Short codigoEta;
    @Column(name="sdFechaInicio_ReqEta") private LocalDateTime fechaInicio;
    @Column(name="siPorcentajeAvance_ReqEta") private Short porcentajeAvance;
    @Column(name="sdFechaTermino_ReqEta") private LocalDateTime fechaTermino;
    @Column(name="vObservacion_ReqEta") private String observacion;
    @Column(name="bActivo_ReqEta") private Boolean activo;
    @Column(name="cCodPerActualizacion") private String codPerActualizacion;
    @Column(name="sdFechaActualizacion_ReqEta") private LocalDateTime fechaActualizacion;
    @Column(name="cNombreTerminal_ReqEta") private String terminal;
}
