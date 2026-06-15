import java.util.Scanner;

public class Connect4 {

    public void tabuleiroBranco(char[][] tabuleiro) {
        // adiciona ao tabuleiro "B" wm todas as linhas e colunas
        for (int linha = 0; linha < 6; linha++) {
            for (int colunas = 0; colunas < 7; colunas++) {
                tabuleiro[linha][colunas] = 'B';
            }
        }
    }

    public void ImprimirTabuleiro(char[][] tabuleiro) {
        for (int linha = 0; linha < 6; linha++) {
            for (int colunas = 0; colunas < 7; colunas++) {
                System.out.print(tabuleiro[linha][colunas] + " ");

            }

            System.out.println();
        }
        System.out.println("1 2 3 4 5 6 7");
        // contagem de colunas
    }

    public char[] escolherCor(Scanner sc) {
        System.out.println("Escolher cor!");
        System.out.println("1 - Vermelho (v): ");
        System.out.println("2 - Azul (a): ");
        char cor = sc.next().charAt(0);

        char corJogador;
        char corComputador;
        if (cor == 'v') {
            corJogador = 'v';
            corComputador = 'a';
            System.out.println("Voce escolheu vermelho. Computador é azul");
        } else {
            corJogador = 'a';
            corComputador = 'v';
            System.out.println("Voce escolheu azul. Computador é vermelho");
        }
        return new char[] { corJogador, corComputador };
    }

    public void adicionarPeca(char cor, char[][] tabuleiro, Scanner sc) {
        int coluna;
        ImprimirTabuleiro(tabuleiro);
        do {
            System.out.println("Digite a coluna que deseja acrescentar a peça: (1 á 7)");
            coluna = sc.nextInt() - 1;

            if (colunaCheia(tabuleiro, coluna)) {
                System.out.println("Coluna cheia! Escolha outra");
            }

        } while (colunaCheia(tabuleiro, coluna));// repete enquanto a coluna estiver cheia

        // roda as linhas de baixo pra cima colocando sempre na mais baixa disponivel
        for (int linha = 5; linha >= 0; linha--) {
            if (tabuleiro[linha][coluna] == 'B') {
                tabuleiro[linha][coluna] = cor;
                break;
            }
        }
        ImprimirTabuleiro(tabuleiro);
    }

    public boolean colunaCheia(char[][] tabuleiro, int coluna) {
        // se a ultima linha da coluna não for b entao está cheia
        if (tabuleiro[0][coluna] != 'B') {
            return true;
        }
        return false;
    }

    public Connect4() {
        // todos os atributos devem ser no construtor
        Scanner sc = new Scanner(System.in);
        char tabuleiro[][] = new char[6][7];
        tabuleiroBranco(tabuleiro);
        char[] cores = escolherCor(sc);
        char corJogador = cores[0]; // cor que o jogador escolheu
        char corComputador = cores[1]; // cor do computador

        adicionarPeca(corJogador, tabuleiro, sc);

        sc.close();
    }

    public static void main(String[] args) {
        Connect4 connect4 = new Connect4();

    }
}