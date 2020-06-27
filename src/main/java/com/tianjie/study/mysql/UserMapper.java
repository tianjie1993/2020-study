package com.tianjie.study.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<TUser> {

    @Select("select * from T_user where id =#{id}")
    TUser getById(@Param("id") Integer id);

}
