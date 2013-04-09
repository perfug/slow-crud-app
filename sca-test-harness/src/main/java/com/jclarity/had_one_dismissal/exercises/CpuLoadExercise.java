package com.jclarity.had_one_dismissal.exercises;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.jclarity.had_one_dismissal.Exercise;

public class CpuLoadExercise extends Exercise {

    class PopulateDb implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                try {
                    getHadOneDismissalApi().populateDb();
                } catch (ClientProtocolException e) {
                    // Deliberately ignore
                } catch (IOException e) {
                    // Deliberately ignore
                }
            }
        }
    }

    @Override
    public void runExercise() {
        isRunning = true;
        threadPool.execute(new PopulateDb());
    }
}
