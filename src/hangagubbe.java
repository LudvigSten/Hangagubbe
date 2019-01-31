import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class hangagubbe {
    public static void main(String[] args) {
        int points = 0;
        String guess;
        int guessesLeft = 3;
        String guessedLetters = "";

        //Läs ord listan (input.txt)
        Scanner inputFile = null;
        try {
            inputFile = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("filen finns int, använd tangentborde iställe..");
            inputFile = new Scanner(System.in);
        }
        ArrayList<String> wordList = new ArrayList<>();
        while (inputFile.hasNextLine()) {
            wordList.add(inputFile.nextLine());
        }
        //Hämta ett slumpvalt ord från ord listan (input.txt)
        Random R = new Random();
        String word = wordList.get(R.nextInt(wordList.size()));
        String star = "*";
        char[] myWord = new char[word.length()];
        for(int i = 0; i<word.length(); i++){

            myWord[i] += star.charAt(0);
        }
        for(int s = 0; s < 1; s++){
            System.out.print(myWord);
            System.out.println();
        }
        //Gissning
        Scanner in = new Scanner(System.in);
        while (guessesLeft > 0) {
            char guessAsChar = in.nextLine().charAt(0);
            guess = Character.toString(guessAsChar);
            while (guessedLetters.contains("" + guessAsChar)) {
                System.out.println("Du ha redan gissa på han!");
                guessAsChar = in.nextLine().charAt(0);
            }
         //Rätt gissning
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess.charAt(0)) {
                    myWord[i] = guess.charAt(0);
                    points ++;
                }
            }
            //Vunnit
            System.out.print(myWord);
            System.out.println();
            if(points == word.length()){
                System.out.println("Congrats my dude!");
                break;
            }

            boolean found = false;
            guessedLetters += guessAsChar;
            char[] wordAsChar = word.toCharArray();
            for(int u = 0; u < wordAsChar.length; u++){
                if (wordAsChar[u] == guessAsChar){
                    myWord[u] = guessAsChar;
                    found = true;
                }
            }
            //Metod för fel gissning och förlust
            guessesLeft = guessWrong(guessesLeft, word, found);
        }

    }

            //Metoder

    private static int guessWrong(int guessesLeft, String word, boolean found) {
        if(!found){
            guessesLeft--;
            System.out.println("Du har " + guessesLeft + " kvar!");
            if(guessesLeft == 2){
                System.out.println(" ___________________ \n"
                        + "|/                 | \n"
                        + "|                  0 \n"
                        + "|                  \n"
                        + "|                  \n"
                        + "| \n"
                        + "| \n"
                        + "/ \\");
            }
            if(guessesLeft == 1){
                System.out.println(" ___________________ \n"
                        + "|/                 | \n"
                        + "|                  0 \n"
                        + "|                 -|- \n"
                        + "|                  \n"
                        + "| \n"
                        + "| \n"
                        + "/ \\");
            }
            if(guessesLeft == 0){
                System.out.println(" ___________________ \n"
                        + "|/                 | \n"
                        + "|                  0 \n"
                        + "|                 -|- \n"
                        + "|                 / \\ \n"
                        + "| \n"
                        + "| \n"
                        + "/ \\");
                System.out.println("the word was " + word);
            }
        }
        return guessesLeft;
    }
}
