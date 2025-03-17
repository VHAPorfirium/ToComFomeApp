package com.example.tocomfomeapp.Adapter;

// Importo as classes necessárias para manipular contexto, intents, imagens, views, etc.
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

    // Lista que contém os restaurantes que serão exibidos no RecyclerView
    private List<Restaurante> listaRestaurantes;
    // Contexto para criar as Views e iniciar as Activities
    private Context context;

    // Construtor que recebe a lista de restaurantes e o contexto
    public RestaurantesAdapter(List<Restaurante> listaRestaurantes, Context context) {
        this.listaRestaurantes = listaRestaurantes;
        this.context = context;
    }

    // ViewHolder: classe interna que contém as referências dos componentes de cada item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Declaro os componentes do layout do item (imagem, nome e RatingBar)
        ImageView imgRestaurante;
        TextView txtNomeRestaurante;
        RatingBar ratingEstrelas;

        // No construtor, faço a vinculação dos componentes com os IDs do layout item_restaurante.xml
        public ViewHolder(View itemView) {
            super(itemView);
            imgRestaurante = itemView.findViewById(R.id.imgRestaurante);
            txtNomeRestaurante = itemView.findViewById(R.id.txtNomeRestaurante);
            ratingEstrelas = itemView.findViewById(R.id.ratingEstrelas);
        }
    }

    //Método responsável por inflar o layout de cada item e criar o ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Uso o LayoutInflater para inflar o layout item_restaurante.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurante, parent, false);
        return new ViewHolder(view);
    }

    // Método que vincula os dados do objeto Restaurante à View (cada item)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Pego o restaurante na posição atual da lista
        final Restaurante restaurante = listaRestaurantes.get(position);

        // Preencho o TextView com o nome do restaurante
        holder.txtNomeRestaurante.setText(restaurante.getNome());
        // Defino a quantidade de estrelas no RatingBar
        holder.ratingEstrelas.setRating(restaurante.getEstrelas());

        // Verifico se existe uma URI de foto associada ao restaurante
        if (restaurante.getFotoUri() != null && !restaurante.getFotoUri().isEmpty()) {
            try {
                // Converto a string da URI para um objeto Uri
                Uri imageUri = Uri.parse(restaurante.getFotoUri());
                // Abro um InputStream para ler o conteúdo da URI usando o ContentResolver
                InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
                // Decodifico o InputStream em um Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                // Fecho o InputStream (se não for nulo)
                if (inputStream != null) {
                    inputStream.close();
                }
                // Defino o Bitmap no ImageView para exibição
                holder.imgRestaurante.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                // Em caso de erro, uso uma imagem placeholder
                holder.imgRestaurante.setImageResource(R.drawable.ic_baseline_camera_alt_24);
            }
        } else {
            // Se não houver URI, exibo o placeholder padrão
            holder.imgRestaurante.setImageResource(R.drawable.ic_baseline_camera_alt_24);
        }

        // Configuro o clique no item para abrir a tela de detalhes do restaurante
        // Além de passar os dados do restaurante via Intent, também envio o índice (posição)
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
            // Inicia a Activity de detalhes
            context.startActivity(intent);
        });
    }

    // Retorna a quantidade de itens da lista (número de restaurantes)
    @Override
    public int getItemCount() {
        return listaRestaurantes.size();
    }
}