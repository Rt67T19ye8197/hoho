package com.example.hoho.data.entities;

public class Item {
    private int id;
    private String lng;
    private String lvl;
    private String article;
    private boolean check;
    private String world;
    private String trans;
    private String pron;
    private String text;
    private String textTrans;
    private String textPron;
    private String teg;

    public Item(int id, String lng, String lvl, String article, boolean check, String world, String trans, String pron,
                String text, String textTrans, String textPron, String teg){
        this.id = id;
        this.lng = lng;
        this.lvl = lvl;
        this.article = article;
        this.check = check;
        this.world = world;
        this.trans = trans;
        this.pron = pron;
        this.text = text;
        this.textTrans = textTrans;
        this.textPron = textPron;
        this.teg = teg;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextTrans() {
        return textTrans;
    }

    public void setTextTrans(String textTrans) {
        this.textTrans = textTrans;
    }

    public String getTextPron() {
        return textPron;
    }

    public void setTextPron(String textPron) {
        this.textPron = textPron;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getPron() {
        return pron;
    }

    public void setPron(String pron) {
        this.pron = pron;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLLA() {
        return lng + "," + lvl + "," + article;
    }

    public String getTeg() {
        return teg;
    }

    public void setTeg(String teg) {
        this.teg = teg;
    }
}
