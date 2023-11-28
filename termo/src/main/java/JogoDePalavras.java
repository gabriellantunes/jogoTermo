import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class JogoDePalavras {

    // Declara um array de strings constante para armazenar as palavras do jogo.
    private static final String[] PALAVRAS = {
            "corvo", "sapos", "touro", "zebra", "tempo", "cobra", "homem", "praia",
            "navio", "barco", "canoa", "metro", "carro", "feliz", "facil", "corpo",
            "verde", "preto", "latas", "cinza", "prata", "legal", "linda", "filho",
            "china", "japao", "chile", "egito", "índia", "humor", "papel", "festa",
            "prato", "garfo", "forno", "jovem", "leite", "chuva", "carro", "mente",
            "papel", "livro", "tinta", "vidro", "lapis", "bolsa", "placa", "curso",
            "salsa", "dança", "samba", "bolso", "marca", "festa", "piano", "sexta",
            "regua", "cinto", "calça", "botas", "luvas", "cacho", "lazer", "navio",
            "chuva", "vento", "preso", "pegar", "sorte", "livro", "meses", "peixe"
    };

    // Define o número máximo de tentativas que o jogador tem para adivinhar a palavra.
    private static final int MAX_TENTATIVAS = 5;

    // Método principal que inicia o jogo.
    public static void main(String[] args) {
        jogar();
    }

    // Método para controlar o fluxo de jogo.
    private static void jogar() {
        //limparConsole();
        String palavraSecreta = escolherPalavra();
        int tamanhoPalavra = palavraSecreta.length();
        Scanner scanner = new Scanner(System.in);
        String palpite;
        int tentativas = 0;
        int espaco = 0;

        //Linha que mostra a palavra sorteada, comentar caso queira esconder
        System.out.println("Palavra secreta: " + palavraSecreta);

        System.out.println("Bem-vindo ao jogo! Tente adivinhar a palavra.");
        System.out.println("Você tem " + MAX_TENTATIVAS + " tentativas. A palavra tem " + tamanhoPalavra + " letras.");

        // Loop principal do jogo que continua até o usuário esgotar as tentativas ou acertar a palavra.
        while (tentativas < MAX_TENTATIVAS) {
            System.out.println();
            System.out.print("Digite seu palpite: ");
            palpite = scanner.nextLine().toLowerCase();

            // Verifica se o palpite tem o tamanho correto.
            if (palpite.length() != tamanhoPalavra) {
                System.out.println("Você digitou uma palavra com tamanho diferente do necessário (" + tamanhoPalavra + " letras). Tente novamente.");
                continue;
            }

            tentativas++; // Incrementa o número de tentativas.

            if (palpite.equals(palavraSecreta)) {
                exibirMensagemVitoria(tentativas, palavraSecreta);
                scanner.close();
                return;
            } else {
                fornecerDica(palavraSecreta, palpite, tamanhoPalavra);
            }

            // Mostra ao usuário o número de tentativas restantes.
            System.out.println("Tentativas restantes: " + (MAX_TENTATIVAS - tentativas));
        }

        // Mensagem exibida quando o usuário esgota todas as tentativas.
        System.out.println("Suas tentativas acabaram! A palavra era: " + palavraSecreta);
        scanner.close();
    }

    // Método para escolher uma palavra aleatória do array.
    private static String escolherPalavra() {
        Random random = new Random();
        return PALAVRAS[random.nextInt(PALAVRAS.length)];
    }

    // Método para fornecer dicas ao usuário baseadas em seu palpite.
    private static void fornecerDica(String palavraSecreta, String palpite, int tamanhoPalavra) {
        StringBuilder palavraMisteriosa = new StringBuilder("-".repeat(tamanhoPalavra));
        StringBuilder letrasCorretasPosicaoCorreta = new StringBuilder();
        StringBuilder letrasCorretasPosicaoErrada = new StringBuilder();

        for (int i = 0; i < tamanhoPalavra; i++) {
            char letraPalpite = palpite.charAt(i);
            if (letraPalpite == palavraSecreta.charAt(i)) {
                palavraMisteriosa.setCharAt(i, letraPalpite);
                letrasCorretasPosicaoCorreta.append(letraPalpite).append(",");
            } else if (palavraSecreta.indexOf(letraPalpite) != -1 && letraPalpite != palpite.charAt(palavraSecreta.indexOf(letraPalpite))) {
                letrasCorretasPosicaoErrada.append(letraPalpite).append(",");
            }
        }

        if (letrasCorretasPosicaoCorreta.length() > 0) {
            letrasCorretasPosicaoCorreta.setCharAt(letrasCorretasPosicaoCorreta.length() - 1, '.');
        }

        if (letrasCorretasPosicaoErrada.length() > 0) {
            letrasCorretasPosicaoErrada.setCharAt(letrasCorretasPosicaoErrada.length() - 1, '.');
        }

        System.out.println("----------------------------------------------------------------");
        System.out.println("Letra correta na posição correta: " + (letrasCorretasPosicaoCorreta.length() > 0 ? letrasCorretasPosicaoCorreta.toString() : "Nenhuma."));
        System.out.println("Letra correta na posição errada: " + (letrasCorretasPosicaoErrada.length() > 0 ? letrasCorretasPosicaoErrada.toString() : "Nenhuma."));
        System.out.println("Palavra misteriosa está no momento desta maneira: \"" + palavraMisteriosa + "\"");
        System.out.println("----------------------------------------------------------------");
    }
    private static void exibirMensagemVitoria(int tentativas, String palavraSecreta) {
        int resposta = JOptionPane.showConfirmDialog(null,
                "<html>Parabéns!!<br>Você acertou a palavra!!<br><br>Tentativas: " + tentativas +
                        "<br>Palavra misteriosa: " + palavraSecreta + "<br><br>Deseja jogar novamente?</html>",
                "Vitória!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            //limparConsole();
            for (int i = 0; i < 2; i++) {
                System.out.println();
            }
            System.out.println("---------------------------- Novo Jogo -------------------------------");
            jogar();
        }
    }
}
