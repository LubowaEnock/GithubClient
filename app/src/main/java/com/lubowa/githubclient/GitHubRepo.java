package com.lubowa.githubclient;

class GitHubRepo {
    private String title, userId, id, body;

    public GitHubRepo(String title, String userId, String id, String body) {
        this.title = title;
        this.userId = userId;
        this.id = id;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
