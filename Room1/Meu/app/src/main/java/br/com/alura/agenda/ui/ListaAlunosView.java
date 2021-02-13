package br.com.alura.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.dataBase.AgendaDataBase;
import br.com.alura.agenda.dao.dataBase.dao.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    //private final AlunoDAO dao;
    private final RoomAlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        //this.dao = new AlunoDAO();
        dao = AgendaDataBase.getInstance(context).getRoomAlunoDAO();
    }

    //Alerta de confirmacao de exclusao de elemeto da lista
    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog//Encadeamento de metodos
                .Builder(context)
                .setTitle(R.string.removendo_aluno)
                .setMessage(R.string.confirmacao_de_exclusao)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();//Retorna informacoes de menu

                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);//Posiacao do elemento que chamou o menu info
                        ListaAlunosView.this.remove(alunoEscolhido);
                    }
                })
                .setNegativeButton(R.string.nao, null)
                .show();//Exibe dialog
    }

    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void remove(Aluno aluno) {//Remove aluno do banco e da lista adapter
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    public void configuraAdapter(ListView listaDeAlunos) {//Setando adapter
        listaDeAlunos.setAdapter(adapter);
    }
}
