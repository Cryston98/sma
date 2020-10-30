package com.example.sma_lab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class dataList extends AppCompatActivity {
    private Button btn_add,btn_del,btn_confirm,btn_exit;
    private RecyclerView stud;
    LinearLayout addData;
    private StudentAdapter studentAdapter;
    private List<StudentModel> stutModelList;
    EditText nume,prenume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        initializeView();
        initializeList();
        setRecycleView();
        onClickAdd();
        onClickDel();
        confirmAdd();
        exitAdd();
    }

    public void initializeList(){
        stutModelList=new ArrayList<>();
        stutModelList.add(new StudentModel("Alex","Pop"));
        stutModelList.add(new StudentModel("Alin","Pop"));
        stutModelList.add(new StudentModel("Maria","Pop"));

    }
    private void setRecycleView()
    {
        studentAdapter= new StudentAdapter(stutModelList);
        stud.setLayoutManager(new LinearLayoutManager(this));
        stud.setAdapter(studentAdapter);
    }

    private  void initializeView(){
        stud= findViewById(R.id.test_list);
        btn_add =findViewById(R.id.add_btn);
        btn_del =findViewById(R.id.del_btn);
        addData=findViewById(R.id.menuAdd);
        nume=findViewById(R.id.nume);
        prenume=findViewById(R.id.prenume);
        btn_confirm=findViewById(R.id.confirm_add);
        btn_exit=findViewById(R.id.exit_add);

    }

    public  void onClickAdd(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               addData.setVisibility(View.VISIBLE);
                //onCreateDialog();
            }
        });
    }

    public  void confirmAdd(){
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nume.getText().toString().matches("") || prenume.getText().toString().matches("")) {
                    Toast.makeText(dataList.this,"Error: Empty field!",Toast.LENGTH_SHORT).show();
                }else{
                    stutModelList.add(new StudentModel(nume.getText().toString(),prenume.getText().toString()));
                    nume.setText("");
                    prenume.setText("");
                    addData.setVisibility(View.GONE);
                    setRecycleView();
                }
            }
        });
    }
    public  void exitAdd(){
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nume.setText("");
                prenume.setText("");
                addData.setVisibility(View.GONE);
            }
        });
    }

    public  void onClickDel(){
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    stutModelList.remove(0);
                }catch (Exception e) {
                    Toast.makeText(dataList.this,"Array Empty",Toast.LENGTH_SHORT).show();
                    }
                setRecycleView();
            }
        });
    }
}