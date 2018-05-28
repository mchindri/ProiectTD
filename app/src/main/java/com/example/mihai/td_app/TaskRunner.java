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


public class TaskRunner extends AsyncTask <String, String, Object>{
    private String localHost;

    public TaskRunner(String localHost) {
        this.localHost = localHost;
    }

    protected Object doInBackground(String... command) {
        if (command[0].equals("test"))
            return test();
        if (command[0].equals("getAllUsers"))
            return getAllUsers();
        return null;
    }
    protected void onProgressUpdate(String... progress) {
        //Toast.makeText(, progress[0], Toast.LENGTH_LONG).show();
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
