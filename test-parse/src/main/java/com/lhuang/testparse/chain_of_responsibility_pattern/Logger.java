package com.lhuang.testparse.chain_of_responsibility_pattern;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author lhuang
 * @version 1.0
 * @date 2019-10-29 14:43
 */
@FunctionalInterface
public interface Logger {

     enum LogLevel{

         INFO, DEBUG, WARNING, ERROR, FUNCTIONAL_MESSAGE, FUNCTIONAL_ERROR;

         public static LogLevel[] all(){
             return values();
         }

    }

     void message(String msg,LogLevel security);

     default Logger appendNext(Logger nextLogger){

         return (msg,security)->{

             message(msg,security);
             nextLogger.message(msg,security);
         };
     }

     static Logger logger(LogLevel[] logLevels, Consumer<String> writeMessage){
         EnumSet<LogLevel> enumSet = EnumSet.copyOf(Arrays.asList(logLevels));
         return (msg,security)->{
             if (enumSet.contains(security)){
                 writeMessage.accept(msg);
             }
         };

     }

    static Logger consoleLogger(LogLevel... levels) {
        return logger(levels, msg -> System.err.println("写到终端: " + msg));
    }

    static Logger emailLogger(LogLevel... levels) {
        return logger(levels, msg -> System.err.println("通过邮件发送: " + msg));
    }

    static Logger fileLogger(LogLevel... levels) {
        return logger(levels, msg -> System.err.println("写到日志文件中: " + msg));
    }

    public static void main(String[] args) {
        /**
         * 构建一个固定顺序的链 【终端记录——邮件记录——文件记录】
         * consoleLogger：终端记录，可以打印所有等级的log信息
         * emailLogger：邮件记录，打印功能性问题 FUNCTIONAL_MESSAGE 和 FUNCTIONAL_ERROR 两个等级的信息
         * fileLogger：文件记录，打印 WARNING 和 ERROR 两个等级信息
         */

        Logger logger = consoleLogger(LogLevel.all())
                .appendNext(emailLogger(LogLevel.FUNCTIONAL_MESSAGE, LogLevel.FUNCTIONAL_ERROR))
                .appendNext(fileLogger(LogLevel.WARNING, LogLevel.ERROR));

        // consoleLogger 可以记录所有 level 的信息
        logger.message("进入到订单流程，接收到参数，参数内容为XXXX", LogLevel.DEBUG);
        logger.message("订单记录生成.", LogLevel.INFO);

        // consoleLogger 处理完，fileLogger 要继续处理
        logger.message("订单详细地址缺失", LogLevel.WARNING);
        logger.message("订单省市区信息缺失", LogLevel.ERROR);

        // consoleLogger 处理完，emailLogger 继续处理
        logger.message("订单短信通知服务失败", LogLevel.FUNCTIONAL_ERROR);
        logger.message("订单已派送.", LogLevel.FUNCTIONAL_MESSAGE);

        //lambda 责任链模式
        UnaryOperator<String> headerProcessing = (str)-> "header process"+str;
        UnaryOperator<String> spellCheckProcessing = (str)->str.replace("lab","666");
        Function<String,String> function = headerProcessing.andThen(spellCheckProcessing);
        System.out.println(function.apply("lab123"));
    }
}

