package testngcase.ios;

//import static org.testng.AssertJUnit.assertTrue;
//import static org.testng.AssertJUnit.fail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaClient;
import macaca.client.commands.Element;



public class IOSAppTest {
    
	boolean isFail;

    private MacacaClient driver = new MacacaClient();
    
    public MacacaClient initDriver() throws Exception {

        JSONObject porps = new JSONObject();
        porps.put("platformName", "Android");
		porps.put("package", "com.tuniu.app.ui");
		porps.put("activity", "com.tuniu.app.ui.activity.LaunchActivity");
		//0: 启动并安装 app。1 (默认): 卸载并重装 app。 2: 仅重装 app。3: 在测试结束后保持 app 状态。
		porps.put("reuse", 3);
		porps.put("udid", "4d4aa2f3");
        JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("desiredCapabilities", porps);

        return driver.initDriver(desiredCapabilities); 
    }

    @BeforeClass
    public void setUp() throws Exception {
    	
    	initDriver();
    }
    
    @BeforeMethod
    public void beforecase() throws Exception {
    	//重置isFail,判读是否执行 aftercase方法
    	isFail = true;
    }

    @Test
    public void case_001xxx() {
        // set screenshot save path
        //String courseFile = directory.getCanonicalPath();
        try {
			driver.sleep(5000);
			Element  el = driver.elementByXPath("//*[@resource-id='com.tuniu.app.ui:id/mainpage_my']");
	        System.out.println("------------我的控件存在------------------");

	        el.click();
	        //判断到达新页面
	        assertTrueReWrite("assertTrue:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//*[@text='我的订单']"));
	        //screenShot();
	        System.out.println("------------控件存在------------------");

	        //返回首页
	        driver.elementByXPath("//*[@resource-id='com.tuniu.app.ui:id/mainpage']").click();
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString()+e.getLocalizedMessage());
		}

    }
    
    @AfterMethod
    public void aftercase() throws Exception {
    	System.out.println("------------进入aftercase方法-----------------");
    	if(isFail==true){

    		}
    		//如果5次循环都不能到首页，重新初始化driver
    		//initDriver();
    	}
    
    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
    
    public void failReWrite(String message) {
		String imgurl = screenShot();
		if (message == null) {
            throw new AssertionError();
        }
        throw new AssertionError(message+" Check:"+" ltltlta target=_blank href=file://"+imgurl+"gtgtgtScreenShotltltlt/agtgtgt"+" ");
	}
	
	
	public void assertTrueReWrite(String message, boolean condition) {
        if (!condition) {
        	failReWrite(message);
        }
    }
	
    
    public String getDateTime(){  
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
        return df.format(new Date());  
    }
    
    
    public String screenShot(){
    	
    	File directory = new File("");
    	String courseFile;
	    try {
	    	courseFile = directory.getCanonicalPath();
	    	String imgname = courseFile+"/report/img/img"+getDateTime()+".png";
	    	System.out.println("-------imgname----------"+imgname);
	    	driver.saveScreenshot(imgname);
	    	return imgname;
		} catch (IOException e) {
			System.out.println("获取路径失败，报错信息："+e.getMessage());
			return "";
		} catch (Exception e1) {
			System.out.println("截图失败，报错信息："+e1.getMessage());
			return "";
		}
	}
}
