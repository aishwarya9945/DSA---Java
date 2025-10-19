package MovementBased;

public class ReverseEntireArray {

    public static void main(String[] args) {

        int arr[] = {12,3,4,5,6};

        int start = 0 , end = arr.length-1;

        while(start<end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }

        System.out.println(java.util.Arrays.toString(arr));
    }
}
