package sat.siger.service.atencion.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name="GRMovPersonaAutorizacion", schema="dbo")
@Data
public class PersonaAutorizacion {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="iCodigo_PerAut") private Integer codigoPerAut;
    @Column(name="iCodigo_Req") private Integer codigoReq;
    @Column(name="siCodigo_Est") private Short codigoEst;
    @Column(name="iCodigo_Per") private Integer codigoPer;
    @Column(name="sdFechaSolicitud_PerAut") private LocalDateTime fechaSolicitud;
    @Column(name="sdFechaAutorizacion_PerAut") private LocalDateTime fechaAutorizacion;
    @Column(name="sdFechaAnulacion_PerAut") private LocalDateTime fechaAnulacion;
    @Column(name="iCodigo_TipEva") private Integer codigoTipEva;
    @Column(name="iCodigo_PerSol_PerAut") private Integer codigoPerSolicitante;
    @Column(name="iCodigo_PerAnul_PerAut") private Integer codigoPerAnulacion;
    @Column(name="bMigracion_PerAut") private Boolean migracion;
    @Column(name="bActivo_PerAut") private Boolean activo;
    @Column(name="cCodPerActualizacion") private String codPerActualizacion;
    @Column(name="sdFechaActualizacion_PerAut") private LocalDateTime fechaActualizacion;
    @Column(name="cNombreTerminal_PerAut") private String nombreTerminal;
}
