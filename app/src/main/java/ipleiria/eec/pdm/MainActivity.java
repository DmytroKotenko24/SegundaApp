package ipleiria.eec.pdm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Color;
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
    private int tentativas = 3; //número de tentativas

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

        if (tentativas > 0) {
            if (txtNumero.getText().toString().trim().equals("")) {
                TextView resultado = findViewById(R.id.textViewResultado);
                resultado.setText(getResources().getString(R.string.txtNaoPreencheu));
                return;
            }
            int guess = Integer.parseInt(txtNumero.getText().toString());
            if (guess == valorAleatorio) {
                TextView resultado = findViewById(R.id.textViewResultado);
                resultado.setText(getResources().getString(R.string.txtAcertou));
                resultado.setTextColor(Color.GREEN);
                tentativas = 0; // termina o jogo
            } else {
                tentativas--;
                if (tentativas > 0) {
                    if (guess > valorAleatorio) {
                        TextView resultado = findViewById(R.id.textViewResultado);
                        resultado.setText(getResources().getText(R.string.txtValorAbaixo));
                    } else {
                        TextView resultado = findViewById(R.id.textViewResultado);
                        resultado.setText(getResources().getText(R.string.txtValorAcima));
                    }
                    TextView ver_tentativas = findViewById(R.id.textViewTentativas);
                    ver_tentativas.setText(String.valueOf(tentativas));
                } else {
                    TextView resultado = findViewById(R.id.textViewResultado);
                    TextView ver_tentativas = findViewById(R.id.textViewTentativas);
                    ver_tentativas.setText(getResources().getString(R.string.txtSemTentativas));
                }
            }
        } else {
            TextView ver_tentativas = findViewById(R.id.textViewTentativas);
            ver_tentativas.setText(getResources().getString(R.string.txtSemTentativas));
            ver_tentativas.setTextColor(Color.RED);
        }
    }

    public int gerarValorAleatorio(int valorMin, int valorMax) {
        int valor = gerador.nextInt(valorMax - valorMin + 1) + valorMin;
        return valor;
    }

}