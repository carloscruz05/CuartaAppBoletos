package com.example.appboletos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private TextView tvPaymentDetails;
    private Button btnConfirmPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tvPaymentDetails = findViewById(R.id.tvPaymentDetails);
        btnConfirmPayment = findViewById(R.id.btnConfirmPayment);

        Intent intent = getIntent();
        String origin = intent.getStringExtra("ORIGIN");
        String destination = intent.getStringExtra("DESTINATION");
        String date = intent.getStringExtra("DATE");
        String time = intent.getStringExtra("TIME");
        String total = intent.getStringExtra("TOTAL");

        String details = "Origen: " + origin + "\n" +
                "Destino: " + destination + "\n" +
                "Fecha: " + date + "\n" +
                "Hora: " + time + "\n" +
                "Total: $" + total;
        tvPaymentDetails.setText(details);

        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic to save data to the database
                DBHelper dbHelper = new DBHelper(PaymentActivity.this);
                boolean result = dbHelper.insertTicket(
                        origin, destination, date, time, Double.parseDouble(total)
                );

                if (result) {
                    // Show success message or navigate to another screen
                    Toast.makeText(PaymentActivity.this, "Pago confirmado", Toast.LENGTH_SHORT).show();
                    finish(); // Optionally finish the activity
                } else {
                    Toast.makeText(PaymentActivity.this, "Error al confirmar el pago", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
