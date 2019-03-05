package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {


    private TextView mText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.fragment, null);

        Bundle bundle = getArguments();

        mText = view.findViewById(R.id.text);

       // mText.setBackgroundColor(bundle.getInt("color", Color.GREEN));
        mText.setText(bundle.getString("name") + "");

        return view;
    }
}
