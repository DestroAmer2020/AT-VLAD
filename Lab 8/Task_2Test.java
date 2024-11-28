import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Task_2Test {

    Task_2 task2 = new Task_2();

    @Test(dataProvider = "arrayProvider")
    public void testInitializeArray(int[] expected) {
        int[] result = task2.initializeArray();
        Assert.assertEquals(result, expected);
    }

    @Test(dataProvider = "arrayProvider")
    public void testPrintArray(int[] array) {
        task2.printArray(array);
    }

    @Test(dataProvider = "oddCountProvider")
    public void testCountOddNumbers(int[] array, int expectedOddCount) {
        int result = task2.countOddNumbers(array);
        Assert.assertEquals(result, expectedOddCount);
    }

    @DataProvider(name = "arrayProvider")
    public Object[][] arrayProvider() {
        return new Object[][]{
            {new int[]{1, 2, 3, 4, 5}}
        };
    }

    @DataProvider(name = "oddCountProvider")
    public Object[][] oddCountProvider() {
        return new Object[][]{
            {new int[]{1, 2, 3, 4, 5}, 3},
            {new int[]{2, 4, 6, 8, 10}, 0},
            {new int[]{1, 3, 5, 7, 9}, 5}
        };
    }
}