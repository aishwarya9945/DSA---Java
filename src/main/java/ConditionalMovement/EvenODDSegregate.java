package ConditionalMovement;

public class EvenODDSegregate {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};

        int left = 0 , right = arr.length - 1;

       while(left<right) {
            if (arr[left] %2 == 0) {
                left++;
            } else if (arr[right] %2 != 0) {
                    right--;
                }
            else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                left++;
                right--;
            }
       }
        System.out.println(java.util.Arrays.toString(arr));
    }
}

