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
    Double resultadoAnterior = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inicializa os componentes da interface
        IniciarComponentes();
        // Oculta a ActionBar
        getSupportActionBar().hide();

        // Associa os botões de operações matemáticas aos seus respectivos listeners
        buttonSoma.setOnClickListener(this);
        buttonSubtracao.setOnClickListener(this);
        buttonMultiplicacao.setOnClickListener(this);
        buttonDivisao.setOnClickListener(this);
        buttonPorcento.setOnClickListener(this);
        buttonVirgula.setOnClickListener(this);

        // Associa os botões numéricos aos seus respectivos listeners
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

        // Associa o botão "C" ao listener que limpa a expressão e o resultado
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewUltimaExpressao.setText("");
                textViewResultado.setText("");
                resultadoAnterior = null;
            }
        });

        // Associa o botão "<-" ao listener que remove o último caractere da expressão
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtém a expressão atual
                String string = textViewUltimaExpressao.getText().toString();
                // Verifica se a expressão não está vazia
                if (!string.isEmpty()) {
                    // Remove o último caractere da expressão
                    String txtExpressao = string.substring(0, string.length() - 1);
                    textViewUltimaExpressao.setText(txtExpressao);
                }
                // Limpa o resultado
                textViewResultado.setText("");
            }
        });

        // Associa o botão "=" ao listener que avalia a expressão e exibe o resultado
        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calculable avaliadorExpressao = null;
                try {
                    // Cria um objeto ExpressionBuilder para avaliar a expressão
                    avaliadorExpressao = new ExpressionBuilder(textViewUltimaExpressao.
                            getText().toString()).build();
                    // Avalia a expressão e obtém o resultado
                    Double resultado = avaliadorExpressao.calculate();
                    // Arredonda o resultado para baixo, caso seja um número inteiro
                    long longResult = (long) resultado.doubleValue();
                    // Verifica se o resultado é um número inteiro
                    if (resultado == (double)longResult){
                        // Exibe o resultado como inteiro
                        textViewResultado.setText((CharSequence) String.valueOf(longResult));
                        resultadoAnterior = Double.valueOf(longResult);
                    }else{
                        // Exibe o resultado com casas decimais
                        textViewResultado.setText((CharSequence) String.valueOf(resultado));
                        resultadoAnterior = resultado;
                    }
                } catch (Exception e) {
                    // Em caso de erro, exibe uma mensagem no log do sistema
                    Log.d(TAG, e.getMessage());
                }
            }
        });
    }

    // Método responsável por inicializar
    // os componentes da interface
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
    // Método responsável por adicionar uma expressão à tela
    public void AcresentarExprecao(String string, boolean limpar){
        // Verifica se o componente TextView correspondente
        // ao resultado está vazio
        if (textViewResultado.getText().equals("")){
            // Se estiver, limpa o componente TextView correspondente à
            // última expressão digitada
            textViewUltimaExpressao.setText(" ");
        }
        // Verifica se a expressão a ser adicionada deve limpar o componente
        // TextView correspondente ao resultado
        if (limpar){
            // Se deve ser limpa, limpa a caixa de texto de resultado e adiciona a
            // string na caixa de texto de última expressão
            textViewResultado.setText(" ");
            textViewUltimaExpressao.append(string);
        }else {
            // Se não deve ser limpa, obtém a última expressão da caixa de texto de última expressão
            String ultimaExpressao = textViewUltimaExpressao.getText().toString();
            // Verifica se a última expressão não está vazia e se termina com um operador
            if (!ultimaExpressao.isEmpty() && (ultimaExpressao.endsWith("+") ||
                    ultimaExpressao.endsWith("-") || ultimaExpressao.endsWith("*")
                    || ultimaExpressao.endsWith("/"))) {
                // Se terminar com um operador, remove o operador final da última expressão
                ultimaExpressao = ultimaExpressao.substring(0, ultimaExpressao.length() - 1);
            }
            // Adiciona a string na última expressão e define a caixa de texto de resultado como vazia
            textViewUltimaExpressao.setText(ultimaExpressao + string);
            textViewResultado.setText(" ");
        }
    }

    // Verifica qual botão foi clicado e adiciona o operador de soma na última expressão
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSomaID:
                if (resultadoAnterior != null) {
                    String resultadoAnteriorString = Double.toString(resultadoAnterior);
                    textViewUltimaExpressao.setText(resultadoAnteriorString);
                }
                AcresentarExprecao("+",false);
                break;
            case R.id.buttonSubtracaoID:
                if (resultadoAnterior != null) {
                    String resultadoAnteriorString = Double.toString(resultadoAnterior);
                    textViewUltimaExpressao.setText(resultadoAnteriorString);
                }
                AcresentarExprecao("-",false);
                break;
            case R.id.buttonMultiplicacaoID:
                if (resultadoAnterior != null) {
                    String resultadoAnteriorString = Double.toString(resultadoAnterior);
                    textViewUltimaExpressao.setText(resultadoAnteriorString);
                }
                AcresentarExprecao("*",false);
                break;
            case R.id.buttonDivisaoID:
                if (resultadoAnterior != null) {
                    String resultadoAnteriorString = Double.toString(resultadoAnterior);
                    textViewUltimaExpressao.setText(resultadoAnteriorString);
                }
                AcresentarExprecao("/",false);
                break;
            case R.id.buttonPorcentoID:
                if (resultadoAnterior != null) {
                    String resultadoAnteriorString = Double.toString(resultadoAnterior);
                    textViewUltimaExpressao.setText(resultadoAnteriorString);
                }
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