package com.example.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    private CustomReceiver mReceiver = new CustomReceiver();
    private static final String RANDOM_NUMBER = "New Random Number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        this.registerReceiver(mReceiver, intentFilter);

        //Register the receiver using the activity context.
        this.registerReceiver(mReceiver, filter);

        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,
                new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    @Override
    protected void onDestroy(){
        //Unregister the receiver
        this.unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        int num = randomNumber();
        customBroadcastIntent.putExtra(RANDOM_NUMBER, String.valueOf(num));
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }

    public int randomNumber(){
        Random random = new Random();
        return random.nextInt(20);
    }
}
