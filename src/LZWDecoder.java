import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.*;

public class LZWDecoder {
	
	private HashMap <Integer, String> dict;
	private int bitNum;
	private String binString;
	private byte [] byteArray;
	private String finalOutput;
	
	public LZWDecoder(String binString, int bitNum, String outputFileName) throws IOException // this is the constructor 
	{
		this.dict = new HashMap <Integer, String>(); // intializes the hash map 
		this.bitNum = bitNum;
		this.binString = binString;
		this.finalOutput = "";
		
		
//		File binFile = new File (binFileName);
//		readBinFile(binFile);
		//decode();
		writeToTxt(outputFileName);
		
	}
	
	//reads the binary file into a string. not working, so I've bypassed this method for now and just input a binary string into the constructor.
	/*
	public void readBinFile(File binFile) throws IOException
	{
		FileInputStream is = new FileInputStream(binFile);
		
		int currInt = is.read();
		while (currInt != -1)
		{
			binString+=currInt;
		}
		is.close();
	}
	
*/ 
	public ArrayList<Integer> getList () throws IOException{
		String binary = binString; // copies the binary string 
		String temp=""; // creates temp varibale 
		ArrayList <Integer> nums= new ArrayList<Integer> (); // creates an arraylist to put all the numbers when converting from binary to integers 
		while (binary.length()>=bitNum){
			int number=0; 
			temp=binary.substring(0,bitNum); // takes substring and sets to temp
			number=Integer.parseInt(temp,2); // turns binary to integer number 
			nums.add(number); // adds the number into an arrayList of nums 
			binary=binary.substring(bitNum);// chops the string off 
		}

		return (nums); 
	}

	public String decode () throws IOException {
		ArrayList<Integer> numbers = getList(); // calls other method to get arraylist of nums
		int counter=256; // keeps track of how big the hash map is 
        HashMap <Integer, String> map = new HashMap <Integer,String> (); // intialized and delcres hash map 
        int current=0; 
        int next=0; 
        String word="";// this will eventually be the whole word that will get returned 
        String combined ="";// string that contains current and next as one string 
        String wordC=""; // the string version of the number of the current value
        String wordN=""; // the string version of the number of the next value
        for (int i=0; i<256; i++){
            map.put(i, ""+(char)i); // created dictionary assigning ascii values to the first 255 characters
        }
        int size=numbers.size(); 
        for (int i=0; i<size; i++){
            if (i<numbers.size()-1){ // makess sure that you wont get out of bounds 
                current=numbers.get(i); // gets first thing in arraylist
                next=numbers.get(i+1); // gets second thing in arraylist
                if (next<counter){ // checks to see that next is already in the dictionary
                    wordC=map.get(current); // converts the numbers into a string 
                    wordN=map.get(next); // same as above but for next 
                    combined=wordC+wordN.substring(0,1); // combines current and next's first letter
                    map.put(counter,combined); // puts the new combined letters into the dictionary 
                    counter++;// increments counter to fit the new size of map
                } 
                else{
                    wordN=map.get(current); // sets WordN to the current word 
                    String letter=wordN.substring(0,1); // gets the first letter of the next word 
                    wordN=wordN+letter; // sets wordN to wordN to the first letter of WordN
                    map.put(counter,wordN); // adds to Hashmap
                    counter++; // increments counter
                }
               ; 
            }
        }
        for (int i=0; i<numbers.size(); i++){
            int index=numbers.get(i); 
           word+=map.get(index); 
        }
        return (word); 
	}
	//writes the 
	public void writeToTxt(String outputFileName) throws FileNotFoundException
	{
		PrintWriter output = new PrintWriter(outputFileName);
		output.print (finalOutput);
		output.close();
	}

		public static void main (String [] args) throws IOException{
		  System.out.print("time in nanoseconds = ");
    	 System.out.println(System.nanoTime());
    	 System.out.print("time in milliseconds = ");
     	System.out.println(System.currentTimeMillis());
		LZWDecoder decoder = new LZWDecoder ("011000010110001001100011011000010110001001100011011000010110001001100011011000010110001001100011011000010110001001100011011000010110001001100011", 9, "output"); 
		System.out.println(decoder.getList()); 
		System.out.println(decoder.decode()); 
	}
	
//	class GFG {
//		 
//	    // Function to convert binary to decimal
//	    private static int binaryToDecimal (String n)
//	    {
//	        String num = n;
//	        int dec_value = 0;
//	 
//	        // Initializing base value to 1,
//	        // i.e 2^0
//	        int base = 1;
//	 
//	        int len = num.length();
//	        for (int i = len - 1; i >= 0; i--) {
//	            if (num.charAt(i) == '1')
//	                dec_value += base;
//	            base = base * 2;
//	        }
//	 
//	        return dec_value;
//	    }

}