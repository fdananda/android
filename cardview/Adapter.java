package com.fdananda.gitcardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MinhaViewHolder> {

    private List<Cardview> listCardview;

    public Adapter(List<Cardview> cardviews) {

        this.listCardview = cardviews;
    }

    @NonNull
    @Override
    public MinhaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Criação da visualização
        View itemCardview = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter, parent, false);
        return new MinhaViewHolder(itemCardview);
    }

    @Override
    public void onBindViewHolder(@NonNull MinhaViewHolder holder, int position) {

        Cardview cardview = listCardview.get(position);

        holder.textTitulo.setText(cardview.getTitulo());
        holder.textAutor.setText(cardview.getAutor());
        holder.textDescricao.setText(cardview.getDescricao());
        holder.imagem.setImageResource(cardview.getImagem());
    }

    @Override
    public int getItemCount() {
        return listCardview.size();
    }

    public class MinhaViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitulo;
        private TextView textDescricao;
        private TextView textAutor;
        private ImageView imagem;

        public MinhaViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitulo  =itemView.findViewById(R.id.textViewTitulo);
            textDescricao  =itemView.findViewById(R.id.textViewDescricao);
            textAutor  =itemView.findViewById(R.id.textViewAutor);
            imagem  =itemView.findViewById(R.id.imageViewImagem);
        }
    }
}
