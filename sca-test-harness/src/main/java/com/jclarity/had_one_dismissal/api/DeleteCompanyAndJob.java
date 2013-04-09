package com.jclarity.had_one_dismissal.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.jclarity.had_one_dismissal.Exercise;

public class DeleteCompanyAndJob implements Runnable {

    private final Exercise exercise;

    public DeleteCompanyAndJob(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public void run() {
        while (exercise.isRunning()) {
            try {
                // a race condition here that can lead to a record failing to be deleted
                // not a big issue
                if (Ids.companyDeletedCount.intValue() < Ids.companyCreatedCount.intValue()) {
                    exercise.getHadOneDismissalApi().deleteCompanyAndJobById(Ids.companyDeletedCount.incrementAndGet() + 1);
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