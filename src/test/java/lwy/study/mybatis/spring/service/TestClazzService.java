package lwy.study.mybatis.spring.service;

import lwy.study.mybatis.pojo.Clazz;
import lwy.study.mybatis.service.IClazzService;
import lwy.study.mybatis.service.impl.ClazzServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class TestClazzService{
    ApplicationContext applicationContext;
    IClazzService clazzService;
    @Before
    public void init(){
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        clazzService = applicationContext.getBean(ClazzServiceImpl.class);
    }

    @Test
    public void testSelectByPage(){
        List<Clazz> clazzes = clazzService.selectByPage(1, 2);
        System.out.println(clazzes);
    }
    @Test
    public void testAdd(){
        Clazz clazz = new Clazz("001","测试1班",19);
        // 防重跑守卫：库里已有同班号就跳过，避免重复插入
        for (Clazz c : clazzService.selectByPage(1, 999)) {
            if (c.getCno().equals(clazz.getCno())) {
                System.out.println("cno=" + clazz.getCno() + " 已存在，跳过插入");
                return;
            }
        }
        clazzService.add(clazz);
        System.out.println("插入成功：" + clazz);
    }


}
