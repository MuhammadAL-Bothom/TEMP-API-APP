# 🌤️ Android Weather API Demo (Java)

## Overview
This simple Android app demonstrates how to **fetch live weather data** from the **OpenWeatherMap API** and display the temperature of a given city on screen.

---

## ✨ Features
- 🌍 Fetches **current temperature** for any city (changeable in code)
- 🔄 Makes a **real-time API call** using `HttpURLConnection`
- 📊 Parses JSON response to extract temperature
- 🖥️ Updates the UI from a background thread using:
  - `runOnUiThread()` ✅ (default)
  - Alternative options: `post()` or `Handler(Looper.getMainLooper())`
- ⚠️ Handles errors gracefully with Toast messages

---

## 📱 Screenshots
<p align="center">
  <img src="https://github.com/user-attachments/assets/51c41cb7-5a4f-4db4-b979-ed1f42093cfb" width="250" alt="Weather in Palestine"/>
</p>
---

## 🛠️ Tech Stack
- **Language:** Java  
- **Networking:** `HttpURLConnection`  
- **JSON Parsing:** `org.json.JSONObject`  
- **Threading:** `ExecutorService` + `UI Thread` updates  

---

## 📂 Key Files
- **MainActivity.java**
  - Builds the API request URL
  - Calls the OpenWeatherMap API
  - Parses JSON response (`main.temp`)
  - Updates `TextView` with the temperature
- **activity_main.xml**
  - `TextView` with ID `temperatureTextView` to display temperature  

---

## ⚙️ Setup
### Gradle
No special dependencies required (uses core Android libraries).

### Manifest
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

---

## 🔑 API Setup
Get a free API key from OpenWeatherMap.

Replace in `MainActivity.java`:

```java
String API_KEY = "YOUR_API_KEY_HERE";
```

Change the city variable if needed:

```java
String city = "Amman"; // Example
```

---

## ▶️ How It Works
App builds a GET request to:

```
https://api.openweathermap.org/data/2.5/weather?q={city}&units=metric&appid={API_KEY}
```

Parses the JSON response:

```json
{
  "main": {
    "temp": 28.87
  }
}
```

Updates the UI with:
```
Temperature in Palestine: 28.87°C
```

---

## 🚀 Suggested Enhancements
- Show more details: humidity, wind speed, description.
- Add a search box for city input instead of hardcoding.
- Cache last temperature locally.
- Add support for multiple cities (RecyclerView).
- Use Retrofit or Volley for cleaner networking.

---

## 📋 Quick Start
1. Clone the project.
2. Insert your API key in `MainActivity.java`.
3. Run on device/emulator with internet access.
4. See live temperature updates for the city.
