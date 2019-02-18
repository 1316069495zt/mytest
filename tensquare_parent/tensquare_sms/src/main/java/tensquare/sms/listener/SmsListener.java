package tensquare.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tensquare.sms.utils.SmsUtil;

import java.util.Map;

@RabbitListener(queues = "sms")
@Component
public class SmsListener {
    @Autowired
    private SmsUtil smsUtil;

    @RabbitHandler
    public void getSms(Map<String,String> map){


        System.out.println("手机号"+map.get("mobile"));
        System.out.println("验证码"+map.get("checkcode"));

    }

}
