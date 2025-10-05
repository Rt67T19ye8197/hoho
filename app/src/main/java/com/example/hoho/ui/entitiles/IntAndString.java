package com.example.hoho.ui.entitiles;

public class IntAndString {

    private Integer idDrawable;
    private String teg;
    public IntAndString(Integer idDrawable, String teg){
        this.idDrawable = idDrawable;
        this.teg = teg;
    }


    public Integer getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(Integer idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String getTeg() {
        return teg;
    }

    public void setTeg(String teg) {
        this.teg = teg;
    }
}
