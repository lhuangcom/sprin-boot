package com.lhuang.testparse;

import com.lhuang.testparse.controller.String_To_Date_Controller;
import com.lhuang.testparse.controller.TestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author lhunag
 * date 2019/8/13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestControllerTest {

    @Value("${info.name}")
    private String name;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private MockMvc mockMvc;

    @Before
    public void setMockMvc(){
        mockMvc = MockMvcBuilders.standaloneSetup(new String_To_Date_Controller()).build();

    }

    @Test
    public void testDateForm() throws Exception {
/*
        System.out.println("监听发布器---"+applicationEventPublisher);
*/

        /*System.out.println("打印名字---"+name);
        ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");

        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(),zoneId);
        System.out.println(zonedDateTime);

        zonedDateTime = ZonedDateTime.parse("2015-05-03T10:15:30+08:00[Asia/Shanghai]");

        System.out.println(zonedDateTime);*/

      /*LocalDateTime.now()
                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                .withLocale(Locale.CHINESE));*/


        //ZonedDateTime zonedDateTime = ZonedDateTime.parse("2015-05-03T10:15:30+01:00[Aisa/Shanghai]");




        mockMvc.perform(MockMvcRequestBuilders.get("/date")
                .param("date","2018-12-05")
                .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}