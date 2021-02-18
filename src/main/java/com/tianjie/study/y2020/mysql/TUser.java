package com.tianjie.study.y2020.mysql;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@TableName("T_USER")
@Data
public class TUser extends Model<TUser> implements Serializable {

    @TableField(value="id")
    private Integer id;

    private String name;

    private Integer age;

    public TUser(int id,String s, int i) {
        this.id = id;
        this.name = s;
        this.age =i;
    }
}
