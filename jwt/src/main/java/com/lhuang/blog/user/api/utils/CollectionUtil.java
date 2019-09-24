package com.lhuang.blog.user.api.utils;

import java.util.Collection;

public class CollectionUtil {

    public static Boolean isEmpty(Collection<?> collection){
        return collection == null || collection.size() == 0;
    }
}
