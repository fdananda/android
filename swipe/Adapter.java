package com.fdananda.gitswipe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Objeto> listaObjetos;

    public Adapter(List<Objeto> lista) {
        this.listaObjetos = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Criação da visualização
        View objetoLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter, parent, false);
        return new MyViewHolder(objetoLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //Exibição dos itens
        Objeto objeto = listaObjetos.get(position);

        holder.atributo1.setText(objeto.getAtributo1());
        holder.atributo2.setText(objeto.getAtributo2());
        holder.atributo3.setText(objeto.getAtributo3());
    }

    @Override
    public int getItemCount() {
        return listaObjetos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView atributo1;
        TextView atributo2;
        TextView atributo3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            atributo1   = itemView.findViewById(R.id.textViewAtributo1);
            atributo2   = itemView.findViewById(R.id.textViewAtributo2);
            atributo3   = itemView.findViewById(R.id.textViewAtributo3);
        }
    }
}
