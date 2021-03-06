package com.ks.agroconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private TextView signUp_text;
    Button btnLogin;
    Boolean saveLogin;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.login_email);
        pass = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_btn);


        //Progress Dialog Created
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.loading_dialog);
        final AlertDialog dialog = builder.create();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mPass = pass.getText().toString().trim();

                //Checking valid input information
                if (TextUtils.isEmpty(mEmail)) {
                    email.setError("Required Field...");
                    return;
                }
                if (TextUtils.isEmpty(mPass)) {
                    pass.setError("Required Field...");
                    return;
                }
                mSharedPreferences = getSharedPreferences("accountRa", MODE_PRIVATE);
                editor = mSharedPreferences.edit();

                String e = mSharedPreferences.getString("email", null);
                String p = mSharedPreferences.getString("password", null);
                if (mEmail.equals(e) && mPass.equals(p)) {

                    dialog.show();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            dialog.cancel();
                        }
                    }, 1000);
                    //startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "Error! TRY AGAIN", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Register Activity
        signUp_text = findViewById(R.id.signUp_text);
        signUp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }
}