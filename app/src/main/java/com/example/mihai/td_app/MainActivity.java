package com.example.mihai.td_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    public static DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBAdapter();
    }
    public void openMaps(View view)
    {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }
    public void openLogin(View view){
        Intent intent = new Intent (MainActivity.this,Login.class);
        startActivity(intent);
    }
    public void openRegister(View view){
        Intent intent = new Intent (MainActivity.this,Register.class);
        startActivity(intent);
    }

}
