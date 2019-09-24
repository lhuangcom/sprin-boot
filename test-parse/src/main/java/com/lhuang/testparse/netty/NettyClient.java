package com.lhuang.testparse.netty;

import com.lhuang.testparse.netty.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author LHuang
 * @since 2019/5/9
 */
@Slf4j
public class NettyClient {

    private static int MAX_RETRY = 5;


    public static void main(String[] args) throws InterruptedException {
        
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup output = new NioEventLoopGroup();

        bootstrap.group(output)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                        nioSocketChannel.pipeline().addLast(new ClientHandler());
                    }
                });


        bind(bootstrap,"127.0.0.1",8000,MAX_RETRY);

        //Channel channel = bootstrap.connect("127.0.0.1",8000).channel();

        /*while (true){
            channel.writeAndFlush(new Date()+"---send  a message");
            TimeUnit.SECONDS.sleep(5);
        }*/

    }


    public static void bind(Bootstrap bootstrap,String host, int hostPort,int retry){

        bootstrap.connect(host,hostPort).addListener(future -> {
            if (future.isSuccess()){
              log.info("连接成功");
            }else if (retry == 0){
                log.error("可供重连次数已用完，连接失败");
            }
            else {
                //第几次重连
                int order = (MAX_RETRY - retry )+1;
                int delay = 1 << order;
                log.error("第"+order+"次连接失败,+"+delay+"秒后尝试重连");
                bootstrap.config().group().schedule(()->{
                    bind(bootstrap,host,hostPort,retry-1);
                },delay,TimeUnit.SECONDS);

            }
        }).channel();





    }


}
