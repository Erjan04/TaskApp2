package kg.geektech.taskapp35.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {

    private final SharedPreferences preferences;

    public Prefs(Context context){
        preferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);
    }
    public void saveBoardsState(){
        preferences.edit().putBoolean("IsShown",true).apply();
    }

    public boolean isShown(){
        return preferences.getBoolean("IsShown",false);
    }
    public void saveAvatar(Uri image){
        preferences.edit().putString("image",image.toString()).apply();
    }
    public String getAvatar(){
        return preferences.getString("image","");
    }
    public void saveEditText(String name){
        preferences.edit().putString("name",name).apply();
    }
    public String getEditText(){
        return preferences.getString("name","");
    }
}
