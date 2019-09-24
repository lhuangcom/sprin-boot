package com.lhuang.testparse;

import com.lhuang.mediadataparse.MediaData;
import com.lhuang.mediadataparse.ParseManager;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.util.*;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * SPI模式测试模块
 */
public class TestMp4Parser {

    @Autowired
    private CacheManager cacheManager;

    public static void main(String[] args) throws Exception {

    }


    public static void testMp4Parser()  {

        MediaData video = null;
        try {
            video = ParseManager.getMediaData(mockSongData("MP4"));
            System.out.println("------------------------");
            System.out.println("Name:" + video.getName());
            System.out.println("Author:" + video.getAuthor());
            System.out.println("Time:" + video.getTime());
            System.out.println("Format:" + video.getFormat());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static byte[] mockSongData(String formatType) {
        return new String(formatType).getBytes();
    }
}
