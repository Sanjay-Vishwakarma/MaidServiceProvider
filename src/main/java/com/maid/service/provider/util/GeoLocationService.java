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
            URL url = new URL("https://ipapi.co/" + ip + "/json/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder responseContent = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                responseContent.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(responseContent.toString());

            location.setCity(json.optString("city"));
            location.setRegion(json.optString("region"));
            location.setCountry(json.optString("country_name"));

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Failed to get geo location: " , e.getMessage());

        }

        return location;
    }
}
