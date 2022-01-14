package com.d2y.demo;

import com.d2y.demo.common.component.MyServiceBean;
import com.d2y.demo.common.component.PeopleServiceBean;
import com.d2y.demo.common.config.LoadPropertyConfig;
import com.d2y.demo.common.ext.definition.Children;
import com.d2y.demo.common.ext.definition.Parent;
import com.d2y.demo.common.ext.importbean.Config;
import com.d2y.demo.common.ext.importbean.scan.dao.ConfigService;
import com.d2y.demo.common.ext.metadata.MetaDemo;
import com.d2y.demo.common.ext.scanner.dao.FoodMapper;
import com.d2y.demo.common.listener.EmailEvent;
import com.d2y.demo.common.utils.SpringUtils;
import com.d2y.demo.mybatis.entity.MybatisDemoUser;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

        Map<String, ConfigService> beansOfType = SpringUtils.getBeansOfType(ConfigService.class);
        for (ConfigService configService : beansOfType.values()) {
            configService.print();
        }
    }

    @Test
    void testMetaData() {
        StandardAnnotationMetadata metadata = new StandardAnnotationMetadata(MetaDemo.class, true);

        // 演示ClassMetadata的效果
        System.out.println("==============ClassMetadata==============");
        ClassMetadata classMetadata = metadata;
        System.out.println(classMetadata.getClassName()); //com.fsx.maintest.MetaDemo
        System.out.println(classMetadata.getEnclosingClassName()); //null  如果自己是内部类此处就有值了
        System.out.println(StringUtils.arrayToCommaDelimitedString(classMetadata.getMemberClassNames())); //com.fsx.maintest.MetaDemo$InnerClass 若木有内部类返回空数组[]
        System.out.println(StringUtils.arrayToCommaDelimitedString(classMetadata.getInterfaceNames())); // java.io.Serializable
        System.out.println(classMetadata.hasSuperClass()); // true(只有Object这里是false)
        System.out.println(classMetadata.getSuperClassName()); // java.util.HashMap

        System.out.println(classMetadata.isAnnotation()); // false（是否是注解类型的Class，这里显然是false）
        System.out.println(classMetadata.isFinal()); // false
        System.out.println(classMetadata.isIndependent()); // true(top class或者static inner class，就是独立可new的)
        // 演示AnnotatedTypeMetadata的效果
        System.out.println("==============AnnotatedTypeMetadata==============");
        AnnotatedTypeMetadata annotatedTypeMetadata = metadata;
        System.out.println(annotatedTypeMetadata.isAnnotated(Service.class.getName())); // true（依赖的AnnotatedElementUtils.isAnnotated这个方法）
        System.out.println(annotatedTypeMetadata.isAnnotated(Component.class.getName())); // true

        System.out.println(annotatedTypeMetadata.getAnnotationAttributes(Service.class.getName())); //{value=serviceName}
        System.out.println(annotatedTypeMetadata.getAnnotationAttributes(Component.class.getName())); // {value=repositoryName}（@Repository的value值覆盖了@Service的）
        System.out.println(annotatedTypeMetadata.getAnnotationAttributes(EnableAsync.class.getName())); // {order=2147483647, annotation=interface java.lang.annotation.Annotation, proxyTargetClass=false, mode=PROXY}

        // 看看getAll的区别：value都是数组的形式
        System.out.println(annotatedTypeMetadata.getAllAnnotationAttributes(Service.class.getName())); // {value=[serviceName]}
        System.out.println(annotatedTypeMetadata.getAllAnnotationAttributes(Component.class.getName())); // {value=[, ]} --> 两个Component的value值都拿到了，只是都是空串而已
        System.out.println(annotatedTypeMetadata.getAllAnnotationAttributes(EnableAsync.class.getName())); //{order=[2147483647], annotation=[interface java.lang.annotation.Annotation], proxyTargetClass=[false], mode=[PROXY]}

        // 演示AnnotationMetadata子接口的效果（重要）
        System.out.println("==============AnnotationMetadata==============");
        AnnotationMetadata annotationMetadata = metadata;
        System.out.println(annotationMetadata.getAnnotationTypes()); // [org.springframework.stereotype.Repository, org.springframework.stereotype.Service, org.springframework.scheduling.annotation.EnableAsync]
        System.out.println(annotationMetadata.getMetaAnnotationTypes(Service.class.getName())); // [org.springframework.stereotype.Component, org.springframework.stereotype.Indexed]
        System.out.println(annotationMetadata.getMetaAnnotationTypes(Component.class.getName())); // []（meta就是获取注解上面的注解,会排除掉java.lang这些注解们）

        System.out.println(annotationMetadata.hasAnnotation(Service.class.getName())); // true
        System.out.println(annotationMetadata.hasAnnotation(Component.class.getName())); // false（注意这里返回的是false）

        System.out.println(annotationMetadata.hasMetaAnnotation(Service.class.getName())); // false（注意这一组的结果和上面相反，因为它看的是meta）
        System.out.println(annotationMetadata.hasMetaAnnotation(Component.class.getName())); // true

        System.out.println(annotationMetadata.hasAnnotatedMethods(Autowired.class.getName())); // true
        annotationMetadata.getAnnotatedMethods(Autowired.class.getName()).forEach(methodMetadata -> {
            System.out.println(methodMetadata.getClass()); // class org.springframework.core.type.StandardMethodMetadata
            System.out.println(methodMetadata.getMethodName()); // getName
            System.out.println(methodMetadata.getReturnTypeName()); // java.lang.String
        });

    }
}
