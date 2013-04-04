package com.jclarity.had_one_dismissal.exercises;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.jclarity.had_one_dismissal.Exercise;
import com.jclarity.had_one_dismissal.HadOneDismissalApi;

public class CpuLoadExercise extends Exercise {

    class PopulateDb implements Runnable {
        @Override
        public void run() {
            HadOneDismissalApi hadOneDismissalApi = new HadOneDismissalApi();
            while (isRunning) {
                try {
                    hadOneDismissalApi.populateDb();
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
