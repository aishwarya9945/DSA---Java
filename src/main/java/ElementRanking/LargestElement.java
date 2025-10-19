package ElementRanking;

public class LargestElement {

    public static void main(String[] args) {
        int[] arr = { 12, 3, 4, 5, 6, 15};

        int largest = arr[0];

        for(int i=1;i<arr.length;i++) {

            if(arr[i]>largest){
                largest = arr[i];
            }
        }
        System.out.println(largest);
    }
}
