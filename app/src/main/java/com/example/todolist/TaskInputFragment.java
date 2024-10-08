package com.example.todolist;

// File: TaskInputFragment.java


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TaskInputFragment extends Fragment {

    private EditText editTextTaskName;
    private EditText editTextTaskDescription;
    private Button buttonSave;

    private TaskInputListener listener;

    // Interface to communicate with MainActivity
    public interface TaskInputListener {
        void onTaskSaved(Task task);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TaskInputListener) {
            listener = (TaskInputListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement TaskInputListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        editTextTaskName = view.findViewById(R.id.editTextTaskName);
        editTextTaskDescription = view.findViewById(R.id.editTextTaskDescription);
        buttonSave = view.findViewById(R.id.buttonSave);

        // Save Button Click Listener
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask(){
        String taskName = editTextTaskName.getText().toString().trim();
        String taskDescription = editTextTaskDescription.getText().toString().trim();

        if (TextUtils.isEmpty(taskName)){
            Toast.makeText(getActivity(), "Please enter a task name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Task object
        Task newTask = new Task(taskName, taskDescription);

        // Pass the task back to MainActivity
        listener.onTaskSaved(newTask);
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }
}

