package br.com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

     private final List<Aluno> alunos = new ArrayList<>();
     private final Context context;

     public ListaAlunosAdapter(Context context) {
          this.context = context;
     }

     @Override//Tamanho da lista
     public int getCount() {//Tamanho da lista
          return alunos.size();
     }

     @Override//Retorna elemento pela posicao
     public Aluno getItem(int posicao) {//Pegando posicao do elemento clicado
          return alunos.get(posicao);
     }

     @Override//Acessa o  elemento pele posicao, e traz seu id que esta na model
     public long getItemId(int posicao) {//Pegando id do elemento clicado
          return alunos.get(posicao).getId();
     }

     @Override//onBind
     public View getView(int posicao, View view, ViewGroup viewGroup) {
          View viewCriada = criaView(viewGroup);//Infla
          Aluno alunoDevolvido = alunos.get(posicao);//Pega aluno da lsita
          vincula(viewCriada, alunoDevolvido);//onBind
          return viewCriada;
     }

     private void vincula(View view, Aluno aluno) {
          TextView nome = view.findViewById(R.id.item_aluno_nome);
          nome.setText(aluno.getNome() + " " + aluno.dataFormatada());
          TextView telefone = view.findViewById(R.id.item_aluno_telefone);
          telefone.setText(aluno.getTelefone());
     }

     //Infla arquivo estatico
     private View criaView(ViewGroup viewGroup) {
          return LayoutInflater
                  .from(context)
                  .inflate(R.layout.item_aluno, viewGroup, false);
     }

     //Adciona alunos a lista de alunos
     public void atualiza(List<Aluno> alunos) {
          this.alunos.clear();
          this.alunos.addAll(alunos);
          notifyDataSetChanged();
     }

     //Remove
     public void remove(Aluno aluno) {
          alunos.remove(aluno);
          notifyDataSetChanged();
     }
}
