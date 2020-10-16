package com.example.sma_tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {
    Button myBtn3;
    TextView dgTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Initialize();
        ClickOnBTN3();
    }

    public void Initialize(){
        myBtn3= findViewById(R.id.start3);
        dgTest=findViewById(R.id.scrapDig);
    }

    public void ClickOnBTN3()
    {
        myBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = getIntent();
                String mess = myIntent.getStringExtra(Dictionary.dialog_key);
                dgTest.setText(mess);
                dgTest.setVisibility(View.VISIBLE);
            }
        });
    }
}