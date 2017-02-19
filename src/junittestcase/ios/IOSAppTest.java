package junittestcase.ios;

import static com.xxx.automation.Driver.initIOSDriver;
import static com.xxx.automation.DriverUtil.screenShot;
import static com.xxx.automation.DriverUtil.failReWrite;
import static com.xxx.automation.DriverUtil.assertTrueReWrite;

import com.alibaba.fastjson.JSONArray;
import macaca.client.MacacaClient;
import macaca.client.commands.Element;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IOSAppTest {
    static MacacaClient driver;
    boolean isFail;
    
    
    @BeforeClass
    public static void setUp() throws Exception {
    	//初始化driver并得到 driver
    	driver = initIOSDriver();
    }
    
    @Before
    public void beforecase() throws Exception {
    	//重置isFail,判读是否执行 aftercase方法
    	isFail = true;
    }

    @Test
    public void case_1XXX() {
        try {
			    driver.sleep(5000);
			    Element  el = driver.elementByXPath("//*[@text='XXX']");
	        el.click();
	        assertTrueReWrite("assertTrueReWrite:元素未找到！",driver.sleep(5000).isElementExist("xpath", "//*[@name='YYY']"));
	        driver.sleep(2000);
	        isFail = false;
		} catch (Exception e) {
			failReWrite(e.toString());
		}

    }
    
    @After
    public void aftercase() throws Exception {
    	if(isFail==true){
    		//如果用例执行出错，检查是否在首页，如果不再首页，点击页面右上角的点，返回
    		for(int i=1;i<3;i++){
	    		if(driver.isElementExist("xpath", "//*[@name='首页']") == true){
	    			return;
	    		}else{
	    			driver.tap(21, 42);
					driver.sleep(2000);
	    		}
    		}
    		//如果3次循环都不能到首页，重新初始化driver
        driver = initIOSDriver();
	return;
    	}
    }
    
    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }
}
