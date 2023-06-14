public class Example {

  public static void main (String args[]) {
    Example example = new Example();

    example.testThatTheFirstTwentyElementsAreCorrect();
    example.testThatTheNextTenElementsAreCorrect();
    example.testRecursivePropertyInStreamByRandomLeaps();
    Utility.generateFibonacciSequence().limit(30).forEach(e -> System.out.print(e+" , "));
  }

  public void testThatTheFirstTwentyElementsAreCorrect() {
    Utility.generateFibonacciSequence().limit(20).toArray();
  }

  public void testRecursivePropertyInStreamByRandomLeaps() {
    for (int i = 0; i < 3; i++) { // Repeat three times
      System.out.println("iteraiton number: " + i);
      Utility.generateFibonacciSequence()
              .skip((int) (Math.random() * 900)) // Begin leap
              .limit(20) // End leap
              .iterator();
      }
    }

  public void testThatTheNextTenElementsAreCorrect() {
    Utility.generateFibonacciSequence().limit(10);
  }

  public void testingPrimitives() {
    double x = 39.21;
    float y = 2.1f;
    // float yf = 2.1; //Required: float, Provided: Double
    var z = x + y;
  }

}

