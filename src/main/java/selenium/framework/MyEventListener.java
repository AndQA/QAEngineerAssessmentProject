package selenium.framework;

import org.testng.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyEventListener implements ITestListener {

	private static final SimpleDateFormat DATE_FORMAT =
			new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

	private static void log(String message) {
		Reporter.log(message + "<br/>", true);
	}

	private String timeStamp() {
		return DATE_FORMAT.format(new Date());
	}

	private void logFinish(ITestResult result) {
		log(result.getMethod().getMethodName() +
				" test has finished at " + timeStamp());
	}

	@Override
	public void onTestStart(ITestResult result) {
		log(result.getMethod().getMethodName() +
				" test has started at " + timeStamp());
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		IRetryAnalyzer retry = result.getMethod().getRetryAnalyzer(result);

		if (retry instanceof RetryAnalyzer) {
			int retryCount = ((RetryAnalyzer) retry).getRetryCount();
			if (retryCount > 0) {
				result.setAttribute("retried", true);
				log("Test passed after retry #" + retryCount);
			}
		}

		logFinish(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logFinish(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log(result.getMethod().getMethodName() +
				" test was skipped at " + timeStamp());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log(result.getMethod().getMethodName() +
				" test failed but within success percentage");
	}

	@Override
	public void onStart(ITestContext context) {
		log("Test suite started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		log("Test suite finished: " + context.getName());
	}
}