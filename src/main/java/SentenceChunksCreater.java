import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunking;
import com.aliasi.sentences.MedlineSentenceModel;
import com.aliasi.sentences.SentenceChunker;
import com.aliasi.sentences.SentenceModel;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;
import com.aliasi.util.Files;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class SentenceChunksCreater {

    static final TokenizerFactory TOKENIZER_FACTORY = IndoEuropeanTokenizerFactory.INSTANCE;
    static final SentenceModel SENTENCE_MODEL = new MedlineSentenceModel();
    static final SentenceChunker SENTENCE_CHUNKER = new SentenceChunker(TOKENIZER_FACTORY, SENTENCE_MODEL);
    List<String> sentenceList = new ArrayList();
    String text = "";

    public SentenceChunksCreater() {

    }

    public String fileContentReader() {
        try {
            File file = new File("input/content.txt");
            String text = Files.readFromFile(file, "ISO-8859-1");
            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void fileContentWrite(String string) {
        try {
            File file = new File("output/NormalizeContent.txt");
            FileUtils.writeStringToFile(file, string,"ISO-8859-1");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getChunksAsHashMap() {

        text = fileContentReader();
        Chunking chunking = SENTENCE_CHUNKER.chunk(text.toCharArray(), 0, text.length());
        Set<Chunk> sentences = chunking.chunkSet();
        if (sentences.size() < 1) {
            System.out.println("No sentence chunks found.");
            return sentenceList;
        }
        String slice = chunking.charSequence().toString();
        int i = 1;
        for (Iterator<Chunk> it = sentences.iterator(); it.hasNext(); ) {
            Chunk sentence = it.next();
            int start = sentence.start();
            int end = sentence.end();
            System.out.println("SENTENCE " + (i++) + ":");
            System.out.println(slice.substring(start, end));
            sentenceList.add(slice.substring(start, end));
        }
        return sentenceList;
    }

    public int getCount() {

        text = fileContentReader();
        Chunking chunking = SENTENCE_CHUNKER.chunk(text.toCharArray(), 0, text.length());
        Set<Chunk> sentences = chunking.chunkSet();
        return sentences.size();
    }
}