package com.example.tocomfomeapp.TelaDetalheRestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tocomfomeapp.R;

public class TelaDetalhesRestauranteActivity extends AppCompatActivity {

    private ImageView imgRestauranteDetalhe;
    private TextView txtNomeRestauranteDetalhe;
    private TextView txtEnderecoDetalhe;
    private TextView txtBairroDetalhe;
    private TextView txtCidadeDetalhe;
    private TextView txtDescricaoDetalhe;
    private RatingBar ratingEstrelasDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_restaurante);

        // Vincular componentes
        imgRestauranteDetalhe = findViewById(R.id.imgRestauranteDetalhe);
        txtNomeRestauranteDetalhe = findViewById(R.id.txtNomeRestauranteDetalhe);
        txtEnderecoDetalhe = findViewById(R.id.txtEnderecoDetalhe);
        txtBairroDetalhe = findViewById(R.id.txtBairroDetalhe);
        txtCidadeDetalhe = findViewById(R.id.txtCidadeDetalhe);
        txtDescricaoDetalhe = findViewById(R.id.txtDescricaoDetalhe);
        ratingEstrelasDetalhe = findViewById(R.id.ratingEstrelasDetalhe);

        // Pegar dados enviados pela Intent
        Intent intent = getIntent();
        if (intent != null) {
            String nome = intent.getStringExtra("NOME_RESTAURANTE");
            String endereco = intent.getStringExtra("ENDERECO_RESTAURANTE");
            String bairro = intent.getStringExtra("BAIRRO_RESTAURANTE");
            String cidade = intent.getStringExtra("CIDADE_RESTAURANTE");
            String descricao = intent.getStringExtra("DESCRICAO_RESTAURANTE");
            float estrelas = intent.getFloatExtra("RATING_RESTAURANTE", 0f);

            // Setar os valores nos componentes
            txtNomeRestauranteDetalhe.setText(nome);
            txtEnderecoDetalhe.setText(endereco);
            txtBairroDetalhe.setText(bairro);
            txtCidadeDetalhe.setText(cidade);
            txtDescricaoDetalhe.setText(descricao);
            ratingEstrelasDetalhe.setRating(estrelas);

            imgRestauranteDetalhe.setImageResource(R.drawable.ic_baseline_camera_alt_24);
        }
    }
}
