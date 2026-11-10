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