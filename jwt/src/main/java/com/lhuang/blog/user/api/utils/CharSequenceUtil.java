package com.lhuang.blog.user.api.utils;

import org.apache.commons.lang3.StringUtils;

public class CharSequenceUtil {

    public static Boolean isEmpty(CharSequence charSequence){
        return StringUtils.isBlank(charSequence);
    }
}
