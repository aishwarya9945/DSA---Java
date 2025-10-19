package MovementBased;

public class ZerosToFront {

    public static void main(String[] args) {

        int[] arr = {1, 0,2,7,0,8,0,9,0,5};

        int pos = arr.length-1;

        for(int i = arr.length-1;i>=0;i--) {
            if(arr[i]!=0) {
                arr[pos--] = arr[i];
            }
        }

        while(pos>=0) {
            arr[pos--] = 0;
        }
    System.out.println(java.util.Arrays.toString(arr));
}
}
