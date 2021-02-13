package br.com.alura.agenda.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class Telefone {

     @PrimaryKey(autoGenerate = true)
     private int id;
     private String numero;
     private TipoTelefone tipo;

     @ForeignKey(entity = Aluno.class,//Entidade de onde vem a foreingKey
             parentColumns = "id",//Chave de origem
             childColumns = "alunoId",//Chave de destino
             onUpdate = CASCADE,//Qaundo edita o aluno, apaga toda a relacao(Com import statico)
             onDelete = CASCADE)////Qaundo deleta o aluno, apaga toda a relacao
     //@ColumnInfo(name = "aluno_id")//Informar o nome da coluna
     private int alunoId;

     @Ignore
     public Telefone(String numero, TipoTelefone tipo) {
          this.numero = numero;
          this.tipo = tipo;
     }

     public Telefone() {
     }

     public int getAlunoId() {
          return alunoId;
     }

     public void setAlunoId(int alunoId) {
          this.alunoId = alunoId;
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getNumero() {
          return numero;
     }

     public void setNumero(String numero) {
          this.numero = numero;
     }

     public TipoTelefone getTipo() {
          return tipo;
     }

     public void setTipo(TipoTelefone tipo) {
          this.tipo = tipo;
     }
}
