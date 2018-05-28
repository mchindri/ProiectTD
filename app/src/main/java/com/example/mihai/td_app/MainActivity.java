package com.example.mihai.td_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener{

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOne = (Button) findViewById(R.id.button);
        buttonOne.setOnClickListener(this);

        tv = (TextView) findViewById(R.id.tvMessages);
    }

    @Override
    public void onClick(View view) {

        /*
        if (DBAdapter.addUser("Gheorghe", "123456", "Acount1", "15fa123"))
         */
        ArrayList<User> users = DBAdapter.getAllUsers();
        if (null != users)
        {
            for (User u : users)
            {
                tv.setText(tv.getText() + "\n" + u.toString());
            }
            Toast.makeText(this, "test work", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, "test don't work", Toast.LENGTH_LONG).show();

    }
}
