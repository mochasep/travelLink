package com.hello.travellink;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class MenuActivity extends Activity {

    Button Tasikmalaya;
    Button Bandung;
    Button Bogor;
    Button Cirebon;
    String nama;
    String no_telp;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent i = getIntent();
        nama = i.getStringExtra("nama");
        no_telp = i.getStringExtra("no_telp");
        id = i.getStringExtra("id");
        Log.i("uu", "onCreate: "+nama);
        Log.i("qq", "onCreate: "+no_telp);
        Tasikmalaya = (Button) findViewById(R.id.tasikmalaya);
        Bandung = (Button) findViewById(R.id.bandung);
        Bogor = (Button) findViewById(R.id.bogor);
        Cirebon = (Button) findViewById(R.id.cirebon);

        Tasikmalaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampil_info(0);
            }
        });

        Bandung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampil_info(1);
            }
        });

        Bogor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampil_info(2);
            }
        });

        Cirebon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampil_info(3);
            }
        });

    }

    void tampil_info(final int pilihan){
        String nama_kota="";
        String tempat_wisata="";
        String lamanya="";
        String kendaraan="";
        String harga="";

        switch (pilihan){
            case 0:
                nama_kota = "TASIKMALAYA";
                tempat_wisata = "Gn. Galunggung - kebun Teh Taraju - Pantai Karangnunggal";
                lamanya = "2 Hari";
                kendaraan = "Suzuki R3  No.Polisi Z 9870 KK";
                harga = "Rp. 300.000";
                break;

            case 1:
                nama_kota = "BANDUNG";
                tempat_wisata = "Tangkuban Parahu - Kawah Putih - the lodge earthbound adventure park";
                lamanya = "2 Hari";
                kendaraan = "Pajero Sport  No.Polisi Z 9947 LK";
                harga = "Rp. 600.000";
                break;

            case 2:
                nama_kota = "BOGOR";
                tempat_wisata = "Talaga Warna - Kebun Raya Bogor - Taman Safari";
                lamanya = "2 Hari";
                kendaraan = "Honda CR-V  No.Polisi Z 9734 IU";
                harga = "Rp. 900.000";
                break;

            case 3:
                nama_kota = "CIREBON";
                tempat_wisata = "Gua Sunyaragi - Keraton Kesepuhan - Cirebon Waterland";
                lamanya = "2 Hari";
                kendaraan = "Toyota Rush  No.Polisi Z 4642 MM";
                harga = "Rp. 500.000";
                break;
        }
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("INFORMASI");
                ab.setMessage("Nama Kota: "+nama_kota+"\n"+
                "Tempat Wisata: "+tempat_wisata+"\n"+
                "Lamanya : "+lamanya+"\n"+
                "Kendaraan : "+kendaraan+"\n"+
                "Harga : "+harga+"\n");

        final String pesanan = "Nama Kota: "+nama_kota+"\n"+
                "Tempat Wisata: "+tempat_wisata+"\n"+
                "Lamanya : "+lamanya+"\n"+
                "Kendaraan : "+kendaraan+"\n"+
                "Harga : "+harga+"\n";

        ab.setPositiveButton("Booking", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pilih_tanggal(pesanan, pilihan);
            }
        });

        ab.setNegativeButton("Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog ad = ab.create();
        ad.show();
    }

    Date hariBooking = new Date();
    Date hariBerangkat = new Date();
    void pilih_tanggal(final String pesanan, final int pilihan){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        final DatePicker dp = new DatePicker(this);
        ab.setView(dp);
        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                laporan(pesanan, pilihan, hariBerangkat.getTime());
            }
        });


        AlertDialog ad = ab.create();
        ad.show();
        dp.init(new Date().getYear() + 1900, new Date().getMonth(), new Date().getDate(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                hariBerangkat = new Date(year-1900,monthOfYear,dayOfMonth);
                Log.i("hhaha", "onDateChanged: " + hariBerangkat.toString());
            }
        });
    }

    void laporan(final String pesanan, final int pilihan, final long hariBrangkat){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("Laporan");
        ab.setMessage("Nama : "+nama+"\n"+
        "No_Telpon : "+no_telp+"\n"+
        "Pesanan :\n"+pesanan+"\n"+
        "Tanggal Booking : "+timeStamp(hariBooking)+"\n"+
        "Tanggal Brangkat : "+timeStamp(new Date(hariBrangkat))+"\n"
        +"Silahkan Lakukan Pembayaran ke No. Rek ABC 1234567890");
        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                connect(pilihan, hariBooking.getTime(), hariBrangkat);
                finish();
            }
        });
        AlertDialog ad = ab.create();
        ad.show();
    }

    String[] nama_bulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
    String timeStamp(Date d)
    {
        return String.valueOf(d.getDate()) + " " + nama_bulan[d.getMonth()] + " " + String.valueOf(d.getYear()+1900);
    }

    void connect( final int id_tmp_wst, final long tgl_booking, final long tgl_berangkat){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(Helper.URL +"laporan.php?id="+id+
                                "&tujuan_wisata=" +String.valueOf(id_tmp_wst)+
                                "&booking=" +String.valueOf(tgl_booking)+
                                "&berangkat="+String.valueOf(tgl_berangkat));
                        URLConnection connection = url.openConnection();
                        connection.connect();
                        InputStream is = connection.getInputStream();
                    }catch (MalformedURLException e){}
                    catch (IOException e) {}
                }
            }).start();
    }

    void keluar(){
        finish();
    }

}
