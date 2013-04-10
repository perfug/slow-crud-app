package com.jclarity.had_one_dismissal.exercises;

import static java.lang.Integer.parseInt;

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

    public CpuLoadExercise(String threadCount) {
        super(parseInt(threadCount));
    }

    @Override
    public void runExercise() {
        isRunning = true;
        for (int i = 0; i < poolSize; i++) {
            threadPool.execute(new PopulateDb());
        }
    }

    @Override
    public void init() {
        performanceProblems.setBatchingDBQueries(false);
    }

    @Override
    public void reset() {
        performanceProblems.setBatchingDBQueries(true);
    }

}
