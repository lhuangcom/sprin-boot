package com.lhuang.testparse.best_singleton;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author lhunag
 * date 2019/9/3
 */
public class SingletonTest {

    @Test
    public void test1() {
        System.out.println(Singleton.INSTANCE.test());
    }
}