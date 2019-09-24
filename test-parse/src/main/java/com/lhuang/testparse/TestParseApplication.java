package com.lhuang.testparse;

import com.lhuang.testparse.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TestParseApplication /*implements CommandLineRunner*/ {



    public static void main(String[] args) {
        //非web方式启动
        //new SpringApplicationBuilder(TestParseApplication.class).web(WebApplicationType.NONE).run(args);

        SpringApplication.run(TestParseApplication.class, args);
    }



    /*@Override
    public void run(String... args) throws Exception {

        System.out.println("非web启动在此处理业务的开始");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("开始休眠");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }*/
}
