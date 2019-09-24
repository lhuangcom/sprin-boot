package com.lhuang.testparse.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author LHuang
 * @since 2019/5/9
 */
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        log.info(new Date()+": 客户端写出数据");

        ByteBuf byteBuf = getByteBuf(ctx);

        ctx.channel().writeAndFlush(byteBuf);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        log.info("客户端收到的数据："+byteBuf.toString(Charset.forName("utf-8")));
    }


    /**
     * 生产数据
     * @param ctx
     * @return
     */
    private  ByteBuf getByteBuf(ChannelHandlerContext ctx){


        //获取二进制抽象ByteBuf
        ByteBuf byteBuf = ctx.alloc().buffer();

        //准备数据
        byte[] bytes = "你好，netty".getBytes(Charset.forName("utf-8"));

        //填充数据到ByteBuf
        byteBuf.writeBytes(bytes);

        return byteBuf;

    }
}
