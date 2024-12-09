package br.com.virtual.market.Model;

import br.com.virtual.market.Enum.ETipoUsuario;

import java.util.UUID;

public abstract class Usuario {

    protected UUID id;
    protected String nome;
    protected String email;
    protected String pass;
    protected ETipoUsuario tipo;

    public Usuario (String nome, String email, String pass, ETipoUsuario tipo) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.pass = pass;
        this.tipo = tipo;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public ETipoUsuario getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTipo(ETipoUsuario tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", tipo=" + tipo +
                '}';
    }

}
