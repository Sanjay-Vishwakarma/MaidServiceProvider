package com.maid.service.provider.util;

import com.maid.service.provider.dto.GeoLocation;
import com.maid.service.provider.exception.AppException;
import org.cloudinary.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeoLocationService {

    public static GeoLocation getGeoLocation(String ip) {
        GeoLocation location = new GeoLocation();

        try {
            String token = "c12990c256f87a"; // Replace with your actual token
            URL url = new URL("https://ipinfo.io/" + ip + "/json?token=" + token);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            if (status != 200) {
                throw new AppException("Failed to fetch location. HTTP Status: " , String.valueOf(status));
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder responseContent = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                responseContent.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(responseContent.toString());

            location.setCity(json.optString("city", "Unknown"));
            location.setRegion(json.optString("region", "Unknown"));
            location.setCountry(json.optString("country", "Unknown"));

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Failed to get geo location", e.getMessage());
        }

        return location;
    }

}
