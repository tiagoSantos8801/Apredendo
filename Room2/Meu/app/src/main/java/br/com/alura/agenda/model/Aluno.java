package br.com.alura.agenda.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
//    private String telefoneFixo;
//    private String telefoneCelular;
    private String email;
    private Calendar momentoDeCadastro = Calendar.getInstance();

    public Calendar getMomentoDeCadastro() {
        return momentoDeCadastro;
    }

    public void setMomentoDeCadastro(Calendar momentoDeCadastro) {
        this.momentoDeCadastro = momentoDeCadastro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public void setTelefoneFixo(String telefoneFixo) {
//        this.telefoneFixo = telefoneFixo;
//    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

//    public String getTelefoneFixo() {
//        return telefoneFixo;
//    }

    public String getEmail() {
        return email;
    }

    @NonNull
    @Override
    public String toString() {
        return nome /*+ " - " + telefoneFixo*/;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }

//    public String getTelefoneCelular() {
//        return telefoneCelular;
//    }

//    public void setTelefoneCelular(String telefoneCelular) {
//        this.telefoneCelular = telefoneCelular;
//    }
}
