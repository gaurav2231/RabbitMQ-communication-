package com.gaurav.rabbitmqdemo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Profile("send")
public class Send {
    static Logger logger = LoggerFactory.getLogger(Send.class);

    private static String QUEUE_NAME = "Minakshi Mishra";

    public  static void main(String[] argv) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.216");  // Praveen
        factory.setHost("192.168.0.189");  // Minakshi

        factory.setPort(5672);
        factory.setUsername("gaurav");
        factory.setPassword("gaurav@123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false,false, false, null);
        String message = "Hayyy Minakshi from Gaurav";
        
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        logger.info("[!] Send '" + message + "'");
        channel.close();
        connection.close();
    }
}
