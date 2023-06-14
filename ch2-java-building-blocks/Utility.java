import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class Utility {

    static int nPrev = 0;
    static int nLast = 0;
    static int nCurrent = 0;
    static int counter = 0;

    private static void resetValues() {
        nPrev = 0;
        nLast = 0;
        nCurrent = 0;
        counter = 0;
    }

    static IntSupplier intSupplier = () -> {
        if( counter < 2){
            nPrev = nLast = nCurrent = 1;
            counter++;
            return nCurrent;
        }
        nPrev = nLast;
        nLast = nCurrent;
        nCurrent = nPrev + nLast;
        counter++;
        if(nCurrent == 10946) System.out.println("10946, counter: " + counter);
        if(nCurrent == 165580141) System.out.println("165580141 counter: " + counter);
        return Integer.valueOf(nCurrent);
    };

    public static IntStream generateFibonacciSequence() {
        return IntStream.generate(intSupplier).sequential();
    }
}
