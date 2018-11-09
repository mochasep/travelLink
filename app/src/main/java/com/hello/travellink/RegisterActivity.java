package com.hello.travellink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class RegisterActivity extends Activity {

    EditText nama;
    EditText email;
    EditText password;
    EditText alamat;
    EditText no_telp;
    Button email_sign_up_button;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nama = (EditText) findViewById(R.id.nama);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        alamat = (EditText) findViewById(R.id.alamat);
        no_telp = (EditText) findViewById(R.id.no_telp);
        email_sign_up_button = (Button) findViewById(R.id.email_sign_up_button);
        textView4 = (TextView) findViewById(R.id.textView4);

    email_sign_up_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(Helper.URL +"registrasi.php?nama="+nama.getText().toString().replace(" ","%20")
                                +"&email=" +email.getText().toString()+"&password="+password.getText().toString().replace(" ","%20")
                                +"&alamat=" +alamat.getText().toString().replace(" ","%20")+"&no_telp="+no_telp.getText().toString());
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                        InputStream is = connection.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                        String data = "";
                        String hasil = "";
                        while ((data=br.readLine())!=null){
                            hasil += data;
                        }

                        Toast.makeText(RegisterActivity.this, "" + connection.getResponseCode(), Toast.LENGTH_SHORT).show();
                        Log.i("test", "run: " +hasil);
                        if (Integer.parseInt(hasil)==1){
                            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(i);
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
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
