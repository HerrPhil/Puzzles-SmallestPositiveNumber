import java.util.*;

public class TraversePairs {

    public static void main(String [] args) {

        System.out.printf("Hello Traverse Pairs Smallest Positive Number%n");

        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java TraversePairs <input>%n");
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
        TraversePairs solution = new TraversePairs(input);
        solution.findSmallestPositiveInteger();
    }

    private String input;

    private int test[][] = new int[9][];

    public TraversePairs(String input) {
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

        int a[] = test[Integer.valueOf(input)-1];

        System.out.printf("Array to test %s%n", Arrays.toString(a));

        // If 1 is never found then the result is 1.
        // otherwise, check for the smallest and next smallest
        // to find the smallest 
        boolean foundOne = false;
        boolean floorCase = false;

        int currentSmallest = Integer.MAX_VALUE;
        int currentNextSmallest = Integer.MAX_VALUE;
        int currentLargest = 0;

        int elements = a.length;

        // edge case - 0 elements
        if (elements == 0) {
            System.out.printf("The result is 1%n");
            return;
        }

        // edge case - 1 element
        if (elements == 1) {
            if (a[0] == 1) {
                System.out.printf("The result is 2%n");
            } else {
                System.out.printf("The result is 1%n");
            }
            return;
        }

        // otherwise traverse the list
        for (int i = 0; i < elements - 1; i++) {

            System.out.printf("the ith index is %d%n", i);

            int pairOne = a[i];

            System.out.printf("pair one is %d%n", pairOne);

            int pairTwo = a[i+1];

            System.out.printf("pair two is %d%n", pairTwo);

            if (pairOne < 1) {
                System.out.printf("default pair one to max value%n");
                pairOne = Integer.MAX_VALUE;
            }

            if (pairTwo < 1) {
                System.out.printf("default pair two to max value%n");
                pairTwo = Integer.MAX_VALUE;
            }


            if (!foundOne) {
                foundOne = pairOne == 1 || pairTwo == 1;
                System.out.printf("found one is %b%n", foundOne);
            }

            int smallest = Math.min(pairOne, pairTwo);
            int nextSmallest = Math.max(pairOne, pairTwo);

            System.out.printf("smallest is %d%n", smallest);
            System.out.printf("next smallest is %d%n", nextSmallest);

            currentLargest = Math.max(currentLargest, nextSmallest);

            if (i == 0) { // seed current values
                System.out.printf("seed values%n");
                currentSmallest = smallest;
                currentNextSmallest = nextSmallest;
                continue;
            }

            if (floorCase && smallest >= 1 && smallest <= 3 ) {
                continue;
            }

            if (nextSmallest < currentSmallest) {
                System.out.printf("next pair smaller than current pair%n");
                // super edge case
                if (smallest == 1 && nextSmallest == 2 && currentSmallest == 3) {
                    // there is no gap for small positive integer between 1 and 2 and 3
                    System.out.printf("no gap for small positive integer between 1 and 2 and 3, continue%n");
                    floorCase = true;
                    continue;
                }
                currentSmallest = smallest;
                currentNextSmallest = nextSmallest;
            }

            if (smallest < currentSmallest) {
                System.out.printf("next pair straddles current smallest value%n");
                if (currentSmallest - smallest > 1) {
                    System.out.printf("gap for small positive integer between smallest and current smallest%n");
                    currentNextSmallest = currentSmallest;
                    currentSmallest = smallest;
                }
                if (currentSmallest - smallest == 1) {
                    System.out.printf("no gap for small positive integer between smallest and current current smallest%n");
                    if ( currentNextSmallest > nextSmallest) {
                        System.out.printf("next smallest value smaller than current next smallest value%n");
                        currentNextSmallest = nextSmallest;
                    }
                }
            }

            if (currentSmallest < smallest) {
                System.out.printf("next pair straddles current next smallest value%n");
                if (smallest - currentSmallest > 1) {
                    System.out.printf("gap for small positive integer between smallest and current current smallest%n");
                    currentNextSmallest = smallest;
                }
                if (smallest - currentSmallest == 1) {
                    System.out.printf("no gap for small positive integer between smallest and current current smallest%n");
                    currentSmallest = smallest;
                    if (currentNextSmallest > nextSmallest) {
                        currentNextSmallest = nextSmallest;
                    }
                }
            }

            // if currentNextSmallest < smallest then do nothing

            System.out.printf("current smallest is %d%n", currentSmallest);
            System.out.printf("current next smallest is %d%n", currentNextSmallest);
        }

        int result = 0;
        if (!foundOne) {
            System.out.printf("the value 1 is not found - one is the smallest positive integer not in the array%n");
            result = 1;
        } else {
            boolean foundGap = currentNextSmallest - currentSmallest > 1;
            if (foundGap) {
                System.out.printf("found gap%n");
                result = currentSmallest + 1;                
            } else {
                System.out.printf("found no gaps%n");
                result = currentLargest + 1;
            }
        }

        System.out.printf("The result is %d%n", result);
    }

}
