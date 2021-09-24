import java.io.IOException;
import java.lang.*;

public class Tester {
    public static void main(String[] args) throws IOException {
    System.out.print("time in nanoseconds = ");
     System.out.println(System.nanoTime());
     System.out.print("time in milliseconds = ");
     System.out.println(System.currentTimeMillis());
     LZW compressor = new LZW(9, "TestFile");
     String testString = compressor.encode("lzw-file1.txt");
     System.out.println (testString);
     LZWDecoder expander = new LZWDecoder (testString, 9, "TestFile2");
        
    }
}
