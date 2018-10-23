import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class TestSentenceChunksCreater {

    @Test
    public void testInputFromFile() {

        Instant start = Instant.now();
        SentenceChunksCreater sentenceChunksCreater = new SentenceChunksCreater();
        List<String> sentenceList = sentenceChunksCreater.getChunksAsHashMap();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));

    }

    @Test
    public void testNormalizeInputFromFile() {

        Instant start = Instant.now();
        SentenceChunksCreater sentenceChunksCreater = new SentenceChunksCreater();
        List<String> sentenceList = sentenceChunksCreater.getChunksAsHashMap();

        String appendStr = "";
        for (String sent: sentenceList) {
            appendStr+=sent.trim() + " ";
        }
        appendStr = appendStr.trim();
        //replace " ' ” '  " all the quotes with space

        sentenceChunksCreater.fileContentWrite(appendStr);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));

    }

    @Test
    public void testSentenceCount() {

        SentenceChunksCreater sentenceChunksCreater = new SentenceChunksCreater();
        System.out.print("SENTENCE count " + sentenceChunksCreater.getCount());

    }
}