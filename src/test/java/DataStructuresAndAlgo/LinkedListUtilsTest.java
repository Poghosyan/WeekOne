package DataStructuresAndAlgo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class LinkedListUtilsTest {

    @Test
    public void insertSorted() {
        LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 6, 9));
        LinkedListUtils.insertSorted(list, 8);
        assertEquals(list.get(5).longValue(), 8);
    }

    @Test
    public void removeMaximumValues() {
        LinkedList<String> list = new LinkedList<String>(Arrays.asList("1", "2", "3", "4", "5", "9"));
        LinkedList<String> repeatList = new LinkedList<String>(Arrays.asList("1", "2", "3", "4", "5", "5", "9"));

        LinkedListUtils.removeMaximumValues(list, 2);
        assertTrue(list.equals(new LinkedList<String>(Arrays.asList("1", "2", "3", "4"))));

        LinkedListUtils.removeMaximumValues(repeatList, 2);
        assertTrue(repeatList.equals(new LinkedList<String>(Arrays.asList("1", "2", "3", "4"))));

        LinkedListUtils.removeMaximumValues(list, -1);
        assertTrue(list.equals(new LinkedList<String>(Arrays.asList("1", "2", "3", "4"))));
    }

    @Test
    public void containsSubsequence() {
        LinkedList<Integer> one = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 6, 9));
        LinkedList<Integer> isContained = new LinkedList<Integer>(Arrays.asList(2, 3, 4));
        LinkedList<Integer> notContaind = new LinkedList<Integer>(Arrays.asList(2, 4));
        boolean shouldBeTrue = LinkedListUtils.containsSubsequence(one, isContained);
        boolean shouldBeFalse = LinkedListUtils.containsSubsequence(one, notContaind);
        assertTrue(shouldBeTrue);
        assertFalse(shouldBeFalse);

        shouldBeFalse = LinkedListUtils.containsSubsequence(one, null);
        assertFalse(shouldBeFalse);
    }
}