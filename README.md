# Certificados Estudiantiles - Proyecto Monolitico (Panel + Java)

Panel de administracion academica (basado en el frontend que
proporcionaste) integrado con el backend Spring Boot para la
creacion y administracion de certificados estudiantiles.

## 1. Que cambio en esta version

Tu frontend (`index.html` + `styles.css` + `app.js`) es HTML/CSS/JS
plano, no Angular. Por eso la integracion tomo este camino:

- El panel ya esta copiado dentro de
  `backend/src/main/resources/static/` — Spring Boot lo sirve
  directamente, **sin ningun paso de build**. Al levantar el
  backend, el panel ya esta disponible en `http://localhost:8080`.
- Se agrego un modulo nuevo, **Estudiantes**, que no existia en tu
  panel (necesario porque un certificado siempre pertenece a un
  estudiante).
- El modulo **Certificaciones** dejo de mostrar datos de ejemplo:
  ahora lista, crea, emite y anula certificados reales contra la
  API (`/api/certificados`), y las 4 tarjetas de estadisticas
  (Total / Pendientes / Emitidas / Anuladas) se calculan con datos
  reales.
- Los modulos **Carga de informacion** y **Contenido de cursos**
  siguen siendo visuales (datos de ejemplo), porque el backend
  actual no tiene entidades para documentos academicos ni cursos.
  Si los necesitas conectados, puedo agregar esos modelos y
  endpoints.
- Las 4 tarjetas del Dashboard con su "Historial" (documentos,
  cursos, certificaciones, pendientes) tambien siguen mostrando
  datos de ejemplo por el mismo motivo.
- El frontend Angular que armamos antes quedo en
  `frontend-angular-no-usado/`, por si lo quieres retomar mas
  adelante. No esta conectado a nada en esta version.

## 2. Arquitectura

```
certificados-app/
├── backend/                          # Spring Boot (Java 17 + Gradle)
│   └── src/main/java/com/certificados/app/
│       ├── controller/                # Capa REST (entrada HTTP)
│       ├── service/                   # Logica de negocio
│       ├── repository/                # Acceso a datos (Spring Data JPA)
│       ├── model/                     # Entidades JPA (Estudiante, Certificado)
│       ├── dto/                       # Objetos de transferencia
│       ├── exception/                 # Manejo centralizado de errores
│       └── config/
│   └── src/main/resources/
│       ├── application.properties     # Config de MySQL y app
│       └── static/                    # <- tu panel (index.html, styles.css, app.js)
│
├── frontend-panel/                   # Copia editable de tu panel (fuente)
│   ├── index.html
│   ├── styles.css
│   └── app.js
│
├── frontend-angular-no-usado/        # Frontend Angular anterior, sin conectar
│
├── build-monolito.sh                 # Empaqueta el JAR final
└── README.md
```

`frontend-panel/` es la fuente editable: si modificas algo ahi,
vuelve a copiarlo a `backend/src/main/resources/static/` antes de
levantar el backend (o corre `build-monolito.sh`, que ya usa lo que
esta en `static/`).

## 3. Entorno necesario para trabajar

| Herramienta | Version recomendada | Para que sirve |
|---|---|---|
| JDK | 17 o superior | Compilar y correr el backend |
| Gradle | 8.8 (o uno instalado localmente) | Build del backend |
| MySQL | 8.x | Base de datos |
| IDE sugerido | IntelliJ IDEA | - |

Ya no necesitas Node.js/Angular CLI para correr este proyecto: el
panel es HTML/CSS/JS puro y Spring Boot lo sirve tal cual.

## 4. Preparar la base de datos

```sql
CREATE DATABASE certificados_db;
```

Las tablas se crean solas al levantar el backend
(`spring.jpa.hibernate.ddl-auto=update`). Ajusta usuario/clave en
`backend/src/main/resources/application.properties` si no usas
`root/root`.

## 5. Ejecutar el proyecto

```bash
cd backend
gradle bootRun
# o, si generaste el wrapper: ./gradlew bootRun
```

Abre `http://localhost:8080`. Ahi mismo esta el panel completo,
consumiendo la API en el mismo origen (no hace falta configurar
CORS para uso normal).

> Nota sobre Gradle: el wrapper (`gradlew`) esta en la estructura
> del proyecto pero sin el binario `gradle-wrapper.jar` (no se pudo
> descargar sin conexion a internet en este entorno). La primera
> vez, con Gradle instalado localmente, ejecuta dentro de
> `backend/`:
> ```bash
> gradle wrapper --gradle-version 8.8
> ```
> Desde ahi `./gradlew` funciona normal.

## 6. Empaquetar el JAR final (produccion)

```bash
./build-monolito.sh
```

Genera `backend/build/libs/certificados-app-0.0.1-SNAPSHOT.jar`,
que ya incluye el panel dentro. Ejecutalo con:

```bash
java -jar backend/build/libs/certificados-app-0.0.1-SNAPSHOT.jar
```

## 7. Modulos del panel

| Modulo | Estado | Conectado a |
|---|---|---|
| Dashboard | Parcial | Las 4 tarjetas de "Historial" siguen con datos de ejemplo |
| Carga de informacion | Visual/mock | Sin backend (no hay entidad Documento) |
| Contenido de cursos | Visual/mock | Sin backend (no hay entidad Curso) |
| Certificaciones | **Real** | `/api/certificados` |
| Estudiantes (nuevo) | **Real** | `/api/estudiantes` |

## 8. Endpoints principales

| Metodo | Ruta | Descripcion |
|---|---|---|
| GET | /api/estudiantes | Listar estudiantes |
| POST | /api/estudiantes | Crear estudiante |
| DELETE | /api/estudiantes/{id} | Eliminar estudiante |
| GET | /api/certificados | Listar certificados |
| POST | /api/certificados | Solicitar certificado |
| PATCH | /api/certificados/{id}/emitir | Marcar como emitido |
| PATCH | /api/certificados/{id}/anular | Anular certificado |

## 9. Proximos pasos sugeridos

- Conectar "Carga de informacion" y "Contenido de cursos" a
  entidades reales (Documento, Curso) si los necesitas funcionales.
- Reemplazar los datos de ejemplo del Dashboard con conteos reales.
- Agregar autenticacion (Spring Security + JWT).
- Generar el PDF del certificado al emitirlo.
