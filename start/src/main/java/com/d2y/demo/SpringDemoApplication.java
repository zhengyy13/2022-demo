package com.d2y.demo;

import com.d2y.demo.common.ext.importbean.registrar.ConfigScan;
import com.d2y.demo.common.ext.importbean.selector.EnableConfigService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableConfigService
@ConfigScan("com.d2y.demo.common.ext.importbean.registrar.dao")
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

}
