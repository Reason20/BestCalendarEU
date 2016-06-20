package com.example.kacper_light_erp.bestcalendareu;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kacper-Light-ERP on 2016-06-18.
 */
public class NetworkRequest {
    private final String url;
    private final HttpMethod method;
    private final String body;

    public NetworkRequest(String url, HttpMethod method, String body) {
        this.url = url;
        this.method = method;
        this.body = body;
    }

    public NetworkRequest(String url, HttpMethod method) {
        this(url, method, null);
    }

    public String execute() throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod(method.getMethod());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);
            //if(method==HttpMethod.POST)
            //    conn.setDoInput(true);
            //OutputStream outputPost = new BufferedOutputStream(conn.getOutputStream());
            if (body != null) {
                //conn.setDoOutput(true);
                conn.getOutputStream().write(body.getBytes());
            }

            conn.connect();
            //conn.getOutputStream().flush();
            //outputPost.write(body.getBytes());
            //outputPost.flush();
            //outputPost.close();
            if(method==HttpMethod.GET) {
                is = conn.getInputStream();
            }
            else{
                int status = conn.getResponseCode();
                if (status!=200)
                    is = conn.getErrorStream();

            }
            return readStream(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readStream(InputStream stream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return total.toString();
    }
}
