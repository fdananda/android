package com.fdananda.crudsqlite2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class AdapterCustomizado extends ArrayAdapter<Atributo> {

    private Context context;
    private final ArrayList<Atributo> atributos;

    public AdapterCustomizado(Context context, ArrayList<Atributo> atributos){
        super(context, R.layout.adapter, atributos);
        this.context = context;
        this.atributos = atributos;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewPersonalizada = inflater.inflate(R.layout.adapter, parent, false);

        TextView id = (TextView) viewPersonalizada.findViewById(R.id.textViewId);
        TextView atributo1 = (TextView) viewPersonalizada.findViewById(R.id.textViewAtributo1);
        TextView atributo2 = (TextView) viewPersonalizada.findViewById(R.id.textViewAtributo2);
        TextView atributo3 = (TextView) viewPersonalizada.findViewById(R.id.textViewAtributo3);

        atributo1.setText(atributos.get(position).getAtributo1());
        atributo2.setText(atributos.get(position).getAtributo2());
        atributo3.setText(atributos.get(position).getAtributo3());
        id.setText(String.valueOf(atributos.get(position).getId()));

        return viewPersonalizada;
    }
}
