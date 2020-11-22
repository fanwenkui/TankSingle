import com.fwk.game.net.TankJoinMsg;
import com.fwk.game.net.TankJoinMsgDecoder;
import com.fwk.game.net.TankJoinMsgEncoder;
import com.fwk.game.tank.Dir;
import com.fwk.game.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CodecTest {
    @Test
    public void encode(){
        EmbeddedChannel channel=new EmbeddedChannel();
        TankJoinMsg msg=new TankJoinMsg(5,6, Dir.DOWN,false, Group.GOOD, UUID.randomUUID());
        channel.pipeline().addLast(new TankJoinMsgEncoder());
        channel.writeOutbound(msg);

        ByteBuf buf = (ByteBuf)channel.readOutbound();
        int x=buf.readInt();
        int y=buf.readInt();
        assertEquals(5,x);
        assertEquals(6,y);
    }

    @Test
    public void decode(){
        EmbeddedChannel channel=new EmbeddedChannel();
        channel.pipeline().addLast(new TankJoinMsgDecoder());

        TankJoinMsg msg=new TankJoinMsg(1,2, Dir.DOWN,false, Group.GOOD, UUID.randomUUID());
        ByteBuf buf= Unpooled.buffer();
        buf.writeBytes(msg.toBytes());

        channel.writeInbound(buf.duplicate());

        TankJoinMsg resp=(TankJoinMsg) channel.readInbound();
        assertEquals(1,resp.x);
        assertEquals(2,resp.y);
    }
}
