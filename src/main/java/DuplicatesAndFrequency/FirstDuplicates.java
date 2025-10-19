package DuplicatesAndFrequency;

import java.util.HashSet;
import java.util.Set;

public class FirstDuplicates {

    public static void main(String[] args) {

        int arr[] = {12,2,8,9,2,10,8};

        Set<Integer> seen = new HashSet<>();
        int firstDuplicate = -1;

        for(int num : arr) {
            if(!seen.add(num)){
                firstDuplicate = num;
                break;
            }
        }
        System.out.print(firstDuplicate);
    }
}
