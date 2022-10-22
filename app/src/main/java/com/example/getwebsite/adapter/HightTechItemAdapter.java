package com.example.getwebsite.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.getwebsite.R;
import com.example.getwebsite.models.HightTechitem;

import java.util.List;

public class HightTechItemAdapter extends BaseAdapter {

    //attribut
    private Context context;
    private List<HightTechitem> hightTechitemList;
    private LayoutInflater inflater;

    public HightTechItemAdapter(Context context, List<HightTechitem> hightTechitemList) {

        this.context = context;
        this.hightTechitemList = hightTechitemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return hightTechitemList.size();
    }

    @Override
    public HightTechitem getItem(int position) {

        return hightTechitemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"SetTextI18n", "ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = inflater.inflate(R.layout.adapter_item,null);

        //recuperer les informations à propos de l'item
        HightTechitem currentItem = getItem(position);
        final String itemDate = currentItem.getDate();
        final String itemCode = currentItem.getCode();
        final String itemCompteur = currentItem.getCompteur();
        final String itemMontant = currentItem.getMontant();
        final String itemEnergie = currentItem.getEnergie();

        //get item dare view
        TextView itemNameView = view.findViewById(R.id.item_date);
        itemNameView.setText(itemDate);

        //get item code View
        TextView itemCodeView = view.findViewById(R.id.item_code);
        itemCodeView.setText("Code: "+itemCode);

        //get item compteur View
        TextView itemCompteurView = view.findViewById(R.id.item_compteur);
        itemCompteurView.setText("Numéro du compteur : "+itemCompteur);

        //get item montant View
        TextView itemMontantView = view.findViewById(R.id.item_montant);
        itemMontantView.setText("Montant : "+itemMontant+" FCFA");

        //get item energie View
        TextView itemEnergieView = view.findViewById(R.id.item_energie);
        itemEnergieView.setText("Energie : "+itemEnergie+ "kwh");

        return view;
    }
}
