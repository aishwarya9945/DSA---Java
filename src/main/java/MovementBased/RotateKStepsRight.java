package MovementBased;

public class RotateKStepsRight {

    public static void main(String[] args) {

        int arr[] = {12, 3, 4, 5, 6, 2};

        int n = arr.length;
        int k = 3;
        k = k % n; // handle k>n

            reverse(arr, 0, n - 1);
            reverse(arr, 0, k - 1);
            reverse(arr, k, n - 1);

            System.out.println(java.util.Arrays.toString(arr));
    }

public static void reverse(int arr[],int start,int end) {
    while (start < end) {
        int temp = start;
        arr[start++] = arr[end];
        arr[end--] = temp;
    }
}

}

