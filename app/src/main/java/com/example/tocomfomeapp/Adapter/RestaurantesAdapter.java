package com.example.tocomfomeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tocomfomeapp.R;
import com.example.tocomfomeapp.TelaDetalheRestaurante.TelaDetalhesRestauranteActivity;
import com.example.tocomfomeapp.Model.Restaurante;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class RestaurantesAdapter extends RecyclerView.Adapter<RestaurantesAdapter.ViewHolder> {

    private List<Restaurante> listaRestaurantes;
    private Context context;

    // Construtor do Adapter
    public RestaurantesAdapter(List<Restaurante> listaRestaurantes, Context context) {
        this.listaRestaurantes = listaRestaurantes;
        this.context = context;
    }

    // Classe interna ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRestaurante;
        TextView txtNomeRestaurante;
        RatingBar ratingEstrelas;

        public ViewHolder(View itemView) {
            super(itemView);
            imgRestaurante = itemView.findViewById(R.id.imgRestaurante);
            txtNomeRestaurante = itemView.findViewById(R.id.txtNomeRestaurante);
            ratingEstrelas = itemView.findViewById(R.id.ratingEstrelas);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla o layout do item (item_restaurante.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantesAdapter.ViewHolder holder, int position) {
        // Pega o restaurante da lista
        final Restaurante restaurante = listaRestaurantes.get(position);

        // Seta os valores nos componentes do layout
        holder.txtNomeRestaurante.setText(restaurante.getNome());
        holder.ratingEstrelas.setRating(restaurante.getEstrelas());

        // Exemplo de imagem "placeholder".
        holder.imgRestaurante.setImageResource(R.drawable.ic_baseline_camera_alt_24);

        // Clique no item para abrir a tela de detalhes
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TelaDetalhesRestauranteActivity.class);

            // Passando dados via Intent
            intent.putExtra("NOME_RESTAURANTE", restaurante.getNome());
            intent.putExtra("ENDERECO_RESTAURANTE", restaurante.getEndereco());
            intent.putExtra("BAIRRO_RESTAURANTE", restaurante.getBairro());
            intent.putExtra("CIDADE_RESTAURANTE", restaurante.getCidade());
            intent.putExtra("DESCRICAO_RESTAURANTE", restaurante.getDescricao());
            intent.putExtra("RATING_RESTAURANTE", restaurante.getEstrelas());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaRestaurantes.size();
    }
}
