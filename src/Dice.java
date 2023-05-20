
import java.security.SecureRandom;
import java.util.Scanner;

/*
 * Você lança dois dados. Cada dado tem seis faces que contêm um, dois, três, quatro, cinco e seis pontos,
respectivamente. Depois que os dados param de rolar, a soma dos pontos nas faces viradas para cima é calculada.
Se a soma for 7 ou 11 no primeiro lance, você ganha. Se a soma for 2, 3 ou 12 no primeiro lance (chamado
“craps”), você perde (isto é, a “casa” ganha). Se a soma for 4, 5, 6, 8, 9 ou 10 no primeiro lance, essa soma torna-
-se sua “pontuação”. Para ganhar, você deve continuar a rolar os dados até “fazer sua pontuação” (isto é, obter
um valor igual à sua pontuação). Você perde se obtiver um 7 antes de fazer sua pontuação.
 */

// Entradas : 2 dados de 6 faces, cada face representa 1 ponto

// Processamento: Sao jogados os dados uma vez e a soma dos pontos das faces viradas para cima e calculada
// Se a soma for 7 ou 11 no primeiro lance, o usuario ganha
// Se a soma for 2, 3 ou 12 no primeiro lance (chamado “craps”), o usuario perde (isto é, a “casa” ganha)
// Se a soma for 4, 5, 6, 8, 9 ou 10 no primeiro lance, essa soma torna-se sua “pontuação”
// O usuario deve jogar os dados ate fazer um resultado igual a sua pontuacao
// O usuario perde se obtiver um 7 antes de fazer sua pontuacao

// Saida : Soma dos pontos das faces viradas para cima e se o usuario perdeu o ganhou

public class Dice {

    private static final SecureRandom randomNumbers = new SecureRandom();
    Scanner sc = new Scanner(System.in);

    int myPoints;
    Status gameStatus;
    int sumOfDice;

    public Status play() {

        System.out.println("*** Bem vindo ao CRAPS ***");
        while (true) {
            System.out.println("Pressione enter para jogar os dados");

            sc.nextLine();

            int sumOfDice = rollDice();

            if (sumOfDice == 7 || sumOfDice == 11) {
                gameStatus = Status.WON;
                System.out.println("\u001B[32mVocê ganhou!\u001B[0m");
                System.out.println("Deseja jogar outra vez ? (s/n)");
                if (sc.nextLine().equals("n")) {
                    System.out.println("Obrigado por jogar Craps! Até a próxima!");
                    break;
                }

            } else if (sumOfDice == 2 || sumOfDice == 3 || sumOfDice == 12) {
                gameStatus = Status.LOST;
                System.out.println("\u001B[31mVocê perdeu!\u001B[0m");
                System.out.println("Deseja jogar outra vez ? (s/n)");
                if (sc.nextLine().equals("n")) {
                    System.out.println("Obrigado por jogar Craps! Até a próxima!");
                    break;
                }
            } else {
                gameStatus = Status.CONTINUE;
                myPoints = sumOfDice;
                System.out.printf("\u001B[33mSua pontuação é %d%n\u001B[0m", myPoints);
                System.out.println("\u001B[33mAgora voce precisa tirar um " + myPoints + " para ganhar\u001B[0m");
                System.out.println("\u001B[38;5;208mCaso tire um 7 voce perde\u001B[0m");
                while (gameStatus == Status.CONTINUE) {

                    System.out.println("Pressione enter para jogar os dados");
                    sc.nextLine();
                    myPoints = rollDice();

                    if (myPoints == sumOfDice) {
                        gameStatus = Status.WON;
                        System.out.println("\u001B[32mVocê ganhou!\u001B[0m");
                    } else if (myPoints == 7) {
                        gameStatus = Status.LOST;
                        System.out.println("\u001B[31mVocê perdeu!\u001B[0m");
                    }

                }
                System.out.println("Deseja jogar outra vez ? (s/n)");
                if (sc.nextLine().equals("n")) {
                    System.out.println("Obrigado por jogar Craps! Até a próxima!");
                    break;
                }

            }

        }

        return gameStatus;
    }

    public int rollDice() {
        int die1 = 1 + randomNumbers.nextInt(6);
        int die2 = 1 + randomNumbers.nextInt(6);
        int sum = die1 + die2;
        System.out.printf("\u001B[38;5;207mPlayer rolled %d + %d = %d%n\u001B[0m", die1, die2, sum);
        return sum;

    }
}
