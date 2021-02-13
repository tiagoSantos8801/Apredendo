package br.com.alura.agenda.asynctask;

import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class SalvaAlunoTask extends BaseAlunoComTelefoneTask {

     private final AlunoDAO alunoDAO;
     private final Aluno aluno;
     private final Telefone telefoneFixo;
     private final Telefone telefoneCelular;
     private final TelefoneDAO telefoneDAO;
//     private final QuandoAlunoSalvoListner listner;

     public SalvaAlunoTask(AlunoDAO alunoDAO,
                           Aluno aluno,
                           Telefone telefoneFixo,
                           Telefone telefoneCelular,
                           TelefoneDAO telefoneDAO,
                           FinalizadaListner listner) {
          super(listner);
          this.alunoDAO = alunoDAO;
          this.aluno = aluno;
          this.telefoneFixo = telefoneFixo;
          this.telefoneCelular = telefoneCelular;
          this.telefoneDAO = telefoneDAO;
//          this.listner = listner;
     }

     @Override
     protected Void doInBackground(Void... voids) {

          int alunoId = alunoDAO.salva(aluno).intValue();//Pegando alunoId pelo id do aluno salvo
          vinculaAlunoComTelefonePeloId(alunoId, telefoneFixo, telefoneCelular);
          telefoneDAO.salva(telefoneFixo, telefoneCelular);
          return null;
     }

//     @Override
//     protected void onPostExecute(Void aVoid) {
//          super.onPostExecute(aVoid);
//          listner.quandoSalvo();//Executa depois da busca
//     }

//     private void vinculaAlunoComTelefonePeloId(int alunoId, Telefone... telefones) {//Seta o id no idAluno nos dois telefones
//          for (Telefone telefone :
//                  telefones) {
//               telefone.setAlunoId(alunoId);
//          }
//     }

//     public interface QuandoAlunoSalvoListner{
//          void quandoSalvo();
//     }
}
