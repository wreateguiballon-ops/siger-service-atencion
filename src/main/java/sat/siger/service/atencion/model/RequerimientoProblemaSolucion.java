package sat.siger.service.atencion.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name="GRMovRequerimientoProblemasSolucion", schema="dbo")
@Data
public class RequerimientoProblemaSolucion {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="iCodigo_ReqProSol") private Integer codigoReqProSol;
    @Column(name="iCodigo_Req") private Integer codigoReq;
    @Column(name="iCodigo_Pro") private Integer codigoPro;
    @Column(name="iCodigo_Sol") private Integer codigoSol;
    @Column(name="bActivo_ReqProsol") private Boolean activo;
    @Column(name="cCodPerActualizacion") private String codPerActualizacion;
    @Column(name="sdFechaActualizacion_ReqProSol") private LocalDateTime fechaActualizacion;
    @Column(name="cNombreTerminal_ReqProSol") private String nombreTerminal;
}
