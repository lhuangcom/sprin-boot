package com.lhuang.testparse.best_singleton;

/**
 * 枚举的单例模式的枚举实现方式
 * 避免了反序列化破坏单例
 * @author lhunag
 * date 2019/8/13
 */
public enum Singleton {

    INSTANCE;

    public String test() {
        System.out.println("加载实例类");
        return super.toString();
    }
}
