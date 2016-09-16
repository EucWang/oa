package cn.wxn.example.webapp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by wangxn on 2016/9/12.
 */

@ContextConfiguration(locations = {
        "classpath:springmvc-servlet.xml",
        "classpath:applicationContext-dao.xml",
        "classpath:applicationContext-service.xml",
        "classpath:applicationContext-transaction.xml"})
public abstract class AppBaseTest  extends AbstractTestNGSpringContextTests{
}
