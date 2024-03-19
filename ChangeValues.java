import java.util.*;

public class ChangeValues {

    public static void main(String [] args) {

        System.out.printf("Hello Change Values Smallest Positive Number%n");

        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java ChangeValues <input>%n");
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
        ChangeValues solution = new ChangeValues(input);
        solution.findSmallestPositiveInteger();
    }

    private String input;

    private int test[][] = new int[9][];

    public ChangeValues(String input) {
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
        test[8] = new int [] {4, -10, 5, -100, 6, -1, 3, -1000, 9, 100}; // value 1 not found
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

        int ptr = 0;

        // Check if 1 is present in the array or not
        for (int i = 0; i < elements; i++) {
            if (a[i] == 1) {
                ptr = 1;
                break;
            }
        }

        // if 1 is not present
        if (ptr == 0) {
            result = 1;
            System.out.printf("The result is %d%n", result);
            return;
        }

        // Changing values to 1
        for (int i = 0; i < elements; i++) {
            if (a[i] <= 0) {
                a[i] = 1;
            }
        }

        System.out.printf("Array after changing values to 1 %s%n", Arrays.toString(a));

        // Updating indices according to values
        for (int i = 0; i < elements; i++) {
            a[(a[i] - 1) % elements] += elements; // ????
        }

        System.out.printf("Array after updating indices according to values %s%n", Arrays.toString(a));

        // Finding which index has a value less than elements value
        boolean match = false;
        for (int i = 0; i < elements; i++) {
            if (a[i] <= elements) {
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
