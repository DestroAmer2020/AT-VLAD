import java.util.concurrent.CountDownLatch;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Part (a) - Using CountDownLatch
class CountDownLatchExample {

    public static void runCountDownLatchExample() throws InterruptedException {
        int numThreads = 3;
        CountDownLatch latch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            new Thread(new Worker(latch)).start();
        }

        latch.await();
        System.out.println("All threads have finished. Continuing in main thread...");
    }

    static class Worker implements Runnable {
        private final CountDownLatch latch;

        public Worker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is working...");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " finished.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
            }
        }
    }
}

// Part (b) - Custom Class with Reflection to Get and Modify Fields
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }
}

class ReflectionExample {

    public static void runReflectionExample() throws Exception {
        Person person = new Person("John Doe", 25);
        System.out.println("Before modification: " + person);

        Class<?> personClass = person.getClass();
        Field[] fields = personClass.getDeclaredFields();

        System.out.println("Fields of Person class:");
        for (Field field : fields) {
            System.out.println(field.getName() + " - " + field.getType());
        }

        Field ageField = personClass.getDeclaredField("age");
        ageField.setAccessible(true); // Allows modifying private field
        ageField.setInt(person, 30);

        System.out.println("After modification: " + person);
    }
}

// Part (c) - Generic Class to Store List of Elements
class Storage<T> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public List<T> getItems() {
        return items;
    }
}

class GenericExample {

    public static void runGenericExample() {
        Storage<String> stringStorage = new Storage<>();
        stringStorage.addItem("Hello");
        stringStorage.addItem("World");

        System.out.println("String Storage: " + stringStorage.getItems());

        Storage<Integer> integerStorage = new Storage<>();
        integerStorage.addItem(1);
        integerStorage.addItem(2);

        System.out.println("Integer Storage: " + integerStorage.getItems());
    }
}

// Part (d) - Catching ArithmeticException with a Custom Message
class ArithmeticExceptionExample {

    public static void runArithmeticExceptionExample() {
        try {
            int result = divide(10, 0);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero. Please provide a non-zero divisor.");
        }
    }

    public static int divide(int a, int b) {
        return a / b;
    }
}

// Main class to run all examples
public class CombinedExample {
    public static void main(String[] args) {
        try {
            System.out.println("Running CountDownLatch Example:");
            CountDownLatchExample.runCountDownLatchExample();

            System.out.println("\nRunning Reflection Example:");
            ReflectionExample.runReflectionExample();

            System.out.println("\nRunning Generic Example:");
            GenericExample.runGenericExample();

            System.out.println("\nRunning ArithmeticException Example:");
            ArithmeticExceptionExample.runArithmeticExceptionExample();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}