package br.com.claus.vtx.srv.gestao.usuario.config.cache.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.redis.enabled", havingValue = "true", matchIfMissing = true)
public class RedisConfig {

    @Bean
    public JedisConnectionFactory redisConnectionFactory(
            @Value("${spring.redis.host:localhost}") String host,
            @Value("${spring.redis.port:6379}") int port
    ){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);

        JedisClientConfiguration client = JedisClientConfiguration.builder()
                .readTimeout(Duration.ofSeconds(5))
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        return new JedisConnectionFactory(
                configuration,
                client
        );
    }
}
