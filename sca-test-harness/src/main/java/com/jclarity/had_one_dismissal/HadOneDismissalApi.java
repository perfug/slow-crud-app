package com.jclarity.had_one_dismissal;

import java.io.IOException;

import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.message.BasicNameValuePair;

public class HadOneDismissalApi {
    private static String HOST = System.getProperty("host", "127.0.0.1");

    private static String PORT = System.getProperty("port","8080");

    private static String URL = "http://" + HOST + ":" + PORT + "/had_one_dismissal/";

    private static String COMPANY_JOB_URL = URL + "companyandjob";
    private static String LOGIN_URL       = URL + "resources/j_spring_security_check";
    private static String LOGOUT_URL      = URL + "resources/j_spring_security_logout";

    private final BasicCookieStore cookies;

    private final Executor executor;

    public HadOneDismissalApi() {
        this.cookies = new BasicCookieStore();
        this.executor = Executor.newInstance()
                                 .cookieStore(cookies);
    }

    private static String companyAndJobByJobId(int id) {
        return COMPANY_JOB_URL + "/" + id;
    }

    public void createCompanyAndJob(String name, String title, int salaryLowerBound, int salaryUpperBound, String description) throws ClientProtocolException, IOException {
        Request .Post(COMPANY_JOB_URL)
                .bodyForm(new BasicNameValuePair("company.name", name),
                          new BasicNameValuePair("job.title", title),
                          new BasicNameValuePair("job.salaryLowerBound", Integer.toString(salaryLowerBound)),
                          new BasicNameValuePair("job.salaryUpperBound", Integer.toString(salaryUpperBound)),
                          new BasicNameValuePair("job.description", description))
                .socketTimeout(1000)
                .connectTimeout(1000)
                .execute();
    }

    public void deleteCompanyAndJobById(int id) throws ClientProtocolException, IOException {
        Request .Delete(companyAndJobByJobId(id))
                .execute();
    }

    public void login(String username, String password) throws ClientProtocolException, IOException {
        Request request = Request.Post(LOGIN_URL)
                                 .bodyForm(new BasicNameValuePair("j_username", username),
                                           new BasicNameValuePair("j_password", password));

        executeWithCookieStore(request);
    }

    public StatusLine logout() throws ClientProtocolException, IOException {
        return executeWithCookieStore(Request.Get(LOGOUT_URL))
                .returnResponse()
                .getStatusLine();
    }

    private Response executeWithCookieStore(Request request) throws ClientProtocolException, IOException {
        return executor.execute(request);
    }
    
}
