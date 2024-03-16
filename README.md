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

