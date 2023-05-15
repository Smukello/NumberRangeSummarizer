package com.numberRangeSummarizer;

import java.util.*;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {
    public static void main(String[] args) {
        NumberRangeSummarizerImpl in = new NumberRangeSummarizerImpl();
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        try {
            // Collect the input string
            Collection<Integer> collectionInput = (List<Integer>)in.collect(input);
            // Summarize the input
            String range = in.summarizeCollection(collectionInput);
            // Print the summarized input
            System.out.println(range);
        } catch (ClassCastException exc) {
            System.out.println("Can't resolve the string");
        }
    } 

    // Collect the input string and convert it to a collection of integers
    public Collection<Integer> collect(String input) {
        String[] strArr = input.split(","); // Separate the string where there is a comma
        int[] integers = new int[strArr.length]; // Initialise an array of int to store the converted string

        // Convert array of strings to array of int
        for (int i = 0; i < strArr.length; i++) {
            integers[i] = Integer.parseInt(strArr[i]);
        }

        // Sort the elements in our array
        Arrays.sort(integers);

        // Use a LinkedHashSet to ensure the collection has unique values and is ordered based on insertion
        Collection<Integer> collection = new LinkedHashSet<>();
        for (int i : integers) {
            collection.add(i);
        }
        return collection;
    }

    // Summarize the collection of integers and returns a string with the ranges
    public String summarizeCollection(Collection<Integer> input) {
        ArrayList<Integer> inputList = new ArrayList<Integer>(input);
        List<String> output = new ArrayList<>();

        // Add a sentinel value to the end of the list to handle the last range
        inputList.add(0);
        int count = 0;

        // Loop through the sorted list comparing adjacent values to determine the ranges
        for (int i = 0; i < inputList.size() - 1; i++) {
            int m = inputList.get(i);
            count++;
            if (m + 1 != inputList.get(i + 1)) {
                if (count == 1) {
                    // Add individual integers to the output list
                    output.add(String.valueOf(m));
                } else {
                    // Add integer range to the output list
                    output.add((m - count + 1) + "-" + m);
                }
                count = 0;
            }
        }

        // Join the output list as a comma-separated string
        return String.join(", ", output);
    }
}
