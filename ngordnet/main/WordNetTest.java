package ngordnet.main;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.List;


public class WordNetTest {
    @Test
    public void testHyponymsSimple() {
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt", "./data/wordnet/hyponyms11.txt");
        assertEquals(Set.of("antihistamine", "actifed"), wn.returnHyponyms(List.of("antihistamine")));
    }

//    @Test
//    public void testOccurrenceAndChangeK0() {
//        WordNet wn = new WordNet("./data/wordnet/synsets11.txt", "./data/wordnet/hyponyms11.txt");
//        List<String> arg = ;
//        String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
//        assertEquals(expected, wn.returnHyponyms(arg));
//    }
}
