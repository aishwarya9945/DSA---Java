package ElementRanking;

public class SecondLargest {

    public static void main(String[] args) {
        int[] arr = {12, 3, 6, 17, 19,5};

        int largest = arr[0];
        int secondlargest = Integer.MIN_VALUE;

        for(int i=1;i<arr.length;i++) {

            if(arr[i]>largest) {
                secondlargest=largest;
                largest = arr[i];
            } else if (arr[i] > secondlargest && arr[i]!= largest){
                secondlargest = arr[i];
            }
        }
       if(secondlargest == Integer.MIN_VALUE){
           System.out.println("Second largest not found");
       } else {
           System.out.println("Second largest found: " +secondlargest);
       }

    }
}
