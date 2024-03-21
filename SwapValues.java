import java.util.*;

public class SwapValues {

    public static void main(String [] args) {

        System.out.printf("Hello Swap Values Smallest Positive Number%n");

        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java SwapValues <input>%n");
            System.out.printf("where <input> is a number associated with a test array (1-9)%n");
            return;
        }
        if (args == null || args.length == 0) { // check for args
            System.out.printf("The number of the test array is necessary%n");
            return;
        }
        String input = args[0];
        boolean hasValidNumber = input.matches("^[1-9]$");
        if (!hasValidNumber) {
            System.out.printf("Number must be between 1 and 9%n");
            return;
        }
        SwapValues solution = new SwapValues(input);
        solution.findSmallestPositiveInteger();
    }

    private String input;

    private int test[][] = new int[9][];

    public SwapValues(String input) {
        this.input = input;
        initialize();
    }

    private void initialize() {
        test[0] = new int [] {};
        test[1] = new int [] {2};
        test[2] = new int [] {1,2,3,4};
        test[3] = new int [] {0, 5, 2, 4, 1, 1000000}; // more than one gap and no super edge case
        test[4] = new int [] {-1000000, -100000, -10000, -1000, -100, -10}; // value 1 not found and all negative values
        test[5] = new int [] {-1000, 6, -10, 5, -1, 3, 0, 2, 1, 10000}; // one gap and super edge case
        test[6] = new int [] {-1000, 6, -10, 5, -1, 3, 0, 2, 1, 10000, 9, -100000}; // more than one gap and super edge case
        test[7] = new int [] {1, 4, -10, 5, -100, 6, -1, 3, -1000, 9, 100}; // more than one gap and no super edge case
        test[8] = new int [] {4, 2, 1, 5};
    }

    public void findSmallestPositiveInteger() {

        System.out.printf("Here is the smallest positive integer%n");

        int result = 0;

        int a[] = test[Integer.valueOf(input)-1];

        System.out.printf("Array to test %s%n", Arrays.toString(a));

        int elements = a.length;

        // edge case - 0 elements
        if (elements == 0) {
            result = 1;
            System.out.printf("The result is %d%n", result);
            return;
        }

        // Check if 1 is present in the array or not
        for (int i = 0; i < elements; i++) {
            // Loop to check boundary condition and for swapping
            while(a[i] >= 1 && a[i] <= elements && a[i] != a[a[i] - 1]) {

                System.out.printf("before swap i: %d, a[i]: %d, a[i] - 1: %d, a[a[i] - 1]: %d%n", i, a[i], a[i] - 1, a[a[i] - 1]);

                // complete a swap
                int temp = a[a[i] - 1];
                a[a[i] - 1] = a[i];
                a[i] = temp;

                System.out.printf("Array after swap %s%n", Arrays.toString(a));
            }
        }

        // Finding which index has a value less than elements value
        boolean match = false;
        for (int i = 0; i < elements; i++) {
            if (a[i] != i + 1) {
                result = i + 1;
                match = true;
                break;
            }
        }

        if (!match) {
            result = elements + 1;
        }

        System.out.printf("The result is %d%n", result);
    }

}
