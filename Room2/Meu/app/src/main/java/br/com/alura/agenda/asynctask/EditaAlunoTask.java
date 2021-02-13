package br.com.alura.agenda.asynctask;

import java.util.List;

import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.model.TipoTelefone;

public class EditaAlunoTask extends BaseAlunoComTelefoneTask {
     private final AlunoDAO alunoDAO;
     private final Aluno aluno;
     private final TelefoneDAO telefoneDAO;
     private final Telefone telefoneFixo;
     private final Telefone telefoneCelular;
     private final List<Telefone> telefonesDoAluno;
//     private final AlunoEditadoListner listner;

     public EditaAlunoTask(AlunoDAO alunoDAO,
                           Aluno aluno,
                           TelefoneDAO telefoneDAO,
                           Telefone telefoneFixo,
                           Telefone telefoneCelular,
                           List<Telefone> telefonesDoAluno,
                           FinalizadaListner listner
             /*AlunoEditadoListner listner*/) {
          super(listner);
          this.alunoDAO = alunoDAO;
          this.aluno = aluno;
          this.telefoneDAO = telefoneDAO;
          this.telefoneFixo = telefoneFixo;
          this.telefoneCelular = telefoneCelular;
          this.telefonesDoAluno = telefonesDoAluno;
//          this.listner = listner1;
     }

     @Override
     protected Void doInBackground(Void... voids) {
          alunoDAO.edita(aluno);
          vinculaAlunoComTelefonePeloId(aluno.getId(), telefoneFixo, telefoneCelular);//Seta idAluno
          atualizaIdsDosTelefones(telefoneFixo, telefoneCelular);
          telefoneDAO.atualiza(telefoneFixo, telefoneCelular);
          return null;
     }

//     @Override
//     protected void onPostExecute(Void aVoid) {
//          super.onPostExecute(aVoid);
//          listner.quandoEditadoListner();
//     }

//     private void vinculaAlunoComTelefonePeloId(int alunoId, Telefone... telefones) {//Seta o id no idAluno nos dois telefones
//          for (Telefone telefone :
//                  telefones) {
//               telefone.setAlunoId(alunoId);
//          }
//     }

     private void atualizaIdsDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {//Seta o id dos telefones de acordo com o banco
          for (Telefone telefone :
                  telefonesDoAluno) {
               if (telefone.getTipo() == TipoTelefone.FIXO) {
                    telefoneFixo.setId(telefone.getId());
               } else {
                    telefoneCelular.setId(telefone.getId());
               }
          }
     }

//     public interface AlunoEditadoListner {
//          void quandoEditadoListner();
//     }
}
