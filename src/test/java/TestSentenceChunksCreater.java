import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class TestSentenceChunksCreater {

    Instant start;

    @Before
    public void beforeMethod() {
        start = Instant.now();
    }

    @After
    public void AfterMethod() {
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }

    @Test
    public void testInputFromFile() {

        SentenceChunksCreater sentenceChunksCreater = new SentenceChunksCreater();
        List<String> sentenceList = sentenceChunksCreater.getChunksAsHashMap();

    }

    @Test
    public void testSingleSentenceFromFile() {

        SentenceChunksCreater sentenceChunksCreater = new SentenceChunksCreater();
        List<String> sentenceList = sentenceChunksCreater.getChunksAsHashMap();
        String sentence = sentenceList.get(0);

    }

    @Test
    public void testSentenceCount() {

        SentenceChunksCreater sentenceChunksCreater = new SentenceChunksCreater();
        System.out.print("SENTENCE count " + sentenceChunksCreater.getCount());

    }

    @Test
    public void testNormalizeInputFromFile() {

        SentenceChunksCreater sentenceChunksCreater = new SentenceChunksCreater();
        List<String> sentenceList = sentenceChunksCreater.getChunksAsHashMap();

        String appendStr = "";
        for (String sent : sentenceList) {
            appendStr += sent.trim() + " ";
        }
        appendStr = appendStr.trim();
        //replace " ' ” '  " all the quotes with space

        sentenceChunksCreater.fileContentWrite(appendStr);
    }
}