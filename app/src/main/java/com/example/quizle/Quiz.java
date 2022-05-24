package com.example.quizle;

public class Quiz {
    private String key;
    private String judul;
    private String subjudul;

    public Quiz(){

    }

    public Quiz(String judul, String subjudul) {
        this.judul = judul;
        this.subjudul = subjudul;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getSubjudul() {
        return subjudul;
    }

    public void setSubjudul(String subjudul) {
        this.subjudul = subjudul;
    }
}