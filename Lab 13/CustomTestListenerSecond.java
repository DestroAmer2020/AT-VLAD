import com.monte.media.Format;
import com.monte.media.math.Rational;
import com.monte.media.avi.AVIWriter;

public class CustomTestListenerSecond implements ITestListener {
    private AVIWriter writer;

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test Failed: " + result.getName());

        try {
            writer = new AVIWriter("video/" + result.getName() + ".avi");
            writer.prepareForWrite(new Format("video/avi", Rational.valueOf(30, 1), 640, 480));
            writer.writeFrame(driver.getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            logger.error("Error recording video: ", e);
        }

        logTestResult(result, "FAIL");
        takeScreenshot(result);
    }

    @Override
    public void onFinish() {
        if (writer != null) {
            writer.close();
        }
    }
}