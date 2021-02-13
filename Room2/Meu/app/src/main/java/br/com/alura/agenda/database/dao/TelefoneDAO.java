package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.alura.agenda.model.Telefone;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TelefoneDAO {

     @Query("SELECT * FROM Telefone t " +
             "WHERE t.alunoId = :alunoId " +//Buscando por alunoId
             "LIMIT 1")
//Limitando a query a 1 resultado
     Telefone buscarPrimeiroTelefoneDoAluno(int alunoId);

     @Insert
     void salva(Telefone... telefones);

     @Query("SELECT * FROM Telefone t " +
             "WHERE t.alunoId = :alunoId ")
//Id da ForeignKey == Id do aluno em questao
     List<Telefone> buscaTodosTelefonesDoAluno(int alunoId);

     @Insert(onConflict = REPLACE)
//Quando do inserir e perceber que ja existe, somente atualiza
     void atualiza(Telefone... telefones);
}

//     @Insert(onConflict = REPLACE)//Quando do inserir e perceber que ja existe, somente atualiza
//     void atualiza(List<Telefone> telefones);

//     @Update
//     void atualiza(List<Telefone> telefones);

//     @Query("SELECT t.* FROM Telefone t " +
//             "JOIN ALUNO a " +
//             "ON t.alunoId = a.id " +
//             "WHERE t.alunoId = :alunoId " +//Buscando por alunoId
//             "LIMIT 1")//Limitando a query a 1 resultado
//     Telefone buscarPrimeiroTelefoneDoAluno(int alunoId);