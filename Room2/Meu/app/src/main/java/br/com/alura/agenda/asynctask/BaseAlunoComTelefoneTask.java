package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.model.Telefone;

abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {

     private final FinalizadaListner listner;

     BaseAlunoComTelefoneTask(FinalizadaListner listner) {
          this.listner = listner;
     }

     void vinculaAlunoComTelefonePeloId(int alunoId, Telefone... telefones) {//Seta o id no idAluno nos dois telefones
          for (Telefone telefone :
                  telefones) {
               telefone.setAlunoId(alunoId);
          }
     }

     @Override
     protected void onPostExecute(Void aVoid) {
          super.onPostExecute(aVoid);
          listner.quandoFinalizada();
     }

     public interface FinalizadaListner {
          void quandoFinalizada();
     }
}
