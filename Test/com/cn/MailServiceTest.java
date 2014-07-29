package com.cn;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cn.MailBean;
import com.cn.MailUtil;
@ContextConfiguration("classpath:applicationContext.xml")
public class MailServiceTest extends AbstractJUnit4SpringContextTests {
	@Resource  
    private MailUtil mailUtil;  
	  
    @Test  
    public void testSendMail() {  
    	try {
    		Properties pro = new Properties();
    		System.out.println(MailServiceTest.class.getClassLoader().getResource("mail.properties").getPath());
			FileInputStream fis = new FileInputStream(MailServiceTest.class.getClassLoader().getResource("mail.properties").getPath());
			pro.load(fis);
			 //创建邮件  
	        MailBean mailBean = new MailBean();  
	        mailBean.setFrom(pro.getProperty("mail.from"));  
	        mailBean.setSubject("hello world");  
	        mailBean.setToEmails(new String[]{"1271439604@qq.com"});  
	        mailBean.setTemplate("hello ${userName} !<a href='www.baidu.com' >baidu</a>");  
	        Map map = new HashMap();  
	        map.put("userName", "Haley Wang");  
	        mailBean.setData(map);  
	        mailUtil.send(mailBean);  
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }  
}
