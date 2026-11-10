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