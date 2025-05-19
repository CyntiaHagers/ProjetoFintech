package br.com.fiap.fintech.model;

public class Endereco {

    private long idUsuario; // Altere para idUsuario
    private int cep;
    private String logradouro;
    private String estado;
    private String cidade;
    private String bairro;
    private String residencia;
    private String complemento;
    private int idEndereco;

    // Construtor
    public Endereco(long idUsuario, int cep, String logradouro, String estado, String cidade,
                    String bairro, String residencia, String complemento, int idEndereco) {
        this.idUsuario = idUsuario;
        this.cep = cep;
        this.logradouro = logradouro;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.residencia = residencia;
        this.complemento = complemento;
        this.idEndereco = idEndereco;
    }

    // Getters e Setters
    public long getIdUsuario() {
        return idUsuario; // Corrigido para idUsuario
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }
}
