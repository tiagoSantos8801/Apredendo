package com.example.a25_fragmentes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a25_fragmentes.R;

public class MainActivity extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

          //Removendo sombra da ActionBar
          getSupportActionBar().setElevation(0);

     }
}