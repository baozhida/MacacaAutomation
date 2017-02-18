package com.xxx.automation;

import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;

public class Driver {

    static MacacaClient driver = new MacacaClient();

    //初始化IOSDriver
    public static MacacaClient initIOSDriver() throws Exception {
        String platform = "IOS";

        JSONObject porps = new JSONObject();
        porps.put("platformName", platform);
        porps.put("app", "./app/xxx.app");
        //0: 启动并安装 app。1 (默认): 卸载并重装 app。 2: 仅重装 app。3: 在测试结束后保持 app 状态。
        porps.put("reuse", 3);
        porps.put("udid", "xxx");
        JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("desiredCapabilities", porps);
        return driver.initDriver(desiredCapabilities); 
        
    }
    
    //初始化Android Driver
    public static MacacaClient initAndroidDriver() throws Exception {
        String platform = "android";

        JSONObject porps = new JSONObject();
        porps.put("platformName", platform);
        porps.put("app", "./app/xxx.apk");
        //0: 启动并安装 app。1 (默认): 卸载并重装 app。 2: 仅重装 app。3: 在测试结束后保持 app 状态。
        porps.put("reuse", 3);
        porps.put("udid", "xxx");
        JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("desiredCapabilities", porps);
        return driver.initDriver(desiredCapabilities); 
    }
}
