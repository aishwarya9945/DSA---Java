package MovementBased;

public class ReverseSubarrayIndiciesIAndJ {

    public static void main(String[] args) {

        int[] arr =  { 12,18,19,21,15,25};

        int i = 3, j = 5;
        reverse(arr,i,j);
        System.out.println(java.util.Arrays.toString(arr));
}
        public static void reverse(int[] arr, int start,int end) {
            while(start<end) {
                int temp = arr[start];
                arr[start++] = arr[end];
                arr[end--] = temp;
       }
}
}
