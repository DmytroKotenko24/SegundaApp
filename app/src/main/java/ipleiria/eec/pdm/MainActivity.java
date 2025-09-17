package ipleiria.eec.pdm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumero;
    private Random gerador = new Random(); //para gerar valor aleatório
    private int valorAleatorio = gerarValorAleatorio(1, 10); //valor aleatório entre 1 e 10

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNumero = findViewById(R.id.editTextNumero);
        valorAleatorio = gerarValorAleatorio(1, 10);
    }


    public void onClickAdivinha(View view) {
        /*
        if (txtNumero.getText().toString().trim().equals("")) {
            Toast.makeText(this,
                    getResources().getString(R.string.txtNaoPreencheu),
                    Toast.LENGTH_LONG).show();
            return;
        }
        int guess = Integer.parseInt(txtNumero.getText().toString());
        if (guess == valorAleatorio) {
            Toast.makeText(this, getResources().
                    getText(R.string.txtAcertou), Toast.LENGTH_LONG).show();
        } else if (guess > valorAleatorio) {
            Toast.makeText(this, getResources().
                    getText(R.string.txtValorAbaixo), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getResources().
                    getText(R.string.txtValorAcima), Toast.LENGTH_LONG).show();
        }
        */

        TextView resultado = findViewById(R.id.textViewResultado);
        int guess = Integer.parseInt(((EditText) findViewById(R.id.editTextNumero)).getText().toString());
        if (guess > targetValue) {
            resultado.setText("Above the target value");
        } else if (guess < targetValue) {
            resultado.setText("Below the target value");
        } else {
            resultado.setText("Correct!");
        }

    }

    public int gerarValorAleatorio(int valorMin, int valorMax) {
        int valor = gerador.nextInt(valorMax - valorMin + 1) + valorMin;
        return valor;
    }

}