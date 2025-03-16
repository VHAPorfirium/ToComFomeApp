package com.example.tocomfomeapp.TelaAdicionar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tocomfomeapp.Model.Restaurante;
import com.example.tocomfomeapp.Repository.RestauranteRepository;
import com.example.tocomfomeapp.R;
import com.example.tocomfomeapp.TelaVisualizar.TelaVisualizarRestaurantesActivity;

public class TelaAdicionarRestauranteActivity extends AppCompatActivity {

    // Usando os IDs conforme definidos no XML enviado
    private EditText edtNomeRestaurante, edtEndereco, edtBairro, edtCidade, edtDescricao;
    private RatingBar ratingEstrelas;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_adicionar);

        // O layout root possui o id "main" conforme seu XML
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vinculação dos componentes com os IDs do XML
        edtNomeRestaurante = findViewById(R.id.edtNomeRestaurante);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtBairro = findViewById(R.id.edtBairro);
        edtCidade = findViewById(R.id.edtCidade);
        edtDescricao = findViewById(R.id.edtDescricao);
        ratingEstrelas = findViewById(R.id.ratingEstrelas);
        btnSalvar = findViewById(R.id.btnSalvar);

        // Ao clicar em Salvar, cria o objeto e adiciona ao repositório em memória
        btnSalvar.setOnClickListener(view -> {
            String nome = edtNomeRestaurante.getText().toString().trim();
            String endereco = edtEndereco.getText().toString().trim();
            String bairro = edtBairro.getText().toString().trim();
            String cidade = edtCidade.getText().toString().trim();
            String descricao = edtDescricao.getText().toString().trim();
            float estrelas = ratingEstrelas.getRating();

            Restaurante novoRestaurante = new Restaurante(nome, endereco, bairro, cidade, descricao, estrelas);

            // Adiciona o restaurante ao repositório em memória
            RestauranteRepository.adicionarRestaurante(novoRestaurante);

            // Abre a tela de visualização dos restaurantes
            Intent intent = new Intent(TelaAdicionarRestauranteActivity.this, TelaVisualizarRestaurantesActivity.class);
            startActivity(intent);

            // Fecha a Activity atual
            finish();
        });
    }
}
