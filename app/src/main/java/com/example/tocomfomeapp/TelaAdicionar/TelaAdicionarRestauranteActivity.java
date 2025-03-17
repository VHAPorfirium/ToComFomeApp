package com.example.tocomfomeapp.TelaAdicionar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

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

    // Guardará a URI da foto (com permissão persistente)
    private String selectedFotoUri = null;

    // Launcher para selecionar imagem via ACTION_OPEN_DOCUMENT
    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    if (uri != null) {
                        // Garante a permissão persistente para ler a URI futuramente
                        final int takeFlags = result.getData().getFlags()
                                & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        // Salva a URI como string
                        selectedFotoUri = uri.toString();

                        // Exibe a imagem localmente
                        imgFoto.setImageURI(uri);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_adicionar);

        // Ajusta o padding para as barras do sistema (opcional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vinculação dos componentes
        edtNomeRestaurante = findViewById(R.id.edtNomeRestaurante);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtBairro = findViewById(R.id.edtBairro);
        edtCidade = findViewById(R.id.edtCidade);
        edtDescricao = findViewById(R.id.edtDescricao);
        ratingEstrelas = findViewById(R.id.ratingEstrelas);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnAdicionarFoto = findViewById(R.id.btnAdicionarFoto);
        imgFoto = findViewById(R.id.imgFoto);

        // Botão para selecionar a imagem
        btnAdicionarFoto.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            pickImageLauncher.launch(intent);
        });

        // Botão para salvar
        btnSalvar.setOnClickListener(view -> {
            String nome = edtNomeRestaurante.getText().toString().trim();
            String endereco = edtEndereco.getText().toString().trim();
            String bairro = edtBairro.getText().toString().trim();
            String cidade = edtCidade.getText().toString().trim();
            String descricao = edtDescricao.getText().toString().trim();
            float estrelas = ratingEstrelas.getRating();

            if (nome.isEmpty() || endereco.isEmpty()) {
                Toast.makeText(this, "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show();
                return;
            }

            Restaurante novoRestaurante = new Restaurante(nome, endereco, bairro, cidade, descricao, estrelas);
            // Define a URI da foto (pode ser null se o usuário não selecionou)
            novoRestaurante.setFotoUri(selectedFotoUri);

            // Salva no repositório em memória
            RestauranteRepository.adicionarRestaurante(novoRestaurante);

            // Vai para a tela de visualização
            Intent intent = new Intent(TelaAdicionarRestauranteActivity.this, TelaVisualizarRestaurantesActivity.class);
            startActivity(intent);
            finish();
        });
    }
}