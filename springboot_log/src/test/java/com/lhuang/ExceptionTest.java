package com.lhuang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhunag
 * date 2020/1/30
 */
public class ExceptionTest {
     class OOMObject{

    }

    @Test
    public void HeapOOMExceptionTest(){
        List<OOMObject> list = new ArrayList<>();
        for (;;){
            list.add(new OOMObject());
        }
    }
}
