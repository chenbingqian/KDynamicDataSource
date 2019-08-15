/*
 * @Title: PermissionService.java
 */
package com.kiki.dynamic.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiki.dynamic.entity.UserInfo;
import com.kiki.dynamic.mapper.UserInfoMapper;

/**
 *
 *
 * @ClassName: PermissionService
 * @author kiki
 * @date 2019年8月9日
 * @version: V1.0
 */
@Service
public class UserInfoService {

    private static Logger logger = LoggerFactory
            .getLogger(UserInfoService.class);
    @Autowired
    private UserInfoMapper userInfoMapper;




    public List<UserInfo> getBingList() {
        // 虽然在MyBatisConfiguration设置了默认的库，这里进行了setDatabaseType设置，改变了操作的db,因此，这里访问的bing的db
        // 这里的操作更改为已get开通的查询bing，以insert开头的，查询kiki->(移动到TransactionAdviceConfig来完成)
        // DynamicDataSource.setDatabaseType(DatabaseType.bing);
        return userInfoMapper.getUserInfoList();
    }


    public List<UserInfo> getKikiList() {
        // MyBatisConfiguration - dataSource.setDefaultTargetDataSource(kiki)
        // 通过MyBatisConfiguration类中查看，可以看见默认的是kiki的这个库，因此这里访问的kiki
        return userInfoMapper.getUserInfoList();
    }



    public String insertUser(String uname, String mail) {
        UserInfo user = new UserInfo();
        user.setUname(uname);
        user.setMail(mail);
        userInfoMapper.insertUser(user);
         user.setUid(1);
         userInfoMapper.insertUserError(user);
        return "success";
    }

    /**
     * 添加数据，有事务
     *   添加事务管理
     * @param uname
     * @param mail
     * @return
     */
    public String noTxInsertUserTran(String uname, String mail) {
        UserInfo user = new UserInfo();
        user.setUname(uname);
        user.setMail(mail);
        userInfoMapper.insertUser(user);
        user.setUid(1);
        userInfoMapper.insertUserError(user);
        return "success";
    }
}
