/*
 * @Title: MyBatisConfig.java
 */
package com.kiki.dynamic.common;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * springboot集成mybatis的基本入口 1）创建数据源(如果采用的是默认的tomcat-jdbc数据源，则不需要)
 * 2）创建SqlSessionFactory 3）配置事务管理器，除非需要使用事务，否则不用配置
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Autowired
    private Environment env;

    // 创建DataSource 第一种方式
    @Bean(name = "kiki")
    public DataSource masterDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName",
                env.getProperty("spring.datasource.master.driverClassName"));
        props.put("url", env.getProperty("spring.datasource.master.url"));
        props.put("username",
                env.getProperty("spring.datasource.master.username"));
        props.put("password",
                env.getProperty("spring.datasource.master.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    // 创建DataSource 第二种方式
    @Bean(name = "bing")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(@Qualifier("kiki") DataSource kiki,
            @Qualifier("bing") DataSource info) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.kiki, kiki);
        targetDataSources.put(DatabaseType.bing, info);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        // 默认的datasource设置为kiki
        dataSource.setDefaultTargetDataSource(kiki);
        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */

    @Bean(name = "dynamicSqlSessionFactory")
    public SqlSessionFactory dynamicSqlSessionFactory(
            @Qualifier("dynamicDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:/mapper/*.xml");
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean(name = "dynamicTransactionManager")
    public PlatformTransactionManager dynamicTransactionManager(DynamicDataSource dataSource) {
        System.out.println("dynamic datasource transaction manager is init");
        return new DataSourceTransactionManager(dataSource);
    }
}
