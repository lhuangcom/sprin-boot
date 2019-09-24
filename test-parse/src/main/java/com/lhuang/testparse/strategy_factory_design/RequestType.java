package com.lhuang.testparse.strategy_factory_design;

/**
 * @author LHuang
 * @since 2019/4/16
 */
public enum RequestType {
    //退出命令
    EXIT("exit"),
    //打印信息
    ECHOINFO("echoInfo"),
    //打印所有信息
    PRINTALL("printAll");

    private final String messsage;

    RequestType( final String message){
        this.messsage = message;
    }

    public String getMesssage() {
        return messsage;
    }






}
