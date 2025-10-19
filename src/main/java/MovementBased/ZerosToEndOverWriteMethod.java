package MovementBased;

public class ZerosToEndOverWriteMethod {

    public static void main(String[] args) {

        int[] arr = {1,2, 0,3,9,0,4,0,5};

        int pos = 0;

        for(int i = 0;i<arr.length-1;i++){
            if(arr[i]!=0) {
                arr[pos++] = arr[i];
            }
        }

        while(pos < arr.length) {
            arr[pos++] = 0;
        }

        System.out.println(java.util.Arrays.toString(arr));
    }
}
