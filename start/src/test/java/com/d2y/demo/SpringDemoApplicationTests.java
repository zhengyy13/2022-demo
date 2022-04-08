package com.d2y.demo;

import com.d2y.demo.common.component.MyServiceBean;
import com.d2y.demo.common.component.PeopleServiceBean;
import com.d2y.demo.common.config.LoadPropertyConfig;
import com.d2y.demo.common.ext.definition.Children;
import com.d2y.demo.common.ext.definition.Parent;
import com.d2y.demo.common.ext.importbean.selector.dao.ConfigService;
import com.d2y.demo.common.ext.metadata.MetaDemo;
import com.d2y.demo.common.ext.scanner.dao.FoodMapper;
import com.d2y.demo.common.listener.EmailEvent;
import com.d2y.demo.common.utils.SpringUtils;
import com.d2y.demo.mybatis.entity.MybatisDemoUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;

import sun.plugin2.message.Message;

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
        System.out.println("end~~~~~~~~~~~~~~~~");
    }

    /**
     * 配置文件加载
     * 1.xml中进行配置文件引入
     * <context:property-placeholder location="classpath:config/*.properties"  file-encoding="utf-8"/>
     * 2.通过注解进行导入
     *
     * @PropertySource(value = "properties/config.properties")
     * 3.通过定义PropertyPlaceholderConfigurer的Bean来进行加载
     * <p>
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

        PeopleServiceBean peopleServiceBean = (PeopleServiceBean) SpringUtils.getApplicationContext()
            .getBean("peopleServiceBean");
        peopleServiceBean.peopleIntroduce();
    }

    @Test
    void testScanner() {
        FoodMapper foodMapper = SpringUtils.getApplicationContext().getBean(FoodMapper.class);
        foodMapper.add("xxxxx");
    }

    @Test
    void testDefinitions() {
        // BeanDefinitionBuilder方式
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinitionBuilder children = BeanDefinitionBuilder.genericBeanDefinition(Children.class)
            .addPropertyValue("address", "工联cc").setParentName("parent");

        BeanDefinitionBuilder parent = BeanDefinitionBuilder.genericBeanDefinition(Parent.class)
            .addPropertyValue("name", "Z").addPropertyValue("age", 25);

        factory.registerBeanDefinition("children", children.getBeanDefinition());
        factory.registerBeanDefinition("parent", parent.getBeanDefinition());

        Children childrenBean = factory.getBean(Children.class);
        System.out.println(childrenBean);

        // BeanFactoryPostProcessor

        // BeanDefinitionRegistryPostProcessor

        // @Import 4种方式
    }

    /**
     * @Import可以导入以下4类 普通类
     * @Configuration配置类 实现了ImportSelector接口的类
     * 实现了ImportBeanDefinitionRegistrar接口的类
     */
    @Test
    void testImportBean() {
//        Config config = SpringUtils.getApplicationContext().getBean(Config.class);
//        config.print();

        //ImportSelector方式
        Map<String, ConfigService> beansOfType = SpringUtils.getBeansOfType(ConfigService.class);
        for (ConfigService configService : beansOfType.values()) {
            configService.print();
        }

        //ImportBeanDefinitionRegistrar方式
        Map<String, com.d2y.demo.common.ext.importbean.registrar.dao.ConfigService> beansOfType1 = SpringUtils
            .getBeansOfType(com.d2y.demo.common.ext.importbean.registrar.dao.ConfigService.class);
        for (com.d2y.demo.common.ext.importbean.registrar.dao.ConfigService configService : beansOfType1.values()) {
            configService.print();
        }
    }

    /**
     * Metadata
     */
    @Test
    void testMetaData() {
        MetaDemo.main(null);
    }


    @Test
    void test() {
        //String messge = "医保支付异常:SDSZYBPT续方接口调用失败：山东地纬方该病人在[990336]山东医保大健康集团医院有限公司医院存在未使用的处方(开立时间：2022-03-31 09:40:59),不能再开方";
        String messge = "医保支付异常:SDSZYBPT续方接口调用失败：山东地纬方该病人在990336山东医保大健康集团医院有限公司医院存在未使用的处方(开立时间：2022-03-31 09:40:59),不能再开方";

        String synchPrescriptionErrorKeyword = "存在未使用的处方";

        if (StringUtils.isNotBlank(messge) && messge.contains(synchPrescriptionErrorKeyword)) {
            System.out.println("山东省直特殊错误处理");
//            System.out.println(messge.replaceAll("(\\[)(.*?)(])", "#############"));

            String regex = "\\[(.*?)]";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(messge);
            if (matcher.find()) {
                System.out.println(matcher.group(1));
            }

            int start = messge.indexOf("[");
            int end = messge.lastIndexOf("]");
            if (start != -1 && end != -1) {
                System.out.println(messge.substring(start + 1, end));
            }

        }

    }

}
