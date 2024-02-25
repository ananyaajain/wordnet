package ngordnet.main;
import java.util.*;

public class Graph {
    //private int Vert;
    //private int Edge;
    private HashMap<Integer, LinkedList<Integer>> adjList;
    private Stack<Integer> myStack;
    private LinkedList<Integer> allChildren;
    private int size;

    public Graph() {

        adjList = new HashMap();
        allChildren = new LinkedList<>();
        size = 0;
    }

    public void addEdge(int v, int e) {
        if (adjList.get(v) == null) {
            LinkedList<Integer> a = new LinkedList<>();
            a.add(e);
            adjList.put(v, a);
            size += 1;
        } else {
            adjList.get(v).add(e);
            size += 1;
        }
    }

    private LinkedList<Integer> Children(int x) {
        return adjList.get(x);
    }

    private void traverseHelp(int x, LinkedList<Integer> lst) {
        lst.add(x);
        LinkedList<Integer> children = Children(x);
        if (children!=null) {
            for (int i : children) {
                traverseHelp(i, lst);
            }
        }
    }


    public LinkedList<Integer> traverse(LinkedList<Integer> x) {
        LinkedList<Integer> lst = new LinkedList<>();
        for (int id : x) {
            traverseHelp(id, lst);
        }
        return lst;
    }

    public boolean isEmpty(int x) {
        if (adjList.get(x) != null) {
            return false;
        }
        return true;
    }

//    public Iterable<String> adj(int v) {return null;}

    public String graphReturnHello() {return "Hello!";}
}

//package ngordnet.main;
//import java.util.*;
//
//public class Graph {
//    //private int Vert;
//    //private int Edge;
//    private HashMap<Integer, ArrayList<Integer>> adjList;
//    private Stack<Integer> myStack;
//    private ArrayList<Integer> allChildren;
//    private int size;
//
//    public Graph() {
//
//        adjList = new HashMap();
//        allChildren = new ArrayList<>();
//        size = 0;
//    }
//
//    public void addEdge(int v, int e) {
//        if (adjList.get(v) == null) {
//            ArrayList<Integer> a = new ArrayList<>();
//            a.add(e);
//            adjList.put(v, a);
//            size += 1;
//        } else {
//            adjList.get(v).add(e);
//            size += 1;
//        }
//    }
//
//    private ArrayList<Integer> Children(int x) {
//        return adjList.get(x);
//    }
//
//    private void traverseHelp(int x, ArrayList<Integer> lst) {
//        lst.add(x);
//        ArrayList<Integer> children = Children(x);
//        if (children == null) {
//            return ;
//        }
//        else {
//            for (int i: children) {
//                traverseHelp(i, lst);
//            }
//        }
//    }
//
//
//    public ArrayList<Integer> traverse(int x) {
//        ArrayList<Integer> lst = new ArrayList<>();
//        traverseHelp(x, lst);
//        return lst;
//    }
//
//    public boolean isEmpty(int x) {
//        if (adjList.get(x) != null) {
//            return false;
//        }
//        return true;
//    }
//
////    public Iterable<String> adj(int v) {return null;}
//
//    public String graphReturnHello() {return "Hello!";}
//}
