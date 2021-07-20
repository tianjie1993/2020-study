package com.tianjie.study.y2021.newwrite;

import com.tianjie.study.y2021.newwrite.cmds.UserUpdateCmd;

public class App {

    public static void main(String[] args) {
        BizContext context = ContextFactoty.requiresAndInit(BizContext.class, "a", "ad", "ad", "ad");
        UserUpdateCmd cmd = CommandFactory.createCmd(UserUpdateCmd.class);
        cmd.execute(context);
    }
}
