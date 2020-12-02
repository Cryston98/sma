package com.example.sma_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sma_lab.AppExecutors;
import com.example.sma_lab.R;
import com.example.sma_lab.TestDatabase;
import com.example.sma_lab.TestEntity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
public class Lab3Main extends AppCompatActivity {

    private TestDatabase testDatabase;
    private List<TestEntity> testEntityList;
    private String name;
    Button savePref,readPref,saveFile,readFile,saveSQL,readSQL;
    EditText inputID;

    String MY_PREFS_NAME="dbPref";
    String KEY_NAME="cheiePref";
    String MyFILE="myfile.txt";
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_main);
        testDatabase = TestDatabase.getInstance(Lab3Main.this);
        testEntityList = new ArrayList<>();
        initialize();
        savePreference();
        readPreference();
        saveFile();
        readFile();
        saveSQL();
        readSQL();

    }

    void initialize(){
        savePref=findViewById(R.id.savePref);
        saveFile=findViewById(R.id.saveFile);
        saveSQL=findViewById(R.id.saveSQL);

        readPref=findViewById(R.id.readPref);
        readFile=findViewById(R.id.readFile);
        readSQL=findViewById(R.id.readSQL);

        inputID=findViewById(R.id.inputID);
        result=findViewById(R.id.resultOpt);

    }
    void savePreference(){
        savePref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringToSave=inputID.getText().toString();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString(KEY_NAME, stringToSave);
                editor.apply();
                String curent=result.getText().toString();
                result.setText(curent+"\n +  Data saved in SharedPreferences!");
                //Toast.makeText(Lab3Main.this,"Date salvate in shared preference : " +stringToSave,Toast.LENGTH_SHORT).show();
            }
        });
    }
    void readPreference(){
        readPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String textSher = prefs.getString(KEY_NAME, "No defined");
                String curent=result.getText().toString();
                result.setText(curent+"\n +  Data read: "+textSher);
                //Toast.makeText(Lab3Main.this,"Date citite : "+textSher,Toast.LENGTH_SHORT).show();
            }
        });

    }
    void saveFile(){
        saveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String stringToSave=inputID.getText().toString();
                    OutputStreamWriter outputStreamWriter=new OutputStreamWriter(Lab3Main.this.openFileOutput(MyFILE, Context.MODE_PRIVATE));
                    outputStreamWriter.write(stringToSave);
                    outputStreamWriter.close();
                    String curent=result.getText().toString();
                    result.setText(curent+"\n +  Data saved in : "+ MyFILE);
                    //Toast.makeText(Lab3Main.this,"Date salvate!",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    String curent=result.getText().toString();
                    result.setText(curent+"\n +  Error when save file!");
                   // Toast.makeText(Lab3Main.this,"Eroare la salvare!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void readFile(){
        readFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stringFromFile="";
                try{
                    InputStream inputStream = Lab3Main.this.openFileInput(MyFILE);
                    if(inputStream!=null)
                    {
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String receiveString="";
                        StringBuilder stringBuilder= new StringBuilder();
                        while((receiveString=bufferedReader.readLine())!=null){
                            stringBuilder.append(receiveString);
                        }
                        inputStream.close();
                        stringFromFile=stringBuilder.toString();

                        String curent=result.getText().toString();
                        result.setText(curent+"\n +  Data din fisierul '"+ MyFILE+"' : " +stringFromFile);
                        //Toast.makeText(Lab3Main.this,"Date din fisier : " + stringFromFile,Toast.LENGTH_SHORT).show();
                    }

                } catch (FileNotFoundException e) {
                    String curent=result.getText().toString();
                    result.setText(curent+"\n +  Error : Fisierul '"+ MyFILE+"' nu se poate gasi !");
                } catch (IOException e) {
                    Toast.makeText(Lab3Main.this,"Error : Nu se poate citi fisierul!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void saveSQL(){

        saveSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TextInputEditText inputEditText;
                TextInputLayout textInputLayout;
                AlertDialog.Builder alert = new AlertDialog.Builder(Lab3Main.this);
                new AlertDialog.Builder(Lab3Main.this, R.style.InputDialogTheme);
                View viewInflated = LayoutInflater.from(Lab3Main.this).inflate(R.layout.view_input_dialog, (ViewGroup) findViewById(R.id.et_input_dialog) , false);
                inputEditText = viewInflated.findViewById(R.id.et_input_dialog);
                textInputLayout = viewInflated.findViewById(R.id.til_input_dialog);
                alert.setView(viewInflated);
                alert.setTitle("Add some value in the database");
                textInputLayout.setHint("Value");
                alert.setPositiveButton("Add value", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (inputEditText.getText() == null || inputEditText.getText().toString().isEmpty())
                        {
                            return;
                        }
                        name = inputEditText.getText().toString();
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                insertToDatabase(name);
                            }
                        });
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();
            }
        });
    }
    void readSQL(){
        readSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                class GetValue extends AsyncTask<Void, Void, TestEntity> {

                    @Override
                    protected TestEntity doInBackground(Void... voids) {
                        TestEntity testEntity = new TestEntity(name, "Ana");
                        testEntityList = testDatabase.testDAO().getAll();
                        //Toast.makeText(Lab3Main.this,"Date SQL: "+testEntity,Toast.LENGTH_SHORT).show();
                        String curent=result.getText().toString();
                        result.setText(curent+"\n +  Data from SQL -> name : "+testEntity.getId());
                        return testEntity;
                    }

                    @Override
                    protected void onPostExecute(TestEntity testEntity) {
                        super.onPostExecute(testEntity);
                    }
                }
                GetValue insertTask = new GetValue();
                insertTask.execute();
            }
        });
    }


    private void insertToDatabase(final String name) {
        class InsertValue extends AsyncTask<Void, Void, TestEntity> {

            @Override
            protected TestEntity doInBackground(Void... voids) {
                TestEntity testEntity = new TestEntity(name, "Ana");
                testDatabase.testDAO().insertAll(testEntity);

                String curent=result.getText().toString();
                result.setText(curent+"\n +  Data saved SQL ! ");

                //Toast.makeText(Lab3Main.this,"Date SQL salvate : "+testEntity,Toast.LENGTH_SHORT).show();

                return testEntity;
            }

            @Override
            protected void onPostExecute(TestEntity testEntity) {
                super.onPostExecute(testEntity);
            }
        }

        InsertValue insertTask = new InsertValue();
        insertTask.execute();
    }






}