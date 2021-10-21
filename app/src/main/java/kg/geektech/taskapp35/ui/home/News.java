package kg.geektech.taskapp35.ui.home;

import java.io.Serializable;

public class News implements Serializable {

    public News() {
    }

    private String title;
    private String time;



    public News(String title,String time) {
        this.title = title;
        this.time = time;
    }

    public News(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
}
