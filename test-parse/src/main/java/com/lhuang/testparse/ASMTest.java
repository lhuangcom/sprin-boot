package com.lhuang.testparse;

import com.lhuang.testparse.api.pojo.User;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.collections.CollectionUtils;
import org.objectweb.asm.*;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Supplier;

/**
 * @author LHuang
 * @since 2019/6/27
 */
public class ASMTest {



    public static void main(String[] args) throws NoSuchMethodException, IOException {
        User user = new User();
        User user1 = user;
        user = null;
        System.out.println(user1);

        System.out.println(Objects.equals(null,null));
        List<String> list = Arrays.asList(
                new String("张三"),
                new String("李四"),
                new String("王五"),
                new String("赵六"));
        list.forEach(System.out::println);

        /*Method method = ClientHandler.class.getDeclaredMethod("channelActive", ChannelHandlerContext.class);
        System.out.println(Arrays.toString(getMethodParamNames(method)));*/
    }




    public static String[] getMethodParamNames(final Method method) throws IOException {

        final int methodParameterCount =  method.getParameterTypes().length;
        final String[] methodParametersNames = new String[methodParameterCount];
        ClassReader cr = new ClassReader(method.getDeclaringClass().getName());
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        cr.accept(new ClassAdapter(cw) {

            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                final Type[] argTypes = Type.getArgumentTypes(desc);

                //参数类型不一致
                if (!method.getName().equals(name) || !matchTypes(argTypes,  method.getParameterTypes())) {
                    return mv;
                }

                return new MethodAdapter(mv) {
                    @Override
                    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {

                        //如果是静态方法，第一个参数就是方法参数，非静态方法，则第一个参数是 this, 然后才是方法的参数
                        int methodParameterIndex = Modifier.isStatic(method.getModifiers()) ? index : index - 1;
                        if (0 <= methodParameterIndex && methodParameterIndex < methodParameterCount) {
                            methodParametersNames[methodParameterIndex] = name;
                        }
                        super.visitLocalVariable(name, desc, signature, start, end, index);
                    }
                };
            }
        }, 0);
        return methodParametersNames;
    }

    /**
     * 比较参数是否一致
     */
    private static boolean matchTypes(Type[] types, Class<?>[] parameterTypes) {
        if (types.length != parameterTypes.length) {
            return false;
        }

        for (int i = 0; i < types.length; i++) {
            if (!Type.getType(parameterTypes[i]).equals(types[i])) {
                return false;
            }
        }


        return true;
    }
}
