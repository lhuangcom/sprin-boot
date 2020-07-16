package com.lhuang.blog.user.api.pojo;
import com.google.common.collect.Lists;
import com.lhuang.blog.user.api.pojo.EmailNoAuth;
import com.lhuang.blog.user.api.pojo.GenderEnum;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.util.stream.Collectors.toMap;

@Data
public class User implements Comparable<User>{
    private String id;

    private String username;

    private String password;

    private String email;

    private GenderEnum sex;

    private EmailNoAuth ext_json;


    public static void main(String[] args) throws IllegalAccessException, IOException, URISyntaxException {

        FileSystem fileSystem = FileSystems.getDefault();
        FileSystemProvider provider = fileSystem.provider();
        System.out.println("Provider: " + provider.toString());

        String format = String.format("%-10s", "123").replace(' ', '_');
        System.out.println(format);
        format = String.format("%05d", 123);
        System.out.println(format);

        //fileIOTest();
        URL url = new URL("https://img-blog.csdnimg.cn/20190918140129601.png");
        UrlResource  urlResource = new UrlResource(url);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(urlResource.getInputStream());
        byte[] bytes = new byte[1024];
        //int read = bufferedInputStream.read(bytes);
        while (bufferedInputStream.read(bytes) > 0){
            System.out.println(bytes.length);

        }

        //;ByteArrayResource








    }/**/

    private static void ReflectTest() throws IllegalAccessException {
        User user =  new User();
        user.setId("123");
        user.setUsername("123");
        user.setPassword("123");
        user.setEmail("123");
        user.setSex(GenderEnum.MALE);
        user.compareTo(user);

        Field[] declaredFields = user.getClass().getDeclaredFields();


        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String name = declaredField.getName();

            System.out.println(name+":"+ declaredField.get(user));
        }
        try {
            User.class.getDeclaredMethod("compareTo", User.class).invoke(new User(),new User());
        } catch (InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private static void fileIOTest() throws IOException {
        Files.write(Paths.get("demo.txt"), "hello".getBytes(Charset.forName("utf-8")));
        Files.write(Paths.get("demo.txt"), "hello".getBytes(Charset.forName("utf-8")), StandardOpenOption.APPEND);
        LongAdder longAdder = new LongAdder();
        try (Stream<String> lines = Files.lines(Paths.get("demo.txt"),Charset.forName("utf-8"))){
            lines.forEach(line-> {
                longAdder.increment();
                System.out.println(line + longAdder.intValue());
            });
        }

        //缓存读
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("demo.txt"));
        char[] chars = new char[5];
        int read;
        while ( (read = bufferedReader.read(chars)) != -1 ){
            System.out.println(chars);
        }

        // DMA（直接内存访问）
        FileChannel in = FileChannel.open(Paths.get("demo.txt"), StandardOpenOption.READ);
        FileChannel out = FileChannel.open(Paths.get("dest.txt"), CREATE, WRITE);
        in.transferTo(0, in.size(), out);


    }


    @Override
    public int compareTo(User o) {
        return Comparator.comparing(User::getUsername)
                .thenComparing(User::getPassword)
                .compare(this, o);
    }
}