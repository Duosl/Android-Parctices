package com.duoshilin.java_design_patter.chain;

/**
 * Created by duoshilin on 2019/2/14.
 * 部门经理
 */
public class DeptManager extends Leader{


    public DeptManager(GeneralManager generalManager) {
        this.roleName = "部门经理";
        this.nextLeader = generalManager;
    }

    @Override
    public void doRequest(double money) {
        if (money < 5000){
            System.out.println("【"+this.roleName+"】同意了申请资金："+money+"元");
        }else {
            System.out.println("资金:"+money+"元，数目过大，【"+roleName+"】无权审批，已交于"+nextLeader.roleName+"审批！");
            nextLeader.doRequest(money);
        }
    }
}
