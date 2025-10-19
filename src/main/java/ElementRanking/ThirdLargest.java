package ElementRanking;

public class ThirdLargest {

    public static void main(String[] args) {
        int arr[] = { 1,2, 3, 5, 8, 9};

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third =  Integer.MIN_VALUE;

        for (int i=0;i<arr.length;i++) {
            if(arr[i]>first) {
                third = second;
                second = first;
                first = arr[i];
            } else if(arr[i]>second) {
                third = second;
                second = arr[i];
            } else if(arr[i]>third) {
                third = arr[i];
            }
        }
        System.out.println(third);
    }
}
