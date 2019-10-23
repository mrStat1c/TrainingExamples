import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, AuthenticationException {

        String uri = "http://localhost:8090/login";

//      GET
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(uri);
        getRequest.setHeader(HttpHeaders.CONTENT_TYPE, "text/html");
        HttpResponse httpResponse = httpClient.execute(getRequest);
        System.out.println("Response Status: " + httpResponse.getStatusLine().getStatusCode());
        System.out.println("Response Body: " + EntityUtils.toString(httpResponse.getEntity()));

//      GET with authentification
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("user1", "user1Pass");
        provider.setCredentials(AuthScope.ANY, credentials);
        httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
        httpResponse = httpClient.execute(getRequest);

//      POST
        HttpPost postRequest = new HttpPost(uri);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "user1"));
        params.add(new BasicNameValuePair("password", "user1Pass"));
        postRequest.setEntity(new UrlEncodedFormEntity(params));
        httpResponse = httpClient.execute(postRequest);

//      POST with json
        String json = "{\"id\":1, \"name\":\"John\"}";
        StringEntity entity = new StringEntity(json);
        postRequest = new HttpPost(uri);
        postRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        postRequest.setEntity(entity);
        httpResponse = httpClient.execute(postRequest);


//      POST with authentification
        postRequest = new HttpPost(uri);
        postRequest.addHeader(new BasicScheme().authenticate(credentials, postRequest, null));
        httpResponse = httpClient.execute(postRequest);

//      https://www.baeldung.com/httpclient-post-http-request
    }
}
