    package com.example.mihai.td_app;

    import android.os.AsyncTask;
    import android.widget.Toast;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.concurrent.ExecutionException;

    import okhttp3.OkHttpClient;
    import okhttp3.Request;
    import okhttp3.Response;

    public class DBAdapter {

        static String localHost = "192.168.43.65:8080"; //hostpot
        //static String localHost = "192.168.0.100:8080"; //homerouter
        static boolean addUser(String username, String pass, String bankAccount, String car_nb) {

            String url = "http://" + localHost + "/parkDB/addUser.php?usr=" + username +
                    "&pass=" + pass + "&bank=" + bankAccount + "&car=" + car_nb;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String resp = response.body().string();
                if (resp.equalsIgnoreCase("OK"))
                    return true;
                else
                    return false;
            } catch (Exception e) {
                System.out.println("Error msg: " + e.getMessage());
            }
            return false;
        }


        static boolean test() {
            TaskRunner asyncTask = new TaskRunner(localHost);
            asyncTask.execute("test");
            //Wait for test
            System.out.println("Waiting for result");
            String result = "";
            try {
                result = (String) asyncTask.get();
                System.out.println("Result: " + result);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (ExecutionException e) {
                System.out.println("Error: " + e.getMessage());
            }
            if (result.equals("working"))
                return true;
            else
                return false;
        }

        static ArrayList<User> getAllUsers() {
            TaskRunner asyncTask = new TaskRunner(localHost);
            asyncTask.execute("getAllUsers");
            ArrayList<User> result = null;
            //Wait for test
            System.out.println("Waiting for result");
            try {
                result = (ArrayList<User>) asyncTask.get();
                System.out.println("Result: " + result);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (ExecutionException e) {
                System.out.println("Error: " + e.getMessage());
            }
            return result;
        }

    }

                    //http://localhost/parkDB/getAllUsers.php
            /*
            AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
                @Override
                protected Void doInBackground(Integer... movieIds) {

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://192.168.1.35/movies.php?id=" + movieIds[0])
                            .build();
                    try {
                        Response response = client.newCall(request).execute();

                        JSONArray array = new JSONArray(response.body().string());

                        for (int i = 0; i < array.length(); i++) {

                            JSONObject object = array.getJSONObject(i);

                            //Movie movie = new Movie(object.getInt("id"), object.getString("movie_name"),
                            //        object.getString("movie_image"), object.getString("movie_genre"));

                            //MainActivity.this.movies.add(movie);
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        System.out.println("Error msg: " + e.getMessage());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    //sa executat
                    //adapter.notifyDataSetChanged();
                }
            };

            asyncTask.execute();
            return null;
            */