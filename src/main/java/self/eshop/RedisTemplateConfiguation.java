package self.eshop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Component
public class RedisTemplateConfiguation {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfig(1000))
                .withInitialCacheConfigurations(initCacheManager())
                .transactionAware()
                .build();
        return cacheManager;
    }

    private Map<String, RedisCacheConfiguration> initCacheManager() {
            Map<String,RedisCacheConfiguration>  cacheConfigurationMap = new HashMap<String, RedisCacheConfiguration>();
            cacheConfigurationMap.put("productCategory",this.defaultCacheConfig(100));
            return cacheConfigurationMap;
    }

    private RedisCacheConfiguration defaultCacheConfig(int second){

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(second)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(getJackson2JsonRedisSerializer()));
        return config;
    }


    /*
    public RedisTemplate<String,?> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, ?> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer valueSerializer = getJackson2JsonRedisSerializer();
        template.setValueSerializer(valueSerializer);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }*/

    private Jackson2JsonRedisSerializer getJackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer valueSerializer =new Jackson2JsonRedisSerializer<Object>(Object.class);

        ObjectMapper om = new ObjectMapper();

        //指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和?public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

        ObjectMapper objectMapper = om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Date.class, new JodaDateJsonSerializer());
        simpleModule.setMixInAnnotation(Date.class, JodaDateJsonDeserializer.class);
        simpleModule.addDeserializer(Date.class, new JodaDateJsonDeserializer());
        om.registerModule(simpleModule);
        valueSerializer.setObjectMapper(om);
        return valueSerializer;
    }


    class JodaDateJsonSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gen.writeString(fmt.format(date));
        }
        @Override
        public void serializeWithType(Date date, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer)
                throws IOException {
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gen.writeString(fmt.format(date));
        }
    }

    class JodaDateJsonDeserializer extends JsonDeserializer<Date> {
        Logger logger = LoggerFactory.getLogger(JodaDateJsonDeserializer.class);

        @Override
        public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            Date result = null;
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                result = fmt.parse(p.readValueAs(String.class));
            } catch (ParseException e) {
                logger.error("转换成日期时,错误" + e.getMessage(), e);
            }
            return result;
        }

        @Override
        public Date deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer)
                throws IOException {
            Date result = null;
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                result = fmt.parse(p.readValueAs(String.class));
            } catch (ParseException e) {
                logger.error("转换成日期时错误" + e.getMessage(), e);
            }
            return result;
        }

    }

}