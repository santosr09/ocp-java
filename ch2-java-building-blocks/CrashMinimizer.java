import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

class CrashMinimizer {
    
    /**
     * This function minimizes a crashing test case to
     * a single character that still causes the crash.
     *
     * @param String command - the command to execute the
     *                         program under test.
     *
     * @param String failingTestInputFilename - the path 
     *                to the file causing a crash in the 
     *                target program. The contents of 
     *                this file are to be minimized by 
     *                this function.
     *
     * @return String - the final, minimized version of the 
     *                failing test input file which still 
     *                causes a crash.
    */
    public static String minimize(
        String command, String failingInputFilename
    ) throws FileNotFoundException, IOException {
        List<String> lines = Utilities.readFile(failingInputFilename);
        List<String> listResult = Utilities.ddmin(lines, harness);

        byte[] bytesAr = listResult.get(0).getBytes(StandardCharsets.UTF_8);
        int cont = 0;
        for(byte item : bytesAr){
            cont++;
            if(item < 0) {
                return String.valueOf(listResult.get(0).charAt(cont -1));
            }
        }
        return "";
    }

    public abstract static class TestHarness<E> {

        public static final int PASS = 1;
        public static final int UNRESOLVED = 0;
        public static final int FAIL = -1;

        /**
         * Returns true if the test passes and false if the test fails
         * @param <E>
         * @param complement
         * @return
         */
        public abstract int run(List<E> input);

    }

    private static TestHarness<String> harness = new TestHarness<String>(){
        @Override
        public int run(List<String> input) {


                for(String item : input) {
                    byte[] bytesAr = item.getBytes(StandardCharsets.UTF_8);
                    int cont = 0;
                    for(byte itemByte : bytesAr){
                        cont++;
                        if(itemByte < 0) {
                            return FAIL;
                        }
                    }

                }
                return PASS;
        }
    };

}