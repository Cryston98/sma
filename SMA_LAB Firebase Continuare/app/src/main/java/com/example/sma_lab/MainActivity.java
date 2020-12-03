package com.example.sma_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.sma_lab.Firebase.FirebaseLab;

public class MainActivity extends AppCompatActivity {
    LinearLayout l1,l2,l3,l4,l5,l6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        click3();
        click2();
        click4();
        click5();
    }

    void initialize(){
        l1=findViewById(R.id.linear1);
        l2=findViewById(R.id.linear2);
        l3=findViewById(R.id.linear3);
        l4=findViewById(R.id.linear4);
        l5=findViewById(R.id.linear5);
        l6=findViewById(R.id.linear6);
    }

    public void click3(){
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Lab3Main.class));
            }
        });
    }

    public void click2(){
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,dataList.class));
            }
        });
    }

    public void click4(){
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivitityLab5.class));
            }
        });
    }

    public void click5(){
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirebaseLab.class));
            }
        });
    }
}