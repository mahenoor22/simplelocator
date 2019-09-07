package com.example.personal.newstart;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentCry extends Fragment {

    Button Woman , Police ,Man ;
    MediaPlayer woman_scream , man_scream,police_siren;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cry , container , false);

        Police = view.findViewById(R.id.police);
        Woman = view.findViewById(R.id.woman);
        Man = view.findViewById(R.id.man);
        woman_scream = MediaPlayer.create(getContext() ,R.raw.woman);
        police_siren = MediaPlayer.create(getContext() , R.raw.police);


        Police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                police_siren.start();
            }
        });

        Woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                woman_scream.start();
            }
        });

        Man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view ;
    }

    @Override
    public void onPause() {
        super.onPause();
        police_siren.pause();
        woman_scream.pause();

    }
}
