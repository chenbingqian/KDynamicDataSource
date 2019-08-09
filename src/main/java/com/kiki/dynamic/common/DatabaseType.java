package com.kiki.dynamic.common;

/**
 * 列出所有的数据源key（常用数据库名称来命名）
 *
 * @ClassName: DatabaseType
 * @author kiki
 * @date 2019年8月9日
 * @version: V1.0
 */
public enum DatabaseType {
    // 注意： 1）这里数据源与数据库是一对一的
    // 2）DatabaseType中的变量名称就是数据库的名称(当然，你也可以定义不一样的名称，不一定非要和数据库的名称一致)
    kiki, bing
}