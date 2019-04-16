package com.duoshilin.java_design_patter.chain;

/**
 * Created by duoshilin on 2019/2/14.
 */
public class TeamLeader extends Leader {

    public TeamLeader(DeptManager deptManager) {
        this.roleName = "组长";
        this.nextLeader = deptManager;
    }

    @Override
    public void doRequest(double money) {
        if (money < 1000){
            System.out.println("【"+this.roleName+"】同意了申请资金："+money+"元");
        }else {
            System.out.println("资金:"+money+"元，数目过大，【"+roleName+"】无权审批，已交于"+nextLeader.roleName+"审批！");
            nextLeader.doRequest(money);
        }
    }
}
