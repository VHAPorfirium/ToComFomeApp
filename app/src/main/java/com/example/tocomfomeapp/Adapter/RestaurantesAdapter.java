package com.example.tocomfomeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import java.io.InputStream;
import java.util.List;

public class RestaurantesAdapter extends RecyclerView.Adapter<RestaurantesAdapter.ViewHolder> {

    private List<Restaurante> listaRestaurantes;
    private Context context;

    public RestaurantesAdapter(List<Restaurante> listaRestaurantes, Context context) {
        this.listaRestaurantes = listaRestaurantes;
        this.context = context;
    }

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Restaurante restaurante = listaRestaurantes.get(position);

        holder.txtNomeRestaurante.setText(restaurante.getNome());
        holder.ratingEstrelas.setRating(restaurante.getEstrelas());

        // Carrega a imagem manualmente
        if (restaurante.getFotoUri() != null && !restaurante.getFotoUri().isEmpty()) {
            try {
                Uri imageUri = Uri.parse(restaurante.getFotoUri());
                InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                if (inputStream != null) {
                    inputStream.close();
                }
                holder.imgRestaurante.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                holder.imgRestaurante.setImageResource(R.drawable.ic_baseline_camera_alt_24);
            }
        } else {
            holder.imgRestaurante.setImageResource(R.drawable.ic_baseline_camera_alt_24);
        }

        // Passa também o índice do item para a tela de detalhes
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TelaDetalhesRestauranteActivity.class);
            intent.putExtra("INDEX", position);
            intent.putExtra("NOME_RESTAURANTE", restaurante.getNome());
            intent.putExtra("ENDERECO_RESTAURANTE", restaurante.getEndereco());
            intent.putExtra("BAIRRO_RESTAURANTE", restaurante.getBairro());
            intent.putExtra("CIDADE_RESTAURANTE", restaurante.getCidade());
            intent.putExtra("DESCRICAO_RESTAURANTE", restaurante.getDescricao());
            intent.putExtra("RATING_RESTAURANTE", restaurante.getEstrelas());
            intent.putExtra("FOTO_URI", restaurante.getFotoUri());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaRestaurantes.size();
    }
}
