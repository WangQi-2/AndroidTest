package com.wq.androidtest.gson;

public class TopApplistItemGson {
    private Integer appid;
    private Integer filesize;
    
    public Integer getFilesize() {
        return filesize;
    }
    
    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }
    
    public String getLastversion() {
        return lastversion;
    }
    
    public void setLastversion(String lastversion) {
        this.lastversion = lastversion;
    }
    
    public String getPackagename() {
        return packagename;
    }
    
    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }
    
    private String title;
    private String logo;
    private double score;
    private Integer downtimes;
    private String lastversion;
    private String packagename;
    
    public Integer getAppid() {
        return appid;
    }
    
    public void setAppid(Integer appid) {
        this.appid = appid;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getLogo() {
        return logo;
    }
    
    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    public double getScore() {
        return score;
    }
    
    public void setScore(double score) {
        this.score = score;
    }
    
    public int getDowntimes() {
        return downtimes;
    }
    
    public void setDowntimes(Integer downtimes) {
        this.downtimes = downtimes;
    }
    
}
