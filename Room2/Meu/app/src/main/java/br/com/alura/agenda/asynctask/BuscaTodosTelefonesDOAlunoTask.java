package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class BuscaTodosTelefonesDOAlunoTask extends AsyncTask<Void, Void, List<Telefone>> {

     private final TelefoneDAO telefoneDAO;
     private final Aluno aluno;
     private final TelefonesDoAlunoEncontradoListner listner;

     public BuscaTodosTelefonesDOAlunoTask(TelefoneDAO telefoneDAO, Aluno aluno, TelefonesDoAlunoEncontradoListner listner) {
          this.telefoneDAO = telefoneDAO;
          this.aluno = aluno;
          this.listner = listner;
     }

     @Override
     protected List<Telefone> doInBackground(Void... voids) {
          return telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId());
     }

     @Override
     protected void onPostExecute(List<Telefone> telefones) {
          super.onPostExecute(telefones);
          listner.quandoEncontrados(telefones);
     }

     public interface TelefonesDoAlunoEncontradoListner{
          void quandoEncontrados(List<Telefone> telefones);
     }

}
