import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayReverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get array size and elements from the user
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Enter " + size + " integer elements:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        // Reverse the array
        int[] reversedArray = new int[size];
        for (int i = 0; i < size; i++) {
            reversedArray[i] = array[size - i - 1];
        }

        // Print the reversed array
        System.out.print("Reversed array: ");
        for (int num : reversedArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Convert array to ArrayList
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : reversedArray) {
            list.add(num);
        }

        // a) Add an element to the end of the list
        System.out.print("Enter a number to add to the end of the list: ");
        int addElement = scanner.nextInt();
        list.add(addElement);

        // b) Remove an element from the list
        System.out.print("Enter the index of the element to remove: ");
        try {
            int removeIndex = scanner.nextInt();
            list.remove(removeIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index! Unable to remove element.");
        }

        // c) Replace an element in the list
        System.out.print("Enter the index of the element to replace: ");
        int replaceIndex = scanner.nextInt();
        System.out.print("Enter the new value: ");
        int newValue = scanner.nextInt();

        try {
            list.set(replaceIndex, newValue);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index! Unable to replace element.");
        }

        // d) Sort the list in ascending order
        Collections.sort(list);
        System.out.println("Sorted list in ascending order: " + list);

        // e) Print the elements of the list
        System.out.println("Final list elements: ");
        for (int element : list) {
            System.out.print(element + " ");
        }
        System.out.println();

        // Create an ArithmeticException situation and catch it
        System.out.print("Enter a divisor to divide the first element of the list by: ");
        try {
            int divisor = scanner.nextInt();
            int result = list.get(0) / divisor;
            System.out.println("Result of division: " + result);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: Cannot divide by zero. Please enter a non-zero divisor.");
        } catch (InputMismatchException e) {
            System.out.println("InputMismatchException: Please enter a valid integer.");
        }

        scanner.close();
    }
}