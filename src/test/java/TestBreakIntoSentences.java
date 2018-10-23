import com.aliasi.util.Files;
import org.junit.Test;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

public class TestBreakIntoSentences {

    @Test
    public void testInputAsString() throws Exception {

        Instant start = Instant.now();
        String text = "The induction of immediate-early (IE) response genes, such as egr-1,\n" +
                "  c-fos, and c-jun, occurs rapidly after the activation of T\n" +
                "  lymphocytes. " +
                "The process of activation involves calcium mobilization,\n" +
                "  activation of protein kinase C (PKC), and phosphorylation of tyrosine\n" +
                "  kinases. p21(ras), a guanine nucleotide binding factor, mediates\n" +
                "  T-cell signal transduction through PKC-dependent and PKC-independent\n" +
                "  pathways. The involvement of p21(ras) in the regulation of\n" +
                "  calcium-dependent signals has been suggested through analysis of its\n" +
                "  role in the activation of NF-AT. We have investigated the inductions\n" +
                "  of the IE genes in response to calcium signals in Jurkat cells (in\n" +
                "  the presence of activated p21(ras)) and their correlated\n" +
                "  consequences.";

        BreakIntoSentences breakIntoSentences = new BreakIntoSentences();
        breakIntoSentences.tokenizeSentence(text);
        breakIntoSentences.getSentenceCount();
        breakIntoSentences.getEachSentenceAsMap();

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end)); // prints PT1M3.553S
    }

    @Test
    public void testInputFromFile() throws Exception {

        Instant start = Instant.now();

        File file = new File("input/content.txt");
        String text = Files.readFromFile(file,"ISO-8859-1");
        BreakIntoSentences breakIntoSentences = new BreakIntoSentences();
        breakIntoSentences.tokenizeSentence(text);
        breakIntoSentences.getSentenceCount();
        Map<Integer, String> sentenceMap = breakIntoSentences.getEachSentenceAsMap();

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end)); // prints PT1M3.553S
    }
}