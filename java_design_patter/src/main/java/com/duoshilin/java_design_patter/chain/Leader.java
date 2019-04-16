package com.duoshilin.java_design_patter.chain;

/**
 * Created by duoshilin on 2019/2/14.
 */
public abstract class Leader {

    protected String roleName;

    protected Leader nextLeader;

    public void setNextLeader(Leader nextLeader) {
        this.nextLeader = nextLeader;
    }

    public abstract void doRequest(double money);
}
