package task_3;
/*
Array Rotation: Write a Java program that takes an array of integers and rotates it by a given number of positions.
Your program should prompt the user to enter the array size and the elements of the array, and then the number 
of positions to rotate the array. Finally, your program should output the rotated array.
Make LinkedList from the result array and perform the following operations: 
a) Add an element to the beginning of the list; b) Add an element to the end of the list;
c) Remove the first element from the list; d) Remove the last element from the list;
e) Print the elements of the list in reverse order;
Make up the situation for ArithmeticException. Catch it and display the explanation for your custom case.
*/

import java.util.*;

public class TASK3BYVLAD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.print("Enter the number of positions to rotate the array: ");
        int positions = scanner.nextInt();

        int[] rotatedArray = rotateArray(array, positions);

        System.out.println("Rotated Array: " + Arrays.toString(rotatedArray));

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int num : rotatedArray) {
            linkedList.add(num);
        }

        linkedList.addFirst(99);
        linkedList.addLast(77);
        System.out.println("List after adding elements: " + linkedList);

        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println("List after removing elements: " + linkedList);

        // Print elements in reverse order
        System.out.print("List in reverse order: ");
        ListIterator<Integer> iterator = linkedList.listIterator(linkedList.size());
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous() + " ");
        }
        System.out.println();

        try {
            System.out.print("Enter a number to divide by (for ArithmeticException): ");
            int divisor = scanner.nextInt();
            int result = 100 / divisor;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: Division by zero is not allowed. Please provide a non-zero divisor.");
        }

        scanner.close();
    }

    public static int[] rotateArray(int[] array, int positions) {
        int size = array.length;
        positions %= size;
        int[] rotated = new int[size];
        for (int i = 0; i < size; i++) {
            rotated[i] = array[(i + positions) % size];
        }
        return rotated;
    }
}
