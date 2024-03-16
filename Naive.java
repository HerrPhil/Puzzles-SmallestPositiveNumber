import java.util.*;

public class Naive {

    public static void main(String [] args) {

        System.out.printf("Hello Naive Smallest Positive Number%n");

        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java Naive <input>%n");
            System.out.printf("where <input> is a number associated with a test array (1-5)%n");
            return;
        }
        if (args == null || args.length == 0) { // check for args
            System.out.printf("The number of the test array is necessary%n");
            return;
        }
        String input = args[0];
        boolean hasValidNumber = input.matches("^[1-5]$");
        if (!hasValidNumber) {
            System.out.printf("Number must be between 1 and 5%n");
            return;
        }
        Naive solution = new Naive(input);
        solution.findSmallestPositiveInteger();
    }

    private String input;

    private int test[][] = new int[5][];

    public Naive(String input) {
        this.input = input;
        initialize();
    }

    private void initialize() {
        test[0] = new int [] {0, 1, 2, 4, 5, 1000000};
        test[1] = new int [] {2};
        test[2] = new int [] {1,2,3,4};
        test[3] = new int [] {-1000000, -100000, -10000, -1000, -100, -10};
        test[4] = new int [] {-1000, 6, -10, 5, -1, 3, 0, 2, 1, 10000};
    }

    public void findSmallestPositiveInteger() {

        System.out.printf("Here is the smallest positive integer%n");

        int a[] = test[Integer.valueOf(input)-1];

        System.out.printf("Array to test %s%n", Arrays.toString(a));

        boolean found = false;
        int result = 0;

        for (int i = 1;; i++) {
            found = false;
            for (int j = 0; j < a.length; j++) {
                int x = a[j];
                if (x == i) {
                    found = true;
                    break;
                }
            }
            result = i;
            if (!found) {
                // return i;
                break;
            }
        }

        System.out.printf("The result is %d%n", result);
    }

}
