package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask<Void, Void, Telefone> {

     //private final TextView campoTelefone;
     private final TelefoneDAO dao;
     private final int idAluno;
     private final PrimeiroTelefoneEncontradoListner listner;

     public BuscaPrimeiroTelefoneDoAlunoTask(//TextView campoTelefone,
                                             TelefoneDAO dao,
                                             int idAluno,
                                             PrimeiroTelefoneEncontradoListner listner) {//QUem chamar o construtor implementa a interface
          //this.campoTelefone = campoTelefone;
          this.dao = dao;
          this.idAluno = idAluno;
          this.listner = listner;
     }

     @Override
     protected Telefone doInBackground(Void... voids) {
          return dao.buscarPrimeiroTelefoneDoAluno(idAluno);
     }

     @Override
     protected void onPostExecute(Telefone primeiroTelefone) {
          super.onPostExecute(primeiroTelefone);
          listner.quandoEncontrado(primeiroTelefone);//Esse listner mostra o modelo de implementacao da interface
//          if (primeiroTelefone != null)
//               campoTelefone.setText(primeiroTelefone.getNumero());
     }

     public interface PrimeiroTelefoneEncontradoListner {//Inner Interface

          void quandoEncontrado(Telefone telefoneEncontrado);
     }
}
