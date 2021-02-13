package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.dataBase.AgendaDataBase;
import br.com.alura.agenda.dao.dataBase.dao.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

     private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
     private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
     private EditText campoNome;
     //private EditText campoSobrenome;
     private EditText campoTelefone;
     private EditText campoEmail;
     //private final AlunoDAO dao = new AlunoDAO();
     private RoomAlunoDAO dao;
     private Aluno aluno;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_formulario_aluno);

          inicializacaoDosCampos();
          carregaAluno();
     }

     @Override//Inflando menu
     public boolean onCreateOptionsMenu(Menu menu) {
          getMenuInflater()
                  .inflate(R.menu.activity_formulario_aluno_menu, menu);
          return super.onCreateOptionsMenu(menu);
     }

     @Override//Quando clica no item de menu
     public boolean onOptionsItemSelected(MenuItem item) {
          int itemId = item.getItemId();
          if (itemId == R.id.activity_formulario_aluno_menu_salvar) {//Checa id clicado
               finalizaFormulario();
          }
          return super.onOptionsItemSelected(item);
     }

     //Recebendo dados via extra
     private void carregaAluno() {//Edita aluno
          Intent dados = getIntent();
          if (dados.hasExtra(CHAVE_ALUNO)) {
               setTitle(TITULO_APPBAR_EDITA_ALUNO);
               aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
               preencheCampos();//Editando dados passados via extra
          } else {//Cria aluno
               setTitle(TITULO_APPBAR_NOVO_ALUNO);
               aluno = new Aluno();
          }
     }

     //Pega o objeto recebido via extra e seta nos capos
     private void preencheCampos() {
          campoNome.setText(aluno.getNome());
          campoTelefone.setText(aluno.getTelefone());
          campoEmail.setText(aluno.getEmail());
     }

     //Salvando aluno editado ou cria novo aluno
     private void finalizaFormulario() {
          preencheAluno();
          if (aluno.temIdValido()) {
               dao.edita(aluno);
          } else {
               dao.salva(aluno);
          }
          finish();
     }

     private void inicializacaoDosCampos() {
          campoNome = findViewById(R.id.activity_formulario_aluno_nome);
          //campoSobrenome = findViewById(R.id.activity_formulario_aluno_sobrenome);
          campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
          campoEmail = findViewById(R.id.activity_formulario_aluno_email);

          AgendaDataBase dataBase = AgendaDataBase.getInstance(this);
          dao = dataBase.getRoomAlunoDAO();
     }

     //Pegando dados modificados nos campos e setando no obj recebido
     private void preencheAluno() {
          String nome = campoNome.getText().toString();
          //String sobrenome = campoSobrenome.getText().toString();
          String telefone = campoTelefone.getText().toString();
          String email = campoEmail.getText().toString();

          aluno.setNome(nome);
          //aluno.setSobrenome(sobrenome);
          aluno.setTelefone(telefone);
          aluno.setEmail(email);
     }
}
