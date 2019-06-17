package com.nonexistentware.recipeappv2.Model;

public class UsefulItem {

    public String uName;
    public String uDescription;
    public String uImageLink;

    public UsefulItem() {
    }

    public UsefulItem(String uName, String uDescription, String uImageLink) {
        this.uName = uName;
        this.uDescription = uDescription;
        this.uImageLink = uImageLink;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuDescription() {
        return uDescription;
    }

    public void setuDescription(String uDescription) {
        this.uDescription = uDescription;
    }

    public String getuImageLink() {
        return uImageLink;
    }

    public void setuImageLink(String uImageLink) {
        this.uImageLink = uImageLink;
    }
}
