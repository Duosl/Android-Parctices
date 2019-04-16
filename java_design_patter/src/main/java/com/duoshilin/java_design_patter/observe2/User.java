package com.duoshilin.java_design_patter.observe2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duoshilin on 2019/2/13.
 * 用户类 -- 实现用户之间互相关注，发送消息时，关注者收到提示
 */
public class User {

    private String username;    //用户名称
    private int articleNum = 0; //文章数
    private List<User> fans = new ArrayList<>();    //用户粉丝
    private List<User> followers = new ArrayList<>();   //用户关注的人

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public int getArticleNum() {
        return articleNum;
    }

    public void addArticleNum() {
        articleNum++;
        System.out.println(this.username+"发表了一篇文章");
        notifyAllFans();
    }

    public void getANewMessage(User user){
        System.out.println(this.username+"收到了"+user.getUsername()+"发表的一篇新文章！");
    }

    public void follow(User user){
        this.followers.add(user);
        user.fans.add(this);
        System.out.println(this.username+"关注了"+user.getUsername());
    }

    public void unfollow(User user){
        this.followers.remove(user);
        user.fans.remove(this);
        System.out.println(this.username+"取消关注了"+user.getUsername());
    }

    public void notifyAllFans(){
        for (User fan: fans) {
            fan.getANewMessage(this);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("---------------------------------------\n");
        sb.append("用户名： "+this.username+"\n");
        sb.append("文章数： "+this.articleNum+"\n");
        sb.append("粉丝："+fans.size()+"个\n");
        if (fans.size()>0){
//            sb.append("\t用户名\t\t文章数量\n");
            for(User fan : fans){
                sb.append("\t"+fan.getUsername()+"\t\t"+fan.getArticleNum()+"篇文章\n");
            }
        }
        sb.append("您关注了： "+followers.size()+"个");
        if (followers.size()>0){
//            sb.append("\t用户名\t\t文章数量\n");
            sb.append("\n");
            for(User follower : followers){
                sb.append("\t"+follower.getUsername()+"\t\t"+follower.getArticleNum()+"篇文章\n");
            }
        }
        return sb.toString().substring(0,sb.lastIndexOf("\n"));
    }
}
