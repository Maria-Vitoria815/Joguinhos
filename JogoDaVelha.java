import java.util.Scanner;

public class JogoDaVelha {
    private char[][] tabuleiro;
    private char jogadorAtual;

    public JogoDaVelha() {
        tabuleiro = new char[3][3];
        jogadorAtual = 'X';
        inicializarTabuleiro();
    }

    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        int linha, coluna;

        do {
            System.out.println("Jogador " + jogadorAtual + ", digite a linha (1-3) e a coluna (1-3) separadas por espaço:");
            linha = scanner.nextInt() - 1;
            coluna = scanner.nextInt() - 1;
        } while (!movimentoValido(linha, coluna));

        tabuleiro[linha][coluna] = jogadorAtual;

        exibirTabuleiro();

        if (verificarVitoria()) {
            System.out.println("Jogador " + jogadorAtual + " venceu!");
            return;
        }

        if (tabuleiroCheio()) {
            System.out.println("Empate!");
            return;
        }

        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        jogar();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = '-';
            }
        }
    }

    private boolean movimentoValido(int linha, int coluna) {
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
            System.out.println("Movimento inválido. Linha e coluna devem estar entre 1 e 3.");
            return false;
        }
        if (tabuleiro[linha][coluna] != '-') {
            System.out.println("Posição ocupada. Escolha outra.");
            return false;
        }
        return true;
    }

    private boolean verificarVitoria() {
        // Verifica linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual ||
                tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual) {
                return true;
            }
        }
        // Verifica diagonais
        if ((tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) ||
            (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual)) {
            return true;
        }
        return false;
    }

    private boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public void exibirTabuleiro() {
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i+1) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        JogoDaVelha jogo = new JogoDaVelha();
        jogo.jogar();
    }
}
