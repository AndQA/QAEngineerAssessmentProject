package selenium.framework;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.concurrent.atomic.AtomicInteger;

import static selenium.properties.EnvironmentManager.envConf;

public class RetryAnalyzer implements IRetryAnalyzer {
	private AtomicInteger retryCount = new AtomicInteger(0);
	int counter = 1;
	int retryLimit = envConf.retryAnalyzerLimit();
	/*
	 * (non-Javadoc)
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 * 
	 * This method decides how many times a test needs to be rerun.
	 * TestNg will call this method every time a test fails. So we 
	 * can put some code in here to decide when to rerun the test.
	 * 
	 * Note: This method will return true if a tests needs to be retried
	 * and false it not.
	 *
	 */
	
	public boolean retry(ITestResult result) {

		if(counter < retryLimit)
		{
			counter++;
			return true;
		}
		return false;
	}
	
	int getRetryCount() {
        return retryCount.get();
	}
}
