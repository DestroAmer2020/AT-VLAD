import org.testng.annotations.Parameters;

@Parameters({"arraySize"})
public void testArraySize(int arraySize) {
    int[] array = task2.initializeArray(arraySize);
    Assert.assertEquals(array.length, arraySize, "Array size does not match expected value.");
}