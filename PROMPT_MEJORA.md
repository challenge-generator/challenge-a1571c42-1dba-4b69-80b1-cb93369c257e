# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/optimizacionia/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/AnalyticsServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/TransactionController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/TransactionController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/optimizacionia/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/optimizacionia/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/optimizacionia/Application.java` — `ApplicationProperties.getThreadPool`: Se invoca `getThreadPool` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/Application.java` — `ApplicationProperties.getLatencyThreshold`: Se invoca `getLatencyThreshold` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/dto/TransactionResponse.java` — `Transaction.type`: Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/dto/TransactionResponse.java` — `Transaction.amount`: Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCase.java` — `Transaction.type`: Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCase.java` — `Transaction.amount`: Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCase.java` — `AnalyticsServicePort.analyze`: Se invoca `analyze` sobre `AnalyticsServicePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `Transaction.type`: Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `Transaction.amount`: Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.getId`: Se invoca `getId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.setType`: Se invoca `setType` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.setAmount`: Se invoca `setAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.setCreatedAt`: Se invoca `setCreatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.setIsHighValue`: Se invoca `setIsHighValue` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.getType`: Se invoca `getType` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.getAmount`: Se invoca `getAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.getIsHighValue`: Se invoca `getIsHighValue` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/TransactionController.java` — `TransactionRequest.transactionId`: Se invoca `transactionId` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/TransactionController.java` — `TransactionRequest.amount`: Se invoca `amount` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/TransactionController.java` — `TransactionRequest.transactionType`: Se invoca `transactionType` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCaseTest.java` — `AnalyticsServicePort.analyzeTransaction`: Se invoca `analyzeTransaction` sobre `AnalyticsServicePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCaseTest.java` — `AnalyticsResult.riskScore`: Se invoca `riskScore` sobre `AnalyticsResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/optimizacionia/infrastructure/adapters/AnalyticsServiceAdapterTest.java` — `AnalyticsResult.riskScore`: Se invoca `riskScore` sobre `AnalyticsResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced L2

### Brecha de conocimiento
APLICA prácticas y estrategias que optimizan el uso de recursos con agentes IA., APLICA prácticas y estrategias que optimizan el uso de recursos con agentes IA.

### Reto
- Tema: optimización de componentes ia
- Seniority: advanced-l2
- Tipo: practical
- Título: Optimización de Recursos en Componentes de IA
- Tiempo estimado: 10 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Identificación de Puntos de Optimización — objetivo: Identificar áreas del componente de IA que pueden ser optimizadas para mejorar el uso de recursos. — entregable (NO resolver): Documento que lista las áreas identificadas para optimización.
- Fase 2: Implementación de Optimizaciones — objetivo: Aplicar estrategias de optimización en las áreas identificadas. — entregable (NO resolver): Componente de IA optimizado y documentación del proceso de optimización.
- Fase 3: Evaluación y Documentación de Resultados — objetivo: Evaluar el impacto de las optimizaciones y documentar los resultados. — entregable (NO resolver): Reporte de evaluación que incluye resultados de pruebas de carga, comparación de rendimiento y propuestas de mejora.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>optimizacionia</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>optimizacionia</name>
    <description>Optimización de recursos en componentes de IA para procesamiento de transacciones bancarias</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
        <springdoc-openapi.version>2.9.1</springdoc-openapi.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi.version}</version>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/Application.java ===
package com.pragma.optimizacionia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@ConfigurationPropertiesScan
public class Application {
    private final ApplicationProperties applicationProperties;

    public Application(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        validateProperties();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(applicationProperties.getThreadPool().getCoreSize());
        executor.setMaxPoolSize(applicationProperties.getThreadPool().getMaxSize());
        executor.setQueueCapacity(applicationProperties.getThreadPool().getQueueCapacity());
        executor.setThreadNamePrefix("async-executor-");
        executor.initialize();
        return executor;
    }

    private void validateProperties() {
        if (applicationProperties.getThreadPool().getCoreSize() <= 0) {
            throw new IllegalStateException("Core pool size must be greater than 0");
        }
        if (applicationProperties.getThreadPool().getMaxSize() <= applicationProperties.getThreadPool().getCoreSize()) {
            throw new IllegalStateException("Max pool size must be greater than core pool size");
        }
        if (applicationProperties.getLatencyThreshold() <= 0) {
            throw new IllegalStateException("Latency threshold must be greater than 0");
        }
    }
}

record ThreadPoolProperties(int coreSize, int maxSize, int queueCapacity) {}

record ApplicationProperties(ThreadPoolProperties threadPool, int latencyThreshold) {}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: optimizacionia
  datasource:
    url: jdbc:postgresql://localhost:5432/optimizacionia
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 20
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
        jdbc:
          batch_size: 50
          order_inserts: true
          order_updates: true

server:
  port: 8080
  tomcat:
    threads:
      max: 200
      min-spare: 10
    connection-timeout: 20000ms

management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    tags:
      application: ${spring.application.name}

resilience4j:
  circuitbreaker:
    instances:
      analyticsService:
        registerHealthIndicator: true
        failureRateThreshold: 50
        minimumNumberOfCalls: 5
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        permittedNumberOfCallsInHalfOpenState: 3
        slidingWindowSize: 10
        slidingWindowType: COUNT_BASED
  retry:
    instances:
      analyticsService:
        maxAttempts: 3
        waitDuration: 100ms
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2
  bulkhead:
    instances:
      analyticsService:
        maxConcurrentCalls: 20
        maxWaitDuration: 10ms

