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