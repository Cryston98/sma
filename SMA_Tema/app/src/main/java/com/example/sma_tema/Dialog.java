package com.example.sma_tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Dialog extends AppCompatActivity {
    Button btn_show_dialog,ok_btn,cancel_btn;
    LinearLayout linearLayout;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initialize();
        showDialog();
        ok_click();
        cancel_click();
    }
    public void initialize(){
        btn_show_dialog=findViewById(R.id.show_dialog);
        ok_btn=findViewById(R.id.ok_btn);
        cancel_btn=findViewById(R.id.cancel_btn);
        linearLayout=findViewById(R.id.dialog);
        editText=findViewById(R.id.dialogText);
    }
    public void showDialog(){
        btn_show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    public void ok_click(){
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputDialog=editText.getText().toString();
                if (inputDialog.isEmpty())
                {
                    Toast.makeText(Dialog.this, "Dialog empty", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Intent newIntent = new Intent(Dialog.this,Activity3.class);
                    newIntent.putExtra(Dictionary.dialog_key,inputDialog);
                    startActivity(newIntent);
                }
            }
        });
    }

    public void cancel_click(){
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputDialog=editText.getText().toString();
                if (inputDialog.isEmpty())
                {
                    Toast.makeText(Dialog.this, "Dialog empty", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    linearLayout.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}