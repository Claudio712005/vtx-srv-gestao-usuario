package br.com.claus.vtx.srv.gestao.usuario.config.cache.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.ObjectMapper;

@Configuration
@ConditionalOnProperty(value = "spring.redis.enabled", havingValue = "true", matchIfMissing = true)
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory connectionFactory,
            ObjectMapper objectMapper
    ){
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        template.setConnectionFactory(connectionFactory);

        GenericJacksonJsonRedisSerializer jsonSerializer =
                new GenericJacksonJsonRedisSerializer(objectMapper);

        template.setKeySerializer(
                new StringRedisSerializer());

        template.setHashKeySerializer(
                new StringRedisSerializer());

        template.setValueSerializer(jsonSerializer);

        template.setHashValueSerializer(jsonSerializer);

        template.afterPropertiesSet();

        return template;
    }
}
