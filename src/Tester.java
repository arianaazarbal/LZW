import java.io.IOException;
import java.lang.*;

public class Tester {
    public static void main(String[] args) throws IOException {

        LZW compressor = new LZW(9, "TestFile");

        String testString = compressor.encode("lzw-file1.txt");
        System.out.println (testString);
        
     System.out.print("time in nanoseconds = ");
     System.out.println(System.nanoTime());
     System.out.print("time in milliseconds = ");
     System.out.println(System.currentTimeMillis());

      LZWDecoder expander = new LZWDecoder (testString, 9, "TestFile2");
        
    }
}
