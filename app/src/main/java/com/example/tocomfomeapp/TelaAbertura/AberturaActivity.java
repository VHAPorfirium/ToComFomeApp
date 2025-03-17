package com.example.tocomfomeapp.TelaAbertura;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tocomfomeapp.R;
import com.example.tocomfomeapp.TelaPrincipal.MainActivity;

public class AberturaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilito o modo Edge-to-Edge para que o conteúdo se estenda por toda a tela
        EdgeToEdge.enable(this);
        // Defino o layout da Activity com o arquivo activity_abertura.xml
        setContentView(R.layout.activity_abertura);

        // Ajusto o padding do layout para considerar as barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Aplico o padding conforme as dimensões das barras do sistema
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Crio um Handler para definir um atraso de 3 segundos antes de iniciar a MainActivity
        new Handler().postDelayed(() -> {
            // Crio uma Intent para abrir a MainActivity após o delay
            Intent intent = new Intent(AberturaActivity.this, MainActivity.class);
            startActivity(intent);
            // Finalizo essa Activity para que o usuário não retorne a ela ao pressionar voltar
            finish();
        }, 3000); // Delay de 3000 milissegundos = 3 segundos
    }
}