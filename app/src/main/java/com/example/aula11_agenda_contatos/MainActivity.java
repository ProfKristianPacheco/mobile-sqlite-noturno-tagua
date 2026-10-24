package com.example.aula11_agenda_contatos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    private EditText nome;
    private EditText telefone;
    private Button salvar;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        nome = findViewById(R.id.editTextNome);
        telefone = findViewById(R.id.editTextTelefone);
        salvar = findViewById(R.id.buttonSalvar);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "APPAGENDA.db").build();

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarContato();
            }
        });
    }

    private void salvarContato() {
        String nomeContato = nome.getText().toString();
        String telefoneContato = telefone.getText().toString();

        if(nomeContato.isEmpty() || telefoneContato.isEmpty()){
            Toast.makeText(this, "Prencha todas as informações!", Toast.LENGTH_SHORT).show();
            return;
        }

        Contato contato = new Contato(nomeContato, telefoneContato);

        new Thread(new Runnable() { //O ACESSO AO BD SÓ PODE SER FEITO FORA DA THREAD PRINCIPAL (UI)
            @Override
            public void run() {
                db.requestContatoDAO().insertContato(contato);
            }
        }).start();
    }
}