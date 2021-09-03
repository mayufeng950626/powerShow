package com.mayufeng.common;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 初始化器，channel注册后，会执行里面的相应的初始化方法
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {
    /**
     * 管道初始化函数
     * @param socketChannel
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 通过socketChannel去获得对应的管道
        ChannelPipeline pipeline = socketChannel.pipeline();
       // 通过管道，添加handler
        // HttpServerCodec是由netty自己提供的助手类，相当于拦截器
        // websocket基于http协议，所以当请求到服务端，我们需要http编解码，响应到客户端做编码
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());
        // 对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // 对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(1024*64));

      // ====================以上是用于支持http协议===========================



        /**
         * This handler does all the heavy lifting for you to run a websocket server.
         *
         * It takes care of websocket handshaking as well as processing of control frames (Close, Ping, Pong). Text and Binary
         * data frames are passed to the next handler in the pipeline (implemented by you) for processing.
         *
         * See <tt>io.netty.example.http.websocketx.html5.WebSocketServer</tt> for usage.
         *
         * The implementation of this handler assumes that you just want to run  a websocket server and not process other types
         * HTTP requests (like GET and POST). If you wish to support both HTTP requests and websockets in the one server, refer
         * to the <tt>io.netty.example.http.websocketx.server.WebSocketServer</tt> example.
         *
         * To know once a handshake was done you can intercept the
         * {@link ChannelInboundHandler#userEventTriggered(ChannelHandlerContext, Object)} and check if the event was instance
         * of {@link WebSocketServerProtocolHandler.HandshakeComplete}, the event will contain extra information about the handshake such as the request and
         * selected subprotocol.
         * 此处理程序为您完成运行 websocket 服务器的所有繁重工作。它负责 websocket 握手以及控制帧（Close、Ping、Pong）的处理。文本和二进制数据帧被传递到管道中的下一个处理程序（由您实现）进行处理。
         * 有关用法，请参阅 <tt>io.netty.example.http.websocketx.html5.WebSocketServer<tt>。
         * 此处理程序的实现假定您只想运行 websocket 服务器而不处理其他类型的 HTTP 请求（如 GET 和 POST）。如果您希望在一个服务器中同时支持 HTTP 请求和 websocket，
         * 请参阅 <tt>io.netty.example.http.websocketx.server.WebSocketServer<tt> 示例。要知道一旦握手完成，
         * 您可以拦截 {@link ChannelInboundHandleruserEventTriggered(ChannelHandlerContext, Object)} 并检查该事件是否是 {@link WebSocketServerProtocolHandler.HandshakeComplete} 的实例，
         * 该事件将包含有关握手的额外信息，例如请求和选定的子协议。
         */
        // websocket服务器处理的协议，用于指定给客户端连接访问的路由，对于websocket来讲，都是以frames进行创数的，不同数据类型frames也不同
      pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 添加自定义的助手类,返回‘hello netty’
        pipeline.addLast("customerHandler", new CustomHandler());
    }
}
