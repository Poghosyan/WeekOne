package DataStructuresAndAlgo;

import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {

	/**
	 * This method assumes the input LinkedList is already sorted in non-descending order (i.e.,such that each element
	 * is greater than or equal to the one that is before it, and inserts the input int value into the correct location
	 * of the list. Note that the method does not return anything, but rather modifies the input LinkedList as a side
	 * effect. If the input LinkedList is null, this method should simply terminate.
	 *
	 * @param list
	 * @param value
	 */
	public static void insertSorted(LinkedList<Integer> list, int value) {

	}


	/**
	 * This method removes all instances of the N largest values in the LinkedList. Because the values are Strings, you
	 * will need to use the String class’ compareTo method to find the largest elements; see the Java API for help with
	 * that method. If the input LinkedList is null or if N is non-positive, this method should simply return without
	 * any modifications to the input LinkedList. Keep in mind that if any of the N largest values appear more than
	 * once in the LinkedList, this method should return remove all instances, so it may remove more than N elements
	 * overall. The other elements in the LinkedList should not be modified and their order must not be changed.
	 *
	 * @param list
	 * @param N
	 */
	public static void removeMaximumValues(LinkedList<String> list, int N) {

	}

	/**
	 * This method determines whether any part of the first LinkedList contains all elements of the second in the same
	 * order with no other elements in the sequence, i.e. it should return true if the second LinkedList is a
	 * subsequence of the first, and false if it is not. The method should return false if either input is null or
	 * empty.
	 *
	 * @param one
	 * @param two
	 * @return
	 */
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {


		return true; // this line is here only so this code will compile if you don't modify it
	}
}
