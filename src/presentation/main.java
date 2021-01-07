package presentation;

import logic.Huffman;
import logic.Node;

import java.util.*;

public class main {

    public static void main(String[] args)
    {


        Huffman huffman = new Huffman("encoded and decoded with huffman");
        //create a map that stores frequencies of characters
        Map<Character,Integer> map = huffman.createFreqMap();
        //sort the map
        Map<Character,Integer> sortedMap = huffman.sortMap(map);
        //enqueue the nodes
        PriorityQueue<Node> queue = huffman.createPriorityQueue(sortedMap);


        //generate the huffman tree
        Node huffTree = huffman.createHuffmanTree(queue);

        //extract the codewords from the tree
        Map<Character,String> codewords = new HashMap<>();
        Map<Character,BitSet> bits = new HashMap<>();
        huffman.codewords(codewords,huffTree,"");
        huffman.codewords(bits,huffTree, new BitSet());
        //use the code words to compress the text
        String huff = huffman.HuffmanCompression(codewords);
        System.out.println(huff);

        //decode the same text using the codewords
        System.out.println(huffman.DecodeHuffman(huff,codewords));

        System.out.println("Number of bits: " + huff.length() + " which is more efficient by : "  + (huffman.text.length()*8-huff.length()) + " than normal ascii encoding");


    }
}
