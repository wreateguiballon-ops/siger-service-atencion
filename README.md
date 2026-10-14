# SIGER - service-atencion

Microservicio del Grupo 3 - Atención de Requerimientos.

## Configuración Spring Initializr
- Group: `sat.siger`
- Artifact: `service-atencion`
- Package: `sat.siger.service.atencion`
- Java: 17
- Packaging: Jar

## Arquitectura
`Controller -> Service -> ServiceImpl -> Repository -> GESTIONRQ`

La estructura sigue el patrón del proyecto CoreSales proporcionado por el profesor: Controller, Service/ServiceImpl, Repository/RepositoryCustom/RepositoryCustomImpl, Model, DTO y Exception.

## Endpoints
1. GET `/api/atenciones/pendientes`
2. GET `/api/requerimientos/{id}`
3. GET `/api/requerimientos/{id}/clasificacion`
4. GET `/api/requerimientos/{id}/adjuntos`
5. GET `/api/requerimientos/{id}/autorizaciones`
6. POST `/api/requerimientos/{id}/iniciar-atencion`
7. POST `/api/requerimientos/{id}/evaluacion-impacto`
8. GET `/api/requerimientos/{id}/evaluacion-impacto`
9. PUT `/api/requerimientos/{id}/evaluacion-impacto`
10. POST `/api/requerimientos/{id}/autorizaciones`
11. GET `/api/requerimientos/{id}/autorizaciones/pendientes`
12. POST `/api/requerimientos/{id}/autorizaciones/{autorizacionId}/solicitar-reasignacion`
13. POST `/api/requerimientos/{id}/atenciones`
14. PUT `/api/requerimientos/{id}/atenciones/{atencionId}`
15. POST `/api/requerimientos/{id}/soluciones`
16. GET `/api/requerimientos/{id}/soluciones`
17. POST `/api/requerimientos/{id}/adjuntos-atencion`
18. GET `/api/requerimientos/{id}/historial-atencion`
19. POST `/api/requerimientos/{id}/finalizar-atencion`
20. GET `/api/atenciones?responsableId={id}`
21. GET `/api/atenciones?estado=EN_PROCESO`

## Base de datos
La aplicación apunta a SQL Server `GESTIONRQ`. Ajustar `application.properties` al servidor, instancia, usuario y contraseña local.

## Importante sobre los Stored Procedures
El archivo `script BD completo.sql` proporcionado contiene el esquema de tablas y relaciones, pero no contiene los cuerpos/firmas de los procedimientos almacenados indicados en la propuesta de endpoints. Por esa razón, esta versión implementa la capa RepositoryCustom con JPA/SQL sobre las tablas reales de `GESTIONRQ`, evitando inventar parámetros de SP.

Los SP identificados en la propuesta quedan documentados en `docs/endpoint-mapping.md`. Cuando se disponga de sus firmas reales, cada método del `RepositoryCustomImpl` puede migrarse a `StoredProcedureQuery` siguiendo el patrón del profesor.
