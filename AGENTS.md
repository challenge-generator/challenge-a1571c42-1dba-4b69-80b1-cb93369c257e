# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Optimización de Recursos en Componentes de IA**.

| | |
|---|---|
| Tema | optimización de componentes ia |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 10 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springframework.boot:spring-boot-starter-aop n/a
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.9.1
- org.postgresql:postgresql n/a
- org.projectlombok:lombok n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.mockito:mockito-core n/a
- org.testcontainers:postgresql n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Identificación de Puntos de Optimización**: Documento que lista las áreas identificadas para optimización.
- **Fase 2 — Implementación de Optimizaciones**: Componente de IA optimizado y documentación del proceso de optimización.
- **Fase 3 — Evaluación y Documentación de Resultados**: Reporte de evaluación que incluye resultados de pruebas de carga, comparación de rendimiento y propuestas de mejora.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (30)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/AnalyticsServiceAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/TransactionController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/TransactionController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/optimizacionia/Application.java` — `ApplicationProperties.getThreadPool`
      Se invoca `getThreadPool` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/Application.java` — `ApplicationProperties.getLatencyThreshold`
      Se invoca `getLatencyThreshold` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/dto/TransactionResponse.java` — `Transaction.type`
      Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/dto/TransactionResponse.java` — `Transaction.amount`
      Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCase.java` — `Transaction.type`
      Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCase.java` — `Transaction.amount`
      Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCase.java` — `AnalyticsServicePort.analyze`
      Se invoca `analyze` sobre `AnalyticsServicePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `Transaction.type`
      Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `Transaction.amount`
      Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.getId`
      Se invoca `getId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.setType`
      Se invoca `setType` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.setAmount`
      Se invoca `setAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.setCreatedAt`
      Se invoca `setCreatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.setIsHighValue`
      Se invoca `setIsHighValue` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.getType`
      Se invoca `getType` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.getAmount`
      Se invoca `getAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java` — `TransactionEntity.getIsHighValue`
      Se invoca `getIsHighValue` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/TransactionController.java` — `TransactionRequest.transactionId`
      Se invoca `transactionId` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/TransactionController.java` — `TransactionRequest.amount`
      Se invoca `amount` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/TransactionController.java` — `TransactionRequest.transactionType`
      Se invoca `transactionType` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCaseTest.java` — `AnalyticsServicePort.analyzeTransaction`
      Se invoca `analyzeTransaction` sobre `AnalyticsServicePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCaseTest.java` — `AnalyticsResult.riskScore`
      Se invoca `riskScore` sobre `AnalyticsResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/optimizacionia/infrastructure/adapters/AnalyticsServiceAdapterTest.java` — `AnalyticsResult.riskScore`
      Se invoca `riskScore` sobre `AnalyticsResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (20)

- `pom.xml`
- `src/main/java/com/pragma/optimizacionia/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/optimizacionia/domain/model/Transaction.java`
- `src/main/java/com/pragma/optimizacionia/domain/model/AnalyticsResult.java`
- `src/main/java/com/pragma/optimizacionia/domain/ports/in/ProcessTransactionPort.java`
- `src/main/java/com/pragma/optimizacionia/domain/ports/out/TransactionRepositoryPort.java`
- `src/main/java/com/pragma/optimizacionia/domain/ports/out/AnalyticsServicePort.java`
- `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/dto/TransactionRequest.java`
- `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/dto/TransactionResponse.java`
- `src/main/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCase.java`
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/TransactionRepositoryAdapter.java`
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters/AnalyticsServiceAdapter.java`
- `src/main/java/com/pragma/optimizacionia/infrastructure/config/Resilience4jConfig.java`
- `src/main/java/com/pragma/optimizacionia/infrastructure/controllers/TransactionController.java`
- `src/main/java/com/pragma/optimizacionia/infrastructure/config/OpenApiConfig.java`
- `src/main/java/com/pragma/optimizacionia/infrastructure/exception/GlobalExceptionHandler.java`
- `src/main/java/com/pragma/optimizacionia/infrastructure/exception/ResourceOptimizationException.java`
- `src/test/java/com/pragma/optimizacionia/application/usecases/TransactionProcessingUseCaseTest.java`
- `src/test/java/com/pragma/optimizacionia/infrastructure/adapters/AnalyticsServiceAdapterTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/optimizacionia`
- `src/main/java/com/pragma/optimizacionia/domain`
- `src/main/java/com/pragma/optimizacionia/domain/model`
- `src/main/java/com/pragma/optimizacionia/domain/ports`
- `src/main/java/com/pragma/optimizacionia/application`
- `src/main/java/com/pragma/optimizacionia/application/usecases`
- `src/main/java/com/pragma/optimizacionia/infrastructure`
- `src/main/java/com/pragma/optimizacionia/infrastructure/adapters`
- `src/main/java/com/pragma/optimizacionia/infrastructure/config`
- `src/main/java/com/pragma/optimizacionia/infrastructure/controllers`
- `src/main/resources`
- `src/test/java/com/pragma/optimizacionia`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced L2
- Brecha que el reto ataca: APLICA prácticas y estrategias que optimizan el uso de recursos con agentes IA., APLICA prácticas y estrategias que optimizan el uso de recursos con agentes IA.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
