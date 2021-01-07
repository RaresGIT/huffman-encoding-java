package testing;
import logic.Huffman;
import logic.Node;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class UnitTests {

    public String ReadFromFile()
    {   String data = "";
        try {
            File myObj = new File("testinginput.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                data =  data + myReader.nextLine();

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

        return data;
    }

    @Test
    public void TestFreqMap()
    {
        long starttime = System.currentTimeMillis();
        System.out.println("-- TEST FREQ MAP --");
        Huffman tester = new Huffman("Test123t1");


        Map<Character,Integer> map = new HashMap<Character, Integer>();
        map.put('1',2);
        map.put('2',1);
        map.put('s',1);
        map.put('3',1);
        map.put('T',1);
        map.put('t',2);
        map.put('e',1);
        System.out.println(map + " freqs for text: Test123t1");

        long endtime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endtime-starttime) + "ms");
        assertEquals(map,tester.createFreqMap());
    }

    @Test
    public void TestSorting()
    {
        long starttime = System.currentTimeMillis();
        System.out.println("-- TEST SORTING --");
        Huffman tester = new Huffman("Test123t1");


        Map<Character,Integer> map = new HashMap<Character, Integer>();
        Map<Character,Integer> sortedmap = new HashMap<Character, Integer>();
        map = tester.createFreqMap();
        sortedmap = tester.sortMap(map);
        System.out.println(sortedmap);

        long endtime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endtime-starttime) + "ms");

        //check if the contents remain the same, so no elements get manipulated during sorting
        assertEquals(map,sortedmap);

    }

    @Test
    public void TestQueues()
    {
        long starttime = System.currentTimeMillis();
        System.out.println("-- TEST PRIORITY QUEUE --");
        Huffman tester = new Huffman("Test123t1");
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        Map<Character,Integer> sortedmap = new HashMap<Character, Integer>();
        map = tester.createFreqMap();
        sortedmap = tester.sortMap(map);
        System.out.println(sortedmap);

        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue = tester.createPriorityQueue(sortedmap);
        System.out.println(queue);

        long endtime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endtime-starttime) + "ms");

        //check that all elements are in the tree
        assertEquals(sortedmap.size(),queue.size());

    }

    @Test
    public void TestTreeCreation()
    {
        long starttime = System.currentTimeMillis();
        System.out.println("-- TEST TREE CREATION --");
        Huffman tester = new Huffman("Test123t1");
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        Map<Character,Integer> sortedmap = new HashMap<Character, Integer>();
        map = tester.createFreqMap();
        sortedmap = tester.sortMap(map);

        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue = tester.createPriorityQueue(sortedmap);


        Node tree = new Node();
        tree = tester.createHuffmanTree(queue);
        System.out.println(tree);

        long endtime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endtime-starttime) + "ms");

        //check if the tree is actually being populated
        assertNotNull(tree);


    }

    @Test
    public void TestWordsFromTrees()
    {
        long starttime = System.currentTimeMillis();
        System.out.println("-- TEST WORD HASHMAP --");
        Huffman tester = new Huffman("Test123t1");
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        Map<Character,Integer> sortedmap = new HashMap<Character, Integer>();
        map = tester.createFreqMap();
        sortedmap = tester.sortMap(map);

        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue = tester.createPriorityQueue(sortedmap);


        Node tree = new Node();
        tree = tester.createHuffmanTree(queue);


        Map<Character,String> codewords = new HashMap<>();
        tester.codewords(codewords,tree,"");

        System.out.println(codewords);

        long endtime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endtime-starttime) + "ms");

        //check if the map of words gets built properly
        assertNotNull(codewords);

    }

    @Test
    public void TestCompression()
    {
        long starttime = System.currentTimeMillis();
        System.out.println("-- TEST HUFFMAN COMPRESSION --");
        Huffman tester = new Huffman("Test123t1");
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        Map<Character,Integer> sortedmap = new HashMap<Character, Integer>();
        map = tester.createFreqMap();
        sortedmap = tester.sortMap(map);

        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue = tester.createPriorityQueue(sortedmap);


        Node tree = new Node();
        tree = tester.createHuffmanTree(queue);


        Map<Character,String> codewords = new HashMap<>();
        tester.codewords(codewords,tree,"");

        String compressedText = tester.HuffmanCompression(codewords);
        System.out.println("Compressed text: " + compressedText);

        long endtime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endtime-starttime) + "ms");

        //check that the text got compressed
        assertNotNull(compressedText);

    }

    @Test
    public void TestDecompression()
    {
        long starttime = System.currentTimeMillis();
        System.out.println("-- TEST HUFFMAN DECOMPRESSION --");
        Huffman tester = new Huffman("Test123t1");
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        Map<Character,Integer> sortedmap = new HashMap<Character, Integer>();
        map = tester.createFreqMap();
        sortedmap = tester.sortMap(map);

        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue = tester.createPriorityQueue(sortedmap);


        Node tree = new Node();
        tree = tester.createHuffmanTree(queue);


        Map<Character,String> codewords = new HashMap<>();
        tester.codewords(codewords,tree,"");

        String compressedText = tester.HuffmanCompression(codewords);
        System.out.println("Compressed text: " + compressedText);

        String decompressedText = tester.DecodeHuffman(compressedText,codewords);
        System.out.println("Decompressed text: " + decompressedText);


        long endtime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endtime-starttime) + "ms");

        //check that after all the steps we can get back to the original text
        assertEquals(decompressedText,tester.text);

    }


    @Test
    void PerformanceTestEncodeHuffman()
    {
        long starttime = System.currentTimeMillis();
        System.out.println("-- TEST HUFFMAN COMPRESSION --");
        Huffman tester = new Huffman(ReadFromFile());
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        Map<Character,Integer> sortedmap = new HashMap<Character, Integer>();
        map = tester.createFreqMap();
        sortedmap = tester.sortMap(map);

        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue = tester.createPriorityQueue(sortedmap);


        Node tree = new Node();
        tree = tester.createHuffmanTree(queue);


        Map<Character,String> codewords = new HashMap<>();
        tester.codewords(codewords,tree,"");

        String compressedText = tester.HuffmanCompression(codewords);
        System.out.println("Compressed text: " + compressedText);

        long endtime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endtime-starttime) + "ms");
    }

    @Test
    void PerformanceTestDecodeHuffman()
    {
        long starttime = System.currentTimeMillis();
        System.out.println("-- TEST HUFFMAN COMPRESSION --");
        Huffman tester = new Huffman(ReadFromFile());
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        Map<Character,Integer> sortedmap = new HashMap<Character, Integer>();
        map = tester.createFreqMap();
        sortedmap = tester.sortMap(map);

        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue = tester.createPriorityQueue(sortedmap);


        Node tree = new Node();
        tree = tester.createHuffmanTree(queue);


        Map<Character,String> codewords = new HashMap<>();
        tester.codewords(codewords,tree,"");

        String compressedText = tester.HuffmanCompression(codewords);
        System.out.println("Compressed text: " + compressedText);

        String decompressedText = tester.DecodeHuffman(compressedText,codewords);
        System.out.println("Decompressed text: " + decompressedText);

        long endtime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endtime-starttime) + "ms");
    }



}
