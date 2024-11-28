import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class CustomInvocationListener implements IInvokedMethodListener {
    private static final Logger logger = LogManager.getLogger(CustomInvocationListener.class);

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        logger.info("Starting test: " + method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        logger.info("Finished test: " + method.getTestMethod().getMethodName());
    }
}