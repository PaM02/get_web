package com.example.getwebsite.utilies;

import android.content.Context;
import android.content.SharedPreferences;

public class SesssionManager {
    //initialise les variables
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //creer le constructeur
    public SesssionManager(Context context){

        sharedPreferences = context.getSharedPreferences("AppKey",0);
        editor = sharedPreferences.edit();
        editor.apply();

    }

    //create set login methode
    public  void setLogin(boolean login){

        editor.putBoolean("KEY_LOGIN",login);
        editor.commit();

    }

    //create get login methode
    public  boolean getLogin(){

        return sharedPreferences.getBoolean("KEY_LOGIN",false);

    }

    //create set username methode
    public  void setUsername(String username){

        editor.putString("KEY_USERNAME",username);
        editor.commit();

    }

    //create get username methode
    public  String getUsername(){

        return sharedPreferences.getString("KEY_USERNAME","");

    }

    //create set prenom methode
    public  void setPrenom(String prenom){

        editor.putString("KEY_PRENOM",prenom);
        editor.commit();

    }

    //create get prenom methode
    public  String getPrenom(){

        return sharedPreferences.getString("KEY_PRENOM","");

    }

    //create set nom methode
    public  void setNom(String nom){

        editor.putString("KEY_NOM",nom);
        editor.commit();

    }

    //create get nom methode
    public  String getNom(){

        return sharedPreferences.getString("KEY_NOM","");

    }



}

