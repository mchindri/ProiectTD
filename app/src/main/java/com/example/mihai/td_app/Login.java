package com.example.mihai.td_app;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Login extends Activity {
    private EditText usernameET;
    private EditText passwordET;
    private TextView popup;
    public static User loginUser; // ca sa pastrezi undeva datele utilizatorului logat

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameET = findViewById(R.id.usernameTextBox);
        passwordET = findViewById(R.id.passwordTextBox);
        popup = findViewById(R.id.popup);
    }

    public void tryLogin(View view) {
        String usr = usernameET.getText().toString();
        String pass = passwordET.getText().toString();
        boolean validLogin=false;
        // iteration over the table
        List<User> users = MainActivity.db.getAllUsers();
        for(User u : users) {
            System.out.println(usernameET.toString()+" "+String.valueOf(passwordET));
            if (u.getUsername().equals(usr) && u.getPassword().equals(pass)) {
                validLogin = true; //if the user is found
                loginUser=u;
                break;
            }
        }

        if (validLogin) {
            Toast.makeText(this, "Succesful loggin", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, MapsActivity.class);
            startActivity(intent);
        } else {
            //Toast.makeText(this, "Fail to loggin", Toast.LENGTH_SHORT).show();
            popup.setVisibility(View.VISIBLE);
        }
    }
}
