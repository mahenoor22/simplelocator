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
import android.widget.TextView;

import java.util.zip.Inflater;

public class SettingsFragment extends Fragment {

    EditText Time;
    String value ;
    public static int set_time = 30;
    Button Change_button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings , container , false);

        Time =  view.findViewById(R.id.time);

        Change_button = view.findViewById(R.id.change_time);

        Change_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               value=Time.getText().toString();
               set_time =Integer.parseInt(value);
            }
        });

        return view;
    }
}
