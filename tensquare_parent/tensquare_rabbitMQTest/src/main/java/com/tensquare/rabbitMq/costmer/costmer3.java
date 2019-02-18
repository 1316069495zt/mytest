package com.tensquare.rabbitMq.costmer;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "zt")
public class costmer3 {

    @RabbitHandler
    public void getMsg(String msg){

        System.out.println("zt"+msg);
    }
}
