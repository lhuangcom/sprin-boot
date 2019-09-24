package com.lhuang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLogApplicationTests {

	@Test
	public void contextLoads() {


		Integer  num = 1;
		int[] a= new int[10];
		Arrays.fill(a,10);
		System.out.println(Arrays.toString(a));
		Double minValue =Stream.of(-2.0).reduce(Double.MIN_VALUE,Double::max);
		Stream.of(1, 2, 3, 4).reduce(Integer::sum);
		System.out.println(minValue);

	}

}

interface Formula{

	double calculate(int a);

	default double sqrt(int a) {
		return Math.sqrt(a);
	}

	default double sqrt1(int a) {
		return Math.sqrt(a);
	}
}
