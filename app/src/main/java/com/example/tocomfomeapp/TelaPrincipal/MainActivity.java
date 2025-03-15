package com.example.tocomfomeapp.TelaPrincipal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tocomfomeapp.R;
import com.example.tocomfomeapp.TelaAdicionar.TelaAdicionarRestauranteActivity;
import com.example.tocomfomeapp.TelaVisualizar.TelaVisualizarRestaurantesActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button btnAddRestaurant;
        Button btnViewRestaurants;

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAddRestaurant = findViewById(R.id.btnAddRestaurant);
        btnAddRestaurant.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, TelaAdicionarRestauranteActivity.class)));

        btnViewRestaurants = findViewById(R.id.btnViewRestaurants);
        btnViewRestaurants.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, TelaVisualizarRestaurantesActivity.class)));
    }
}
