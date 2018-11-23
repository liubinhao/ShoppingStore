package com.shop.dev.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.text.SimpleDateFormat;

// 相当于 spring 中的 xml配置文件
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<Object,Object> template =
                new RedisTemplate<>();
        // 给 template设置一个连接工厂, 外层的方法参数列表注入加一个 连接工厂(该工厂直接从redis 容器中获取)
        template.setConnectionFactory(factory);
        // 对key进行序列化
        // 对value进行序列化
        Jackson2JsonRedisSerializer<Object> valueSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // 定制Jackson,供 key 和 value 序列化
        ObjectMapper mapper = new ObjectMapper();
        // 在序列化过程当中,如果遇到时间类型,转换成下面设置的时间格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        // TODO: 百度查 对象的空属性是否序列化,转换成更美观的Json格式
        valueSerializer.setObjectMapper(mapper);
        // key 和 value 开始设置定制好的 Jackson 序列化.
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(valueSerializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        // 在属性配置之后也加载其他配置, 即在 Application.yml 中的配置也会加载.
        template.afterPropertiesSet();
        return template;
    }
}
