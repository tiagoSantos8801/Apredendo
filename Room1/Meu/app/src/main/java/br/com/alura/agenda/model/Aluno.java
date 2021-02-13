package br.com.alura.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Aluno implements Serializable {

     @PrimaryKey(autoGenerate = true)
     private int id = 0;

     private String nome;
     //private String sobrenome;
     private String telefone;
     private String email;
     private Calendar momentoDeCadastro = Calendar.getInstance();

     @Ignore
     public Aluno(String nome, /*String sobrenome*/ String telefone, String email) {
          this.nome = nome;
          //this.sobrenome = sobrenome;
          this.telefone = telefone;
          this.email = email;
     }

     public Aluno() {

     }

     public void setId(int id) {
          this.id = id;
     }

     public void setNome(String nome) {
          this.nome = nome;
     }

     /*public void setSobrenome(String sobrenome) {
          this.sobrenome = sobrenome;
     }*/

     public void setTelefone(String telefone) {
          this.telefone = telefone;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public void setMomentoDeCadastro(Calendar momentoDeCadastro) {
          this.momentoDeCadastro = momentoDeCadastro;
     }

     public int getId() {
          return id;
     }

     public String getNome() {
          return nome;
     }

    /* public String getSobrenome() {
          return sobrenome;
     }*/

     public String getTelefone() {
          return telefone;
     }

     public String getEmail() {
          return email;
     }

     public Calendar getMomentoDeCadastro() {
          return momentoDeCadastro;
     }

     @NonNull
     @Override
     public String toString() {
          return nome + " - " + telefone;
     }

     public boolean temIdValido() {
          return id > 0;
     }

     public String getNomeCompleto() {
          return nome /*+ " " + sobrenome*/;
     }

     public String dataFormatada(){
          SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
          return format.format(momentoDeCadastro.getTime());
     }
}
