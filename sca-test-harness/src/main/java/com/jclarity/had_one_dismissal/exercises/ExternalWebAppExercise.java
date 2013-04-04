package com.jclarity.had_one_dismissal.exercises;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.jclarity.had_one_dismissal.Exercise;
import com.jclarity.had_one_dismissal.HadOneDismissalApi;

public class ExternalWebAppExercise extends Exercise {

    class LoginAndOut implements Runnable {
        @Override
        public void run() {
            HadOneDismissalApi hadOneDismissalApi = new HadOneDismissalApi();
            while (isRunning) {
                try {
                    hadOneDismissalApi.login("foo", "bar");
                    hadOneDismissalApi.logout();
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
        for (int i = 0; i < threadPool.getCorePoolSize(); i++) {
            threadPool.execute(new LoginAndOut());
        }
    }

}
