package com.neo.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * Created by asus on 2018/8/6.
 */
public class BitAndWildPermission implements PermissionResolver{

    @Override
    public Permission resolvePermission(String s) {
        if(s.startsWith("+")) {
            return new BitPermission(s);
        }
        return new WildcardPermission(s);

    }
}
