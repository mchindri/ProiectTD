package com.example.mihai.td_app;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class TaskRunner extends AsyncTask <Object, String, Object>{
    private String localHost;

    public TaskRunner(String localHost) {
        this.localHost = localHost;
    }

    protected Object doInBackground(Object... params) { //param[0] = command; param[1] = param to functions
        if (params[0].equals("test"))
            return test();
        if (params[0].equals("getAllUsers"))
            return getAllUsers();
        if (params[0].equals("createUser"))
            return createUser((User)params[1]);
        if (params[0].equals("getAllStations"))
            return getAllStations();
        return null;
    }

    private ArrayList<Station> getAllStations() {
        ArrayList<Station> stList = new ArrayList<>();
        String url = "http://" + localHost + "/getAllStations.php";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONArray array = new JSONArray(response.body().string());

            for (int i = 0; i < array.length(); i++) {

                JSONObject object = array.getJSONObject(i);

                Station station = new Station(object.getInt("station_id"), object.getString("station_name"),
                        object.getInt("nb_of_spots"), object.getInt("ocupied"), object.getInt("reserved"),
                        object.getDouble("lat"), object.getDouble("lnt"));
                stList.add(station);
                //MainActivity.this.movies.add(movie);
            }
            return stList;
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        } catch (JSONException e) {
            System.out.println("JSON Exception: " + e.getMessage());
        }
        return null;
    }


    private boolean createUser(User user)
    {
        String url = "http://" + localHost + "/addUser.php?usr=" + user.getUsername() +
                "&pass=" + user.getPassword() + "&bank=" + user.getBank_account() + "&car=" + user.getCar_number();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String resp = response.body().string();
            System.out.println("Create user response: " + resp);
            if (resp.equalsIgnoreCase("OK"))
                return true;
            else
                return false;
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return false;
    }


    private String test()
    {
        String url = "http://" + localHost + "/test.php";
        String resp = "";
        //
        System.out.println("Url:" + url);
        //
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            resp = response.body().string();
        }catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        System.out.println("Response: " + resp);
        return resp;
    }
    private ArrayList<User> getAllUsers()
    {
        ArrayList<User> usrArray = new ArrayList<>();
        String url = "http://" + localHost + "/getAllUsers.php";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONArray array = new JSONArray(response.body().string());

            for (int i = 0; i < array.length(); i++) {

                JSONObject object = array.getJSONObject(i);

                User user = new User(object.getInt("user_id"), object.getString("username"), object.getString("password"),
                        object.getString("bank_account"), object.getString("car_number"));
                usrArray.add(user);
                //MainActivity.this.movies.add(movie);
            }
            return usrArray;
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        } catch (JSONException e) {
            System.out.println("JSON Exception: " + e.getMessage());
        }
        return null;
    }
}
