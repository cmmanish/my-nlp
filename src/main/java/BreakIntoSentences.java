import com.aliasi.sentences.MedlineSentenceModel;
import com.aliasi.sentences.SentenceModel;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Use SentenceModel to find sentence boundaries in text
 */
public class BreakIntoSentences {

    final private static Logger log = Logger.getLogger(String.valueOf(BreakIntoSentences.class));
    static final TokenizerFactory TOKENIZER_FACTORY = IndoEuropeanTokenizerFactory.INSTANCE;
    static final SentenceModel SENTENCE_MODEL = new MedlineSentenceModel();
    List<String> tokenList = new ArrayList<>();
    List<String> whiteList = new ArrayList<>();
    int[] sentenceBoundaries;
    Map<Integer, String> sentenceMap = new HashMap<>();

    public void tokenizeSentence(String freeText) {

        try {
            Tokenizer tokenizer = TOKENIZER_FACTORY.tokenizer(freeText.toCharArray(), 0, freeText.length());
            tokenizer.tokenize(tokenList, whiteList);
        } catch (Exception e) {

        }
    }

    public int getSentenceCount() {
        try {
            String[] tokens = new String[tokenList.size()];
            String[] whites = new String[whiteList.size()];
            tokenList.toArray(tokens);
            whiteList.toArray(whites);

            sentenceBoundaries = BreakIntoSentences.SENTENCE_MODEL.boundaryIndices(tokens, whites);
            if (sentenceBoundaries.length < 1) {
                System.out.println("No sentence boundaries found.");
                return 0;
            }
        } catch (Exception e) {
        }
        System.out.println("INPUT -  " + sentenceBoundaries.length + " SENTENCES");
        return sentenceBoundaries.length;
    }

    public Map<Integer, String> getEachSentenceAsMap() {
        String sentence = "";
        try {
            String[] tokens = new String[tokenList.size()];
            String[] whites = new String[whiteList.size()];
            tokenList.toArray(tokens);
            whiteList.toArray(whites);
            String word = "";

            int[] sentenceBoundaries = BreakIntoSentences.SENTENCE_MODEL.boundaryIndices(tokens, whites);
            if (sentenceBoundaries.length < 1) {
                System.out.println("No sentence boundaries found.");
            }
            int sentStartTok = 0;
            int sentEndTok = 0;
            for (int i = 0; i < sentenceBoundaries.length; ++i) {
                sentEndTok = sentenceBoundaries[i];
                System.out.println("SENTENCE " + (i + 1) + ": ");
                for (int j = sentStartTok; j <= sentEndTok; j++) {
                    word = tokens[j] + whites[j + 1];
                    System.out.print(word);
                    sentence += word;
                }
                System.out.println();
                sentStartTok = sentEndTok + 1;
                sentenceMap.put(i, sentence);
                sentence = "";
            }
        } catch (Exception e) {
        }
        return sentenceMap;
    }
}