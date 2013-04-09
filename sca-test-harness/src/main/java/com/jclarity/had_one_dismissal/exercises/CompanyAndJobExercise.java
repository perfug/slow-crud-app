package com.jclarity.had_one_dismissal.exercises;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.client.ClientProtocolException;

import com.jclarity.had_one_dismissal.Exercise;
import com.jclarity.had_one_dismissal.HadOneDismissalApi;

/**
 * Performs two tasks, creating job/company listing then deleting it
 *
 */
public abstract class CompanyAndJobExercise extends Exercise {

    protected final AtomicInteger companyCreatedCount;
    protected final AtomicInteger companyDeletedCount;
    protected final HadOneDismissalApi hadOneDismissalApi = new HadOneDismissalApi();

    private final int enqueueJobsCount;
    private final int deleteJobsCount;

    public CompanyAndJobExercise(int enqueueJobsCount, int deleteJobsCount) {
        super(enqueueJobsCount + deleteJobsCount);
        this.enqueueJobsCount = enqueueJobsCount;
        this.deleteJobsCount = deleteJobsCount;

        companyCreatedCount = new AtomicInteger(0);
        companyDeletedCount = new AtomicInteger(0);
    }

    class CreateCompanyAndJob implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                try {
                    hadOneDismissalApi.createCompanyAndJob("wang", "head of awesome", 3, 4, "sdfettre");
                    companyCreatedCount.incrementAndGet();
                } catch (ClientProtocolException e) {
                    // Deliberately ignore
                } catch (IOException e) {
                    // Deliberately ignore
                }
            }
        }
    }

    class DeleteCompanyAndJob implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                try {
                    // a race condition here that can lead to a record failing to be deleted
                    // not a big issue
                    if (companyDeletedCount.intValue() < companyCreatedCount.intValue()) {
                        hadOneDismissalApi.deleteCompanyAndJobById(companyDeletedCount.incrementAndGet() + 1);
                    } else {
                        Thread.sleep(100);
                    }
                } catch (ClientProtocolException e) {
                    // Deliberately ignore
                } catch (IOException e) {
                    // Deliberately ignore
                } catch (InterruptedException e) {
                    // Deliberately ignore
                }
            }
        }
    }

    @Override
    public void runExercise() {
        for (int i = 0; i < enqueueJobsCount; i++) {
            threadPool.execute(new CreateCompanyAndJob());
        }

        for (int i = 0; i < deleteJobsCount; i++) {
            threadPool.execute(new DeleteCompanyAndJob());
        }
    }

}
