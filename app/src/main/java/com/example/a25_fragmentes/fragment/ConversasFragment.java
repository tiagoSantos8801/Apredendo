package com.example.a25_fragmentes.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a25_fragmentes.R;


public class ConversasFragment extends Fragment {

     TextView textConversas;

     public ConversasFragment() {

     }

     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          // Inflate the layout for this fragment
          View view = inflater.inflate(R.layout.fragment_conversas, container, false);

          textConversas = view.findViewById(R.id.textConversas);
          textConversas.setText("Conversas Modificado");

          return view;
     }
}