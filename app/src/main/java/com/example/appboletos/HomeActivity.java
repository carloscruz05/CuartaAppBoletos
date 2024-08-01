package com.example.appboletos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnSellTicket, btnConsultTicket, btnCancelTicket, btnChangeTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnSellTicket = findViewById(R.id.btnSellTicket);
        btnConsultTicket = findViewById(R.id.btnConsultTicket);
        btnCancelTicket = findViewById(R.id.btnCancelTicket);
        btnChangeTicket = findViewById(R.id.btnChangeTicket);

        String username = getIntent().getStringExtra("USERNAME");
        tvWelcome.setText("Hola, Bienvenido " + username);

        btnSellTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, TicketActivity.class));
            }
        });

        // Similar listeners for other buttons
    }
}
