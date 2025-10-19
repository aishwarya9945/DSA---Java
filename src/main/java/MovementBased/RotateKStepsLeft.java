package MovementBased;

public class RotateKStepsLeft {

    public static void main(String[] args) {
        int[] arr = {12,6,8,9,0,3,5};
        
        int k = 2;
        
        int n= arr.length;
        k=k%n;
        
        reverse(arr,0,k-1);
        reverse(arr,k,n-1);
        reverse(arr,0,n-1);
        
        System.out.println(java.util.Arrays.toString(arr));
    }

    public static void reverse(int arr[], int start,int end) {
        while(start<end) {
            int temp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = temp;
        }
    }
    
}
