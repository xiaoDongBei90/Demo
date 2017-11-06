package com.example.lixiaowei.retrofitdemo;

/**
 * @ author  LiXiaoWei
 * @ date  2017/10/30.
 * desc:
 */

public class AnswerListBean {
    private int commentId;
    private int userId;
    private String createTime;
    private int id;
    private String title;
    private int tag;
    private String content;
    private int status;
    private boolean commented;

    public int getCommentId() {
        return commentId;
    }

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

    public int getStatus() {
        return status;
    }

    public boolean isCommented() {
        return commented;
    }
}
