package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.ListaAlunosView;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

     private static final String TITULO_APPBAR = "Lista de alunos";
     private ListaAlunosView listaAlunosView;

     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_lista_alunos);

          setTitle(TITULO_APPBAR);
          listaAlunosView = new ListaAlunosView(this);//Somente pega-se o context apos criacao da activity

          configuraFabNovoAluno();
          configuraLista();
     }

     @Override//Infla menu de contexto
     public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
          super.onCreateContextMenu(menu, v, menuInfo);
          getMenuInflater()
                  .inflate(R.menu.activity_lista_alunos_menu, menu);
     }

     @Override//Checa se o item de menu clicado foi igual ao id do item de menu em quetao
     public boolean onContextItemSelected(MenuItem item) {

          int itemId = item.getItemId();
          if (itemId == R.id.activity_lista_alunos_menu_remover) {
               listaAlunosView.confirmaRemocao(item);
          }
          return super.onContextItemSelected(item);
     }

     private void configuraFabNovoAluno() {//Click no botao fab
          FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
          botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    ListaAlunosActivity.this.abreFormularioModoInsereAluno();
               }
          });
     }

     private void abreFormularioModoInsereAluno() {//Abre formularioActivity
          startActivity(new Intent(this, FormularioAlunoActivity.class));
     }

     @Override
     protected void onResume() {//Quando a activity sai de onPause
          super.onResume();
          listaAlunosView.atualizaAlunos();
     }

     private void configuraLista() {
          ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);//Inicializa ListView
          listaAlunosView.configuraAdapter(listaDeAlunos);//Seta adapter que esta em ListaAlunosView
          configuraListenerDeCliquePorItem(listaDeAlunos);
          registerForContextMenu(listaDeAlunos);//Vincula o menu de contexto a esta lista
     }

     private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {//Configurando click na lista(Editar elemento)
          listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                    Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);//Pega item de acordo com a posicao
                    ListaAlunosActivity.this.abreFormularioModoEditaAluno(alunoEscolhido);//Abre FormularioAlunoActivity
               }
          });
     }

     private void abreFormularioModoEditaAluno(Aluno aluno) {//Abre formulario passando elemento da lista selecionado
          Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
          vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
          startActivity(vaiParaFormularioActivity);
     }

}
