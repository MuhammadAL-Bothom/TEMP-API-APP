package com.example.tempapiapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    String city = "Palestine"; // Change the city here
    // ‼️‼️ (( Jordan , London , Tafilah , Palestine , Egypt )) you can wright any city you want .

    String API_KEY = "API_KEY";
    String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=";

    public TextView temperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureTextView = findViewById(R.id.temperatureTextView);

        // Fetch temperature directly
        fetchTemperature();

    }

    // Fetch temperature from OpenWeatherMap API (synchronously)
    public void fetchTemperature() {
        // Create a new ExecutorService to handle background tasks
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            try {
                // Create URL from the full API URL
                String fullUrl = BASE_URL + API_KEY;
                URL url = new URL(fullUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONObject main = jsonResponse.getJSONObject("main");
                double temperature = main.getDouble("temp");

                // Update UI with the fetched temperature
                //‼️‼️‼️‼️just uncomment the way you want to use and comment the other way that has been used before


                //‼️ 1. //
                runOnUiThread(() -> temperatureTextView.setText("Temperature in " + city + ": " + temperature + "°C"));

                //‼️ 2. //
                // temperatureTextView.post(() -> temperatureTextView.setText("Temperature in " + city + ": " + temperature + "°C"));

                //‼️ 3. //
                // Handler handler = new Handler(Looper.getMainLooper());
                // handler.post(() -> temperatureTextView.setText("Temperature in " + city + ": " + temperature + "°C"));


            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show());
            }
        });
    }


}
