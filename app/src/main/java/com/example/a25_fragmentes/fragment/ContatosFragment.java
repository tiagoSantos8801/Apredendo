package com.example.a25_fragmentes.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a25_fragmentes.R;


public class ContatosFragment extends Fragment {

     TextView textContato;

     public ContatosFragment() {

     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          View view = inflater.inflate(R.layout.fragment_contatos, container, false);

          textContato = view.findViewById(R.id.textContato);
          textContato.setText("Contato Modificado");

          return view;
     }
}