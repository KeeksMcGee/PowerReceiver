package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    private static final String RANDOM_NUMBER = "New Random Number";

    @Override
    public void onReceive(Context context, Intent intent) {
       String intentAction = intent.getAction();
       String numString = intent.getStringExtra(RANDOM_NUMBER);
       int number = Integer.valueOf(numString);
       if(intentAction !=null){
           String toastMessage = context.getString(R.string.unknown_action);
           switch(intentAction){
               case Intent.ACTION_POWER_CONNECTED:
                   toastMessage = context.getString(R.string.power_connected);
                   break;
               case Intent.ACTION_POWER_DISCONNECTED:
                   toastMessage = context.getString(R.string.power_disconnected);
                   break;
               case ACTION_CUSTOM_BROADCAST:
                   toastMessage = "Custom Broadcast Received";
                   break;
               case Intent.ACTION_HEADSET_PLUG:
                   toastMessage = "Headset plugged in";
           }
           //Display the toast.
           Toast.makeText(context, toastMessage + "\nSquare of the Random Number is: " + number * number, Toast.LENGTH_SHORT).show();
       }
    }
}
