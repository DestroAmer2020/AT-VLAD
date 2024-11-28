import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class CustomSuiteListener implements ISuiteListener {
    private static final Logger logger = LogManager.getLogger(CustomSuiteListener.class);

    @Override
    public void onStart(ISuite suite) {
        logger.info("Suite Started: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        logger.info("Suite Finished: " + suite.getName());
    }
}