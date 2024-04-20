package org.me.gcu.gilmour_tyler_s2340365_vr12;

public class Thing {
    private String title;
    private String description;
    private String pubDate;


    public void SetTitle(String title){
        this.title = title;
    }

    public void SetDescription(String description) {
        this.description = description;
    }

    public void SetPubDate(String pubDate) {
        this.pubDate = pubDate;
    }




    public String GeTitle() { return title; }
    public String GetDescription() { return description; }
    public String GetPubDate() { return pubDate; }



    public String toString() {
        return "\n"+"Day:"  + "\n"+ title + "\n" + "\n" +"Weather" + "\n"+ description + "\n" +"\n"+ "Date: " + pubDate;
    }
}
