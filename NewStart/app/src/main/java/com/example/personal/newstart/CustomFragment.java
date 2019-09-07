package com.example.personal.newstart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomFragment extends Fragment {

    EditText message;
    Button change ;
    public static String Message = "SAVE ME. I might be in distress. If I don't pick your call then I might be in a real trouble.";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom , container , false);

        message = view.findViewById(R.id.message1);
        Message = message.getText().toString();
        change = view.findViewById(R.id.Change);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message = message.getText().toString();
                Toast.makeText(getContext() , "Message Changed Successfully",Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }
}
