package MovementBased;

public class LastBecomeFirst {

    public static void main(String[] args) {
        int[] arr = {1, 0, 6, 9, 3, 5};

        int n = arr.length;

        int last = arr[n - 1];

        for (int i = n-2; i >=0; i--) {
            arr[i + 1] = arr[i];
        }

        arr[0] = last;

        System.out.println(java.util.Arrays.toString(arr));
}
    }
