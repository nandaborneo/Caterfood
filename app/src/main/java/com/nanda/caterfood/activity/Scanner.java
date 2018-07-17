package com.nanda.caterfood.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.nanda.caterfood.R;
import com.nanda.caterfood.konfigurasi.Config;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private boolean scanned = false;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        scanned = sharedPreferences.getBoolean(Config.SCANNED_SHARED_PREF, false);

        //If we will get true
        if(scanned){
            //We will start the Profile Activity
            Intent intent = new Intent(Scanner.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(final Result rawResult) {
        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString());
        dialog = new AlertDialog.Builder(Scanner.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.scanner_dialog, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Nomor Meja");
        dialog.setCancelable(false);
        TextView result = dialogView.findViewById(R.id.result);
        result.setText(rawResult.getText());
        mScannerView.stopCamera();


        dialog.setPositiveButton("Sumbit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                SharedPreferences sharedPreferences = Scanner.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean(Config.SCANNED_SHARED_PREF,true);
                editor.putString(Config.SCANNED_DATA_PREF,rawResult.getText());

                editor.commit();

                Intent intent = new Intent(Scanner.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(Scanner.this, "You Looks Hungry Order Now", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mScannerView.startCamera();
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.GRAY);
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.BLACK);
        mScannerView.resumeCameraPreview(this);
    }
}
