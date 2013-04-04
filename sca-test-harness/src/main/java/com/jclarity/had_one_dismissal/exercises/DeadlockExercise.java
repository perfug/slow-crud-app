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
public class DeadlockExercise extends Exercise {

    private final AtomicInteger companyCreatedCount;
    private final AtomicInteger companyDeletedCount;
    private final HadOneDismissalApi hadOneDismissalApi = new HadOneDismissalApi();

    private static final int ENQUEUE_JOBS_COUNT = 2;
    private static final int DELETE_JOBS_COUNT = 2;

    public DeadlockExercise() {
        super();
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
        for (int i = 0; i < ENQUEUE_JOBS_COUNT; i++) {
            threadPool.execute(new CreateCompanyAndJob());
        }

        for (int i = 0; i < DELETE_JOBS_COUNT; i++) {
            threadPool.execute(new DeleteCompanyAndJob());
        }
    }

}
