package task_2;
/*
V 13: Int Arrays
Objective: To understand and use arrays with the int type in Java.
Instructions:
Declare and initialize an array of int.
Use a for loop to print the elements of the array to the console.
Use a for loop to find the number of odd in the array.
*/

public class TASK2ByVlad {
    public static void main(String[] args) {

        int[] numbers = {2, 5, 8, 11, 14, 17, 20};

        System.out.println("Array elements:");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();

        int oddCount = 0;
        for (int number : numbers) {
            if (number % 2 != 0) {
                oddCount++;
            }
        }

        System.out.println("Number of odd numbers in the array: " + oddCount);
    }
}

