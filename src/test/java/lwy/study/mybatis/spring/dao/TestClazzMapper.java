package lwy.study.mybatis.spring.dao;


import lwy.study.mybatis.dao.ClazzMapper;
import lwy.study.mybatis.pojo.Clazz;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class TestClazzMapper {
    ApplicationContext applicationContext;
    ClazzMapper clazzMapper;
    @Before
    public void init(){
   applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
   clazzMapper = applicationContext.getBean(ClazzMapper.class);
    }
    @Test
    public void testSelectAll(){
        List<Clazz> clazzes = clazzMapper.selectAll();
        System.out.println(clazzes);
    }
    @Test
    public void testAdd(){
        Clazz clazz = new Clazz("002","测试2班",19);
        // 防重跑守卫：库里已有同班号就跳过，避免重复插入
        for (Clazz c : clazzMapper.selectAll()) {
            if (c.getCno().equals(clazz.getCno())) {
                System.out.println("cno=" + clazz.getCno() + " 已存在，跳过插入");
                return;
            }
        }
        clazzMapper.insert(clazz);
        System.out.println("插入成功：" + clazz);
    }
}
