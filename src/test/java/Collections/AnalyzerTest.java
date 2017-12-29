package Collections;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AnalyzerTest {

    @Test
    public void readFile() {
        List<Sentence> sentences = Analyzer.readFile("reviews.txt");
        assertTrue(!sentences.isEmpty());
    }
}