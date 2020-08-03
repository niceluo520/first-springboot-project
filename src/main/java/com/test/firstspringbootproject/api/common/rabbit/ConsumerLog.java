package com.test.firstspringbootproject.api.common.rabbit;

import com.test.firstspringbootproject.sys.domain.UserLog;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue_demo")
public class ConsumerLog {

    @RabbitHandler
    public void process(UserLog userLog){

        System.out.println(userLog);
        //save(userLog);
        System.out.println("录入成功");
    }
}
