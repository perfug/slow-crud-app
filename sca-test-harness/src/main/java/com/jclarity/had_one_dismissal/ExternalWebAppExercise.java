package com.jclarity.had_one_dismissal;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class ExternalWebAppExercise extends Exercise {

    public static void main(String[] args) throws InterruptedException {
        new ExternalWebAppExercise().runExercise();
    }

    class LoginAndOut implements Runnable {
        public void run() {
            HadOneDismissalApi hadOneDismissalApi = new HadOneDismissalApi();
            while (true) {
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

    public void runExercise() {
        for (int i = 0; i < threadPool.getCorePoolSize(); i++) {
            threadPool.execute(new LoginAndOut());
        }
    }

}
