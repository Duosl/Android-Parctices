package com.duoshilin.java_design_patter.chain;

/**
 * Created by duoshilin on 2019/2/14.
 */
public class GeneralManager extends Leader {

    public GeneralManager() {
        this.roleName = "总经理";
    }

    @Override
    public void doRequest(double money) {
        if (money < 10000){
            System.out.println("【"+ this.roleName+"】同意了申请资金："+money+"元");
        }else {
            System.out.println("资金:"+money+"元，数目巨大，【"+this.roleName+"】拒绝了本次资金申请！");
        }
    }
}
