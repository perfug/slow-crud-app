package com.jclarity.had_one_dismissal;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

public class HODApi {
    private static String HOST = System.getProperty("host", "127.0.0.1");

    private static String PORT = System.getProperty("port","8080");

    private static String URL = "http://" + HOST + ":" + PORT + "/had_one_dismissal/";

    private static String COMPANY_JOB_URL = URL + "companyandjob";

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
}
