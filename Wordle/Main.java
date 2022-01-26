import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class Main{
    public static void main(String[] args){
        ArrayList<String> words = loadWords("words.txt");
        ArrayList<String> guesses = new ArrayList<>();
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        HashSet<String> wrongLetters = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        String ans = words.get((int)(Math.random()*words.size()));        
        System.out.println("V = letter in the correct place, X = letter not in the word, _ = letter in the word but not correct place");
        String guess = sc.nextLine();
        int tries = 0;
        while(true){
            if(!words.contains(guess)){
                System.out.println("invaldie owrd");
            } else if(guesses.contains(guess)){
                System.out.println("u alrdy guessed this word");
            } else{
                results.add(new ArrayList<>());
                for(int i = 0; i<guess.length(); i++){
                    if(ans.charAt(i) == guess.charAt(i)){
                        results.get(tries).add("V");
                    } else if(ans.contains(guess.substring(i,i+1))){
                        results.get(tries).add("_");
                    } else {
                        results.get(tries).add("X");
                        wrongLetters.add(guess.substring(i,i+1));
                    }
                }
                guesses.add(guess);
                tries++;
                System.out.print("\f");
                System.out.println("V = letter in the correct place, X = letter not in the word, _ = letter in the word but not correct place");
                displayGuesses(guesses, results, wrongLetters, tries);
                if(guess.equals(ans)){
                    System.out.println("u won with " + tries + " tries the word was " + ans);
                    break;
                }
                if(tries>=6){
                    System.out.println("u lost. the word was " + ans);
                    break;
                }
            }
            guess = sc.nextLine();
        }
    }

    public static void displayGuesses(ArrayList<String> guesses, ArrayList<ArrayList<String>> results, HashSet<String> wrongLetters, int tries){
        for(int i = 0; i<guesses.size(); i++){
            for(int j = 0; j<guesses.get(i).length(); j++){
                System.out.print(guesses.get(i).substring(j, j+1) + " ");
            }
            System.out.println();
            for(int j = 0; j<results.get(i).size(); j++){
                System.out.print(results.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Wrong Letters:");
        for(String i : wrongLetters){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(tries + " tries used. " + (6-tries) + " remaining");
    }

    public static ArrayList<String> loadWords(String n){
        ArrayList<String> ar = new ArrayList<>();
        File wordFile = new File(n);
        try{
            Scanner fileScanner = new Scanner(wordFile);
            while(fileScanner.hasNext()){
                String w = fileScanner.nextLine();
                if(w.length() == 5 && !Character.isUpperCase(w.charAt(0))){
                    ar.add(w);
                }
            }
        } catch(FileNotFoundException e){
            System.out.println(e);
        }
        return ar;
    }
}
