package br.com.alura.agenda;

import android.app.Application;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class AgendaApplication extends Application {

     @Override
     public void onCreate() {
          super.onCreate();
          criaAlunosTeste();
     }

     private void criaAlunosTeste() {
          AlunoDAO alunoDAO = new AlunoDAO();
          alunoDAO.salva(new Aluno("Thiago", "+55 (88) 9 - 9776-7854","thiago@gmail.com"));
          alunoDAO.salva(new Aluno("Bruno", "+55 (88) 9 - 8802-5502","bruno@gmail.com"));
     }
}
