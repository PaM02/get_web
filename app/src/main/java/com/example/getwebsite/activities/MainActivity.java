package com.example.getwebsite.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import com.example.getwebsite.DateTime.DateTime;
import com.example.getwebsite.R;
import com.example.getwebsite.models.HightTechitem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("StaticFieldLeak")
    public  static TextView textView;
    public  static String string;
    EditText editText;
    String compteur;
    String date;
    static MainActivity mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        string = "";
        textView =  findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        mainActivity = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchData process = new fetchData();
                process.execute();


            }
        });
        Button button3 =findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });

        //loadData();



    }

    public class fetchData extends AsyncTask<String,String,String> {
        String data ="";
        Boolean aBoolean=false;
        ProgressDialog progress;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        String montant="";
        String msg="";
        String senergie="";

        @Override
        protected void onPreExecute() {

            progress = ProgressDialog.show(MainActivity.this, "","Merci de patienter..", true);

        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... strings) {

            compteur = "47000564758";

            montant=editText.getText().toString();

            if (montant.equals("")){

                msg="merci de remplir le montant";
            }
            else {

                if (Float.parseFloat(montant)<15){

                    msg ="le montant que vous avez saisi est trop petite";
                }
                else {
                    float energie = Float.parseFloat(montant)/141;
                    senergie=""+energie;

                    if (senergie.length()>3){

                        senergie = senergie.substring(0,(senergie.indexOf('.')+2));
                    }

                    try {
                        // on concaténe les donnees dan url
                        URL url = new URL("http://51.75.250.106:81/PHP_Demo/Gettoken.php?meterid="+compteur+"&&amount="+senergie);

                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String line = "";
                        while(line != null){
                            line = bufferedReader.readLine();
                            data = data + line;
                        }

                        data = data.substring((data.indexOf("\"result\":\"")+10),160);
                        aBoolean = true;


                    } catch (MalformedURLException e) {
                        aBoolean=false;
                        msg = "il y'a un probleme de connexion";

                        e.printStackTrace();
                    } catch (IOException e) {
                        aBoolean=false;
                        msg = "il y'a un probleme de connexion";
                        e.printStackTrace();
                    }

                }

            }

            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            //je rend invisible le  progressBar
            progress.dismiss();

            if (aBoolean){

                //si tout est ok j'ouvre une nouvele fenetre
                textView.setText(data);
                string=""+data;

                builder.setTitle("Paiement réalisé avec succés.");
                builder.setMessage("Numéro à charger sur \ncompteur N° : "+compteur+"\nCode : "+data);
                builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
                date= DateTime.getDateEN();
                Main2Activity.hightTechitemList.add(new HightTechitem(""+date,""+data,""+compteur,""+montant,""+senergie));
               saveData();

            }

            else{

                //sinon le toast message
                //si tout est ok j'ouvre une nouvele fenetre
                textView.setText(s);

            }

        }
    }

  public void saveData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("AppKey",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Main2Activity.hightTechitemList);
        editor.putString("task list", json);
        editor.apply();
    }

    static void loadData() {
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("AppKey",0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<HightTechitem>>() {}.getType();
        Main2Activity.hightTechitemList = gson.fromJson(json, type);

    }

}
