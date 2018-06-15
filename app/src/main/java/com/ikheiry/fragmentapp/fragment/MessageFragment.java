package com.ikheiry.fragmentapp.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ikheiry.fragmentapp.R;

public class MessageFragment extends Fragment {

    private EditText editText;
    private Button button;
    private OnMessageReadListener messageReadListener;

    public MessageFragment() {
        // Required empty public constructor
    }

    public interface OnMessageReadListener{
        public void onMessageRead(String message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        editText = view.findViewById(R.id.txt_message);
        button = view.findViewById(R.id.bn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editText.getText().toString();
                messageReadListener.onMessageRead(message);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try {
            messageReadListener = (OnMessageReadListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must override onMessageRead...");
        }
    }
}
