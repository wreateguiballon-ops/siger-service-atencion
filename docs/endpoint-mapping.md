# Mapeo de endpoints y BD

| # | Endpoint | Tabla principal | SP indicado en la propuesta |
|---|---|---|---|
| 1 | GET /api/atenciones/pendientes | GRMaeRequerimiento / GRMovRequerimiento | spGR_Requerimiento_Consultar @siTipBus=7 |
| 2 | GET /api/requerimientos/{id} | GRMaeRequerimiento | spGR_Requerimiento_Consultar @siTipBus=1 |
| 3 | GET /api/requerimientos/{id}/clasificacion | GRMaeRequerimiento | spGR_Requerimiento_Consultar @siTipBus=2 |
| 4 | GET /api/requerimientos/{id}/adjuntos | GRMovDocumentoAdjunto | spGR_Requerimiento_ConsultarDocumentoAdjunto |
| 5 | GET /api/requerimientos/{id}/autorizaciones | GRMovPersonaAutorizacion | spGR_Requerimiento_ConsultarPersonaAutorizacion |
| 6 | POST /api/requerimientos/{id}/iniciar-atencion | GRMaeRequerimiento / GRMovRequerimiento | — |
| 7 | POST /api/requerimientos/{id}/evaluacion-impacto | GRMovEvaluacionDatos | spGR_Requerimiento_EvaluarAutorizacion @siTipBus=1 |
| 8 | GET /api/requerimientos/{id}/evaluacion-impacto | GRMovEvaluacionDatos | spGR_Requerimiento_EvaluarAutorizacion @siTipBus=2 |
| 9 | PUT /api/requerimientos/{id}/evaluacion-impacto | GRMovEvaluacionDatos | spGR_Requerimiento_EvaluarAutorizacion @siTipBus=1 |
| 10 | POST /api/requerimientos/{id}/autorizaciones | GRMovPersonaAutorizacion | spGR_Requerimiento_RegistrarPersonaAutorizacion @siTipBus=1 |
| 11 | GET /api/requerimientos/{id}/autorizaciones/pendientes | GRMovPersonaAutorizacion | spGR_Requerimiento_ConsultarPersonaAutorizacion |
| 12 | POST /api/requerimientos/{id}/autorizaciones/{autorizacionId}/solicitar-reasignacion | GRMovPersonaAutorizacion | No indicado en la propuesta |
| 13 | POST /api/requerimientos/{id}/atenciones | GRMovRequerimiento | No indicado en la propuesta |
| 14 | PUT /api/requerimientos/{id}/atenciones/{atencionId} | GRMovRequerimiento | No indicado en la propuesta |
| 15 | POST /api/requerimientos/{id}/soluciones | GRMovRequerimientoProblemasSolucion | spGR_Requerimiento_RegistrarProblemaSolucion |
| 16 | GET /api/requerimientos/{id}/soluciones | GRMovRequerimientoProblemasSolucion | spGR_Requerimiento_ConsultarProblemaSolucion |
| 17 | POST /api/requerimientos/{id}/adjuntos-atencion | GRMovDocumentoAdjunto | spGR_Requerimiento_RegistrarDocumentoAdjunto |
| 18 | GET /api/requerimientos/{id}/historial-atencion | GRMovRequerimiento | spGR_Requerimiento_ConsultarHistorial |
| 19 | POST /api/requerimientos/{id}/finalizar-atencion | GRMaeRequerimiento / GRMovRequerimiento | spGR_Requerimiento_RegistrarEtapaAvance |
| 20 | GET /api/atenciones?responsableId={id} | GRMaeRequerimiento / GRMovRequerimiento | — |
| 21 | GET /api/atenciones?estado=EN_PROCESO | GRMaeRequerimiento / GRTabEstado | — |

Nota: el script SQL entregado para este trabajo define las tablas, índices y claves foráneas, pero no incluye los `CREATE PROCEDURE` de los SP anteriores. No se inventaron parámetros de entrada para ellos.
