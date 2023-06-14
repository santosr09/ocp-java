
import java.io.*;
import java.nio.file.*;
/* Note: this file is available for your testing purposes 
   and will not be included with your final submission. */

public class CandidateTest {

    public static void main(String[] args) throws IOException {
        CandidateTest test = new CandidateTest();
        test.providedExample1();
        test.providedExample2();
    }

    public void providedExample1() throws IOException {
        String programNameUnderTest = "CaesarCipher";
        String failingInputFilename = "example_01.txt";
        String expected = "æ";
      
        String actual = CrashMinimizer.minimize(
            "java " + programNameUnderTest, failingInputFilename
        );
        System.out.println("expected = result: " + actual.equals(expected));
        System.out.println("actual: " + actual);
    }
  

    public void providedExample2() throws IOException {
        String programNameUnderTest = "CaesarCipher";
        String failingInputFilename = "example_02.txt";
        String expected = "↑";
      
        String actual = CrashMinimizer.minimize(
            "java " + programNameUnderTest, failingInputFilename
        );

        System.out.println("expected = result: " + actual.equals(expected));
        System.out.println("actual: " + actual);
    }
}