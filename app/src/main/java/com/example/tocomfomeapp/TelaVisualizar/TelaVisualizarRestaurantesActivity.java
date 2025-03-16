package com.example.tocomfomeapp.TelaVisualizar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tocomfomeapp.Adapter.RestaurantesAdapter;
import com.example.tocomfomeapp.Model.Restaurante;
import com.example.tocomfomeapp.Repository.RestauranteRepository;
import com.example.tocomfomeapp.R;

import java.util.List;

public class TelaVisualizarRestaurantesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRestaurantes;
    private RestaurantesAdapter adapter;
    private List<Restaurante> listaRestaurantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_visualizar_restaurantes);

        recyclerViewRestaurantes = findViewById(R.id.recyclerViewRestaurantes);
        recyclerViewRestaurantes.setLayoutManager(new LinearLayoutManager(this));

        // Busca a lista estática do repositório
        listaRestaurantes = RestauranteRepository.getListaRestaurantes();

        // Se quiser inserir dados "fake" apenas na primeira vez:
        if (listaRestaurantes.isEmpty()) {
            listaRestaurantes.add(new Restaurante("Restaurante A", "Rua 1", "Bairro 1", "Cidade 1", "Descrição 1", 4.0f));
            listaRestaurantes.add(new Restaurante("Restaurante B", "Rua 2", "Bairro 2", "Cidade 2", "Descrição 2", 5.0f));
            listaRestaurantes.add(new Restaurante("Restaurante C", "Rua 3", "Bairro 3", "Cidade 3", "Descrição 3", 3.0f));
        }

        // Cria o Adapter
        adapter = new RestaurantesAdapter(listaRestaurantes, this);
        recyclerViewRestaurantes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Atualiza a lista caso algo tenha mudado
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
