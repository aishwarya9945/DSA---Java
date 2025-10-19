package DuplicatesAndFrequency;

import java.util.HashSet;
import java.util.Set;
public class DuplicateExists {

    public static void main(String[] args) {

        int arr[] = {12,12, 3, 5, 6, 8, 9};

        Set<Integer> seen = new HashSet<>();

        boolean hasDuplicate = false;

        for(int num : arr){
            if(!seen.add(num)){
                hasDuplicate = true;
                break;

            }
        }

        System.out.println("Duplicate Exists : " +hasDuplicate);
    }
}
