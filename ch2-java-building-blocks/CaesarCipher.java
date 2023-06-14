import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CaesarCipher {

    public static void main(String[] args) 
        throws IOException, FileNotFoundException {

        if (args.length != 1) {
            System.exit(1);
        }

        String path = args[0];

        String outputFile = "output.txt";

        try {

            List<String> resultList;
            try (Stream<String> lines = Files.lines(Paths.get(path))) {
                resultList = lines.collect(Collectors.toList());
            }
            StringBuilder sbOut = new StringBuilder();

            sbOut.append(executeCiphering(resultList));

            System.out.println("sbOut: " + sbOut.toString());
            PrintWriter outFile = new PrintWriter(outputFile);

            outFile.println(sbOut.toString());
            outFile.close();
        }
        catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static String executeCiphering(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        for(String line : lines) {
            char[] shifted = new char[line.length()];
            int shiftedLen = 0;
            int shiftAmount = 10;
            int maxAmount = 128;

            for (byte item : line.getBytes(StandardCharsets.UTF_8)) {
                shifted[shiftedLen++] = (char)((item + shiftAmount) % maxAmount);
            }
            sb.append(shifted);
        }

        return sb.toString();
    }

}