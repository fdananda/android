package com.fdananda.gitlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class AdapterCustomizado extends ArrayAdapter<Objeto> {

    private Context context;
    private final ArrayList<Objeto> objetos;

    public AdapterCustomizado(Context context, ArrayList<Objeto> objetos){
        super(context, R.layout.adapter, objetos);
        this.context = context;
        this.objetos = objetos;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewPersonalizada = inflater.inflate(R.layout.adapter, parent, false);

        TextView atributo1 = (TextView) viewPersonalizada.findViewById(R.id.TextViewAtributo1);
        TextView atributo2 = (TextView) viewPersonalizada.findViewById(R.id.TextViewDescricao);

        atributo1.setText(objetos.get(position).getAtributo1());
        atributo2.setText(objetos.get(position).getAtributo2());

        return viewPersonalizada;
    }
}
