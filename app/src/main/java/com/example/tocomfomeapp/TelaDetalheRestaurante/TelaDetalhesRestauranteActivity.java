package com.example.tocomfomeapp.TelaDetalheRestaurante;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tocomfomeapp.R;
import com.example.tocomfomeapp.Repository.RestauranteRepository;
import com.example.tocomfomeapp.TelaEditar.TelaEditarActivity;

import java.io.InputStream;

public class TelaDetalhesRestauranteActivity extends AppCompatActivity {

    private ImageView imgRestauranteDetalhe;
    private TextView txtNomeRestauranteDetalhe;
    private TextView txtEnderecoDetalhe;
    private TextView txtBairroDetalhe;
    private TextView txtCidadeDetalhe;
    private TextView txtDescricaoDetalhe;
    private RatingBar ratingEstrelasDetalhe;
    private Button btnEditar, btnExcluir;
    private int index = -1; // índice do restaurante na lista

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_restaurante);

        imgRestauranteDetalhe = findViewById(R.id.imgRestauranteDetalhe);
        txtNomeRestauranteDetalhe = findViewById(R.id.txtNomeRestauranteDetalhe);
        txtEnderecoDetalhe = findViewById(R.id.txtEnderecoDetalhe);
        txtBairroDetalhe = findViewById(R.id.txtBairroDetalhe);
        txtCidadeDetalhe = findViewById(R.id.txtCidadeDetalhe);
        txtDescricaoDetalhe = findViewById(R.id.txtDescricaoDetalhe);
        ratingEstrelasDetalhe = findViewById(R.id.ratingEstrelasDetalhe);
        btnEditar = findViewById(R.id.button4);
        btnExcluir = findViewById(R.id.button5);

        Intent intent = getIntent();
        if (intent != null) {
            txtNomeRestauranteDetalhe.setText(intent.getStringExtra("NOME_RESTAURANTE"));
            txtEnderecoDetalhe.setText(intent.getStringExtra("ENDERECO_RESTAURANTE"));
            txtBairroDetalhe.setText(intent.getStringExtra("BAIRRO_RESTAURANTE"));
            txtCidadeDetalhe.setText(intent.getStringExtra("CIDADE_RESTAURANTE"));
            txtDescricaoDetalhe.setText(intent.getStringExtra("DESCRICAO_RESTAURANTE"));
            ratingEstrelasDetalhe.setRating(intent.getFloatExtra("RATING_RESTAURANTE", 0f));
            String fotoUri = intent.getStringExtra("FOTO_URI");
            index = intent.getIntExtra("INDEX", -1);

            if (fotoUri != null && !fotoUri.isEmpty()) {
                try {
                    Uri imageUri = Uri.parse(fotoUri);
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    imgRestauranteDetalhe.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                    imgRestauranteDetalhe.setImageResource(R.drawable.ic_baseline_camera_alt_24);
                }
            } else {
                imgRestauranteDetalhe.setImageResource(R.drawable.ic_baseline_camera_alt_24);
            }
        }

        // Botão Editar: lança a Activity de edição (a ser implementada)
        btnEditar.setOnClickListener(v -> {
            if (index != -1) {
                Intent editIntent = new Intent(TelaDetalhesRestauranteActivity.this, TelaEditarActivity.class);
                // Passa o índice e os dados atuais para pré-preencher a tela de edição
                editIntent.putExtra("INDEX", index);
                editIntent.putExtra("NOME_RESTAURANTE", txtNomeRestauranteDetalhe.getText().toString());
                editIntent.putExtra("ENDERECO_RESTAURANTE", txtEnderecoDetalhe.getText().toString());
                editIntent.putExtra("BAIRRO_RESTAURANTE", txtBairroDetalhe.getText().toString());
                editIntent.putExtra("CIDADE_RESTAURANTE", txtCidadeDetalhe.getText().toString());
                editIntent.putExtra("DESCRICAO_RESTAURANTE", txtDescricaoDetalhe.getText().toString());
                editIntent.putExtra("RATING_RESTAURANTE", ratingEstrelasDetalhe.getRating());
                editIntent.putExtra("FOTO_URI", intent.getStringExtra("FOTO_URI"));
                startActivity(editIntent);
            } else {
                Toast.makeText(this, "Erro: índice inválido", Toast.LENGTH_SHORT).show();
            }
        });

        // Botão Excluir: remove o restaurante e volta à tela de visualização
        btnExcluir.setOnClickListener(v -> {
            if (index != -1) {
                RestauranteRepository.removerRestaurante(index);
                Toast.makeText(this, "Restaurante excluído", Toast.LENGTH_SHORT).show();
                finish(); // Fecha a tela de detalhes
            } else {
                Toast.makeText(this, "Erro: índice inválido", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
