package MovementBased;

public class ZerosToEndSwapBasedMethod {

    public static void main(String[] args) {

        int[] arr ={12,3,0,0,9,8,7,0,6};

        int i = 0;

        for(int j=0;j<arr.length;j++) {
            if(arr[j]!=0) {
                if(arr[i] !=arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
                i++;
            }
        }
        System.out.println(java.util.Arrays.toString(arr));
    }
}
