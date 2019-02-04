package com.saket.sampleaidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.saket.sampleaidl.IMathAidlInterface;

public class MainActivity extends AppCompatActivity {

    IMathAidlInterface mIMathAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(view -> {
            //Connect to remote MathService
            initConnection();
        });
    }

    /* THIS DOES NOT WORK: You must Implement ServiceConnection class not instantiate it.
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mIMathAidlInterface = IMathAidlInterface.Stub.asInterface(iBinder);
            try {
                int response = mIMathAidlInterface.addNumbers(5,6);
                Log.v("TAG", "Response: " + response);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
                Log.v("TAG", "" + componentName.flattenToShortString());
        }
    };
    */

    //callback method that listens to service connected state
    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mIMathAidlInterface = IMathAidlInterface.Stub.asInterface(iBinder);
            try {
                int response = mIMathAidlInterface.addNumbers(5,6);
                Log.v("TAG", "Response: " + response);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v("TAG", "" + componentName.flattenToShortString());
        }
    }


    private void initConnection() {
        //Intent inMathService = new Intent(IMathAidlInterface.class.getName());
        Intent inMathService = new Intent();
        inMathService.setAction("com.saket.sampleaidl");
        //From 5.0 annonymous intent calls are suspended so replacing with server app's package name
        inMathService.setPackage("com.saket.sampleaidl");
        if (bindService(inMathService, new MyServiceConnection(), Context.BIND_AUTO_CREATE)) {
            //
            Log.v("TAG", "Connected");
        }else {
            Log.v("TAG", "Not connected");
        }
    }
}
