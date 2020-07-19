package com.lhuang.testparse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
