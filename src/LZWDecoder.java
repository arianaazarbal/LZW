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

	/*
	public void decode ()
	{
		for (int x = 0; x<256; x++) // creating the dictionary 
		{
			char ch = (char)x;
			dict.put(x, String.valueOf(ch));
		}
		ArrayList <Integer> nums= new ArrayList<Integer> (); // creates an arraylist to put all the numbers when converting from binary to integers 
		String binStringCopy = binString; // makes copy of string 
		String currBinString = binString.substring (0,bitNum); // takes the substring of the first probably 9 bits
		binString= binString.substring(bitNum); // assings binString to the substring 
		int currInt = Integer.parseInt(currBinString, 2); // changes the binary code to an integer 
		nums.add(currInt); 
//		finalOutput = currString;
		
		String nextBinString = ""; // intializes the next string fr binary numbers 
		int nextInt= 0; 
		String nextString= ""; // initalizes next string for actual letters 
		
//		String lastSymbolInDict = "";
		
		nextBinString = binString.substring(0, bitNum); // takes the substring of the binString to get the next character after taking the substring of current 
		binString= binString.substring(bitNum); // chops string off 
		nextInt = Integer.parseInt(nextBinString, 2); // converts binary of nextBinString into integer 
		nums.add(nextInt); 

		int counter = 256;

		while (binString.length()>= bitNum) // as long as the word has enough bits to convert into an intger 
		{

			if (nextString!= null)
			{
				String currBinString = binString.substring (0,bitNum); // takes the substring of the first probably 9 bits
				binString= binString.substring(bitNum); // assings binString to the substring 
				int currInt = Integer.parseInt(currBinString, 2); // changes the binary code to an integer 
				nums.add(currInt); 
				dict.put(counter, currString+ nextString.substring(0,1));
				currString = nextString;



			}
			else // this is the edge case 
			{
				dict.put(counter, currString + currString.substring(0,1));
				currString = currString + currString.substring(0,1);
			}
			
			nextBinString = binString.substring(0, bitNum);
			binString= binString.substring(bitNum);
			nextDecimal = Integer.parseInt(nextBinString, 2);
			nextString = dict.get(nextInt);
			
			counter++;
		}
		
		for (int x = 0; x<binStringCopy.length()/bitNum;x++)
		{
			String currBinStringCopy = binStringCopy.substring(0,bitNum);
			binStringCopy= binStringCopy.substring(bitNum);
			finalOutput+=dict.get(Integer.parseInt(currBinStringCopy, 2));
		}
	}
	*/ 
	//writes the 
	public void writeToTxt(String outputFileName) throws FileNotFoundException
	{
		PrintWriter output = new PrintWriter(outputFileName);
		output.print (finalOutput);
		output.close();
	}

		public static void main (String [] args) throws IOException{
		LZWDecoder decoder = new LZWDecoder ("011000010110001001100011011000010110001001100011011000010110001001100011011000010110001001100011011000010110001001100011011000010110001001100011", 9, "output"); 
		System.out.println(decoder.getList()); 
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