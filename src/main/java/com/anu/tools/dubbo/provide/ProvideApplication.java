package com.anu.tools.dubbo.provide;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Describe: 提供者客户端
 * Author: gao.xu
 * Mail: gao.xu@ustcinfo.com
 * Date: 2018年07月09日 9:34
 * Copyright: © 2018.Anu Studio., Ltd. All rights reserved.
 */
public class ProvideApplication {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:dubbo/dubbo-provider.xml"});
        context.start();
        System.out.println("provide start success!");
        System.in.read();
    }

}
