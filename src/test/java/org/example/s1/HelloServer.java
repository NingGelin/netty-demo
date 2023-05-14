package org.example.s1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * <p></p>
 *
 * @author : ninggelin
 * @date : 2023-05-14 20:23:15
 **/
public class HelloServer {
    public static void main(String[] args) throws InterruptedException {
        // 1 启动器，负责组装netty组建，启动服务器
        new ServerBootstrap()
                // 2 BossEventLoop, WorkEventLoop(selector, thread), group组
                .group(new NioEventLoopGroup())
                // 3 选择服务器的ServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
                // 4 boss负责处理连接 worker（child）负责处理读写，决定了worker（child）能执行那些操作（handler）
                .childHandler(
                        // channel代表和客户端进行数据读写的通道
                        // initializer初始化，负责添加别的handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                // 添加具体的handler
                                // 将ByteBuf转换为字符串
                                ch.pipeline().addLast(new StringDecoder());
                                // 自定义handler
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    // 读事件
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        // 打印上一步转换好的字符串
                                        System.out.println(msg);
                                    }
                                });
                            }
                        })
                // 监听端口
                .bind(8090);
    }
}
