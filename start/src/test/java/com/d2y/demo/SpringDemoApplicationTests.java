package com.d2y.demo;

import com.d2y.demo.common.component.MyServiceBean;
import com.d2y.demo.common.component.PeopleServiceBean;
import com.d2y.demo.common.config.LoadPropertyConfig;
import com.d2y.demo.common.listener.EmailEvent;
import com.d2y.demo.common.utils.SpringUtils;
import com.d2y.demo.mybatis.entity.MybatisDemoUser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDemoApplicationTests {

    @Autowired
    LoadPropertyConfig loadPropertyConfig;

    @Test
    void contextLoads() {
    }

    /**
     * 通过ClassPathBeanDefinitionScanner扫描自定义注解并加载到spring容器中
     */
    @Test
    void testMyBeanDefinationRegisterPostProcessor() {
        MyServiceBean myServiceBean = (MyServiceBean) SpringUtils.getApplicationContext().getBean("myServiceBean");
        System.out.println(myServiceBean);
    }

    /**
     * Aop测试
     * spel表达式测试
     */
    @Test
    void testAspect() {
        MyServiceBean myServiceBean = (MyServiceBean) SpringUtils.getApplicationContext().getBean("myServiceBean");

        MybatisDemoUser user = new MybatisDemoUser();
        user.setBizId(40025843216419393L);
        user.setRemark("安卓测试问诊支付0322主诉");
        user.setId(33756L);
        user.setUsername("D2Y");
        user.setPassword("123");
        myServiceBean.add(user);
    }

    /**
     * spring事件监听(观察者模式)
     * 1.定义事件
     * 2.定义监听器
     * 3.发送事件
     * <p>
     * ######线程池问题
     * Spring事件监听器（ApplicationListener）onApplicationEvent方法默认是发布者线程调用（同步）
     * 如果想用执行器（Executor）进行调用的（异步），需要将默认的SimpleApplicationEventMulticaster事件广播器中的执行器设置一个
     */
    @Test
    void testListener() throws InterruptedException {
        MybatisDemoUser user = new MybatisDemoUser();
        user.setBizId(40025843216419393L);
        user.setRemark("安卓测试问诊支付0322主诉");
        user.setId(33756L);
        user.setUsername("D2Y");
        user.setPassword("123");

        System.out.println("事件发送：" + Thread.currentThread().getName());
        SpringUtils.getApplicationContext().publishEvent(new EmailEvent(user));
    }

    /**
     * 配置文件加载
     * 1.xml中进行配置文件引入
     * <context:property-placeholder location="classpath:config/*.properties"  file-encoding="utf-8"/>
     * 2.通过注解进行导入
     * @PropertySource(value = "properties/config.properties")
     * 3.通过定义PropertyPlaceholderConfigurer的Bean来进行加载
     *
     * 使用配置文件中的属性
     * 1.xml中使用--占位符
     * ${tenant.ids}
     * 2.注解的使用
     * @Value("${tenant.ids}")
     */
    @Test
    void testProperty() {
        Integer[] tenantIds = loadPropertyConfig.getTenantIds();
        for (Integer tenantId : tenantIds) {
            System.out.println(tenantId);
        }
    }

    /**
     * BeanPostProcessor
     * PriorityOrdered.class
     * Ordered.class
     * 其他
     */
    @Test
    void testBeanPostProcessor() {
//        MyServiceBean myServiceBean = (MyServiceBean) SpringUtils.getApplicationContext().getBean("myServiceBean");
//        myServiceBean.peopleIntroduce();

        PeopleServiceBean peopleServiceBean = (PeopleServiceBean) SpringUtils.getApplicationContext().getBean("peopleServiceBean");
        peopleServiceBean.peopleIntroduce();
    }

}
