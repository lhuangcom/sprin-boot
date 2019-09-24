package com.lhuang.mapper;

import com.lhuang.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LHuang on 2018/8/20.
 */

@Repository
public interface UserMapper {

    @Select("select name from User where id = #{id}")
    public String  getNameById(Long id);

    @Select("select * from User where name = #{name}")
    public List<User> LikeName(String name);

    @Select("select * from User where id = #{id}")
    public User getUserById(Long id);
}
