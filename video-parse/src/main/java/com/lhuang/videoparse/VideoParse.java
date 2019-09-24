package com.lhuang.videoparse;


import com.lhuang.mediadataparse.MediaData;
import com.lhuang.mediadataparse.Parse;
import com.lhuang.mediadataparse.ParseManager;

import java.util.Arrays;

public class VideoParse implements Parse {

    public final byte[] FORMAT = "MP4".getBytes();

    public final int FORMAT_LENGTH = FORMAT.length;

    static
    {
        try
        {
            ParseManager.registerParse(new VideoParse());
            System.out.println("video初始化");
        }
        catch (Exception e)
        {
            throw new RuntimeException("Can't register parser!");
        }
    }

    private boolean isDataCompatible(byte[] data) {
        byte[] format = Arrays.copyOfRange(data, 0, FORMAT_LENGTH);
        return Arrays.equals(format, FORMAT);
    }


    @Override
    public MediaData parse(byte[] data) throws Exception {
        if (!isDataCompatible(data)) {
            throw new Exception("data format is wrong.");
        }
        //parse data by mp3 format type
        return new MediaData("陈楚生", "mp4", "《有没有人曾告诉你》", 320L);
    }
}
