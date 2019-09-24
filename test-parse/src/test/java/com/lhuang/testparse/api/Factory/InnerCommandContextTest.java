package com.lhuang.testparse.api.Factory;

import com.lhuang.testparse.strategy_factory_design.InnerCommand;
import com.lhuang.testparse.strategy_factory_design.InnerCommandContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author LHuang
 * @since 2019/4/16
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class InnerCommandContextTest {

    @Test
    public void getInstance() {

        InnerCommand innerCommand = InnerCommandContext.getInstance("exit");
        innerCommand.process("");

        //报错，不宜通过为止命令生成枚枚举实例；
        //RequestType.valueOf("");
    }
}