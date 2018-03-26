package com.flowedu.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by jihoan on 2017. 9. 5..
 */
@Component
public class RabbitmqClient {

    public static String HOST = "192.168.0.59";
    private Connection connection = null;
    private Channel channel = null;

    public Channel getChannel() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitmqClient.HOST);
        factory.setUsername("flowedu");
        factory.setPassword("flowedu3400");
        factory.setPort(5672);
        this.connection = factory.newConnection();
        this.channel = connection.createChannel();

        return this.channel;
    }

    public void close() throws Exception {
        this.channel.close();
        this.connection.close();
    }
}
