package br.com.alura.agenda.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();//Equivale ao onCreat de banco
    private static int contadorDeIds = 1;//Id autoIncrement

    public void salva(Aluno aluno) {//Seta um id para o aluno criado e adciona na lista
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        atualizaIds();
    }

    private void atualizaIds() {//Gerador de ids AUTOINCREMENT
        contadorDeIds++;
    }

    public void edita(Aluno aluno) {//Pega o aluno pela posicao de acordo com id
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if (alunoEncontrado != null) {
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);//Pega posicao do index do aluno
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {//Pega aluno pelo id
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {//Retorna lista de alunos
        return (List<Aluno>) new ArrayList<>(alunos);//Cast redundante, pois ArrayList extend de List
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        if(alunoDevolvido != null){
            alunos.remove(alunoDevolvido);//Remove-se pelo objeto ou pelo index
        }
    }
}
