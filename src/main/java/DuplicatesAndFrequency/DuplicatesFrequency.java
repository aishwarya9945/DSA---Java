package DuplicatesAndFrequency;

import java.util.HashMap;
import java.util.Map;

public class DuplicatesFrequency {

    public static void main(String[] args) {

        int arr[] = {12, 3, 3, 1, 1, 5, 5, 5};

        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
            for (int key : freq.keySet()) {
                System.out.println(key+ "->" +freq.get(key));


            }
        }
    }
