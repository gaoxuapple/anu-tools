package com.anu.tools.dubbo.consumer;

import com.anu.tools.dubbo.info.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Describe: 消费者客户端
 * Author: gao.xu
 * Mail: gao.xu@ustcinfo.com
 * Date: 2018年07月09日 9:34
 * Copyright: © 2018.Anu Studio., Ltd. All rights reserved.
 */
public class ConsumerApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:dubbo/dubbo-consumer.xml"});
        context.start();
        System.out.println("consumer start success!");
        DemoService demoService = (DemoService)context.getBean("demoService");

        try {
            while (true) {
                Thread.sleep(1000);
                String result = demoService.sayHello("gaoxu");
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
