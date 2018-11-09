package com.hello.travellink;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;

public class IdleActivity extends Activity {

    Button trip;
    Button info;
    Button help;
    String nama;
    String no_telp;
    String id;
    Button keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle);
        Intent i = getIntent();
        nama = i.getStringExtra("nama");
        no_telp = i.getStringExtra("no_telp");
        id = i.getStringExtra("id");
        Log.i("uu", "onCreate: "+nama);
        Log.i("qq", "onCreate: "+no_telp);

        trip = (Button) findViewById(R.id.trip);
        info = (Button) findViewById(R.id.info);
        help = (Button) findViewById(R.id.help);
        keluar = (Button) findViewById(R.id.keluar);

        trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                i.putExtra("nama",nama);
                i.putExtra("no_telp",no_telp);
                startActivity(i);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), InformasiActivity.class);
                startActivity(j);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(k);
            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(l);
            }
        });


    }
}
