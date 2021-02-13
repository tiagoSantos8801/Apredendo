package br.com.alura.agenda.dao.dataBase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.alura.agenda.model.Aluno;

@Dao
public interface RoomAlunoDAO {

     @Insert
//O Room entende que tem que inserir no banco de dados
     void salva(Aluno aluno);

     @Query("SELECT * FROM aluno")
//Vai buscar a informacao no banco
     List<Aluno> todos();

     @Delete
     void remove(Aluno aluno);

     @Update
     void edita(Aluno aluno);
}
