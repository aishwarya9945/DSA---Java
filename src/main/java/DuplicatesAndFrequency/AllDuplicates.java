package DuplicatesAndFrequency;

import java.util.HashSet;
import java.util.Set;

public class AllDuplicates {

    public static void main(String[] args) {

        int[] arr = { 12,3,4,5,5,4,16,3};

        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for(int num : arr) {
            if(!seen.add(num)){
                duplicates.add(num);
            }
        }
        System.out.println(duplicates);
    }
}
