package com.example.tocomfomeapp.TelaAdicionar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

    private EditText edtNomeRestaurante, edtEndereco, edtBairro, edtCidade, edtDescricao;
    private RatingBar ratingEstrelas;
    private Button btnSalvar, btnAdicionarFoto;
    private ImageView imgFoto;

    // Launcher para selecionar imagem da galeria
    private ActivityResultLauncher<Intent> photoPickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_adicionar);

        // Ajuste de padding para as barras do sistema
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
        btnAdicionarFoto = findViewById(R.id.btnAdicionarFoto);
        imgFoto = findViewById(R.id.imgFoto);

        // Configura o launcher para selecionar imagem da galeria
        photoPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        // Exibe a imagem selecionada no ImageView
                        imgFoto.setImageURI(selectedImageUri);
                    }
                }
        );

        // Abre a galeria ao clicar no botão de adicionar foto
        btnAdicionarFoto.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            photoPickerLauncher.launch(intent);
        });

        // Ao clicar em Salvar, cria o objeto Restaurante e adiciona no repositório em memória
        btnSalvar.setOnClickListener(view -> {
            String nome = edtNomeRestaurante.getText().toString().trim();
            String endereco = edtEndereco.getText().toString().trim();
            String bairro = edtBairro.getText().toString().trim();
            String cidade = edtCidade.getText().toString().trim();
            String descricao = edtDescricao.getText().toString().trim();
            float estrelas = ratingEstrelas.getRating();

            Restaurante novoRestaurante = new Restaurante(nome, endereco, bairro, cidade, descricao, estrelas);
            RestauranteRepository.adicionarRestaurante(novoRestaurante);

            // Abre a tela de Visualizar Restaurantes
            Intent intent = new Intent(TelaAdicionarRestauranteActivity.this, TelaVisualizarRestaurantesActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
