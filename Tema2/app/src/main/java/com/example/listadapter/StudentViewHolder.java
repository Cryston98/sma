package com.example.listadapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    private TextView numeTV;
    private TextView prenumeTV;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        initialize();
    }

    public void initialize()
    {
        numeTV=itemView.findViewById(R.id.nume);
        prenumeTV=itemView.findViewById(R.id.prenume);
    }

    public void setValue(String nume,String prenume)
    {
        this.numeTV.setText(nume);
        this.prenumeTV.setText(prenume);
    }

}
