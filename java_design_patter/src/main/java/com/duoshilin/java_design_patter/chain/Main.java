package com.duoshilin.java_design_patter.chain;

/**
 * Created by duoshilin on 2019/2/14.
 * 责任链模式
 *
 */
public class Main {

    public static void main(String[] args) {
        GeneralManager generalManager = new GeneralManager();
        DeptManager deptManager = new DeptManager(generalManager);
        TeamLeader teamLeader = new TeamLeader(deptManager);

        teamLeader.doRequest(800);
        teamLeader.doRequest(3000);
        teamLeader.doRequest(9800);
        teamLeader.doRequest(19800);
    }
}
