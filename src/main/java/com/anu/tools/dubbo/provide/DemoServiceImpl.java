package com.anu.tools.dubbo.provide;

import com.anu.tools.dubbo.info.DemoService;

/**
 * Describe:
 * Author: gao.xu
 * Mail: gao.xu@ustcinfo.com
 * Date: 2018年07月09日 9:33
 * Copyright: © 2018.Anu Studio., Ltd. All rights reserved.
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
