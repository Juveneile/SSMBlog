package com.hxx.entity;

/**
 * @ClassName Blogger
 * @Description //TODO 博主实体
 * @Author haoxx
 * @Date 2019/4/3 15:11
 * @Version 1.0
 */
public class Blogger {
    /*
        禁用基本数据类型，因为会默认赋值为0，进而干扰数据库引用NULL
   */
    private Integer id; // 编号
    private String userName; // 用户名
    private String password; // 密码
    private String profile; // 个人简介
    private String nickName; // 昵称
    private String sign; // 个性签名
    private String imageName; // 头像

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Blogger{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
