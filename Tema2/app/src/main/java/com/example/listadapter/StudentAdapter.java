package com.example.listadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {
    private List<StudentModel> studentModelList;

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_student,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentModel studModel= studentModelList.get(position);
        holder.setValue(studModel.getNume(),studModel.getPrenume());
    }

    public StudentAdapter(List<StudentModel> studentModelsList)
    {
        this.studentModelList=studentModelsList;
    }

    @Override
    public int getItemCount() {
        return studentModelList.size();
    }
}
