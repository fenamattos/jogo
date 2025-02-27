import java.util.Scanner; // Importa a classe Scanner para ler o que o jogador digita
public class JogoDaVelha {
    // Cria o tabuleiro como uma matriz 3x3, onde cada posição começa vazia (' ')
    private static char[][] tabuleiro = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    private static char jogadorAtual = 'X'; // Define quem começa o jogo (X sempre começa)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria o objeto scanner para ler as jogadas
        boolean jogoAtivo = true; // Controlar se o jogo ainda está continuando ou acabou

        System.out.println("🎯 Bem-vindo ao Jogo da Velha!");
        imprimirTabuleiro(); // Mostra o tabuleiro vazio no começo

        // Enquanto o jogo ainda não acabou (se alguém ganhar ou empatar)
        while (jogoAtivo) {
            System.out.println("Jogador " + jogadorAtual + ", escolha sua posição (linha e coluna de 0 a 2):");
            int linha = scanner.nextInt(); // Ler a linha
            int coluna = scanner.nextInt(); // Ler a coluna

            // Validar se a posição escolhida é dentro do tabuleiro e se está vazia
            if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == ' ') {
                tabuleiro[linha][coluna] = jogadorAtual; // Marcar a jogada no tabuleiro
                imprimirTabuleiro(); // Atualizar o tabuleiro na tela

                // Verificar se o jogador atual venceu
                if (verificarVencedor()) {
                    System.out.println("🎉 Parabéns! O jogador " + jogadorAtual + " venceu!");
                    jogoAtivo = false; // encerra o jogo
                } else if (verificarEmpate()) { // Se ninguém venceu e não há mais espaço
                    System.out.println("⚔️ O jogo empatou!");
                    jogoAtivo = false; // encerra o jogo
                } else {
                    // mudar para o próximo jogador (X -> O, O -> X)
                    jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("❌ Jogada inválida! Tente novamente.");
            }
        }
        scanner.close(); // Fecha o scanner
    }

    // Mostra o tabuleiro na tela para os jogadores
    private static void imprimirTabuleiro() {
        System.out.println("  0 1 2"); // Mostra os números das colunas
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " "); // Mostra os números das linhas
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]); // Mostra o valor da posição (X, O ou espaço vazio)
                if (j < 2) System.out.print("|"); // Colocar as barrinhas entre as colunas
            }
            System.out.println(); // Pular para a próxima linha
            if (i < 2) System.out.println("  -----"); // Colocar as linhas horizontais
        }
    }

    // Verificar se alguém venceu (linhas, colunas ou diagonais)
    private static boolean verificarVencedor() {
        // Verificar linhas e colunas
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) || 
                (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual)) {
                return true;
            }
        }
        // Verificar diagonais
        if ((tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) || 
            (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual)) {
            return true;
        }
        return false;
    }

    // Verificar se todas as posições estão preenchidas (empate)
    private static boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') { // Se encontrar uma posição vazia, ainda tem tempo para jogar
                    return false;
                }
            }
        }
        return true; // Se não achou nenhuma posição vazia, é empate
    }
}
