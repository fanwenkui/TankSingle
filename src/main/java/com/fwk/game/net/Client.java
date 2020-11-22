package com.fwk.game.net;

import com.fwk.game.tank.Tank;
import com.fwk.game.tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    private Channel channel = null;

    public void connect() {
        EventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        try {
            ChannelFuture future = bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer())
                    .connect("localhost", 8888)
                    .sync();

            future.addListener((ChannelFutureListener) e -> {
                if (e.isSuccess()) {
                    channel = e.channel();
                }
            });

            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes());
        channel.writeAndFlush(buf);
    }


    class ClientChannelInitializer extends ChannelInitializer {
        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline()
                    .addLast(new TankJoinMsgDecoder())
                    .addLast(new TankJoinMsgEncoder())
                    .addLast(new ChannelHandler());
        }
    }

    class ChannelHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            Tank tank= TankFrame.INSTANCE.getMyTank();
            ctx.channel().writeAndFlush(new TankJoinMsg(tank.getX(),tank.getY(),tank.getDir(),tank.isMoving(),tank.getGroup(),tank.getId()));
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            TankJoinMsg tankJoinMsg=(TankJoinMsg) msg;
            if(tankJoinMsg.id.equals(TankFrame.INSTANCE.getMyTank().getId()) || TankFrame.INSTANCE.hasId(tankJoinMsg.id)){
                return;
            }
            TankFrame.INSTANCE.tankJoin(tankJoinMsg);
            ctx.channel().writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMyTank()));
        }
    }
}
