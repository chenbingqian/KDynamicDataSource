package com.kiki.dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *
 * Main
 *
 * @ClassName: KikiApplication
 * @author kiki
 * @date 2019年8月6日
 * @version: V1.0
 */
@SpringBootApplication(scanBasePackages = "com.kiki", exclude = DataSourceAutoConfiguration.class)
public class KikiApplication
{
    public static void main(String[] args) {
        SpringApplication.run(KikiApplication.class, args);
    }
}
