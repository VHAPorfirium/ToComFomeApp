package com.example.tocomfomeapp.TelaPrincipal;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tocomfomeapp.R;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ativa o modo Edge-to-Edge (se quiser manter esse comportamento)
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        // Em vez de aplicar insets só na imgLogo, aplicamos no layout raiz
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Aplica padding para “empurrar” tudo (inclusive a logo) para baixo da status bar
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
