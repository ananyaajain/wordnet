package ngordnet.main;
import edu.princeton.cs.algs4.In;
import ngordnet.ngrams.NGramMap;

import java.util.PriorityQueue;

import java.util.*;
import ngordnet.ngrams.TimeSeries;

public class WordNet {
    private Graph IDtoID;
    private HashMap<Integer, LinkedList<String>> IDtoWord;
    private HashMap<String, LinkedList<Integer>> wordToID;

    //wrapper for a graph
    public WordNet(String File1, String File2) {
        IDtoID = new Graph();
        IDtoWord = new HashMap<>();
        wordToID = new HashMap<String, LinkedList<Integer>>();
        //build the graph --> add all the edges, parse them out from two data files - hyponyms and synsets
        In syn = new In(File1);
        In hyp = new In(File2);
        In hash3 = new In(File1);

        while (hyp.hasNextLine()) {
            String[] words = hyp.readLine().split(",");
            int key = Integer.parseInt(words[0]);
            for (int x = 1; x < words.length; x++) {
                IDtoID.addEdge(key, Integer.parseInt(words[x]));
            }
        }

        while (syn.hasNextLine()) {
            String[] x = syn.readLine().split(",");
            String[] temp = x[1].split(" ");
            LinkedList<String> y = new LinkedList<>();
            for (String i : temp) {
                y.add(i);
            }
            IDtoWord.put(Integer.parseInt(x[0]), y);
        }

        while (hash3.hasNextLine()) {
            String[] line = hash3.readLine().split(",");
            String[] words = line[1].split(" ");
            for (String word : words) {
                if (wordToID.containsKey(word)) {
                    wordToID.get(word).add(Integer.valueOf(line[0]));
                } else {
                    LinkedList<Integer> ids = new LinkedList<>();
                    ids.add(Integer.valueOf(line[0]));
                    wordToID.put(word, ids);
                }
            }
        }

    }

    public TreeSet<String> helperHyponyms(String word) {
        TreeSet<String> connections = new TreeSet<>();
//    //    for (int i = 0; i < IDtoWord.size(); i++) {
//    //        for (String val : IDtoWord.get(i)) {
//    //            if (val.equals(word)) {
        if (wordToID.get(word) != null) {
            LinkedList<Integer> i = wordToID.get(word);
            LinkedList<Integer> ids = IDtoID.traverse(i);
            for (int id : ids) {
                for (String w : IDtoWord.get(id)) {
                    connections.add(w);
                }
            }
        }
        return connections;
    }

    public TreeSet<String> returnHyponyms(List<String> given) {
        if (given.size() > 1) {
            TreeSet<String> first = helperHyponyms(given.get(0));
            for (int i = 1; i < given.size(); i++) {
                TreeSet<String> comp = helperHyponyms(given.get(i));
                first.retainAll(comp);
            }
            return first;
        } else {
            return helperHyponyms(given.get(0));
        }
    }

    public TreeSet<String> returnHyponyms(List<String> given, int k, NGramMap ngm, int startYear, int endYear) {
        PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
        TreeSet<String> ts = returnHyponyms(given);
        HashMap<Double, String> freqWord = new HashMap<>();
        LinkedList<String> temp = new LinkedList<>();
        TreeSet<String> ret = new TreeSet<>();

        for (String str : ts) {
            TimeSeries hist = ngm.countHistory(str, startYear, endYear);
            List<Double> allData = hist.data();
            double sum = 0.0;
            for (Double val : allData)
                sum += val;
//            if (sum != 0.0) {
                pq.add(sum);
                freqWord.put(sum, str);
//            }
        }
        int count = 0;
        while (count < k && !pq.isEmpty()) {
            Double x = pq.poll();
            if (x != 0.0) {
                ret.add(freqWord.get(x));
                count++;
            } else {
                count++;
            }
        }
        return ret;
    }
}




//package ngordnet.main;
//import edu.princeton.cs.algs4.In;
//import ngordnet.ngrams.NGramMap;
//
//import java.util.PriorityQueue;
//
//import java.util.*;
//import ngordnet.ngrams.TimeSeries;
//
//public class WordNet {
//    private Graph IDtoID;
//    private HashMap<Integer, LinkedList<String>> IDtoWord;
//    private int count;
//    private HashMap<LinkedList<String>, Integer> wordToID;
//
//    //wrapper for a graph
//    public WordNet(String File1, String File2) {
//        count = 0;
//        IDtoID = new Graph();
//        IDtoWord = new HashMap<>();
//        wordToID = new HashMap<LinkedList<String>, Integer>();
//        //build the graph --> add all the edges, parse them out from two data files - hyponyms and synsets
//        In syn = new In(File1);
//        In hyp = new In(File2);
//
//        while (hyp.hasNextLine()) {
//            String[] words = hyp.readLine().split(",");
//            int key = Integer.parseInt(words[0]);
//            for (int x = 1; x < words.length; x++) {
//                IDtoID.addEdge(key, Integer.parseInt(words[x]));
//            }
//        }
//
//        while (syn.hasNextLine()) {
//            String[] x = syn.readLine().split(",");
//            String[] temp = x[1].split(" ");
//            LinkedList<String> y = new LinkedList<>();
//            for (String i : temp) {
//                y.add(i);
//            }
//            IDtoWord.put(Integer.parseInt(x[0]), y);
//            wordToID.put(y, Integer.parseInt(x[0]));
//        }
//
//    }
//
//    public TreeSet<String> helperHyponyms(String word) {
//        TreeSet<String> connections = new TreeSet<>();
//        for (int i = 0; i < IDtoWord.size(); i++) {
//            for (String val : IDtoWord.get(i)) {
//                if (val.equals(word)) {
//                    ArrayList<Integer> ids = IDtoID.traverse(i);
//                    for (int id : ids) {
//                        for (String w : IDtoWord.get(id)) {
//                            connections.add(w);
//                        }
//                    }
//                }
//            }
//        }
//        return connections;
//    }
//
//    public TreeSet<String> returnHyponyms(List<String> given) {
//        if (given.size() > 1) {
//            TreeSet<String> first = helperHyponyms(given.get(0));
//            for (int i = 1; i < given.size(); i++) {
//                TreeSet<String> comp = helperHyponyms(given.get(i));
//                first.retainAll(comp);
//            }
//            return first;
//        } else {
//            return helperHyponyms(given.get(0));
//        }
//    }
//
//    public TreeSet<String> returnHyponyms(List<String> given, int k, NGramMap ngm, int startYear, int endYear) {
//        PriorityQueue<Double> pq = new PriorityQueue<>();
//        TreeSet<String> ts = returnHyponyms(given);
//        HashMap<Double, String> freqWord = new HashMap<>();
//        TreeSet<String> ret = new TreeSet<>();
//
//        for (String str : ts) {
//            TimeSeries hist = ngm.countHistory(str, startYear, endYear);
//            List<Double> allData = hist.data();
//            Double sum = 0.0;
//            for (Double val : allData)
//                sum += val;
//            pq.add(sum);
//            freqWord.put(sum, str);
//        }
//        count = ts.size();
//        while (count > k) {
//            pq.poll();
//            count--;
//        }
//        for (Double i : pq) {
//            ret.add(freqWord.get(i));
//        }
//        return ret;
//    }
//}

