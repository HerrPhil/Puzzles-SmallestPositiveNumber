# Puzzles-SmallestPositiveNumber

Find the smallest positive number not in an array of positive or negative integers.

You are given an array A of N integers.

Smallest result is 1.

Write an efficient algorithm for the following assumptions:

- N is an integer with a range [1..100,000]
- each element of array A is an integer within the range [-1,000,000..1,000,000]
- A is unordered

## Naive Solution

The naive solution is to look at the array A for every value of a variable i, where i starts at 1 and counts up.

When the i<sup>th</sup> value is found, then try the next one.

When the i<sup>th</sup> value is not found, then this is the smallest positive integer not in array A.

This is O(n<sup>2</sup>) efficient.

## Traverse by Pairs Solution

The traverse solution iterates over the array A once.

The solution ignores zero and negatives; treats them as Integer.MAX_VALUE = 2,147,483,647

The solution looks at a pair of values every time.

This is O(n) efficient.

The goal is to find either:

- 1 is not in the list, or
- there is a gap for the smallest positive integer not in the list, or
- there is no gap for the smallest integer; the smallest positive integer not in the list is one more than the largest value

Here is how the algorithm goes.

There are two special cases tracked with booleans.

The algorithm tracks whether the value 1 is found in the array.

The algorithm tracks whether a floor case exists. This is when the array contains 1, 2, 3 and 1 and 2 are a pair deep in the array.

The algorithm starts with a pair of tracked values, current smallest and current next smallest. These are initialized to MAX_VALUE.

The algorithm tracks the current largest value in the array A. This is initialized to 0;

The first edge case the algorithm checks is whether the array A is empty; the result is 1.

The second edge case the algorithm checks is whether the array A has one element whose value is 1; the result is 2.

The third edge case the algorithm checks is whether the array A has one element whose value is not 1; the result is 1.

Otherwise, the algorithm tranverses the array.

The algorithm looks at pairs a[i] and a[i+1].

If either peeked pair value is less than 1, then it is treated as MAX_VALUE.

If the foundOne flag is not set, then check whether the pair values have a 1 value.

Sort the pair value. The array is unordered. The pair must be ordered to aid finding the smallest positive integer.

The algorithm now knows the smallest and next smallest value of this iteration.

Track the current largest value by storing the maximum value of current largest or next smallest.

If ith index is equal to 0, then seed the current smallest and current next smallest values with the smallest and next smallest values, respectively.

Continue to the next pair.

If the algorithm has seen the floor case and the smallest value is between 1 and 3, inclusive, then there is no analysis.

Continue to the next pair.

If the next smallest value is less than the current smallest value, then this pair is less than all checked values, so far.
Check for the floor case. When the smallest value is 1, the next smallest value is 2, and the current smallest value is 3,
then there is no gap for the smallest positive integer here. Mark the floor case as found and continue to the next pair.
Otherwise, store the current smallest and current next smallest values.

Continue to the next pair.

If the smallest value is less than the current smallest value, then this pair straddles the current smallest value.

If the difference between the smallest value and current smallest value is greater than 1,
then there is a gap for the smallest positive integer. Tighten up the current values.
The current next smallest value is assigned the current smallest value.
The current smallest value is assigned the smallest value.

If the difference between the smallest value and current smallest value is equal to 1, 
then there is no gap for the smallest positive integer.
If the current next smallest value is greater than the next smallest value, tighten up the bigger value.
The current next smallest vlau is assigned the next smallest value.

Continue to the next pair.

If the current smallest value is less than the smallest value, then this pair straddles the current next smallest value.

If the difference between the current smallest value and smallest value is greater than 1,
then there is a gap for the smallest positive integer. Tighten up the current values.
The current next smallest value is assigned the smallest value.

If the difference betwen the current smallest value and the smallest value is equal to 1,
then there is no gap for the smallest positive integer.
The current smallest value is assigned the smallest value.
If the current next smallest value is greater than the next smallest value,
then the current next smallest value is assigned the next smallest value.

Continue to the next pair.

If the current next smallest value is less than the smallest value, then do nothing.

Continue to the next pair.

After the looping finshes, check the current values.

If the value 1 is not found, then the smallest positive integer is 1.

Otherwise, check for a gap between the current smallest pair.

If the difference between the the current smallest value and current next smallest value is greater than 1,
then the smallest positive integer is current smallest value plus 1.
Otherwise the smallest positive integer is current largest value plus 1.

## Change Values Solution

Found [here](https://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/).

The idea is to mark the elements in the array which are greater than N and less than 1 with 1.

Follow the steps below to solve the problem:

- The smallest positive integer is 1. First, we will check if 1 is present in the array or not. If it is not present then 1 is the answer.
- If present then, again traverse the array. The largest possible answer is N+1 where N is the size of the array. 
  - When traversing the array, if we find any number less than 1 or greater than N, change it to 1. 
  - This will not change anything as the answer will always be between 1 to N+1. Now our array has elements from 1 to N.
- Now, for every ith number, increase arr[ (arr[i]-1) ] by N. But this will increase the value more than N. So, we will access the array by arr[(arr[i]-1)%N].
- We will find now which index has a value less than N+1. Then i+1 will be our answer. 

At first, the assignment of any element of the array to 1 when it is greater than N did not make sense to me.

Then I realized that very big values an N close to or equal to the very big value to make the smallest positive number near that value.

The edge case is an array of length full of contiguous value, and one value that is huge.

The gap between the large value and N-1 means the smallest positive integer is N.

The smallest positive integer is N+1 when the array is all contiguous values less than or equal to N.

Further to that, I struggle why add an arbitrary value, like N, to each element based on an existing value in an element at index i would work.

Then I realized that all the values are now indices in the array.

The first indice not in the array will be the smallest positive

## Swap Values Solution

Found [here](https://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/).

The idea is to swap the elements which are in the range 1 to N should be placed at their respective indexes.

Follow the steps below to solve the problem:

- Traverse the array, Ignore the elements which are greater than N and less than 1.
- While traversing, check if a[i] not equal to a[a[i]-1] holds true or not .
  - If the above condition is true then swap a[i] and a[a[i] – 1]  and swap until (a[i] not equal to a[a[i] – 1]) condition fails.
- Traverse the array and check whether a[i] not equal i + 1; when this is true then return i + 1.
- If all are equal to its index then return N+1.

The stop condition was one detail that was not obvious to me as to why it works.

After a few swaps, the array with be in a state where there is an element whose value is one more than its index.

For example when i=1 and a[i] = 2.

a[i] is a[1] = 2

a[a[i] - 1] is a[2 - 1] is a[1] = 2

In this state, when a[i] is not equal to i + 1, then the missing smallest positive integer is i + 1.

If all n values are checked and the condition is not met, then n + 1 is the final answer.

The swapping process will have left the array like, for example:

```
i:.....0 1 2 3

value:.1 2 5 7
```
