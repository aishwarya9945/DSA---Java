package ConditionalMovement;

public class MaxMinAlternatively {

    public static void main(String[] args) {

        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};

        int start = 0, end = arr.length-1;

        int[] result = new int[arr.length];
        boolean pickMax = true;

        for (int i = 0; i < arr.length;i++) {
            if (pickMax) {
                result[i] = arr[end--];
            } else {
                result[i] = arr[start++];
            }

            pickMax = !pickMax;
        }
        System.arraycopy(result, 0, arr, 0, arr.length);
        System.out.println(java.util.Arrays.toString(arr));
}
}