import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Utilities {
    
    /**
     * Reads the lines of a file into a List<String>
     *
     * @param String file - the path to the file to read
     * @return List<String> - the contents of the file as lines
    */
    public static List<String> readFile(String file) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        Scanner scan = new Scanner(new File(file));

        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }

        scan.close();
        return lines;
    }

    /**
     * Writes a List<String> to file
     *
     * @param file - the name of the file to write to
     * @param List<String> - the data to write
    */
    public static void writeToFile(String file, List<String> list) throws IOException {
        Files.write(Paths.get(file), list, Charset.defaultCharset());
    }
    
    /**
     * Executes a command and captures its stderr
     *
     * @param command - the command to execute, including arguments
     * @return String - the stderr string emitted by the program during execution
    */
    public static String runCommand(String command) throws IOException {
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader br = new BufferedReader(
            new InputStreamReader(p.getErrorStream())
        );
        StringBuilder sb = new StringBuilder(); 
        String s = null;
        
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }

        return sb.toString();
    }

    public static <E> List<E> ddmin(List<E> input, CrashMinimizer.TestHarness<E> harness) {

        int n = 2;
        while(input.size() >= 2) {
            List<List<E>> subsets = split(input, n);
            boolean complementFailing = false;

            for(List<E> subset : subsets){
                List<E> complement = difference(input, subset);
                if (harness.run(complement) == TestHarness.FAIL) {
                    input = complement;
                    n = Math.max(n - 1, 2);
                    complementFailing = true;
                    break;
                }
            }

            if (!complementFailing) {
                if (n == input.size()) {
                    break;
                }
                // increase set granularity
                n = Math.min(n * 2, input.size());
            }
        }
        return input;
    }

    private static <E> List<E> difference(List<E> a, List<E> b) {
        List<E> result = new LinkedList<E>();
        result.addAll(a);
        result.removeAll(b);
        return result;
    }

    private static <E> List<List<E>> split(List<E> s, int n) {
        List<List<E>> subsets = new LinkedList<List<E>>();
        int position = 0;
        for (int i = 0; i < n; i++) {
            List<E> subset = s.subList(position, position + (s.size() - position) / (n - i));
            subsets.add(subset);
            position += subset.size();
        }
        return subsets;
    }


}