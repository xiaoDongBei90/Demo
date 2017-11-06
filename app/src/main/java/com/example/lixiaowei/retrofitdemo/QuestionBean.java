package com.example.lixiaowei.retrofitdemo;

import java.util.List;

/**
 * @ author  LiXiaoWei
 * @ date  2017/10/30.
 * desc:
 */

public class QuestionBean {
    private int userId;
    private String createTime;
    private int id;
    private String title;
    private int tag;
    private String content;
    private int attitudeSum;
    private String nickname;
    private String avatar;
    private boolean commented;
    private int myAttitude;
    private int status;
    private String link;
    private List<ImageListBean> imgList;
    private List<AnswerListBean> commentList;

    public int getUserId() {
        return userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getTag() {
        return tag;
    }

    public String getContent() {
        return content;
    }

    public int getAttitudeSum() {
        return attitudeSum;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isCommented() {
        return commented;
    }

    public int getMyAttitude() {
        return myAttitude;
    }

    public int getStatus() {
        return status;
    }

    public String getLink() {
        return link;
    }

    public List<ImageListBean> getImgList() {
        return imgList;
    }

    public List<AnswerListBean> getCommentList() {
        return commentList;
    }
}
