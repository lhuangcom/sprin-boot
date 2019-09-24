package com.lhuang.testparse.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     *
     * CachingConfigurerSupport重写keyGenerator()解决了使用@Cache的key生成问题（使用方法参数组合生成的一个缓存数据的key，
     * 如果2个方法，参数是一样的，但执行逻辑不同，那么将会导致执行第二个方法时命中第一个方法的缓存），
     * 缓存的key是包名+方法名+参数列表
     *
     * */
    @Bean
    @Override
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method,Object... params){
              StringBuilder sb = new StringBuilder();
              sb.append(target.getClass().getName());
              // sb.append(method.getName());
              for (Object object:params){
                  sb.append(object.toString());
              }
              return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                // 设置缓存有效期一小时
                .entryTtl(Duration.ofHours(1L))
                // 不缓存空值
                .disableCachingNullValues();

        //此处应用的数值可以在yaml文件配置，再再这里读取
        //设置一个初始化的缓存空间set集合
        Set<String> cacheNames = new HashSet<>();
        cacheNames.add("redis_cache1");
        cacheNames.add("redis_cache2");

        //对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
        configurationMap.put("redis_cache1",redisCacheConfiguration);
        configurationMap.put("redis_cache2",redisCacheConfiguration.entryTtl(Duration.ofSeconds(120L)));

        return RedisCacheManager
                .builder(redisConnectionFactory)
                //一定要先调用该方法设置初始化的缓存名
                .initialCacheNames(cacheNames)
                //再初始化相关的配置
                .withInitialCacheConfigurations(configurationMap)
                .cacheDefaults(redisCacheConfiguration).build();
    }

    /**
     * retemplate相关配置
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate redisTemplate = new RedisTemplate();

        redisTemplate.setConnectionFactory(factory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // 值采用json序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return  redisTemplate;
    }

}
