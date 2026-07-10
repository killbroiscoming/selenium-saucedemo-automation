package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2;
    // 2 retries means: first run + 2 more attempts = 3 total runs

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;

            System.out.printf("Retrying test: " + result.getName()
            + " | Attempt: " + (retryCount + 1));

            return true;
        }

        return false;
    }
}
