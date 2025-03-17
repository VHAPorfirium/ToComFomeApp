package com.example.tocomfomeapp.TelaEditar;

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

public class TelaEditarActivity extends AppCompatActivity {

    private EditText edtNomeRestaurante, edtEndereco, edtBairro, edtCidade, edtDescricao;
    private RatingBar ratingEstrelas;
    private Button btnSalvar, btnAdicionarFoto;
    private ImageView imgFoto;

    // Armazena a URI selecionada (como string)
    private String selectedFotoUri = null;

    // Launcher para selecionar imagem usando ACTION_OPEN_DOCUMENT
    private ActivityResultLauncher<Intent> pickImageLauncher;

    // Índice do restaurante a ser editado (passado via Intent)
    private int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_editar);

        // Ajusta padding para barras do sistema (opcional)
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

        // Configura o launcher para selecionar imagem
        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        if(uri != null) {
                            // Pede permissão persistente para ler a URI
                            final int takeFlags = result.getData().getFlags() &
                                    (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                            getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);

                            selectedFotoUri = uri.toString();
                            imgFoto.setImageURI(uri);
                        }
                    }
                }
        );

        btnAdicionarFoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            pickImageLauncher.launch(intent);
        });

        // Recebe dados da Intent (pré-preenchimento)
        Intent intent = getIntent();
        if(intent != null) {
            index = intent.getIntExtra("INDEX", -1);
            edtNomeRestaurante.setText(intent.getStringExtra("NOME_RESTAURANTE"));
            edtEndereco.setText(intent.getStringExtra("ENDERECO_RESTAURANTE"));
            edtBairro.setText(intent.getStringExtra("BAIRRO_RESTAURANTE"));
            edtCidade.setText(intent.getStringExtra("CIDADE_RESTAURANTE"));
            edtDescricao.setText(intent.getStringExtra("DESCRICAO_RESTAURANTE"));
            ratingEstrelas.setRating(intent.getFloatExtra("RATING_RESTAURANTE", 0f));

            String fotoUri = intent.getStringExtra("FOTO_URI");
            if(fotoUri != null && !fotoUri.isEmpty()) {
                try {
                    Uri imageUri = Uri.parse(fotoUri);
                    imgFoto.setImageURI(imageUri);
                    selectedFotoUri = fotoUri;
                } catch(Exception e) {
                    e.printStackTrace();
                    imgFoto.setImageResource(R.drawable.ic_baseline_camera_alt_24);
                }
            } else {
                imgFoto.setImageResource(R.drawable.ic_baseline_camera_alt_24);
            }
        }

        // Ao clicar em Salvar, atualiza o restaurante
        btnSalvar.setOnClickListener(v -> {
            String nome = edtNomeRestaurante.getText().toString().trim();
            String endereco = edtEndereco.getText().toString().trim();
            String bairro = edtBairro.getText().toString().trim();
            String cidade = edtCidade.getText().toString().trim();
            String descricao = edtDescricao.getText().toString().trim();
            float estrelas = ratingEstrelas.getRating();

            if(nome.isEmpty() || endereco.isEmpty()) {
                Toast.makeText(this, "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show();
                return;
            }

            Restaurante novoRestaurante = new Restaurante(nome, endereco, bairro, cidade, descricao, estrelas);
            novoRestaurante.setFotoUri(selectedFotoUri);

            if(index != -1) {
                // Atualiza o item no repositório
                RestauranteRepository.editarRestaurante(index, novoRestaurante);
                Toast.makeText(this, "Restaurante atualizado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro: índice inválido", Toast.LENGTH_SHORT).show();
            }
            // Volta para a tela de visualização
            Intent i = new Intent(TelaEditarActivity.this, TelaVisualizarRestaurantesActivity.class);
            startActivity(i);
            finish();
        });
    }
}
