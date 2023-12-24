package wordguessinggame;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class WordGuessingGame {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Integer> scores = new ArrayList<>();
    static ArrayList<String> words = new ArrayList<>();
    static ArrayList<Character> knownLetter = new ArrayList<>();
    static int userRights = 0;
    static int userPoints = 0;
    static String selectedWord;
    static String newWord = null;
    static int selection = 0;
    //I created some variables and arraylists.These are outside of main because I need to use them several methods.

    public static String getMaskedWord(String word, ArrayList<Character> knownLetter) {
        //it is return the hidden word with updates.
        String result = "";
        for (int i = 0; i < word.length(); i++) {

            Character currentChar = word.charAt(i);
            //it is take currentchar of word.
            if (knownLetter.contains(currentChar)) {
                result = result.concat(currentChar.toString());

            } else {
                result = result.concat("_ ");
            }
        }//it is create the word if selected word contains current char with _'s and current char.
        return result;
    }

    public static void mainMenu() {
        System.out.print("1-" + "add a word\n");
        System.out.print("2-" + "new game\n");
        System.out.print("3-" + "show scores\n");
        System.out.print("4-" + "exit\n");
        System.out.println("Select a number:");
        //This method shows the main menu for user.
        selection = input.nextInt();
        switch (selection) {
            case 1:
                addWord();
                break;
            case 2:
                newGame();
                break;
            case 3:
                showScores();
                break;
            case 4:
                System.out.println("Game is closing.");
                System.exit(0);
                //this case end the game directly.
                break;
            default:
                System.out.println("Invalid input. Try again.");

                break;
        }
    }

    public static void addWord() {
        System.out.println("Enter the word which you want to add to game:");
        newWord = input.next();

        if (newWord.length() < 3) {
            System.out.println("This word is too short.You should give more than 3 letter.");
            //this if check is word more than 3 letter.
        }
        if (newWord.length() > 3) {
            for (String b : words) {
                if (newWord.equalsIgnoreCase(b)) {
                    System.out.println("You cannot add a word two time.");
                    //this if check is word added before.
                }

            }
        }
        if (newWord.length() > 3) {
            for (char c : newWord.toCharArray()) {
                if (Character.isDigit(c)) {
                    System.out.println("You cannot enter a word which is include digit.");
                    //this if check is word include digit.
                }

            }
        }
        System.out.println("Word is added.");
        words.add(newWord);
        //This case(1) check all conditions for example is word include digit.

        mainMenu();

    }

    public static void newGame() {
        System.out.println("Game is starting.");
        Collections.shuffle(words);//this is shuffle all word in words arraylist.
        selectedWord = words.get(0);//this take first member of words arraylist. 
        userRights = selectedWord.length() / 2;
        userPoints = 0;
        knownLetter.clear();
        System.out.println("Selected word: " + selectedWord.replaceAll(".", "_ "));
        //this show the gamer selected word but with _'s.
        System.out.println("You have " + userRights + " rights.");
        String maskedWord = null;

        while (userRights > 0) {

            maskedWord = getMaskedWord(selectedWord, knownLetter);
            //it is call getMaskedWord method.
//                        System.out.println("Selected word: " + selectedWord.replaceAll(".", "_ "));
            System.out.println("Select one char: ");
            String guess1 = input.next();
            //it takes input from user to guess the hidden word.
            knownLetter.add(guess1.charAt(0));
            //it takes character which user enter in known letter arraylist.

            if (selectedWord.contains(guess1)) {
                for (int i = 0; i < selectedWord.length(); i++) {

                    maskedWord = getMaskedWord(selectedWord, knownLetter);

                    if (maskedWord.equals(selectedWord)) {
                        //if user know the word true it finish the loop. 
                        break;
                    }
                }
            }
            System.out.println(maskedWord);
            //it show the updated hidden word every guess.
            if (!maskedWord.contains("_")) {
                userPoints += 10;
                System.out.println("You won.");
                System.out.println("Your point is= " + userPoints);
                //if the guess is true gamer takes 10 points and loop finish.
                break;
            } else if (!selectedWord.contains(guess1)) {
                --userRights;
                //if guess is not true gamer lose 1 right.
                System.out.println("There is not " + guess1);
                System.out.println("You have " + userRights + " rights.");
                if (userRights == 0) {
                    System.out.println("You do not have any rights.");

                }

            }
        }
        mainMenu();
    }

    public static void showScores() {
        System.out.println("Score=" + userPoints);
        Collections.sort(scores);
        for (int j : scores) {
            System.out.println(scores);
        }
        //this case list the scores of gamer and print them.
        mainMenu();
    }

    public static void main(String[] args) {
        mainMenu();
    }

}
