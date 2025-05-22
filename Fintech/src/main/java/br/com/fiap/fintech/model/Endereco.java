package br.com.fiap.fintech.model;

public class Endereco {

    private int idEndereco;        // PK
    private long idUsuario;        // FK para TB_USUARIO
    private String cep;            // Pode ser String para manter zeros à esquerda
    private String logradouro;
    private String estado;
    private String cidade;
    private String bairro;
    private int residencia;        // Número da residência como int
    private String complemento;

    // Construtor padrão
    public Endereco() {}

    // Construtor com parâmetros (opcional)
    public Endereco(int idEndereco, long idUsuario, String cep, String logradouro, String estado,
                    String cidade, String bairro, int residencia, String complemento) {
        this.idEndereco = idEndereco;
        this.idUsuario = idUsuario;
        this.cep = cep;
        this.logradouro = logradouro;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.residencia = residencia;
        this.complemento = complemento;
    }

    // Getters e Setters
    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
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

    public int getResidencia() {
        return residencia;
    }

    public void setResidencia(int residencia) {
        this.residencia = residencia;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}

