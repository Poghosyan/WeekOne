package Collections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Analyzer {

    public static List<Sentence> readFile(String filename) {
        ArrayList<Sentence> sentences = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            String nextLine;
            while ((nextLine = buffer.readLine()) != null) {
                String[] parts = nextLine.trim().split(" ", 2);
                int score;
                try {
                    if (parts.length != 2) {
                        continue;
                    }
                    score = Integer.parseInt(parts[0]);
                    if (score <= 2 && score >= -2) {
                        Sentence sentence = new Sentence(score, parts[1]);
                        sentences.add(sentence);
                    }
                } catch (NumberFormatException e) { }
            }
        } catch (IOException | NullPointerException e) {
            return new ArrayList<>();
        }

        return sentences;
    }

    /**
     * Finds all of the individual tokens/words in the text field of each Sentence in the List and
     * create Word objects for each distinct word.
     * <p>
     * As an example, consider this text:
     * <p>
     * It 's a lot of fun to learn about data structures .
     * <p>
     * Your program should convert " It " to " it " (to make it lowercase) and ignore " 's " and the period at the end
     * of the sentence since those tokens do not start with a letter.
     *
     * @param sentences
     * @return set of all words in the list of sentences given
     */
    public static Set<Word> allWords(List<Sentence> sentences) {
        ArrayList<Word> wordList = new ArrayList<>();
        StringTokenizer tokenizer;
        String current;
        if (sentences != null) {
            for (Sentence sentence : sentences) {
                if (sentence == null) {
                    continue;
                }
                tokenizer = new StringTokenizer(sentence.text);
                while (tokenizer.hasMoreTokens()) {
                    current = tokenizer.nextToken();
                    current = current.toLowerCase();

                    if (",.;:'!?-$".contains(current.substring(0, 1))) {
                        continue;
                    }

//				current = current.replaceAll(".?!:;,", "");

                    Word word = new Word(current);

                    if (wordList.contains(word)) {
                        word = wordList.get(wordList.indexOf(word));
                        word.increaseTotal(sentence.getScore());
                    } else {
                        word.increaseTotal(sentence.getScore());
                        wordList.add(word);
                    }
                }
            }
        }
        return new HashSet<>(wordList);
    }

    /**
     * This method should
     * iterate over each Word in the input Set, use the Word’s calculateScore method to get the average sentiment score
     * for that Word, and then place the text of the Word (as key) and calculated score (as value) in a Map.
     *
     * @param words
     * @return
     */
    public static Map<String, Double> calculateScores(Set<Word> words) {
        HashMap<String, Double> calculatedScores = new HashMap<>();
        if (words != null) {
            for (Word word : words) {
                if (word != null && !word.getText().isEmpty())
                    calculatedScores.put(word.getText(), word.calculateScore());
            }
        }
        return calculatedScores;
    }


    /**
     * Calculates and return the average score of all the words in the input sentence.
     *
     * @param wordScores the word scores which will be used to calculate average score of sentence
     * @param sentence
     * @return the average score of all the words in the input sentence
     */
    public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
        if (wordScores != null && !wordScores.isEmpty() && sentence != null && !sentence.isEmpty()) {
            StringTokenizer tokenizer = new StringTokenizer(sentence);
            String current;
            Double score = 0.0;
            int wordCount = 0;

            while (tokenizer.hasMoreTokens()) {
                current = tokenizer.nextToken();
                current = current.toLowerCase();

                if (",.;:'!?-$".contains(current.substring(0, 1))) {
                    continue;
                }

                wordCount++;

                if (wordScores.containsKey(current)) {
                    score += wordScores.get(current);
                }
            }

            return wordCount == 0 ? score : score / wordCount;
        }

        return 0;

    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify the name of the input file");
            System.exit(0);
        }
        String filename = args[0];
        System.out.print("Please enter a sentence: ");
        Scanner in = new Scanner(System.in);
        String sentence = in.nextLine();
        in.close();
        List<Sentence> sentences = Analyzer.readFile(filename);
        Set<Word> words = Analyzer.allWords(sentences);
        Map<String, Double> wordScores = Analyzer.calculateScores(words);
        double score = Analyzer.calculateSentenceScore(wordScores, sentence);
        System.out.println("The sentiment score is " + score);
    }
}
