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

        listaRestaurantes = RestauranteRepository.getListaRestaurantes();

        adapter = new RestaurantesAdapter(listaRestaurantes, this);
        recyclerViewRestaurantes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}