package com.tianjie.study.y2020.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<TUser> {

    @Select("select * from T_user where id =#{id}")
    TUser getById(@Param("id") Integer id);

}
