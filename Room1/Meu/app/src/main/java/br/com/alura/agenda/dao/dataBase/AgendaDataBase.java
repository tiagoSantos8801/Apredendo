package br.com.alura.agenda.dao.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.alura.agenda.dao.dataBase.converter.ConversorCalendar;
import br.com.alura.agenda.dao.dataBase.dao.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;

import static br.com.alura.agenda.dao.dataBase.AgendaMigrations.TODAS_AS_MIGRATION;

//Informa a classe que sera o banco
@Database(entities = {Aluno.class}, version = 4, exportSchema = false)
@TypeConverters({ConversorCalendar.class})
public abstract class AgendaDataBase extends RoomDatabase {

     private static final String NOME_BANCO_DE_DADOS = "agenda.db";

     public abstract RoomAlunoDAO getRoomAlunoDAO();

     //Devolve instancia de si mesmo
     public static AgendaDataBase getInstance(Context context) {
          return Room
                  .databaseBuilder(context, AgendaDataBase.class, NOME_BANCO_DE_DADOS)//Construtor
                  .allowMainThreadQueries()
                  .addMigrations(TODAS_AS_MIGRATION)//Array de migrations
                  .build();//Instancia - Construir
     }
}
