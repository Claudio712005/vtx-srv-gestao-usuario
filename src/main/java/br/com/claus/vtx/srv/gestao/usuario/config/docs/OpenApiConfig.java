package br.com.claus.vtx.srv.gestao.usuario.config.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(value = "springdoc.api-docs.enabled", havingValue = "true", matchIfMissing = true)
public class OpenApiConfig {

    private final Environment environment;

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            String appName = environment.getProperty("spring.application.name", "Application");
            String appVersion = environment.getProperty("spring.application.version", "1.0.0");
            openApi.info(new Info()
                    .title(appName)
                    .version(appVersion)
                    .description("API documentation for " + appName));

            sortSchemas(openApi);
        };
    }

    public void sortSchemas(OpenAPI openApi) {
        if (openApi.getComponents() != null && openApi.getComponents().getSchemas() != null) {
            Map<String, Schema> schemas = openApi.getComponents().getSchemas()
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(java.util.stream.Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue,
                            LinkedHashMap::new
                    ));

            openApi.getComponents().setSchemas(schemas);
        }
    }
}
