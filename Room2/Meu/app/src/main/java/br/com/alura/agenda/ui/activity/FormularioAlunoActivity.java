package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.asynctask.BuscaTodosTelefonesDOAlunoTask;
import br.com.alura.agenda.asynctask.EditaAlunoTask;
import br.com.alura.agenda.asynctask.SalvaAlunoTask;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.model.TipoTelefone;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

     private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
     private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
     private EditText campoNome;
     private EditText campoTelefoneFixo;
     private EditText campoTelefoneCelular;
     private EditText campoEmail;
     private Aluno aluno;
     private List<Telefone> telefonesDoAluno;
     private AlunoDAO alunoDAO;
     private TelefoneDAO telefoneDAO;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_formulario_aluno);

          //Instancias
          AgendaDatabase database = AgendaDatabase.getInstance(this);//Banco
          alunoDAO = database.getAlunoDAO();
          telefoneDAO = database.getTelefoneDAO();

          inicializacaoDosCampos();
          carregaAluno();
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          getMenuInflater()
                  .inflate(R.menu.activity_formulario_aluno_menu, menu);
          return super.onCreateOptionsMenu(menu);
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
          int itemId = item.getItemId();
          if (itemId == R.id.activity_formulario_aluno_menu_salvar) {
               finalizaFormulario();
          }
          return super.onOptionsItemSelected(item);
     }

     private void carregaAluno() {
          Intent dados = getIntent();
          if (dados.hasExtra(CHAVE_ALUNO)) {
               setTitle(TITULO_APPBAR_EDITA_ALUNO);
               aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
               preencheCampos();
          } else {
               setTitle(TITULO_APPBAR_NOVO_ALUNO);
               aluno = new Aluno();
          }
     }

     private void preencheCampos() {
//          campoTelefoneFixo.setText(aluno.getTelefonefixo);
//          campoTelefoneCelular.setText(aluno.getTelefoneCelular);

          campoNome.setText(aluno.getNome());
          campoEmail.setText(aluno.getEmail());
          preencheCamposDeTelefone();
     }

     private void preencheCamposDeTelefone() {
          new BuscaTodosTelefonesDOAlunoTask(telefoneDAO, aluno, telefones -> {
               FormularioAlunoActivity.this.telefonesDoAluno = telefones;//Pega lista da async
               for (Telefone telefone :
                       telefonesDoAluno) {
                    if (telefone.getTipo() == TipoTelefone.FIXO) {
                         campoTelefoneFixo.setText(telefone.getNumero());//Seta campo telefoneFixo
                    } else {
                         campoTelefoneCelular.setText(telefone.getNumero());//Seta campo telefoneCelular
                    }
               }
          }).execute();
          //telefonesDoAluno = telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId());//Lista de telefones com id do aluno
//          for (Telefone telefone :
//                  telefonesDoAluno) {
//               if (telefone.getTipo() == TipoTelefone.FIXO) {
//                    campoTelefoneFixo.setText(telefone.getNumero());//Seta campo telefoneFixo
//               } else {
//                    campoTelefoneCelular.setText(telefone.getNumero());//Seta campo telefoneCelular
//               }
//          }
     }

     private void finalizaFormulario() {

          preencheAluno();

          //2 Objetos com numero e tipo de telefone
          Telefone telefoneFixo = criaTelefone(campoTelefoneFixo, TipoTelefone.FIXO);
          Telefone telefoneCelular = criaTelefone(campoTelefoneCelular, TipoTelefone.CELULAR);

          if (aluno.temIdValido()) {
               editaAluno(telefoneFixo, telefoneCelular);//2 objetos Telefone para vinculo com aluno
          } else {
               salvaAluno(telefoneFixo, telefoneCelular);
          }
          //finish();//Sempre finalizar os acessos aos dados na asyncTask
     }

     private Telefone criaTelefone(EditText campoTelefoneFC, TipoTelefone tipo) {//Pega dados dos campos e seta na model
          String numeroTelefone = campoTelefoneFC.getText().toString();
          return new Telefone(numeroTelefone, tipo);
     }

     private void salvaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
          //Quando concluir o salvamento no banco da o finish();
          new SalvaAlunoTask(alunoDAO, aluno,
                  telefoneFixo, telefoneCelular, telefoneDAO,
                  () -> finish()).execute();
//          int alunoId = alunoDAO.salva(aluno).intValue();//Pegando alunoId pelo id do aluno salvo
//          vinculaAlunoComTelefonePeloId(alunoId, telefoneFixo, telefoneCelular);
//          telefoneDAO.salva(telefoneFixo, telefoneCelular);
     }

     private void editaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
          new EditaAlunoTask(alunoDAO, aluno, telefoneDAO, telefoneFixo, telefoneCelular, telefonesDoAluno, () -> finish()).execute();
//          alunoDAO.edita(aluno);
//          vinculaAlunoComTelefonePeloId(aluno.getId(), telefoneFixo, telefoneCelular);//Seta idAluno
//          atualizaIdsDosTelefones(telefoneFixo, telefoneCelular);
//          telefoneDAO.atualiza(telefoneFixo, telefoneCelular);
     }

//     private void atualizaIdsDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {//Seta o id dos telefones de acordo com o banco
//          for (Telefone telefone :
//                  telefonesDoAluno) {
//               if (telefone.getTipo() == TipoTelefone.FIXO) {
//                    telefoneFixo.setId(telefone.getId());
//               } else {
//                    telefoneCelular.setId(telefone.getId());
//               }
//          }
//     }

//     private void vinculaAlunoComTelefonePeloId(int alunoId, Telefone... telefones) {//Seta o id no idAluno nos dois telefones
//          for (Telefone telefone :
//                  telefones) {
//               telefone.setAlunoId(alunoId);
//          }
//     }

     private void inicializacaoDosCampos() {
          campoNome = findViewById(R.id.activity_formulario_aluno_nome);
          campoTelefoneFixo = findViewById(R.id.activity_formulario_aluno_telefone_fixo);
          campoTelefoneCelular = findViewById(R.id.activity_formulario_aluno_telefone_celular);
          campoEmail = findViewById(R.id.activity_formulario_aluno_email);
     }

     private void preencheAluno() {//Pega dados dos campos e seta na model

          String nome = campoNome.getText().toString();
          String email = campoEmail.getText().toString();
//        String telefoneFixo = campoTelefoneFixo.getText().toString();
//        String telefoneCelular = campoTelefoneCelular.getText().toString();

          aluno.setNome(nome);
          aluno.setEmail(email);
//        aluno.setTelefoneFixo(telefoneFixo);
//        aluno.setTelefoneCelular(telefoneCelular);
     }
}