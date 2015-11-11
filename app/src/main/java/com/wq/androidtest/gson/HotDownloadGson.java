package com.wq.androidtest.gson;

import java.util.ArrayList;
import java.util.List;

public class HotDownloadGson {
    private String errcode;
    private String message;
    private List<TopApplistItemGson> topapplist = new ArrayList<TopApplistItemGson>();
    
    public String getErrcode() {
        return errcode;
    }
    
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public List<TopApplistItemGson> getTopapplist() {
        return topapplist;
    }
    
    public void setTopapplist(List<TopApplistItemGson> topapplist) {
        this.topapplist = topapplist;
    }
    
}
