package model;

public class Docente {

    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String departamento;
    private boolean ativo;

    public Docente() {
    }

    public Docente(String nome, String email, String telefone, String departamento, boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.departamento = departamento;
        this.ativo = ativo;
    }

    public Docente(int id, String nome, String email, String telefone, String departamento, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.departamento = departamento;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
