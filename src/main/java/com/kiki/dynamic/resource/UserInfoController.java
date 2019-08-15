/*
 * @Title: PermissionController.java
 */
package com.kiki.dynamic.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiki.dynamic.entity.UserInfo;
import com.kiki.dynamic.service.UserInfoService;

/**
 * @ClassName: UserInfoController
 * @author kiki
 * @date 2019年8月9日
 * @version: V1.0
 */
@RestController
public class UserInfoController {

    @Autowired
    UserInfoService permissionService;

    @RequestMapping("/bing")
    public List<UserInfo> bing() {
        return permissionService.getBingList();
    }

    @RequestMapping("/kiki")
    public List<UserInfo> kiki() {
        return permissionService.getKikiList();
    }

    @GetMapping("/insert/{uname}/{mail}")
    public String insertUser(@PathVariable String uname,
            @PathVariable String mail) {
        return  permissionService.insertUser(uname, mail);
    }
    //没有事务
    @GetMapping("/insert/notx/{uname}/{mail}")
    public String insertTranUser(@PathVariable String uname,
                             @PathVariable String mail) {
        return  permissionService.noTxInsertUserTran(uname, mail);
    }


}
