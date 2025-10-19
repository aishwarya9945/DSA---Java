package DuplicatesAndFrequency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicatesUnSorted {

    public static void main(String[] args) {

        int arr[] = {12,3,1,12,3,5};

        Set<Integer> seen = new HashSet<>();
        List<Integer> results = new ArrayList<>();

        for(int num : arr) {
            if(seen.add(num)) {
                results.add(num);
            }
        }
        System.out.print(results);
    }
}
