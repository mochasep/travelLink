package com.hello.travellink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LoginActivity extends Activity {

    EditText email;
    EditText password;
    Button email_sign_in_button;
    TextView email_sign_up_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        email_sign_in_button = (Button) findViewById(R.id.email_sign_in_button);
        email_sign_up_button = (TextView) findViewById(R.id.email_sign_up_button);

        email_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                URL url = new URL(Helper.URL +"login.php?email="+email.getText().toString()+"&password="+password.getText().toString());
                                URLConnection connection = url.openConnection();
                                connection.connect();
                                InputStream is = connection.getInputStream();
                                BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                                String data = "";
                                String hasil = "";
                                int i = 0;
                                String nama="";
                                String no_telp="";
                                String id="";
                                while ((data=br.readLine())!=null){
                                    if (i==0){
                                        nama=data;
                                    }else if(i == 1){
                                        no_telp=data;
                                    } else{
                                        id=data;
                                    }
                                    i++;
                                    hasil += data;
                                }
                                Log.i("test", "run: " +hasil);
                                if (hasil.length()>0){
                                    Intent in = new Intent(getApplicationContext(),IdleActivity.class);
                                    in.putExtra("nama",nama);
                                    in.putExtra("no_telp",no_telp);
                                    in.putExtra("id",id);
                                    startActivity(in);
                                }else{
                                    Log.i("koko", "run: wrong");
                                }
                            }catch (MalformedURLException e){

                            }catch (IOException e){

                            }
                        }
                    }).start();

                }
        });

        email_sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}

