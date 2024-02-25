package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;

import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wn;
    private NGramMap ngm;
    public HyponymsHandler(WordNet wn, NGramMap ng) {
        this.wn = wn;
        this.ngm = ng;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        if (k == 0) {
            return wn.returnHyponyms(words).toString();
        } else {
            return wn.returnHyponyms(words, k, ngm, startYear, endYear).toString();
        }
    }
}

