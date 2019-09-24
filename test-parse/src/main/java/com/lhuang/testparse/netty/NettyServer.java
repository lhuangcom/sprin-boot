package com.lhuang.testparse.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/9
 */
@Slf4j
public class NettyServer {

    public static void  main(String[] args){

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                       // nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new ServerHandler());
                }});

        bind(serverBootstrap,8000);
    }

    public static void bind(ServerBootstrap serverBootstrap,int hostPort){

        serverBootstrap.bind(hostPort).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()){
                    log.info(hostPort+" 端口绑定成功");
                }
                else {
                    log.info(hostPort+" 端口绑定失败");
                    bind(serverBootstrap,hostPort+1);
                }
            }
        });




    }


}
