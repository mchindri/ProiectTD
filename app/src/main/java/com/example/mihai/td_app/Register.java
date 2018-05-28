package com.example.mihai.td_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
    private EditText usernameET,passwordET,bankAccountET,carNumberET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameET=findViewById(R.id.usernameReg);
        passwordET=findViewById(R.id.passwordReg);
        bankAccountET=findViewById(R.id.bankAccountReg);
        carNumberET=findViewById(R.id.carNumberReg);
    }

    public void confirmAction(View view){
        User user = new User();
        user.setUsername(usernameET.getText().toString());
        user.setPassword(passwordET.getText().toString());
        user.setBank_account(bankAccountET.getText().toString());
        user.setCar_number(carNumberET.getText().toString());

        if (MainActivity.db.createUser(user))
        {
            Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else
        {
            Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
        }
    }
}
