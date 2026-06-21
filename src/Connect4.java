import java.util.Random;
import java.util.Scanner;

public class Connect4 {

    Scanner sc = new Scanner(System.in);

    public void tabuleiroBranco(char[][] tabuleiro) {
        // adiciona ao tabuleiro "B" wm todas as linhas e colunas
        for (int linha = 0; linha < 6; linha++) {
            for (int colunas = 0; colunas < 7; colunas++) {
                tabuleiro[linha][colunas] = 'B';

            }
        }
    }

    public void ImprimirTabuleiro(char[][] tabuleiro) {
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println();
        for (int linha = 0; linha < 6; linha++) {
            for (int colunas = 0; colunas < 7; colunas++) {
                System.out.print(tabuleiro[linha][colunas] + " ");

            }

            System.out.println();
        }
        System.out.println("1 2 3 4 5 6 7");
        // contagem de colunas
    }

    public char[] verficarQuemComeca(Scanner sc, char[][] tabuleiro) {
        Random random = new Random();
        int sorteio = random.nextInt(2);
        char corJogador;
        char corComputador;
        char quemComeca;
        if (sorteio == 0) {
            System.out.println("Jogador começa");
            char[] cores = escolherCor(sc);
            corJogador = cores[0];
            corComputador = cores[1];
            quemComeca = 'j';
        } else {
            System.out.println("Computador começa");
            quemComeca = 'c';
            char sorteioCores = random.nextInt(2) == 0 ? 'v' : 'a';
            if (sorteioCores == 'v') {
                corJogador = 'a';
                corComputador = 'v';
                System.out.println("Computador escolheu vermelho. Você é azul");
            } else {
                corJogador = 'v';
                corComputador = 'a';
                System.out.println("Computador escolheu azul. Você é vermelho");
            }
        }
        return new char[] { corJogador, corComputador, quemComeca };

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
        } else if (cor == 'a') {
            corJogador = 'a';
            corComputador = 'v';
            System.out.println("Voce escolheu azul. Computador é vermelho");
        } else {
            System.out.println("Você so pode digitar A para azul e V para vermelho");
            return escolherCor(sc);// coloquei isso para sempre pedir de nvo caso ele coloque outra letra
        }
        // jogadaComputador(corJogador, corComputador, tabuleiro, sc); // computador
        // começa aqui

        return new char[] { corJogador, corComputador };

    }

    public void adicionarPeca(char corJogador, char corComputador, char[][] tabuleiro, Scanner sc) {// aqui do jogador
        int coluna;
        // ImprimirTabuleiro(tabuleiro);
        System.out.println();
        System.out.println("Agora é a sua vez!");
        System.out.println();
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
                tabuleiro[linha][coluna] = corJogador;
                break;
            }
        }
    }

    public boolean colunaCheia(char[][] tabuleiro, int coluna) {
        // se a ultima linha da coluna não for b entao esta cheia
        if (tabuleiro[0][coluna] != 'B') {
            return true;
        }
        return false;
    }

    public void jogadaComputador(char corJogador, char corComputador, char[][] tabuleiro, Scanner sc) {// aqui vai ser a
                                                                                                       // jogada do //
                                                                                                       // computador
        int coluna;
        // ImprimirTabuleiro(tabuleiro);
        Random random = new Random();
        System.out.println();
        System.out.println("Agora é o computador");
        System.out.println();

        do {
            coluna = random.nextInt(7);// gera um numero de 1 a 7
        } while (colunaCheia(tabuleiro, coluna));// repete enquanto a coluna estiver cheia

        for (int linha = 5; linha >= 0; linha--) {
            if (tabuleiro[linha][coluna] == 'B') {
                tabuleiro[linha][coluna] = corComputador;
                break;
            }
        }
    }

    public boolean verificarJOgada(char[][] tabuleiro, char cor) {
        // horizontal
        for (int linha = 0; linha < 6; linha++) {
            for (int coluna = 0; coluna < 4; coluna++) {
                if (tabuleiro[linha][coluna] == cor && tabuleiro[linha][coluna + 1] == cor
                        && tabuleiro[linha][coluna + 2] == cor && tabuleiro[linha][coluna + 3] == cor) {
                    return true;
                }
            }
        }
        // vertical
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 7; coluna++) {
                if (tabuleiro[linha][coluna] == cor && tabuleiro[linha + 1][coluna] == cor
                        && tabuleiro[linha + 2][coluna] == cor && tabuleiro[linha + 3][coluna] == cor) {
                    return true;
                }
            }
        }
        // diagonal
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 4; coluna++) {
                if (tabuleiro[linha][coluna] == cor && tabuleiro[linha + 1][coluna + 1] == cor
                        && tabuleiro[linha + 2][coluna + 2] == cor && tabuleiro[linha + 3][coluna + 3] == cor) {
                    return true;
                }
            }
        }

        for (int linha = 3; linha < 6; linha++) {
            for (int coluna = 0; coluna < 4; coluna++) {
                if (tabuleiro[linha][coluna] == cor && tabuleiro[linha - 1][coluna + 1] == cor
                        && tabuleiro[linha - 2][coluna + 2] == cor && tabuleiro[linha - 3][coluna + 3] == cor) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificarEmpate(char[][] tabuleiro) {
        for (int linha = 0; linha < 6; linha++) {
            for (int coluna = 0; coluna < 7; coluna++) {
                if (tabuleiro[linha][coluna] == 'B') {// verifica se existe ainda espaço livre
                    return false;// ainda tem
                }
            }
        }
        return true;// empate
    }

    public void Jogo(char[][] tabuleiro, Scanner sc) {
        char resposta;

        do {
            tabuleiroBranco(tabuleiro);

            char[] cores = verficarQuemComeca(sc, tabuleiro);
            char corJogador = cores[0];
            char corComputador = cores[1];
            char quemComeca = cores[2]; 

            while (true) {

                ImprimirTabuleiro(tabuleiro);

                if (quemComeca == 'j') {
                    adicionarPeca(corJogador, corComputador, tabuleiro, sc);

                    if (verificarJOgada(tabuleiro, corJogador)) {
                        ImprimirTabuleiro(tabuleiro);
                        System.out.println("Você venceu!");
                        break;
                    }
                    quemComeca = 'c';

                } else {
                    jogadaComputador(corJogador, corComputador, tabuleiro, sc);

                    if (verificarJOgada(tabuleiro, corComputador)) {
                        ImprimirTabuleiro(tabuleiro);
                        System.out.println("Computador venceu!");
                        break;
                    }
                    quemComeca = 'j';
                }
                if (verificarEmpate(tabuleiro)) {
                    ImprimirTabuleiro(tabuleiro);
                    System.out.println("Empate!");
                    break;
                }
            }

            System.out.println("Deseja jogar novamente? (s/n)");
            resposta = sc.next().charAt(0);

        } while (resposta == 's' || resposta == 'S');

        System.out.println("Obrigado por jogar!");
    }

    public Connect4() {
        // todos os atributos devem ser no construtor
        Scanner sc = new Scanner(System.in);
        char tabuleiro[][] = new char[6][7];

        Jogo(tabuleiro, sc);

        sc.close();
    }

    public static void main(String[] args) {
        Connect4 connect4 = new Connect4();

    }
}
