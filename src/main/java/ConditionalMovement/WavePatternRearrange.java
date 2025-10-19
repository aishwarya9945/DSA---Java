package ConditionalMovement;

public class WavePatternRearrange {

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7,8,10,9};

        for(int i = 0;i<arr.length - 1;i+=2) {

            if(arr[i]<arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] =temp;
            }
        }
        System.out.println(java.util.Arrays.toString(arr));
    }
}

