package com.tianjie.study.oracl;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@TableName("T_LOCKK")
@Data
public class Lock extends Model<Lock> implements Serializable {

    @TableId(value="LOCK_ID",type = IdType.INPUT)
    private String lockId;


}
