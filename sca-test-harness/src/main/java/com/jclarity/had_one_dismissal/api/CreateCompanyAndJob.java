package com.jclarity.had_one_dismissal.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.jclarity.had_one_dismissal.Exercise;

public class CreateCompanyAndJob implements Runnable {

    private final Exercise exercise;

    public CreateCompanyAndJob(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public void run() {
        while (exercise.isRunning()) {
            try {
                exercise.getHadOneDismissalApi().createCompanyAndJob("wang", "head of awesome", 3, 4, "sdfettre");
                Ids.companyCreatedCount.incrementAndGet();
            } catch (ClientProtocolException e) {
                // Deliberately ignore
            } catch (IOException e) {
                // Deliberately ignore
            }
        }
    }

}
