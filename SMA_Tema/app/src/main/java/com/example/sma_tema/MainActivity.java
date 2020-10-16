package com.example.sma_tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button myBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialize();
        ClickOnBTN();
    }


    public void Initialize(){
        myBtn= findViewById(R.id.start);
    }

    public void ClickOnBTN()
    {
        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent= new Intent(MainActivity.this,Dialog.class);
                startActivity(myIntent);
            }
        });
    }
}