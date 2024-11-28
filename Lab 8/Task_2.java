public class Task_2 {
    public int[] initializeArray() {
        return new int[]{1, 2, 3, 4, 5};
    }

    public void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    public int countOddNumbers(int[] array) {
        int count = 0;
        for (int num : array) {
            if (num % 2 != 0) count++;
        }
        return count;
    }
}