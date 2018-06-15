package com.ikheiry.fragmentapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ikheiry.fragmentapp.communication.DisplayFragment;
import com.ikheiry.fragmentapp.communication.MsgFragment;
import com.ikheiry.fragmentapp.fragment.HomeFragment;
import com.ikheiry.fragmentapp.fragment.MessageFragment;

public class MainActivity extends AppCompatActivity implements MessageFragment.OnMessageReadListener, MsgFragment.OnMessageSendListener {

    public static FragmentManager fragmentManager;
    //private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textView = findViewById(R.id.txt_display_message);

        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new MsgFragment(), null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onMessageRead(String message) {
        //textView.setText(message);
    }

    @Override
    public void onMessageSend(String message) {

        DisplayFragment displayFragment = new DisplayFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        displayFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, displayFragment, null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
