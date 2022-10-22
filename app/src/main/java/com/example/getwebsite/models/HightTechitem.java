package com.example.getwebsite.models;

public class HightTechitem {

    //attribut
    private  String date,code,compteur,montant,energie;
    public HightTechitem(String date,String code,String compteur,String montant,String energie) {
        this.date = date;
        this.code = code;
        this.compteur = compteur;
        this.montant = montant;
        this.energie = energie;
    }

    public String getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public String getCompteur() {
        return compteur;
    }

    public String getMontant() {
        return montant;
    }

    public String getEnergie() {
        return energie;
    }


}
