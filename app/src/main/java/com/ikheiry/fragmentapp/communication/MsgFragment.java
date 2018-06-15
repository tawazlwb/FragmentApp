package com.ikheiry.fragmentapp.communication;


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

public class MsgFragment extends Fragment {

    private EditText editText;
    private Button button;
    OnMessageSendListener messageSendListener;

    public interface OnMessageSendListener{
        public void onMessageSend(String message);
    }

    public MsgFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg, container, false);

        editText = view.findViewById(R.id.txt_message);
        button = view.findViewById(R.id.bn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editText.getText().toString();
                messageSendListener.onMessageSend(message);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try {
            messageSendListener = (OnMessageSendListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must override onMessageSend...");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        editText.setText("");
    }
}
