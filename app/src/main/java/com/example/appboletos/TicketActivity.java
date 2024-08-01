package com.example.appboletos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;

public class TicketActivity extends AppCompatActivity {

    private Spinner spOrigin, spDestination;
    private DatePicker dpDate;
    private TimePicker tpTime;
    private EditText etTotal;
    private Button btnPay;

    private String[] destinations = {"Mérida", "Progreso", "Valladolid", "Tizimín", "Izamal"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        spOrigin = findViewById(R.id.spOrigin);
        spDestination = findViewById(R.id.spDestination);
        dpDate = findViewById(R.id.dpDate);
        tpTime = findViewById(R.id.tpTime);
        etTotal = findViewById(R.id.etTotal);
        btnPay = findViewById(R.id.btnPay);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, destinations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spOrigin.setAdapter(adapter);
        spDestination.setAdapter(adapter);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String origin = spOrigin.getSelectedItem().toString();
                String destination = spDestination.getSelectedItem().toString();
                int day = dpDate.getDayOfMonth();
                int month = dpDate.getMonth();
                int year = dpDate.getYear();
                String date = year + "-" + (month + 1) + "-" + day; // Format date as YYYY-MM-DD
                int hour = tpTime.getCurrentHour();
                int minute = tpTime.getCurrentMinute();
                String time = hour + ":" + minute; // Format time as HH:MM
                String total = etTotal.getText().toString();

                // Create intent to start PaymentActivity
                Intent intent = new Intent(TicketActivity.this, PaymentActivity.class);
                intent.putExtra("ORIGIN", origin);
                intent.putExtra("DESTINATION", destination);
                intent.putExtra("DATE", date);
                intent.putExtra("TIME", time);
                intent.putExtra("TOTAL", total);
                startActivity(intent);
            }
        });
    }
}
