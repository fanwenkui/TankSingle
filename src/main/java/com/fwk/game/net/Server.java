package com.fwk.game.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server {
    private ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public void start() {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup(2);
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            ChannelFuture future = bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer())
                    .bind(8888)
                    .sync();

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ServerChannelInitializer extends ChannelInitializer {
        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline()
                    .addLast(new TankJoinMsgDecoder())
                    .addLast(new TankJoinMsgEncoder())
                    .addLast(new ChannelHandler());
            clients.add(channel);
        }
    }

    class ChannelHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            TankJoinMsg tankJoinMsg=(TankJoinMsg)msg;
            ServerFrame.INSTANCE.updateClientMsg(tankJoinMsg.toString());
            clients.writeAndFlush(msg);
        }
    }
}
