//package netty;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelFutureListener;
//import io.netty.channel.ChannelHandler.Sharable;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//
//import java.nio.charset.StandardCharsets;
//
///**
// * @Auth justinniu
// * @Date 2018/11/7
// * @Desc
// */
//@Sharable
//public class EchoServerHandler extends ChannelInboundHandlerAdapter {
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf in = (ByteBuf) msg;
//        System.out.println("Server received: " + in.toString(StandardCharsets.UTF_8));
//        ctx.write(in);
//    }
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) {
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        cause.printStackTrace();
//        ctx.close();
//    }
//
//
//@Path("/create")
//public ActionResult create() throws Exception{
//
//        String[] content = {"太好看了", "讲的太棒了", "讲的还行吧", "听了很有帮助", "老师也太厉害了吧", "一般一般", "我真是太笨了， 都没听懂", "太垃圾了", "真是不好看"};
//        Random random = new Random(37);
//        for (int i = 0; i < 100; i++) {
//        Map map = new HashMap(20);
//        map.put("userId", getUserId(getCurrentUserId()));
//        map.put("userName", getCurrentUserName());
//        map.put("targetId", "456");
//        map.put("targetType", Constants.TARGET_TYPE_VIDEO);
//        map.put("channel",(short) 1);
//        map.put("starNum", i % 5);
//        map.put("content", "测试评论：" + content[random.nextInt(9) % 9]);
//        map.put("courseName", "测试文库");
//        int result = scf.getCommentService().addComment(getBizCodeType(), map);
//        log.info(result+"");
//
//        }
//        return ActionResultUtil.sendJsonActionResult(Result.builtOk("hha"));
//        }
//}
