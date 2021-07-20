package com.tianjie.study.y2021.newwrite.cmds;

import com.tianjie.study.y2021.newwrite.BizContext;
import com.tianjie.study.y2021.newwrite.Command;
import com.tianjie.study.y2021.newwrite.Context;

public class UserUpdateCmd implements Command<Integer, BizContext> {

    @Override
    public Integer execute(BizContext context) {
        System.out.println("我是执行用户更新命令");
        return 1;
    }

}
