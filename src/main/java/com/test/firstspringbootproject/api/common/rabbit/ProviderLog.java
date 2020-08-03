package com.test.firstspringbootproject.api.common.rabbit;

import com.test.firstspringbootproject.sys.domain.UserLog;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//  @Component  把普通实体类注入到spring容器中去
@Component
public class ProviderLog {

    @Autowired
    private AmqpTemplate template;

    /**
     * 生产者发送消息,direct模式下需要传递一个routingKey
     * @param userLog
     * @throws Exception
     */
    public void send(UserLog userLog){
        System.out.println("发送用户日志信息到rabbitmq: "+userLog);
        template.convertAndSend("demoExchange","keyDemo",userLog);
    }
}