application:
  thread-pool:
    core-size: 10
    max-size: 50
    queue-capacity: 100
  latency-threshold: 200

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/domain/model/Transaction.java ===
package com.pragma.optimizacionia.domain.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Transaction(
    @NotNull(message = "Transaction ID cannot be null")
    UUID id,

    @NotBlank(message = "Account ID cannot be blank")
    @Size(max = 50, message = "Account ID must be less than 50 characters")
    String accountId,

    @NotBlank(message = "Transaction type cannot be blank")
    @Size(max = 20, message = "Transaction type must be less than 20 characters")
    String type,

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    BigDecimal amount,

    @NotBlank(message = "Currency cannot be blank")
    @Size(min = 3, max = 3, message = "Currency must be 3 characters")
    String currency,

    @NotNull(message = "Timestamp cannot be null")
    @PastOrPresent(message = "Timestamp cannot be in the future")
    LocalDateTime timestamp,

    @NotBlank(message = "Status cannot be blank")
    @Size(max = 20, message = "Status must be less than 20 characters")
    String status,

    @Size(max = 200, message = "Description must be less than 200 characters")
    String description
) {
    public Transaction {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
    
    public boolean isHighValue() {
        return amount.compareTo(new BigDecimal("10000")) > 0;
    }
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/domain/model/AnalyticsResult.java ===
package com.pragma.optimizacionia.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AnalyticsResult(
    @NotNull(message = "Result ID cannot be null")
    UUID id,

    @NotBlank(message = "Account ID cannot be blank")
    @Size(max = 50, message = "Account ID must be less than 50 characters")
    String accountId,

    @NotNull(message = "Risk score cannot be null")
    BigDecimal riskScore,

    @NotNull(message = "Fraud probability cannot be null")
    BigDecimal fraudProbability,

    @NotNull(message = "Customer behavior score cannot be null")
    BigDecimal customerBehaviorScore,

    @NotNull(message = "Last updated cannot be null")
    LocalDateTime lastUpdated,

    @Size(max = 500, message = "Additional info must be less than 500 characters")
    String additionalInfo
) {
    public AnalyticsResult {
        if (riskScore.compareTo(BigDecimal.ZERO) < 0 || riskScore.compareTo(BigDecimal.TEN) > 0) {
            throw new IllegalArgumentException("Risk score must be between 0 and 10");
        }
        if (fraudProbability.compareTo(BigDecimal.ZERO) < 0 || fraudProbability.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("Fraud probability must be between 0 and 1");
        }
        if (customerBehaviorScore.compareTo(BigDecimal.ZERO) < 0 || customerBehaviorScore.compareTo(BigDecimal.TEN) > 0) {
            throw new IllegalArgumentException("Customer behavior score must be between 0 and 10");
        }
    }
    
    public boolean isHighRisk() {
        return riskScore.compareTo(new BigDecimal("7")) > 0;
    }
    
    public boolean isFraudSuspicious() {
        return fraudProbability.compareTo(new BigDecimal("0.8")) > 0;
    }
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/domain/ports/in/ProcessTransactionPort.java ===
package com.pragma.optimizacionia.domain.ports.in;

import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import java.util.concurrent.CompletionStage;

public interface ProcessTransactionPort {
    /**
     * Processes a transaction and returns the analytics result.
     * @param transaction the transaction to process
     * @return a CompletionStage containing the analytics result
     */
    CompletionStage<AnalyticsResult> processTransaction(Transaction transaction);
    
    /**
     * Processes a batch of transactions and returns the count of successfully processed transactions.
     * @param transactions the list of transactions to process
     * @return a CompletionStage containing the count of successfully processed transactions
     */
    CompletionStage<Integer> processBatchTransactions(Iterable<Transaction> transactions);
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/domain/ports/out/TransactionRepositoryPort.java ===
package com.pragma.optimizacionia.domain.ports.out;

import com.pragma.optimizacionia.domain.model.Transaction;

import java.util.concurrent.CompletableFuture;

/**
 * Puerto de salida para la persistencia de transacciones.
 * Define el contrato que la capa de dominio utiliza para guardar transacciones.
 * La implementación concreta reside en la capa de infraestructura.
 */
public interface TransactionRepositoryPort {

    /**
     * Persiste una transacción en el repositorio.
     * @param transaction la transacción a persistir
     * @return un CompletableFuture que se completa con la transacción persistida
     */
    CompletableFuture<Transaction> save(Transaction transaction);

    /**
     * Persiste un lote de transacciones en el repositorio.
     * @param transactions iterable de transacciones a persistir
     * @return un CompletableFuture que se completa con el número de transacciones persistidas
     */
    CompletableFuture<Integer> saveBatch(Iterable<Transaction> transactions);

    /**
     * Busca una transacción por su identificador único.
     * @param transactionId identificador de la transacción
     * @return un CompletableFuture que puede completarse con la transacción si existe
     */
    CompletableFuture<Transaction> findById(String transactionId);
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/domain/ports/out/AnalyticsServicePort.java ===
package com.pragma.optimizacionia.domain.ports.out;

import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import com.pragma.optimizacionia.domain.model.Transaction;

import java.util.concurrent.CompletableFuture;

/**
 * Puerto de salida para comunicar resultados al servicio de analíticas de cliente (SAC).
 * Define el contrato para enviar resultados del procesamiento de transacciones
 * al sistema externo de analíticas.
 */
public interface AnalyticsServicePort {

    /**
     * Envía un resultado de análisis al servicio de analíticas.
     * @param transaction la transacción procesada
     * @param result el resultado del análisis realizado
     * @return un CompletableFuture que se completa con true si el envío fue exitoso
     */
    CompletableFuture<Boolean> sendResult(Transaction transaction, AnalyticsResult result);

    /**
     * Envía un lote de resultados de análisis al servicio de analíticas.
     * @param transactions iterable de transacciones procesadas
     * @param results iterable de resultados correspondientes
     * @return un CompletableFuture que se completa con el número de resultados enviados
     */
    CompletableFuture<Integer> sendBatchResults(
            Iterable<Transaction> transactions,
            Iterable<AnalyticsResult> results
    );

    /**
     * Verifica la disponibilidad del servicio de analíticas.
     * @return un CompletableFuture que se completa con true si el servicio está disponible
     */
    CompletableFuture<Boolean> isAvailable();
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/infrastructure/controllers/dto/TransactionRequest.java ===
package com.pragma.optimizacionia.infrastructure.controllers.dto;

import com.pragma.optimizacionia.domain.model.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO para recibir solicitudes de procesamiento de transacciones desde el STP.
 * Representa la entrada de datos en la capa de infraestructura que será
 * transformada a una entidad del dominio.
 */
public record TransactionRequest(

        @NotBlank(message = "Transaction ID cannot be blank")
        @Size(max = 50, message = "Transaction ID must be less than 50 characters")
        String transactionId,

        @NotBlank(message = "Transaction type cannot be blank")
        @Size(max = 20, message = "Transaction type must be less than 20 characters")
        String transactionType,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @NotBlank(message = "Source account cannot be blank")
        @Size(max = 20, message = "Source account must be less than 20 characters")
        String sourceAccount,

        @NotBlank(message = "Destination account cannot be blank")
        @Size(max = 20, message = "Destination account must be less than 20 characters")
        String destinationAccount,

        @NotNull(message = "Timestamp cannot be null")
        Instant timestamp,

        String clientId,

        String merchantId,

        String location
) {
    /**
     * Transforma este DTO en una entidad del dominio.
     * @return una nueva instancia de Transaction con los datos de esta solicitud
     */
    public Transaction toDomain() {
        return new Transaction(
                transactionId,
                transactionType,
                amount,
                sourceAccount,
                destinationAccount,
                timestamp,
                clientId,
                merchantId,
                location
        );
    }

    /**
     * Valida que la transacción sea de alto valor según el umbral configurado.
     * @param threshold el umbral en centavos para considerar alta valor
     * @return true si el monto supera el umbral
     */
    public boolean isHighValue(long threshold) {
        return amount.multiply(BigDecimal.valueOf(100)).longValue() > threshold;
    }
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/infrastructure/controllers/dto/TransactionResponse.java ===
package com.pragma.optimizacionia.infrastructure.controllers.dto;

import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

public record TransactionResponse(
    @NotNull
    private final String transactionId,
    
    @NotNull
    private final String transactionType,
    
    @NotNull
    private final BigDecimal amount,
    
    @NotNull
    private final Instant processedAt,
    
    @NotNull
    private final boolean isHighValue,
    
    @NotNull
    private final boolean isHighRisk,
    
    @NotNull
    private final boolean isFraudSuspicious,
    
    @NotNull
    private final String status,
    
    private final String message
) {
    public static TransactionResponse fromDomain(Transaction transaction, AnalyticsResult analyticsResult, String transactionId, String status, String message) {
        return new TransactionResponse(
            transactionId,
            transaction.type(),
            transaction.amount(),
            Instant.now(),
            transaction.isHighValue(),
            analyticsResult.isHighRisk(),
            analyticsResult.isFraudSuspicious(),
            status,
            message
        );
    }
    
    public static TransactionResponse success(Transaction transaction, AnalyticsResult analyticsResult, String transactionId) {
        return fromDomain(transaction, analyticsResult, transactionId, "SUCCESS", "Transaction processed successfully");
    }
    
    public static TransactionResponse failed(Transaction transaction, String transactionId, String errorMessage) {
        return fromDomain(
            transaction, 
            new AnalyticsResult(false, false), 
            transactionId, 
            "FAILED", 
            errorMessage
        );
    }
    
    public static TransactionResponse pending(Transaction transaction, String transactionId) {
        return fromDomain(
            transaction, 
            new AnalyticsResult(false, false), 
            transactionId, 
            "PENDING", 
            "Transaction is being processed"
        );
    }
    
    public String getTransactionId() {
        return this.transactionId;
    }
    
    public String getTransactionType() {
        return this.transactionType;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public Instant getProcessedAt() {
        return this.processedAt;
    }
    
    public boolean isHighValue() {
        return this.isHighValue;
    }
    
    public boolean isHighRisk() {
        return this.isHighRisk;
    }
    
    public boolean isFraudSuspicious() {
        return this.isFraudSuspicious;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public String getMessage() {
        return this.message;
    }
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCase.java ===
package com.pragma.optimizacionia.application.usecases;

import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.domain.ports.in.ProcessTransactionPort;
import com.pragma.optimizacionia.domain.ports.out.AnalyticsServicePort;
import com.pragma.optimizacionia.domain.ports.out.TransactionRepositoryPort;
import com.pragma.optimizacionia.infrastructure.controllers.dto.TransactionResponse;
import com.pragma.optimizacionia.infrastructure.exception.ResourceOptimizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.StreamSupport;

@Service
public class TransactionProcessingUseCase implements ProcessTransactionPort {
    
    private static final Logger logger = LoggerFactory.getLogger(TransactionProcessingUseCase.class);
    private static final int MAX_BATCH_SIZE = 1000;
    private static final int MIN_AMOUNT_THRESHOLD = 10000;
    
    private final TransactionRepositoryPort transactionRepository;
    private final AnalyticsServicePort analyticsService;
    
    public TransactionProcessingUseCase(
            TransactionRepositoryPort transactionRepository,
            AnalyticsServicePort analyticsService) {
        this.transactionRepository = transactionRepository;
        this.analyticsService = analyticsService;
    }
    
    @Override
    public CompletionStage<AnalyticsResult> processTransaction(Transaction transaction) {
        logger.info("Processing transaction: type={}, amount={}", transaction.type(), transaction.amount());
        
        if (transaction == null) {
            logger.error("Transaction cannot be null");
            return CompletableFuture.failedFuture(
                new ResourceOptimizationException("Transaction cannot be null"));
        }
        
        if (transaction.type() == null || transaction.type().isBlank()) {
            logger.error("Transaction type is required");
            return CompletableFuture.failedFuture(
                new ResourceOptimizationException("Transaction type is required"));
        }
        
        if (transaction.amount() == null || transaction.amount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            logger.error("Transaction amount must be positive");
            return CompletableFuture.failedFuture(
                new ResourceOptimizationException("Transaction amount must be positive"));
        }
        
        String transactionId = generateTransactionId();
        
        try {
            transactionRepository.save(transaction);
            logger.debug("Transaction saved with id: {}", transactionId);
        } catch (Exception e) {
            logger.error("Failed to save transaction: {}", e.getMessage());
            return CompletableFuture.failedFuture(
                new ResourceOptimizationException("Failed to save transaction: " + e.getMessage()));
        }
        
        CompletionStage<AnalyticsResult> analyticsResultFuture = analyticsService.analyze(transaction);
        
        return analyticsResultFuture.whenComplete((result, throwable) -> {
            if (throwable != null) {
                logger.error("Analytics service failed for transaction {}: {}", transactionId, throwable.getMessage());
            } else {
                logger.info("Analytics completed for transaction {}: highRisk={}, fraudSuspicious={}",
                    transactionId, result.isHighRisk(), result.isFraudSuspicious());
                
                if (result.isFraudSuspicious()) {
                    logger.warn("FRAUD ALERT: Transaction {} flagged as suspicious", transactionId);
                }
                
                if (result.isHighRisk() && transaction.isHighValue()) {
                    logger.warn("HIGH RISK ALERT: High-value transaction {} requires manual review", transactionId);
                }
            }
        });
    }
    
    @Override
    public CompletionStage<Integer> processBatchTransactions(Iterable<Transaction> transactions) {
        logger.info("Starting batch transaction processing");
        
        if (transactions == null) {
            logger.error("Transaction batch cannot be null");
            return CompletableFuture.failedFuture(
                new ResourceOptimizationException("Transaction batch cannot be null"));
        }
        
        long transactionCount = StreamSupport.stream(transactions.spliterator(), false).count();
        logger.info("Batch size: {} transactions", transactionCount);
        
        if (transactionCount > MAX_BATCH_SIZE) {
            logger.warn("Batch size {} exceeds maximum allowed {}. Processing in chunks.", 
                transactionCount, MAX_BATCH_SIZE);
        }
        
        int[] processedCount = {0};
        int[] failedCount = {0};
        
        return CompletableFuture.supplyAsync(() -> {
            int chunkNumber = 0;
            java.util.Iterator<Transaction> iterator = transactions.iterator();
            
            while (iterator.hasNext()) {
                chunkNumber++;
                int processedInChunk = 0;
                
                for (int i = 0; i < MAX_BATCH_SIZE && iterator.hasNext(); i++) {
                    Transaction transaction = iterator.next();
                    
                    try {
                        CompletionStage<AnalyticsResult> resultFuture = processTransaction(transaction);
                        AnalyticsResult result = resultFuture.toCompletableFuture().get();
                        processedInChunk++;
                        
                        if (result.isHighRisk()) {
                            logger.debug("High-risk transaction detected in chunk {}", chunkNumber);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        logger.error("Processing interrupted for transaction in chunk {}: {}", chunkNumber, e.getMessage());
                        failedCount[0]++;
                    } catch (ExecutionException e) {
                        logger.error("Execution failed for transaction in chunk {}: {}", chunkNumber, e.getMessage());
                        failedCount[0]++;
                    }
                }
                
                processedCount[0] += processedInChunk;
                logger.info("Chunk {} processed: {} transactions", chunkNumber, processedInChunk);
                
                if (chunkNumber % 10 == 0) {
                    logger.info("Progress: {} / {} transactions processed", processedCount[0], transactionCount);
                }
            }
            
            logger.info("Batch processing completed. Total: {}, Failed: {}", processedCount[0], failedCount[0]);
            return processedCount[0];
        });
    }
    
    public TransactionResponse processAndRespond(Transaction transaction) {
        try {
            CompletionStage<AnalyticsResult> resultFuture = processTransaction(transaction);
            AnalyticsResult result = resultFuture.toCompletableFuture().get();
            
            String transactionId = generateTransactionId();
            return TransactionResponse.success(transaction, result, transactionId);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            String transactionId = generateTransactionId();
            logger.error("Processing interrupted: {}", e.getMessage());
            return TransactionResponse.failed(transaction, transactionId, "Processing interrupted: " + e.getMessage());
            
        } catch (ExecutionException e) {
            String transactionId = generateTransactionId();
            logger.error("Processing failed: {}", e.getMessage());
            return TransactionResponse.failed(transaction, transactionId, "Processing failed: " + e.getCause().getMessage());
        }
    }
    
    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
    
    public boolean shouldOptimizeProcessing(Transaction transaction) {
        return transaction.isHighValue() || 
               (transaction.amount() != null && transaction.amount().compareTo(java.math.BigDecimal.valueOf(MIN_AMOUNT_THRESHOLD)) >= 0);
    }
    
    public int getOptimalThreadCount() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        return Math.max(2, availableProcessors - 1);
    }
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java ===
package com.pragma.optimizacionia.infrastructure.adapters;

import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.domain.ports.out.TransactionRepositoryPort;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {
    
    private static final Logger logger = LoggerFactory.getLogger(TransactionRepositoryAdapter.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Transaction save(Transaction transaction) {
        logger.debug("Saving transaction: type={}, amount={}", transaction.type(), transaction.amount());
        
        try {
            TransactionEntity entity = toEntity(transaction);
            entityManager.persist(entity);
            
            logger.info("Transaction saved successfully with ID: {}", entity.getId());
            return transaction;
            
        } catch (PersistenceException e) {
            logger.error("Failed to save transaction: {}", e.getMessage());
            throw new RuntimeException("Failed to save transaction: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Optional<Transaction> findById(Long id) {
        logger.debug("Finding transaction by ID: {}", id);
        
        try {
            TransactionEntity entity = entityManager.find(TransactionEntity.class, id);
            if (entity == null) {
                logger.debug("Transaction not found with ID: {}", id);
                return Optional.empty();
            }
            
            logger.debug("Transaction found: {}", entity.getId());
            return Optional.of(toDomain(entity));
            
        } catch (Exception e) {
            logger.error("Error finding transaction by ID {}: {}", id, e.getMessage());
            return Optional.empty();
        }
    }
    
    @Override
    public List<Transaction> findAll() {
        logger.debug("Retrieving all transactions");
        
        try {
            TypedQuery<TransactionEntity> query = entityManager.createQuery(
                "SELECT t FROM TransactionEntity t", TransactionEntity.class);
            List<TransactionEntity> entities = query.getResultList();
            
            logger.debug("Retrieved {} transactions", entities.size());
            return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
            
        } catch (Exception e) {
            logger.error("Error retrieving all transactions: {}", e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public List<Transaction> findByType(String type) {
        logger.debug("Finding transactions by type: {}", type);
        
        try {
            TypedQuery<TransactionEntity> query = entityManager.createQuery(
                "SELECT t FROM TransactionEntity t WHERE t.type = :type", TransactionEntity.class);
            query.setParameter("type", type);
            List<TransactionEntity> entities = query.getResultList();
            
            logger.debug("Found {} transactions of type: {}", entities.size(), type);
            return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
            
        } catch (Exception e) {
            logger.error("Error finding transactions by type {}: {}", type, e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public List<Transaction> findHighValueTransactions(BigDecimal threshold) {
        logger.debug("Finding high-value transactions with threshold: {}", threshold);
        
        try {
            TypedQuery<TransactionEntity> query = entityManager.createQuery(
                "SELECT t FROM TransactionEntity t WHERE t.amount >= :threshold", TransactionEntity.class);
            query.setParameter("threshold", threshold);
            List<TransactionEntity> entities = query.getResultList();
            
            logger.debug("Found {} high-value transactions", entities.size());
            return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
            
        } catch (Exception e) {
            logger.error("Error finding high-value transactions: {}", e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public void deleteById(Long id) {
        logger.debug("Deleting transaction by ID: {}", id);
        
        try {
            TransactionEntity entity = entityManager.find(TransactionEntity.class, id);
            if (entity != null) {
                entityManager.remove(entity);
                logger.info("Transaction deleted successfully: {}", id);
            } else {
                logger.warn("Transaction not found for deletion: {}", id);
            }
            
        } catch (Exception e) {
            logger.error("Error deleting transaction {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete transaction: " + e.getMessage(), e);
        }
    }
    
    @Override
    public long count() {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(t) FROM TransactionEntity t", Long.class);
            return query.getSingleResult();
            
        } catch (Exception e) {
            logger.error("Error counting transactions: {}", e.getMessage());
            return 0L;
        }
    }
    
    @Override
    public boolean existsById(Long id) {
        logger.debug("Checking if transaction exists: {}", id);
        
        try {
            TransactionEntity entity = entityManager.find(TransactionEntity.class, id);
            return entity != null;
            
        } catch (Exception e) {
            logger.error("Error checking transaction existence {}: {}", id, e.getMessage());
            return false;
        }
    }
    
    private TransactionEntity toEntity(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setType(transaction.type());
        entity.setAmount(transaction.amount());
        entity.setCreatedAt(Instant.now());
        entity.setIsHighValue(transaction.isHighValue());
        return entity;
    }
    
    private Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
            entity.getType(),
            entity.getAmount(),
            entity.getIsHighValue() != null && entity.getIsHighValue()
        );
    }
    
    @Entity
    @Table(name = "transactions")
    public static class TransactionEntity {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(nullable = false, length = 20)
        private String type;
        
        @Column(nullable = false, precision = 19, scale = 4)
        private BigDecimal amount;
        
        @Column(name = "created_at", nullable = false)
        private Instant createdAt;
        
        @Column(name = "is_high_value")
        private Boolean isHighValue;
        
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public BigDecimal getAmount() {
            return amount;
        }
        
        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
        
        public Instant getCreatedAt() {
            return createdAt;
        }
        
        public void setCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
        }
        
        public Boolean getIsHighValue() {
            return isHighValue;
        }
        
        public void setIsHighValue(Boolean isHighValue) {
            this.isHighValue = isHighValue;
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/infrastructure/adapters/AnalyticsServiceAdapter.java ===
package com.pragma.optimizacionia.infrastructure.adapters;

import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import com.pragma.optimizacionia.domain.ports.out.AnalyticsServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Component
public class AnalyticsServiceAdapter implements AnalyticsServicePort {

    private static final Logger log = LoggerFactory.getLogger(AnalyticsServiceAdapter.class);

    private final RestTemplate restTemplate;
    private final String analyticsServiceUrl;
    private final int analyticsServiceTimeout;

    public AnalyticsServiceAdapter(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${external.analytics.service.url:http://localhost:8081/api/analytics}") String analyticsServiceUrl,
            @Value("${external.analytics.service.timeout:5000}") int analyticsServiceTimeout) {
        this.restTemplate = restTemplateBuilder
                .connectTimeout(Duration.ofMillis(analyticsServiceTimeout))
                .readTimeout(Duration.ofMillis(analyticsServiceTimeout))
                .build();
        this.analyticsServiceUrl = analyticsServiceUrl;
        this.analyticsServiceTimeout = analyticsServiceTimeout;
    }

    @Override
    public CompletionStage<AnalyticsResult> analyzeTransaction(String transactionId, Map<String, Object> transactionData) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.debug("Enviando transacción {} al servicio de analíticas", transactionId);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("transactionId", transactionId);
                requestBody.put("data", transactionData);

                HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

                @SuppressWarnings("unchecked")
                Map<String, Object> response = restTemplate.postForObject(
                        analyticsServiceUrl + "/analyze",
                        request,
                        Map.class
                );

                if (response == null) {
                    log.warn("Respuesta nula del servicio de analíticas para transacción {}", transactionId);
                    return new AnalyticsResult(false, false);
                }

                boolean highRisk = Boolean.TRUE.equals(response.get("highRisk"));
                boolean fraudSuspicious = Boolean.TRUE.equals(response.get("fraudSuspicious"));

                log.debug("Análisis completado para transacción {}: highRisk={}, fraudSuspicious={}",
                        transactionId, highRisk, fraudSuspicious);

                return new AnalyticsResult(highRisk, fraudSuspicious);

            } catch (Exception e) {
                log.error("Error al analizar transacción {}: {}", transactionId, e.getMessage());
                return new AnalyticsResult(false, false);
            }
        });
    }

    @Override
    public CompletionStage<Boolean> healthCheck() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                restTemplate.getForObject(analyticsServiceUrl + "/health", String.class);
                return true;
            } catch (Exception e) {
                log.warn("Health check fallido para servicio de analíticas: {}", e.getMessage());
                return false;
            }
        });
    }
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/infrastructure/config/Resilience4jConfig.java ===
package com.pragma.optimizacionia.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    // Superficie de práctica: el estudiante implementará las configuraciones de Resilience4j
    // según las necesidades del retos de optimización de recursos en componentes de IA.

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();

        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .build();

        return RetryRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/infrastructure/controllers/TransactionController.java ===
package com.pragma.optimizacionia.infrastructure.controllers;


import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.domain.ports.in.ProcessTransactionPort;
import com.pragma.optimizacionia.infrastructure.controllers.dto.TransactionRequest;
import com.pragma.optimizacionia.infrastructure.controllers.dto.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/api/v1/transactions")
@Tag(name = "Transacciones", description = "Endpoints para procesamiento de transacciones bancarias")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    private final ProcessTransactionPort processTransactionPort;

    public TransactionController(ProcessTransactionPort processTransactionPort) {
        this.processTransactionPort = processTransactionPort;
    }

    @PostMapping
    @Operation(summary = "Procesar transacción individual", description = "Procesa una transacción bancaria individual a través del componente de IA")
    public CompletionStage<ResponseEntity<TransactionResponse>> processTransaction(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la transacción a procesar",
                    required = true)
            @RequestBody TransactionRequest request) {

        log.info("Recibida solicitud de procesamiento de transacción: {}", request.transactionId());

        return processTransactionPort.processTransaction(request.toDomain())
                .thenApply(result -> {
                    TransactionResponse response = TransactionResponse.fromDomain(
                            request.transactionId(),
                            request.amount(),
                            request.transactionType(),
                            result
                    );
                    log.info("Transacción {} procesada exitosamente", request.transactionId());
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                })
                .exceptionally(ex -> {
                    log.error("Error al procesar transacción {}: {}", request.transactionId(), ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(TransactionResponse.error(request.transactionId(), ex.getMessage()));
                });
    }

    @PostMapping("/batch")
    @Operation(summary = "Procesar lote de transacciones", description = "Procesa múltiples transacciones bancarias en lote")
    public CompletionStage<ResponseEntity<Map<String, Object>>> processBatchTransactions(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Lista de transacciones a procesar",
                    required = true)
            @RequestBody List<TransactionRequest> requests) {

        log.info("Recibida solicitud de procesamiento en lote: {} transacciones", requests.size());

        List<com.pragma.optimizacionia.domain.model.Transaction> transactions = requests.stream()
                .map(TransactionRequest::toDomain)
                .toList();

        return processTransactionPort.processBatchTransactions(transactions)
                .thenApply(count -> {
                    log.info("Lote de {} transacciones procesado exitosamente", count);
                    return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                            "status", "SUCCESS",
                            "processedCount", count,
                            "totalReceived", requests.size()
                    ));
                })
                .exceptionally(ex -> {
                    log.error("Error al procesar lote de transacciones: {}", ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(Map.of(
                                    "status", "ERROR",
                                    "message", ex.getMessage()
                            ));
                });
    }

    @GetMapping("/{transactionId}")
    @Operation(summary = "Consultar estado de transacción", description = "Retorna el estado de procesamiento de una transacción específica")
    public ResponseEntity<TransactionResponse> getTransactionStatus(
            @Parameter(description = "ID de la transacción", required = true)
            @PathVariable String transactionId) {

        log.debug("Consultando estado de transacción: {}", transactionId);

        return ResponseEntity.status(HttpStatus.OK).body(
                TransactionResponse.pending(transactionId)
        );
    }

    @GetMapping("/health")
    @Operation(summary = "Verificar salud del servicio", description = "Endpoint de health check para el servicio de transacciones")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "UP",
                "service", "transaction-processor"
        ));
    }
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/infrastructure/config/OpenApiConfig.java ===
package com.pragma.optimizacionia.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración centralizada de OpenAPI/Swagger para documentar la API REST.
 * Proporciona metadatos sobre la API y configura la seguridad global.
 */
@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name:optimizacionia}")
    private String applicationName;

    @Value("${app.api.version:v1}")
    private String apiVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Optimización de Recursos IA")
                        .description("""
                            API REST para el procesamiento de transacciones bancarias
                            con componentes de Inteligencia Artificial optimizados.
                            
                            Esta API permite procesar transacciones individuales y en lote,
                            integrándose con servicios de análisis de riesgo y fraude.
                            
                            ## Características principales
                            - Procesamiento de transacciones en tiempo real
                            - Análisis de riesgo y detección de fraude
                            - Optimización de recursos bajo carga maxima (10,000 TPS)
                            - Latencia objetivo menor a 200ms
                            """)
                        .version(apiVersion)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("desarrollo@pragma.com.co")
                                .url("https://www.pragma.com.co"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token JWT para autenticación. Obtener token del endpoint /auth/login")));
    }

    /**
     * Configura un bean de OpenAPI específico para el entorno de desarrollo.
     * En producción, esta configuración puede ser diferente.
     */
    @Bean
    public OpenAPI developmentOpenAPI(@Value("${spring.profiles.active:default}") String profile) {
        OpenAPI openAPI = customOpenAPI();
        if ("dev".equals(profile)) {
            openAPI.getInfo().setDescription(
                    openAPI.getInfo().getDescription() + "\n\n**Entorno de Desarrollo**\n" +
                            "Esta es la versión de desarrollo de la API."
            );
        }
        return openAPI;
    }
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/infrastructure/exception/GlobalExceptionHandler.java ===
package com.pragma.optimizacionia.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manejador global de excepciones para toda la API REST.
 * Proporciona respuestas consistentes y estructuradas para diferentes tipos de errores.
 * Implementa el patrón de manejo de errores centralizado para toda la aplicación.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String ERROR_CATEGORY_VALIDATION = "VALIDATION_ERROR";
    private static final String ERROR_CATEGORY_INTERNAL = "INTERNAL_ERROR";
    private static final String ERROR_CATEGORY_NOT_FOUND = "RESOURCE_NOT_FOUND";
    private static final String ERROR_CATEGORY_EXTERNAL = "EXTERNAL_SERVICE_ERROR";

    /**
     * Maneja excepciones de validación de argumentos en requests.
     * Ocurre cuando los datos enviados no cumplen las restricciones definidas en los DTOs.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, WebRequest request) {
        
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });

        String details = fieldErrors.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));

        logger.warn("Error de validación en request: {}", details);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message("Error de validación en los datos enviados")
                .category(ERROR_CATEGORY_VALIDATION)
                .details(details)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Maneja violaciones de restricciones de validación a nivel de parámetro.
     * Se activa cuando se validan parámetros de método o campos de entidad.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        
        String violations = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        logger.warn("Violación de restricciones: {}", violations);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message("Violación de restricciones de validación")
                .category(ERROR_CATEGORY_VALIDATION)
                .details(violations)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Maneja errores cuando el JSON recibido no puede ser parseado o tiene formato inválido.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, WebRequest request) {
        
        logger.warn("Mensaje no legible: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message("El formato del cuerpo de la petición es inválido")
                .category(ERROR_CATEGORY_VALIDATION)
                .details("JSON mal formado o tipo de contenido no soportado")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Maneja errores de tipo de argumento cuando el tipo de un parámetro no coincide.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        
        String message = String.format("El parámetro '%s' esperaba un valor de tipo '%s' pero recibió '%s'",
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido",
                ex.getValue());

        logger.warn("Tipo de argumento inválido: {}", message);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message("Tipo de parámetro inválido")
                .category(ERROR_CATEGORY_VALIDATION)
                .details(message)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Maneja excepciones personalizadas de optimización de recursos.
     * Excepciones específicas del dominio de optimización de IA.
     */
    @ExceptionHandler(ResourceOptimizationException.class)
    public ResponseEntity<ErrorResponse> handleResourceOptimizationException(
            ResourceOptimizationException ex, WebRequest request) {
        
        logger.error("Excepción de optimización de recursos: {}", ex.getMessage());

        HttpStatus status = ex.getStatus() != null ? ex.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .category(ex.getCategory())
                .details(ex.getDetails())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }

    /**
     * Maneja excepciones generales no controladas.
     * Captura cualquier excepción no manejada específicamente.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        
        logger.error("Excepción no controlada: ", ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("Error interno del servidor")
                .category(ERROR_CATEGORY_INTERNAL)
                .details("Ha ocurrido un error inesperado. Por favor, contacte al administrador.")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    /**
     * Clase interna para representar respuestas de error estructuradas.
     * Proporciona un formato consistente para todos los errores de la API.
     */
    public static class ErrorResponse {
        private Instant timestamp;
        private int status;
        private String error;
        private String message;
        private String category;
        private String details;
        private String path;

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public Instant getTimestamp() { return timestamp; }
        public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }

        public static class ErrorResponseBuilder {
            private Instant timestamp;
            private int status;
            private String error;
            private String message;
            private String category;
            private String details;
            private String path;

            public ErrorResponseBuilder timestamp(Instant timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ErrorResponseBuilder status(int status) {
                this.status = status;
                return this;
            }

            public ErrorResponseBuilder error(String error) {
                this.error = error;
                return this;
            }

            public ErrorResponseBuilder message(String message) {
                this.message = message;
                return this;
            }

            public ErrorResponseBuilder category(String category) {
                this.category = category;
                return this;
            }

            public ErrorResponseBuilder details(String details) {
                this.details = details;
                return this;
            }

            public ErrorResponseBuilder path(String path) {
                this.path = path;
                return this;
            }

            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.category = this.category;
                response.details = this.details;
                response.path = this.path;
                return response;
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/optimizacionia/infrastructure/exception/ResourceOptimizationException.java ===
package com.pragma.optimizacionia.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * Excepción personalizada para errores relacionados con la optimización de recursos en componentes de IA.
 * Se utiliza para representar errores específicos del dominio de optimización de recursos,
 * incluyendo errores de capacidad, latencia, y fallas en servicios externos.
 * 
 * Esta excepción proporciona información detallada sobre el tipo de error para facilitar
 * el diagnóstico y la respuesta apropiada al cliente.
 */
public class ResourceOptimizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus status;
    private final String category;
    private final String details;

    /**
     * Constructor básico con mensaje de error y estado HTTP por defecto.
     * @param message Descripción del error
     */
    public ResourceOptimizationException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.category = "RESOURCE_OPTIMIZATION_ERROR";
        this.details = null;
    }

    /**
     * Constructor con mensaje y estado HTTP específico.
     * @param message Descripción del error
     * @param status Código de estado HTTP apropiado para el error
     */
    public ResourceOptimizationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.category = "RESOURCE_OPTIMIZATION_ERROR";
        this.details = null;
    }

    /**
     * Constructor completo con mensaje, estado y causa.
     * @param message Descripción del error
     * @param status Código de estado HTTP apropiado
     * @param cause Excepción original que causó el error
     */
    public ResourceOptimizationException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.category = "RESOURCE_OPTIMIZATION_ERROR";
        this.details = null;
    }

    /**
     * Constructor con todos los campos para máximo detalle.
     * @param message Descripción principal del error
     * @param status Código de estado HTTP
     * @param category Categoría del error para clasificación
     * @param details Información adicional sobre el error
     */
    public ResourceOptimizationException(String message, HttpStatus status, String category, String details) {
        super(message);
        this.status = status;
        this.category = category;
        this.details = details;
    }

    /**
     * Constructor completo con causa raíz.
     * @param message Descripción principal del error
     * @param status Código de estado HTTP
     * @param category Categoría del error
     * @param details Información adicional
     * @param cause Excepción original
     */
    public ResourceOptimizationException(String message, HttpStatus status, String category, 
                                          String details, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.category = category;
        this.details = details;
    }

    /**
     * Crea una excepción para errores de capacidad excedida.
     * @param currentCapacity Capacidad actual del sistema
     * @param requestedCapacity Capacidad solicitada
     * @return Nueva instancia de ResourceOptimizationException
     */
    public static ResourceOptimizationException capacityExceeded(int currentCapacity, int requestedCapacity) {
        String message = String.format("Capacidad excedida: capacidad actual %d, solicitada %d", 
                currentCapacity, requestedCapacity);
        return new ResourceOptimizationException(
                message,
                HttpStatus.SERVICE_UNAVAILABLE,
                "CAPACITY_EXCEEDED",
                String.format("El sistema no puede procesar la solicitud: capacidad máxima de %d transacciones/segundo", currentCapacity)
        );
    }

    /**
     * Crea una excepción para errores de latencia excesiva.
     * @param currentLatency Latencia actual en milisegundos
     * @param threshold Latencia objetivo máxima
     * @return Nueva instancia de ResourceOptimizationException
     */
    public static ResourceOptimizationException latencyExceeded(long currentLatency, long threshold) {
        String message = String.format("Latencia excedida: actual %dms, umbral %dms", 
                currentLatency, threshold);
        return new ResourceOptimizationException(
                message,
                HttpStatus.SERVICE_UNAVAILABLE,
                "LATENCY_EXCEEDED",
                String.format("La latencia actual (%dms) supera el umbral permitido (%dms)", currentLatency, threshold)
        );
    }

    /**
     * Crea una excepción para errores de servicio externo no disponible.
     * @param serviceName Nombre del servicio externo
     * @return Nueva instancia de ResourceOptimizationException
     */
    public static ResourceOptimizationException externalServiceUnavailable(String serviceName) {
        String message = String.format("Servicio externo no disponible: %s", serviceName);
        return new ResourceOptimizationException(
                message,
                HttpStatus.SERVICE_UNAVAILABLE,
                "EXTERNAL_SERVICE_UNAVAILABLE",
                String.format("El servicio '%s' no está disponible en este momento", serviceName)
        );
    }

    /**
     * Crea una excepción para errores de timeout.
     * @param operation Nombre de la operación que excedió el timeout
     * @param timeoutMs Timeout configurado en milisegundos
     * @return Nueva instancia de ResourceOptimizationException
     */
    public static ResourceOptimizationException timeoutExceeded(String operation, long timeoutMs) {
        String message = String.format("Timeout excedido en operación: %s (timeout: %dms)", operation, timeoutMs);
        return new ResourceOptimizationException(
                message,
                HttpStatus.REQUEST_TIMEOUT,
                "TIMEOUT_EXCEEDED",
                String.format("La operación '%s' tardó más de %d milisegundos", operation, timeoutMs)
        );
    }

    /**
     * Crea una excepción para errores de procesamiento de lotes.
     * @param batchSize Tamaño del lote
     * @param processed Cantidad procesada exitosamente
     * @param failed Cantidad que falló
     * @return Nueva instancia de ResourceOptimizationException
     */
    public static ResourceOptimizationException batchProcessingFailed(int batchSize, int processed, int failed) {
        String message = String.format("Error en procesamiento por lotes: %d/%d exitosos", processed, batchSize);
        return new ResourceOptimizationException(
                message,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "BATCH_PROCESSING_FAILED",
                String.format("Se procesaron %d transacciones exitosamente y %d fallaron", processed, failed)
        );
    }

    /**
     * Crea una excepción para errores de configuración de recursos.
     * @param resourceName Nombre del recurso mal configurado
     * @param reason Razón del error de configuración
     * @return Nueva instancia de ResourceOptimizationException
     */
    public static ResourceOptimizationException invalidConfiguration(String resourceName, String reason) {
        String message = String.format("Configuración inválida para recurso: %s", resourceName);
        return new ResourceOptimizationException(
                message,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INVALID_CONFIGURATION",
                reason
        );
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return String.format("ResourceOptimizationException{message='%s', status=%s, category='%s', details='%s'}",
                getMessage(), status, category, details);
    }
}

// === ARCHIVO: src/test/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCaseTest.java ===
package com.pragma.optimizacionia.application.usecases;

import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.domain.ports.out.AnalyticsServicePort;
import com.pragma.optimizacionia.domain.ports.out.TransactionRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para TransactionProcessingUseCase")
class TransactionProcessingUseCaseTest {

    @Mock
    private TransactionRepositoryPort transactionRepositoryPort;

    @Mock
    private AnalyticsServicePort analyticsServicePort;

    private TransactionProcessingUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new TransactionProcessingUseCase(transactionRepositoryPort, analyticsServicePort);
    }

    @Test
    @DisplayName("Debe procesar una transacción válida exitosamente")
    void shouldProcessValidTransactionSuccessfully() throws Exception {
        Transaction transaction = new Transaction(
            "TXN001",
            "transfer",
            new BigDecimal("1000.00"),
            "ACC123",
            "ACC456",
            LocalDateTime.now(),
            "PENDING"
        );

        AnalyticsResult mockResult = new AnalyticsResult(true, false, 0.65);
        when(analyticsServicePort.analyzeTransaction(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(mockResult));
        when(transactionRepositoryPort.save(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(transaction));

        CompletionStage<AnalyticsResult> result = useCase.processTransaction(transaction);

        AnalyticsResult actualResult = result.toCompletableFuture().get();
        assertNotNull(actualResult);
        assertTrue(actualResult.isHighRisk());
        assertFalse(actualResult.isFraudSuspicious());

        verify(analyticsServicePort, times(1)).analyzeTransaction(any(Transaction.class));
        verify(transactionRepositoryPort, times(1)).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe identificar transacciones de alto valor correctamente")
    void shouldIdentifyHighValueTransactions() throws Exception {
        Transaction highValueTransaction = new Transaction(
            "TXN002",
            "wire_transfer",
            new BigDecimal("50000.00"),
            "ACC789",
            "ACC012",
            LocalDateTime.now(),
            "PENDING"
        );

        assertTrue(highValueTransaction.isHighValue(),
            "Transacción con monto mayor a 10000 debe ser de alto valor");

        AnalyticsResult mockResult = new AnalyticsResult(false, true, 0.92);
        when(analyticsServicePort.analyzeTransaction(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(mockResult));
        when(transactionRepositoryPort.save(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(highValueTransaction));

        CompletionStage<AnalyticsResult> result = useCase.processTransaction(highValueTransaction);

        AnalyticsResult actualResult = result.toCompletableFuture().get();
        assertNotNull(actualResult);
        assertFalse(actualResult.isHighRisk());
        assertTrue(actualResult.isFraudSuspicious());
    }

    @Test
    @DisplayName("Debe procesar lote de transacciones correctamente")
    void shouldProcessBatchTransactions() throws Exception {
        Transaction tx1 = new Transaction(
            "TXN003", "payment", new BigDecimal("500.00"),
            "ACC111", "ACC222", LocalDateTime.now(), "PENDING"
        );
        Transaction tx2 = new Transaction(
            "TXN004", "payment", new BigDecimal("750.00"),
            "ACC333", "ACC444", LocalDateTime.now(), "PENDING"
        );
        List<Transaction> transactions = List.of(tx1, tx2);

        AnalyticsResult result1 = new AnalyticsResult(false, false, 0.10);
        AnalyticsResult result2 = new AnalyticsResult(false, false, 0.15);

        when(analyticsServicePort.analyzeTransaction(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(result1))
            .thenReturn(CompletableFuture.completedFuture(result2));
        when(transactionRepositoryPort.save(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(tx1))
            .thenReturn(CompletableFuture.completedFuture(tx2));

        CompletionStage<Integer> result = useCase.processBatchTransactions(transactions);

        Integer processedCount = result.toCompletableFuture().get();
        assertEquals(2, processedCount, "Debe procesar exactamente 2 transacciones");
        verify(analyticsServicePort, times(2)).analyzeTransaction(any(Transaction.class));
        verify(transactionRepositoryPort, times(2)).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe manejar excepción del servicio de analíticas gracefully")
    void shouldHandleAnalyticsServiceException() {
        Transaction transaction = new Transaction(
            "TXN005", "transfer", new BigDecimal("2000.00"),
            "ACC555", "ACC666", LocalDateTime.now(), "PENDING"
        );

        when(analyticsServicePort.analyzeTransaction(any(Transaction.class)))
            .thenReturn(CompletableFuture.failedFuture(
                new RuntimeException("Servicio de analíticas no disponible")));

        CompletionStage<AnalyticsResult> result = useCase.processTransaction(transaction);

        assertThrows(Exception.class, () -> result.toCompletableFuture().get());
        verify(transactionRepositoryPort, never()).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe guardar transacción antes de invocar análisis")
    void shouldSaveTransactionBeforeCallingAnalysis() throws Exception {
        Transaction transaction = new Transaction(
            "TXN006", "deposit", new BigDecimal("3000.00"),
            "ACC777", "ACC888", LocalDateTime.now(), "PENDING"
        );

        AnalyticsResult mockResult = new AnalyticsResult(true, false, 0.50);
        when(analyticsServicePort.analyzeTransaction(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(mockResult));
        when(transactionRepositoryPort.save(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(transaction));

        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);

        useCase.processTransaction(transaction).toCompletableFuture().get();

        verify(transactionRepositoryPort, times(1)).save(transactionCaptor.capture());
        verify(analyticsServicePort, times(1)).analyzeTransaction(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe procesar transacciones pequeñas correctamente")
    void shouldProcessSmallTransactions() throws Exception {
        Transaction smallTransaction = new Transaction(
            "TXN007", "payment", new BigDecimal("50.00"),
            "ACC999", "ACC000", LocalDateTime.now(), "PENDING"
        );

        assertFalse(smallTransaction.isHighValue(),
            "Transacción con monto menor a 10000 no debe ser de alto valor");

        AnalyticsResult mockResult = new AnalyticsResult(false, false, 0.05);
        when(analyticsServicePort.analyzeTransaction(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(mockResult));
        when(transactionRepositoryPort.save(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(smallTransaction));

        CompletionStage<AnalyticsResult> result = useCase.processTransaction(smallTransaction);

        AnalyticsResult actualResult = result.toCompletableFuture().get();
        assertNotNull(actualResult);
        assertFalse(actualResult.isHighRisk());
        assertFalse(actualResult.isFraudSuspicious());
        assertTrue(actualResult.riskScore() < 0.3);
    }
}

// === ARCHIVO: src/test/java/com/pragma/optimizacionia/infrastructure/adapters/AnalyticsServiceAdapterTest.java ===
package com.pragma.optimizacionia.infrastructure.adapters;

import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.infrastructure.exception.ResourceOptimizationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para AnalyticsServiceAdapter")
class AnalyticsServiceAdapterTest {

    private static final String ANALYTICS_API_URL = "http://analytics-service:8080/api/v1/analyze";

    @Mock
    private RestTemplate restTemplate;

    private AnalyticsServiceAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new AnalyticsServiceAdapter(restTemplate);
    }

    @Test
    @DisplayName("Debe retornar análisis exitoso cuando el servicio responde correctamente")
    void shouldReturnAnalysisWhenServiceRespondsSuccessfully() {
        Transaction transaction = new Transaction(
            "TXN100", "transfer", new BigDecimal("15000.00"),
            "ACC100", "ACC200", LocalDateTime.now(), "PENDING"
        );

        Map<String, Object> apiResponse = Map.of(
            "highRisk", true,
            "fraudSuspicious", false,
            "riskScore", 0.75
        );

        when(restTemplate.postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        )).thenReturn(new ResponseEntity<>(apiResponse, HttpStatus.OK));

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(transaction);

        AnalyticsResult analyticsResult = result.toCompletableFuture().join();

        assertNotNull(analyticsResult);
        assertTrue(analyticsResult.isHighRisk());
        assertFalse(analyticsResult.isFraudSuspicious());
        assertEquals(0.75, analyticsResult.riskScore(), 0.01);

        verify(restTemplate, times(1)).postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        );
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el servicio de analíticas retorna error")
    void shouldThrowExceptionWhenServiceReturnsError() {
        Transaction transaction = new Transaction(
            "TXN101", "payment", new BigDecimal("5000.00"),
            "ACC300", "ACC400", LocalDateTime.now(), "PENDING"
        );

        when(restTemplate.postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        )).thenReturn(new ResponseEntity<>(Map.of(), HttpStatus.INTERNAL_SERVER_ERROR));

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(transaction);

        assertThrows(ResourceOptimizationException.class, () -> result.toCompletableFuture().join());
    }

    @Test
    @DisplayName("Debe manejar respuestas con valores nulos correctamente")
    void shouldHandleNullValuesInResponse() {
        Transaction transaction = new Transaction(
            "TXN102", "deposit", new BigDecimal("8000.00"),
            "ACC500", "ACC600", LocalDateTime.now(), "PENDING"
        );

        Map<String, Object> apiResponse = Map.of(
            "highRisk", (Object) null,
            "fraudSuspicious", null,
            "riskScore", null
        );

        when(restTemplate.postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        )).thenReturn(new ResponseEntity<>(apiResponse, HttpStatus.OK));

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(transaction);

        AnalyticsResult analyticsResult = result.toCompletableFuture().join();

        assertNotNull(analyticsResult);
        assertFalse(analyticsResult.isHighRisk());
        assertFalse(analyticsResult.isFraudSuspicious());
    }

    @Test
    @DisplayName("Debe transformar correctamente transacciones de alto riesgo")
    void shouldTransformHighRiskTransactionsCorrectly() {
        Transaction highRiskTransaction = new Transaction(
            "TXN103", "wire_transfer", new BigDecimal("100000.00"),
            "ACC700", "ACC800", LocalDateTime.now(), "PENDING"
        );

        Map<String, Object> apiResponse = Map.of(
            "highRisk", true,
            "fraudSuspicious", true,
            "riskScore", 0.95
        );

        when(restTemplate.postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        )).thenReturn(new ResponseEntity<>(apiResponse, HttpStatus.OK));

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(highRiskTransaction);

        AnalyticsResult analyticsResult = result.toCompletableFuture().join();

        assertTrue(analyticsResult.isHighRisk());
        assertTrue(analyticsResult.isFraudSuspicious());
        assertTrue(analyticsResult.riskScore() > 0.9);
    }

    @Test
    @DisplayName("Debe manejar excepción de conexión correctamente")
    void shouldHandleConnectionException() {
        Transaction transaction = new Transaction(
            "TXN104", "transfer", new BigDecimal("2000.00"),
            "ACC900", "ACC901", LocalDateTime.now(), "PENDING"
        );

        when(restTemplate.postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        )).thenThrow(new ResourceOptimizationException("Error de conexión con el servicio de analíticas"));

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(transaction);

        assertThrows(ResourceOptimizationException.class, () -> result.toCompletableFuture().join());
    }

    @Test
    @DisplayName("Debe mapear correctamente el campo fraudSuspicious del API")
    void shouldMapFraudSuspiciousFieldCorrectly() {
        Transaction transaction = new Transaction(
            "TXN105", "payment", new BigDecimal("25000.00"),
            "ACC902", "ACC903", LocalDateTime.now(), "PENDING"
        );

        Map<String, Object> apiResponse = Map.of(
            "highRisk", false,
            "fraudSuspicious", true,
            "riskScore", 0.45
        );

        when(restTemplate.postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        )).thenReturn(new ResponseEntity<>(apiResponse, HttpStatus.OK));

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(transaction);

        AnalyticsResult analyticsResult = result.toCompletableFuture().join();

        assertFalse(analyticsResult.isHighRisk());
        assertTrue(analyticsResult.isFraudSuspicious());
    }

    @Test
    @DisplayName("Debe rechazar transacciones con monto cero")
    void shouldRejectTransactionsWithZeroAmount() {
        Transaction zeroAmountTransaction = new Transaction(
            "TXN106", "payment", BigDecimal.ZERO,
            "ACC904", "ACC905", LocalDateTime.now(), "PENDING"
        );

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(zeroAmountTransaction);

        assertThrows(Exception.class, () -> result.toCompletableFuture().join());
        verify(restTemplate, never()).postForEntity(anyString(), any(), any(Class.class));
    }
}
```
