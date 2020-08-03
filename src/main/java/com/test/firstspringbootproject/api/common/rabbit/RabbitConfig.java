package com.test.firstspringbootproject.api.common.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    //消息队列名称
    final static String queueName = "queue_demo";

    //交换机名称
    final static String exchangeName = "demoExchange";

    @Bean
    public Queue getQueue(){
        return new Queue(RabbitConfig.queueName);
    }

    //声明一个direct类型的交换机
    @Bean
    public DirectExchange getExchange(){
        return new DirectExchange(RabbitConfig.exchangeName);
    }

    //绑定Queue队列到交换机,并且指定routingKey
    @Bean
    public Binding getBinding(Queue queue,DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("keyDemo");
    }

}
