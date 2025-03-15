package com.example.tocomfomeapp.TelaVisualizar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tocomfomeapp.Adapter.RestaurantesAdapter;
import com.example.tocomfomeapp.Model.Restaurante;
import com.example.tocomfomeapp.R;

import java.util.ArrayList;
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

        // Exemplo de dados "fake". Depois você vai buscar do banco de dados.
        listaRestaurantes = new ArrayList<>();
        listaRestaurantes.add(new Restaurante("Restaurante A", "Rua 1", "Bairro 1", "Cidade 1", "Descrição 1", 4.0f));
        listaRestaurantes.add(new Restaurante("Restaurante B", "Rua 2", "Bairro 2", "Cidade 2", "Descrição 2", 5.0f));
        listaRestaurantes.add(new Restaurante("Restaurante C", "Rua 3", "Bairro 3", "Cidade 3", "Descreção 3", 3.0f));
        listaRestaurantes.add(new Restaurante("Restaurante D", "Rua 4", "Bairro 4", "Cidade 4", "Descreção 4", 2.0f));
        listaRestaurantes.add(new Restaurante("Restaurante E", "Rua 5", "Bairro 5", "Cidade 5", "Descreção 5", 1.0f));
        listaRestaurantes.add(new Restaurante("Restaurante F", "Rua 6", "Bairro 6", "Cidade 6", "Descreção 6", 4.0f));
        listaRestaurantes.add(new Restaurante("Restaurante G", "Rua 7", "Bairro 7", "Cidade 7", "Descreção 7", 5.0f));

        // Cria o Adapter e seta no RecyclerView
        adapter = new RestaurantesAdapter(listaRestaurantes, this);
        recyclerViewRestaurantes.setAdapter(adapter);
    }
}
