package com.example.a25_fragmentes.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a25_fragmentes.R;
import com.example.a25_fragmentes.fragment.ContatosFragment;
import com.example.a25_fragmentes.fragment.ConversasFragment;

public class MainActivity extends AppCompatActivity {

     private Button buttonConversa, buttonContato;
     private ConversasFragment conversasFragment;
     private ContatosFragment contatosFragment;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

          buttonConversa = findViewById(R.id.buttonConversa);
          buttonContato = findViewById(R.id.buttonContato);

          //Removendo sombra da ActionBar
          getSupportActionBar().setElevation(0);

          //Instanciando a classe do fagmento
          conversasFragment = new ConversasFragment();

          //Configurando obj para o fragmento - gerenciador de fragmentos() - serve para configurar um fragmento()
          FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
          transaction.replace(R.id.frameConteudo, conversasFragment );//Adionando um fragmento(local de exibicao, frament)
          transaction.commit();

          buttonConversa.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                    conversasFragment = new ConversasFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameConteudo, conversasFragment);
                    transaction.commit();
               }
          });

          buttonContato.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                    contatosFragment = new ContatosFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameConteudo, contatosFragment);
                    transaction.commit();
               }
          });

     }
}