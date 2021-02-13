package br.com.alura.agenda.dao.dataBase.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConversorCalendar {

     @TypeConverter//Informa que se trata de um metodo de conversao
     public Long paraLong(Calendar valor) {//Converte para o tipo que o banco de dados suporta
          if (valor != null){
               return valor.getTimeInMillis();
          }
          return null;
     }

     @TypeConverter
     public Calendar paraCalendar(Long valor) {//Pega do banco e converte para a exibicao
          Calendar momentoAtual = Calendar.getInstance();
          if (valor != null) {
               momentoAtual.setTimeInMillis(valor);
          }
          return momentoAtual;
     }

}
