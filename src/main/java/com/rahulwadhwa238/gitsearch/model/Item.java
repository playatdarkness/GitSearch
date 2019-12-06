package com.rahulwadhwa238.gitsearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("avatar_url")
    @Expose
    private String avatarURL;

    @SerializedName("html_url")
    @Expose
    private String linkURL;

    public Item(String login, String avatarURL, String linkURL, List description) {
        this.login = login;
        this.avatarURL = avatarURL;
        this.linkURL = linkURL;
    }

    public String getLogin()    {
        return login;
    }
    public void setLogin(String login)  {
        this.login = login;
    }

    public String getAvatarURL()    {
        return avatarURL;
    }
    public void setAvatarURL(String avatarURL)  {
        this.avatarURL = avatarURL;
    }

    public String getLinkURL()  {
        return linkURL;
    }
    public void setLinkURL(String linkURL)  {
        this.linkURL = linkURL;
    }

}
