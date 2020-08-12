package com.tianjie.study.oracl;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@TableName("DEPT")
@Data
public class DEPT extends Model<DEPT> implements Serializable {

    @TableId(value="DEPTNO",type = IdType.INPUT)
    private String deptno;


}
