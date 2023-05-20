
import java.security.SecureRandom;

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

    int myPoints = 0;
    Status gameStatus;
    int sumOfDice = rollDice();

    public Status play() {
        switch (sumOfDice) {
            case 7:
            case 11:
                gameStatus = Status.WON;
                break;
            case 2:
            case 3:
            case 12:
                gameStatus = Status.LOST;
                break;
            default:
                gameStatus = Status.CONTINUE;
                myPoints = sumOfDice;
                System.out.printf("Point is %d%n", myPoints);
                break;
        }

        while (gameStatus == Status.CONTINUE) {
            sumOfDice = rollDice();

            if (sumOfDice == myPoints) {
                gameStatus = Status.WON;
            } else if (sumOfDice == 7) {
                gameStatus = Status.LOST;
            }
        }

        if (gameStatus == Status.WON) {
            System.out.println("\u001B[32mPlayer wins\u001B[32m");
        } else {
            System.out.println("\u001B[31mPlayer loses\u001B[31m");
        }

        return gameStatus;
    }

    public int rollDice() {
        int die1 = 1 + randomNumbers.nextInt(6);
        int die2 = 1 + randomNumbers.nextInt(6);
        int sum = die1 + die2;
        System.out.printf("Player rolled %d + %d = %d%n", die1, die2, sum);
        return sum;

    }
}
