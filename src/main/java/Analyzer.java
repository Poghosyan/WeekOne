package main.java;

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
					score = Integer.parseInt(parts[0]);
					if (parts.length != 2)
					    continue;
					Sentence sentence = new Sentence(score, parts[1]);
					sentences.add(sentence);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			return new ArrayList<>();
		}

		return sentences;
	}

	/**
	 *
	 * Finds all of the individual tokens/words in the text field of each Sentence in the List and
	 * create Word objects for each distinct word. The Word objects should keep track of the number of occurrences of
	 * that word in all Sentences, and the total cumulative score of all Sentences in which it appears. This method
	 * should then return a Set of those Word objects. If the input List of Sentences is null or is empty, the allWords
	 * method should return an empty Set. If a Sentence object in the input List is null, this method should ignore it
	 * and process the non-null Sentences. As you can see, allWords needs to tokenize/split the text of each Sentence
	 * to get the individual words.
	 *
	 * As an example, consider this text:
	 *
	 * It 's a lot of fun to learn about data structures.
	 *
	 * Your program should convert " It " to " it " (to make it lowercase) and ignore " 's " and the period at the end
	 * of the sentence since those tokens do not start with a letter.
	 *
	 * @param sentences
	 * @return
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
		ArrayList<Word> wordList = new ArrayList<>();
		StringTokenizer tokenizer;
		String current;

		for (Sentence sentence : sentences) {
			tokenizer = new StringTokenizer(sentence.text);
			while (tokenizer.hasMoreTokens()) {
				current = tokenizer.nextToken();
				current = current.toLowerCase();

				if (",.;:'!?-".contains(current.substring(0, 1))) {
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
		for (Word word : words) {
			if (word.getText() != null && !word.getText().isEmpty())
			calculatedScores.put(word.getText(), word.calculateScore());
		}
		return calculatedScores;
	}


	/**
	 * Finally, implement the calculateSentenceScore method in Analyzer.java. This method should use the Map to
	 * calculate and return the average score of all the words in the input sentence. Note that you will need to
	 * tokenize/split the sentence, as you did in Part 2. If a word in the sentence does not start with a letter, then
	 * you can ignore it, but if it starts with a letter and is not present in the Map, assign it a score of 0. You may
	 * assume that all words in the Map consist only of lowercase letters but do not assume that all words in the input sentence consist only of lowercase letters; you should
	 * convert them to lowercase before checking whether they’re contained in the Map. If the input Map is null or
	 * empty, or if the input sentence is null or empty or does not contain any valid words, this method should return
	 * 0. Although you can (should!) test each method individually, you can test the entire program using the main
	 * method in Analyzer.java. Be sure to specify the name of the input file as the argument to main. Please do not
	 * change the signature of the calculateSentenceScore method and do not modify Sentence.java or Word.java. Also,
	 * please do not create new .java files. If you need to create new classes, add them to Analyzer.java. Last, please
	 * make sure that your  Analyzer class is in the default package, i.e. there is no “package” declaration at the top
	 * of the source code.
	 *
	 * Helpful Hints
	 *
	 * Documentation about the methods in the List, Set, and Map interfaces are available as part of the Java API docs:
	 *
	 * https://docs.oracle.com/javase/8/docs/api/java/util/List.html
	 * https://docs.oracle.com/javase/8/docs/api/java/util/Set.html
	 * https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
	 *
	 * Refer to this documentation if you need help understanding the methods that are available to you. In implementing
	 * this program, we recommend that you implement and test each of the four methods one at a time. That is, rather
	 * than implementing all four methods and then hoping it all works correctly, do Part 1 and convince yourself that
	 * it works, then do Part 2 (either using the output of Part 1, or input that you create by hand) and convince
	 * yourself that it works, and so on. It’s a lot easier to debug this way!
	 *
	 * @param wordScores
	 * @param sentence
	 * @return
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

		/* IMPLEMENT THIS METHOD! */
		
		return 0; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
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
