# Optimización de Recursos en Componentes de IA

En el dominio de la banca, la implementación de componentes de Inteligencia Artificial (IA) debe ser eficiente en el uso de recursos para asegurar que el sistema pueda manejar picos de carga y mantener una latencia aceptable. El objetivo es aplicar prácticas y estrategias que optimicen el uso de recursos en estos componentes. Los componentes de IA interactúan con el sistema de procesamiento de transacciones (STP) y con el servicio de analíticas de cliente (SAC). El STP envía solicitudes de procesamiento a la IA, mientras que el SAC consume los resultados para generar informes. La optimización debe considerar el rendimiento bajo carga máxima (10,000 transacciones por segundo) y asegurar que la latencia no supere los 200 milisegundos.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | optimización de componentes ia |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 10 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Identificación de Puntos de Optimización

**Objetivo:** Identificar áreas del componente de IA que pueden ser optimizadas para mejorar el uso de recursos.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Analiza el componente de IA y determina cuáles son las operaciones más costosas en términos de recursos.
- Identifica al menos tres áreas que podrían ser optimizadas.

**Entregable:** Documento que lista las áreas identificadas para optimización.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el uso de memoria y CPU en las operaciones del componente.
- Evalúa el impacto de cada operación en la latencia del sistema.

</details>

### Fase 2: Implementación de Optimizaciones

**Objetivo:** Aplicar estrategias de optimización en las áreas identificadas.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Selecciona una de las áreas identificadas y aplica una estrategia de optimización.
- Documenta el proceso y los resultados obtenidos.

**Entregable:** Componente de IA optimizado y documentación del proceso de optimización.

<details>
<summary>Pistas de conocimiento</summary>

- Considera técnicas como la reducción de dimensionalidad, el uso de algoritmos más eficientes o la paralelización de tareas.
- Mide el impacto de la optimización en el uso de recursos y la latencia.

</details>

### Fase 3: Evaluación y Documentación de Resultados

**Objetivo:** Evaluar el impacto de las optimizaciones y documentar los resultados.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Realiza pruebas de carga en el componente optimizado y mide el rendimiento.
- Documenta los resultados y compara con el rendimiento antes de la optimización.
- Identifica posibles mejoras adicionales.

**Entregable:** Reporte de evaluación que incluye resultados de pruebas de carga, comparación de rendimiento y propuestas de mejora.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de pruebas de carga para simular condiciones de pico.
- Documenta claramente los resultados y las mejoras observadas.
- Propone al menos una mejora adicional que podría ser implementada.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las optimizaciones de recursos en componentes de IA y por qué son importantes en el dominio de la banca?
- **paraQueSirve**: ¿Cómo se aplican las optimizaciones de recursos para mejorar el rendimiento del componente de IA en el sistema de banca?
- **comoSeUsa**: ¿Qué estrategias de optimización se pueden aplicar en el componente de IA y cómo se implementan?
- **erroresComunes**: ¿Cuáles son los errores comunes al optimizar componentes de IA y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones debes tomar al elegir una estrategia de optimización para el componente de IA?

## Criterios de Evaluacion

- Identificación correcta de áreas para optimización.
- Aplicación efectiva de estrategias de optimización.
- Documentación clara del proceso y resultados de la optimización.
- Evaluación y comparación del rendimiento antes y después de la optimización.
- Propuesta de mejoras adicionales fundamentadas.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
