package com.jw.zmq;

import org.junit.jupiter.api.Test;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * guoyy com.jw.zmq
 *
 * @Description: com.jw.zmq.Demo
 * @Author: guoyiyong/james
 * @Date: 2020-01-08 15:40
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Demo {

    @Test
    public void test() {
        try (ZMQ.Context context = ZMQ.context(1)) {
            ZMQ.Socket socket = context.socket(SocketType.REQ);
            socket.connect("tcp://127.0.0.1:5555");

            long now = System.currentTimeMillis();
            for (int i = 0; i < 100_0000; i++) {
                String request = "hello" + i;
                // 向reponse端发送数据
                socket.send(request.getBytes());
                // 接收response发送回来的数据
                // 在request/response模型中send之后必须要recv之后才能继续send，这可能是为了保证整个request/response的流程走完
                byte[] response = socket.recv();
                System.out.println("receive : " + new String(response));
            }
            long after = System.currentTimeMillis();

            System.out.println((after - now) / 1000);
        }
    }

    public static void main(String[] args) {
        // 实现了 Closeable 接口
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            socket.bind("tcp://*:5555");

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);

                // Print the message
                String res = new String(reply, ZMQ.CHARSET);
                System.out.println("Received: [" + res + "]");

                // Send a response
                String response = "Hello, world!" + res;
                socket.send(response.getBytes(ZMQ.CHARSET), 0);
            }
        }
    }
}
