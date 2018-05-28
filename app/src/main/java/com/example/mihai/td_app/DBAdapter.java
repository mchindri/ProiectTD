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
        static boolean createUser(User user) {
            TaskRunner asyncTask = new TaskRunner(localHost);
            asyncTask.execute("createUser", user);
            System.out.println("Waiting for result");
            Boolean ok = false;
            try {
                ok = (Boolean) asyncTask.get();
                System.out.println("Result: " + ok);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (ExecutionException e) {
                System.out.println("Error: " + e.getMessage());
            }
            return (ok);
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

        public ArrayList<Station> getAllStations() {
            TaskRunner asyncTask = new TaskRunner(localHost);
            asyncTask.execute("getAllStations");
            ArrayList<Station> result = null;
            //Wait for test
            System.out.println("Waiting for result");
            try {
                result = (ArrayList<Station>) asyncTask.get();
                System.out.println("Result: " + result);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (ExecutionException e) {
                System.out.println("Error: " + e.getMessage());
            }
            return result;
        }
    }