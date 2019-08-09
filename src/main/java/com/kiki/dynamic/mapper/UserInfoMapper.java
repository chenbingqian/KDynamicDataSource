/*
 * @Title: UserInfoMapper.java
 */
package com.kiki.dynamic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.kiki.dynamic.entity.UserInfo;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @ClassName: UserInfoMapper
 * @author dell
 * @date 2019年8月9日
 * @version: V1.0
 */
@Mapper
public interface UserInfoMapper {
    @Select("select uid,uname,mail from kiki_userinfo ")
    @Results({
            @Result(property = "uid", column = "uid", javaType = Integer.class),
            @Result(property = "uname", column = "uname", javaType = String.class),
            @Result(property = "mail", column = "mail", javaType = String.class) })
    public List<UserInfo> getUserInfoList();

    @Insert({ "insert into kiki_userinfo(uname,mail) values(#{uname}, #{mail})" })
    int insertUser(UserInfo user);

    @Insert({ "insert into kiki_userinfo(uid,uname,mail) values(#{uid},#{uname}, #{mail})" })
    int insertUserError(UserInfo user);
}
