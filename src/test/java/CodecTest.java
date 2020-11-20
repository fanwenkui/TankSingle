import com.fwk.game.net.TankMsg;
import com.fwk.game.net.TankMsgDecode;
import com.fwk.game.net.TankMsgEncode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CodecTest {
    @Test
    public void encode(){
        EmbeddedChannel channel=new EmbeddedChannel();
        TankMsg msg=new TankMsg(5,6);
        channel.pipeline().addLast(new TankMsgEncode());
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
        channel.pipeline().addLast(new TankMsgDecode());

        TankMsg msg=new TankMsg(1,2);
        ByteBuf buf= Unpooled.buffer();
        buf.writeBytes(msg.toBytes());

        channel.writeInbound(buf.duplicate());

        TankMsg resp=(TankMsg) channel.readInbound();
        assertEquals(1,resp.x);
        assertEquals(2,resp.y);
    }
}
