package com.lhuang.mediadataparse;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParseManager {

    private static final CopyOnWriteArrayList<ParseInfo> registerParses = new CopyOnWriteArrayList<>();

    static {
        System.out.println("mediadata初始化");
        loadInitialParses();
        System.out.println("registerParses的数量"+registerParses.size());

    }

    private static void loadInitialParses(){
        ServiceLoader<Parse> loaderParses = ServiceLoader.load(Parse.class);
        Iterator parseIterator = loaderParses.iterator();

        //这里的作用是调用实现类完成实现类的初始化和注入
        while (parseIterator.hasNext()){
            parseIterator.next();
        }

    }

    public static void registerParse(Parse parse){
        registerParses.add(new ParseInfo(parse));
    }

    public static MediaData getMediaData(byte[] data) throws Exception {
        for (ParseInfo parseInfo : registerParses){
            MediaData mediaData = parseInfo.parse.parse(data);

            if (mediaData != null){
                return mediaData;
            }
        }

        throw  new ParserNotFoundException("10001", "Can not find corresponding data:" + new String(data));
    }
}
