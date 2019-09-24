package com.lhuang.testparse.random_algorithm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Random算法不能还原上一个随机数
 *
 * @author lhunag
 * date 2019/8/16
 */
public class Randoms {


    public static void main(String[] args) {
        //获取系统当前时间
        Calendar ca  = Calendar.getInstance();
        int i;
        //将随机数的种子设置为当前系统时间的分*秒
        Random random = new Random(ca.get(Calendar.MINUTE) * ca.get(Calendar.SECOND));
        //参数是随机数最大不超过得值
        i=random.nextInt(Integer.MAX_VALUE);

        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        //shuffle算法
        Collections.shuffle(new ArrayList<>());

    }

}
