package com.tianjie.study.y2021.tranaop;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;

public class TestSync extends TransactionSynchronizationAdapter {

    private String id;

    public TestSync(String id) {
        TranUtil.set(id);
        this.id= id;
    }

    @Override
    public void afterCompletion(int status) {
        TranUtil.complete(id);
        TranUtil.remove(id);
    }
}
