package com.lhuang.blog.user;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.lhuang.blog.user.api.exception.BaseException;
import com.lhuang.blog.user.api.pojo.User;
import com.lhuang.blog.user.api.thread.ThreadPoolSingleton;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.apache.commons.io.Charsets;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {

        //基本数据类型不能通过构造器反射生成
        final Map<Class<?>, Object> DEFAULT_VALUES = Stream
                .of(boolean.class, byte.class, char.class, double.class, float.class, int.class, long.class, short.class)
                .collect(toMap(clazz -> (Class<?>) clazz, clazz -> Array.get(Array.newInstance(clazz, 1), 0)));


        int[] x = {1,2,3};
        Object o = Array.newInstance(boolean.class,x);
        boolean[][][] o1 = (boolean[][][]) o;
        BigDecimal decimal = new BigDecimal(Double.toString(100));
        BigDecimal bigDecimal = new BigDecimal("100");

        List<Integer> list = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toCollection(ArrayList::new));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("start");
        //查询实体的内存占用大小
        System.out.println(ObjectSizeCalculator.getObjectSize(decimal));
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

        RuntimeException runtimeException = new RuntimeException();
        runtimeException.addSuppressed(new RuntimeException());

    }


    @Test
    public void testThreadPool() throws InterruptedException {




        System.out.println("//按照用户名分组，统计下单数量");
        //根据map的value比较顺序
        System.out.println(Lists.newArrayList(new User()).stream().collect(Collectors.groupingBy(User::getUsername, Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).collect(Collectors.toList()));
        //Collectors.averagingInt()
        Collectors.joining(",","[","}");
        //Collectors.summingInt()
        System.out.println(ThreadPoolSingleton.INSTANCEE.getInstance().submit(()->{
            System.out.println("执行任务了");
        }));
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 10000000, 0.01);

        System.out.println(ThreadPoolSingleton.INSTANCEE.getInstance());

    }

    @Test
    public void testRedisLock(){
        System.out.println(redisTemplate.opsForValue().setIfAbsent("key","value",2,TimeUnit.SECONDS));
        redisTemplate.delete("key");
        System.out.println(redisTemplate.opsForValue().setIfAbsent("key","value",2,TimeUnit.SECONDS));


    }

    @Test
    public void filesExample() throws IOException {
        //无限深度，递归遍历文件夹
        try (Stream<Path> pathStream = Files.walk(Paths.get("."))) {
            pathStream.filter(Files::isRegularFile) //只查普通文件
                    .filter(FileSystems.getDefault().getPathMatcher("glob:**/*.java")::matches) //搜索java源码文件
                    .flatMap(ThrowingFunction.unchecked(path ->
                            Files.readAllLines(path).stream() //读取文件内容，转换为Stream<List>
                                    .filter(line -> Pattern.compile("public class").matcher(line).find()) //使用正则过滤带有public class的行
                                    .map(line -> path.getFileName() + " >> " + line))) //把这行文件内容转换为文件名+行
                    .forEach(System.out::println); //打印所有的行
        }

    }

    @FunctionalInterface
    public interface ThrowingFunction<T, R, E extends Throwable> {
        static <T, R, E extends Throwable> Function<T, R> unchecked(ThrowingFunction<T, R, E> f) {
            return t -> {
                try {
                    return f.apply(t);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            };
        }

        R apply(T t) throws E;
    }





}

