package DuplicatesAndFrequency;

import java.util.Arrays;

public class RemoveDuplicatesSorted {

    public static void main(String[] args) {
        int arr[] = {3,3,4,8,5,5};

    int i = 0;

    for(int j=1;j<arr.length;j++) {
        if(arr[j]!=arr[i]){
            i++;
            arr[i]=arr[j];

        }
    }

    System.out.println(Arrays.toString(Arrays.copyOf(arr,i+1)));
}
}
