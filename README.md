# Calculadora Simples
 
Este código é responsável por definir o comportamento dos botões em uma calculadora simples. Ele sobrescreve o método onCreate, que é chamado quando a atividade é criada. O método super.onCreate é chamado para executar a implementação do onCreate da classe pai, que executa ações de inicialização padrão. Em seguida, o método setContentView é chamado para definir o layout da atividade.

O método inicializarComponentes é responsável por inicializar os componentes da interface do usuário (TextViews e Buttons) e associá-los às variáveis Java.

Os botões são configurados para responder a cliques no método onClick, que é implementado como parte da interface View.OnClickListener. Os botões de operação aritmética (soma, subtração, multiplicação e divisão) chamam o método acresentarExpressao para atualizar a expressão matemática exibida na calculadora.

O botão de porcentagem realiza cálculos específicos, e os outros botões numéricos simplesmente chamam o método acresentarExpressao para adicionar o número à expressão.

O botão Reset é configurado para limpar a expressão e o resultado, e o botão Delete remove o último caractere da expressão.

Finalmente, o botão de igualdade chama o avaliador de expressão para calcular o resultado da expressão e exibi-lo no TextView de resultado. Se houver um resultado anterior, o valor é salvo e pode ser usado novamente na próxima operação.
