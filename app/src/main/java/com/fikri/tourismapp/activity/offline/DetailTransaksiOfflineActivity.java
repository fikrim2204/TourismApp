package com.fikri.tourismapp.activity.offline;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fikri.tourismapp.R;

public class DetailTransaksiOfflineActivity extends AppCompatActivity {
TextView txtName,txtEmail,txtDate,txtDest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi_offline);
        txtName=findViewById(R.id.txt_name_content_transaction_off);
        txtEmail=findViewById(R.id.txt_email_content_transaction_off);
        txtDest=findViewById(R.id.txt_dest_content_transaction_off);
        txtDate=findViewById(R.id.txt_date_content_transaction_off);

        String name,email,date,dest;

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        date = getIntent().getStringExtra("date");
        dest= getIntent().getStringExtra("destination");

        System.out.println(name);
        System.out.println(email);
        System.out.println(date);
        System.out.println(dest);

        txtName.setText(name);
        txtEmail.setText(email);
        txtDest.setText(dest);
        txtDate.setText(date);
    }
}
