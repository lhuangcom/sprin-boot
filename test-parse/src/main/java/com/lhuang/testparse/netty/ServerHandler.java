package com.lhuang.testparse.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author LHuang
 * @since 2019/5/9
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        log.info("服务端收到的数据："+byteBuf.toString(Charset.forName("utf-8")));

        ByteBuf sendContent = getByteBuf(ctx);

        ctx.channel().writeAndFlush(sendContent);


    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){


        //获取二进制抽象ByteBuf
        ByteBuf byteBuf = ctx.alloc().buffer();

        //准备数据
        byte[] bytes = "欢迎关注本服务器".getBytes(Charset.forName("utf-8"));

        //填充数据到ByteBuf
        byteBuf.writeBytes(bytes);

        return byteBuf;

    }
}
