package com.edu.smu.track2career.manager;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class RestfulManager {

    // HTTP GET request
    public static JsonObject sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();

        String basicAuth = "Basic dHJhY2syY2FyZWVyOlAqTV9oeEhCcypValhzPkQ=";
        conn.setRequestProperty("Authorization", basicAuth);

        // optional default is GET
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        JsonObject results = new JsonParser().parse(response.toString()).getAsJsonObject();
        return results;
    }

    // String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
    // HTTP POST request
    public static JsonObject sendPost(String url, String urlParameters) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();

        String basicAuth = "Basic dHJhY2syY2FyZWVyOlAqTV9oeEhCcypValhzPkQ=";
        conn.setRequestProperty("Authorization", basicAuth);

        conn.setRequestProperty("Content-Type", "application/json");

        //add reuqest header
        conn.setRequestMethod("POST");

        // Send post request
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(urlParameters.getBytes());
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);
        
        if (responseCode == 404) {
            throw new Exception("404");
        }
        else if (responseCode == 502) {
            throw new Exception("502");
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        JsonObject results = new JsonParser().parse(response.toString()).getAsJsonObject();
        return results;
    }

    // String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
    // HTTP POST request
    public static JsonObject sendPostWageDb(String url, String urlParameters) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();

        String basicAuth = "Basic d2FnZWRiX2FwaV91c2VyOndhZ2VkYmFwaQ==";
        conn.setRequestProperty("Authorization", basicAuth);

        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        //add reuqest header
        conn.setRequestMethod("POST");

        // Send post request
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(urlParameters.getBytes());
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        JsonObject results = new JsonParser().parse(response.toString()).getAsJsonObject();
        return results;
    }
}
