package logic;

import java.util.*;
import java.util.stream.Collectors;

public class Huffman {

    public static String text = "";

    public Huffman(String text) {
        this.text = text;
    }

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        Huffman.text = text;
    }

    public static Map<Character,Integer> createFreqMap()
    {
        char[] chars = text.toCharArray();
        Map<Character,Integer> charFrequency = new HashMap<Character, Integer>();

        for(int i=0;i<chars.length;i++) //O(n)
        {
            if(!charFrequency.containsKey(chars[i]))
                charFrequency.put(chars[i],1); //O(1)
            else
                charFrequency.put(chars[i],charFrequency.get(chars[i]) +1); //O(1)

        }

        return charFrequency;

    }//O(n)

    public static Map<Character,Integer> sortMap(Map<Character,Integer> map)
    {
        Map<Character,Integer> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        return sortedMap;
    }
    public static PriorityQueue<Node> createPriorityQueue(Map<Character,Integer> map)
    {
        ArrayList<Node> nodes = new ArrayList<Node>();
        //System.out.println(sortedMap);

        for(Map.Entry<Character,Integer> entry : map.entrySet()) //O(n)
        {
            //System.out.println(entry.getKey() + " " + entry.getValue());
            Node node = new Node(entry.getKey(),entry.getValue()); //O(1)
            nodes.add(node);
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(nodes); //O(1)

        return queue;

    }//O(n)

    public static Node createHuffmanTree(PriorityQueue<Node> queue)
    {
        Node tree = new Node();
        while(queue.size() > 1) //O(n)
        {
            Node node1 = queue.poll();
            Node node2 = queue.poll();
            int newFreq = node1.myFrequency + node2.myFrequency;
            Node newnode = new Node(newFreq,node1,node2); //O(1)

            //System.out.println(newFreq);

            queue.offer(newnode); //O(1)
        }
        tree = queue.poll();
        return tree;
    }//O(n)



    public static Map<Character,String> codewords(Map<Character,String> cws, Node node, String s)
    {

        if(node.isLeaf())
        {
            cws.put(node.myChar,s);
            System.out.println(node.myChar + " " + s);
        }
        else {

            codewords(cws, node.Left, s + "0");
            codewords(cws, node.Right, s + "1");

            //This is a customized DFS, which is log(n) complexity
        }

        return cws;


    }//O(log n)

    //i tried to implement the BitSet feature but I just dont know how to get
    // the BitSets to build properly because of the recursive nature of my original implementation
    static int index = 0;
    public static Map<Character,BitSet> codewords(Map<Character,BitSet> cws, Node node, BitSet s)
    {

        if(node.isLeaf())
        {
            s.set(index + 1);
            cws.put(node.myChar,s);
            System.out.println(node.myChar + " " + index + " " + s);

        }
        else {

            index++;
            System.out.println(index);
            codewords(cws, node.Left, s);
            s.set(index);
            codewords(cws, node.Right, s);
            index = 0;
        }
        return cws;
    }

    public static String HuffmanCompression(Map<Character,String> codewords)
    {
        String result="";
        char[] chars = text.toCharArray();
        for(char c : chars) //O(n)
        {
            result += codewords.get(c); //O(1)
        }

        return result;
    }//O(n)


    public static String DecodeHuffman(String encodedtext,Map<Character,String> codewords)
    {
        char[] chars = encodedtext.toCharArray();
        String decoder = "";
        String result = "";
        for(char c : chars) //O(n)
        {
            decoder += c;
            if(codewords.containsValue(decoder)) //O(1) since its a hmap
                for(Map.Entry<Character,String> entry : codewords.entrySet()) //O(n)
                {
                    if(Objects.equals(decoder,entry.getValue())) { //O(1) since its an hmap entry

                        result += entry.getKey();
                        decoder="";
                    }
                }

        }


        return result;
    }
}//O(n*n)
