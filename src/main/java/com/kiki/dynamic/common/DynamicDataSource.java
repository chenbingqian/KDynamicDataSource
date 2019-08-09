package com.kiki.dynamic.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源(需要继承AbstractRoutingDataSource)
 *
 * @ClassName: DynamicDataSource
 * @author kiki
 * @date 2019年8月9日
 * @version: V1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    // 保存一个线程安全的Database容器
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDatabaseType();
    }
}