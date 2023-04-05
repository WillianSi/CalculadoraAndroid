package br.edu.ifsuldeminas.mch.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import kotlin.reflect.KFunction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ifsuldeminas.mch.calc";
    private Button buttonUm,buttonDois,buttonTres,buttonQuatro,buttonCinco,
            buttonSeis,buttonSete,buttonOito,buttonNove,buttonZero,
            buttonIgual,buttonMultiplicacao,buttonDivisao,buttonSoma,buttonSubtracao,
            buttonPorcento,buttonVirgula,
            buttonReset,buttonDelete;
    private TextView textViewResultado,textViewUltimaExpressao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IniciarComponentes();
        getSupportActionBar().hide();

        buttonSoma.setOnClickListener(this);
        buttonSubtracao.setOnClickListener(this);
        buttonMultiplicacao.setOnClickListener(this);
        buttonDivisao.setOnClickListener(this);
        buttonPorcento.setOnClickListener(this);
        buttonVirgula.setOnClickListener(this);
        buttonZero.setOnClickListener(this);
        buttonUm.setOnClickListener(this);
        buttonDois.setOnClickListener(this);
        buttonTres.setOnClickListener(this);
        buttonQuatro.setOnClickListener(this);
        buttonCinco.setOnClickListener(this);
        buttonSeis.setOnClickListener(this);
        buttonSete.setOnClickListener(this);
        buttonOito.setOnClickListener(this);
        buttonNove.setOnClickListener(this);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewUltimaExpressao.setText("");
                textViewResultado.setText("");
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView expresao = findViewById(R.id.textViewUltimaExpressaoID);
                String string = expresao.getText().toString();
                if (!string.isEmpty()){
                    byte v0 = 0;
                    int v1 = string.length()-1;
                    String txtEpresao = string.substring(v0,v1);
                    expresao.setText(txtEpresao);
                }
                textViewResultado.setText("");
            }
        });

        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calculable avaliadorExpressao = null;
                try {
                    avaliadorExpressao = new ExpressionBuilder(textViewUltimaExpressao.
                            getText().toString()).build();
                    Double resultado = avaliadorExpressao.calculate();
                   long longResult = (long) resultado.doubleValue();;
                    if (resultado == (double)longResult){
                        textViewResultado.setText((CharSequence) String.valueOf(longResult));
                    }else{
                        textViewResultado.setText((CharSequence) String.valueOf(resultado));
                    }

                    // Limpa o conteúdo da TextView "textViewUltimaExpressao"
                    textViewUltimaExpressao.setText("");

                    // Atualiza o valor atual do TextView "textViewResultado" com o resultado calculado
                    String resultadoAtual = textViewResultado.getText().toString();
                    textViewUltimaExpressao.setText(resultadoAtual + textViewUltimaExpressao.getText().toString());
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        });
    }

    private void IniciarComponentes(){
        textViewResultado = findViewById(R.id.textViewResultadoID);
        textViewUltimaExpressao = findViewById(R.id.textViewUltimaExpressaoID);
        buttonDelete = findViewById(R.id.buttonDeleteID);
        buttonReset = findViewById(R.id.buttonResetID);
        buttonIgual = findViewById(R.id.buttonIgualID);
        buttonMultiplicacao = findViewById(R.id.buttonMultiplicacaoID);
        buttonDivisao = findViewById(R.id.buttonDivisaoID);
        buttonPorcento = findViewById(R.id.buttonPorcentoID);
        buttonSoma = findViewById(R.id.buttonSomaID);
        buttonSubtracao = findViewById(R.id.buttonSubtracaoID);
        buttonVirgula = findViewById(R.id.buttonVirgulaID);
        buttonUm = findViewById(R.id.buttonUmID);
        buttonDois = findViewById(R.id.buttonDoisID);
        buttonTres = findViewById(R.id.buttonTresID);
        buttonQuatro = findViewById(R.id.buttonQuatroID);
        buttonCinco = findViewById(R.id.buttonCincoID);
        buttonSeis = findViewById(R.id.buttonSeisID);
        buttonSete = findViewById(R.id.buttonSeteID);
        buttonOito = findViewById(R.id.buttonOitoID);
        buttonNove = findViewById(R.id.buttonNoveID);
        buttonZero = findViewById(R.id.buttonZeroID);
    }
    public void AcresentarExprecao(String string, boolean limpar){
        if (textViewResultado.getText().equals("")){
            textViewUltimaExpressao.setText(" ");
        }
        if (limpar){
            textViewResultado.setText(" ");
            textViewUltimaExpressao.append(string);
        }else {
            String ultimaExpressao = textViewUltimaExpressao.getText().toString();
            if (!ultimaExpressao.isEmpty() && (ultimaExpressao.endsWith("+") || ultimaExpressao.endsWith("-") || ultimaExpressao.endsWith("*") || ultimaExpressao.endsWith("/"))) {
                ultimaExpressao = ultimaExpressao.substring(0, ultimaExpressao.length() - 1);
            }
            textViewUltimaExpressao.setText(ultimaExpressao + string);
            textViewResultado.setText(" ");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSomaID:
                AcresentarExprecao("+",false);
                break;
            case R.id.buttonSubtracaoID:
                AcresentarExprecao("-",false);
                break;
            case R.id.buttonMultiplicacaoID:
                AcresentarExprecao("*",false);
                break;
            case R.id.buttonDivisaoID:
                AcresentarExprecao("/",false);
                break;
            case R.id.buttonPorcentoID:
                AcresentarExprecao("%",false);
                break;
            case R.id.buttonVirgulaID:
                AcresentarExprecao(".",true);
                break;
            case R.id.buttonZeroID:
                AcresentarExprecao("0",true);
                break;
            case R.id.buttonUmID:
                AcresentarExprecao("1",true);
                break;
            case R.id.buttonDoisID:
                AcresentarExprecao("2",true);
                break;
            case R.id.buttonTresID:
                AcresentarExprecao("3",true);
                break;
            case R.id.buttonQuatroID:
                AcresentarExprecao("4",true);
                break;
            case R.id.buttonCincoID:
                AcresentarExprecao("5",true);
                break;
            case R.id.buttonSeisID:
                AcresentarExprecao("6",true);
                break;
            case R.id.buttonSeteID:
                AcresentarExprecao("7",true);
                break;
            case R.id.buttonOitoID:
                AcresentarExprecao("8",true);
                break;
            case R.id.buttonNoveID:
                AcresentarExprecao("9",true);
                break;
        }
    }
}