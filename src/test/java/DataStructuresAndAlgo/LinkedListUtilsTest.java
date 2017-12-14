package DataStructuresAndAlgo;

import org.junit.Before;
import org.junit.Test;

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
    }

    @Test
    public void containsSubsequence() {
    }
}