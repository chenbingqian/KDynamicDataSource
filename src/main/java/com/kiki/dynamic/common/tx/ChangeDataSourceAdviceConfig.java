package com.kiki.dynamic.common.tx;

import java.util.regex.Pattern;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.kiki.dynamic.common.DatabaseType;
import com.kiki.dynamic.common.DynamicDataSource;

/**
 * 根据业务类型，动态切换数据源
 *
 * @ClassName: ChangeDataSourceAdviceConfig
 * @author kiki
 * @date 2019年8月9日
 * @version: V1.0
 */
@Aspect
@Component
//@Order注解用于定义的AOP先于事物执行(这里保证了在事务之前完成数据源的切换，以免切换数据源失效)
@Order(-1)
public class ChangeDataSourceAdviceConfig {
    private static Logger logger = LoggerFactory
            .getLogger(ChangeDataSourceAdviceConfig.class);

    /**
     * 使用空方法定义切点表达式
     */
    @Pointcut("execution(* com.kiki.dynamic.service.**.*(..))")
    public void txConfig() {
    }

    @Before("txConfig()")
    public void setDataSourceKey(JoinPoint point) {
        // service的方法名
        String methodName = point.getSignature().getName();
        logger.info("请求的方法是：" + methodName);
        // 分别以insert|add|update|del|delete|edit开头的方法操作kiki这个datasource
        Pattern p = Pattern.compile("^insert|add|update|del|delete|edit|getKiki");
        if (p.matcher(methodName).find()) {
            logger.info("operation db is.........kiki");
            DynamicDataSource.setDatabaseType(DatabaseType.kiki);
        } else {
            logger.info("operation db is.........bing");
            DynamicDataSource.setDatabaseType(DatabaseType.bing);
        }
    }
}
