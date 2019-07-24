package com.fikri.tourismapp.activity.offline;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.fikri.tourismapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TransaksiOfflineActivity extends AppCompatActivity {
    Button buttonSubmit;
    ImageButton buttonDate;
    EditText edtName, edtEmail, edtDesctination,edtTanggal;
    SimpleDateFormat dateFormat;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_offline);
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        edtName = findViewById(R.id.et_name_customer);
        edtEmail = findViewById(R.id.et_email);
        buttonSubmit = findViewById(R.id.btn_submit_transaction);
        edtDesctination = findViewById(R.id.et_destination);
        edtTanggal = findViewById(R.id.et_Tanggal);
        buttonDate = findViewById(R.id.btn_input_date_transoff);
        final String destination = getIntent().getStringExtra("destination");
        edtDesctination.setText(destination);
        edtDesctination.setEnabled(false);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSubmit = new Intent(TransaksiOfflineActivity.this, DetailTransaksiOfflineActivity.class);
                intentSubmit.putExtra("name", edtName.getText().toString());
                intentSubmit.putExtra("email", edtEmail.getText().toString());
                intentSubmit.putExtra("destination", destination);
                intentSubmit.putExtra("date",edtTanggal.getText().toString());
                startActivity(intentSubmit);
            }
        });

    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                edtTanggal.setText(dateFormat.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
