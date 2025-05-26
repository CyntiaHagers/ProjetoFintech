package br.com.fiap.fintech.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Meta {

    private Long id;
    private Long usuarioId;
    private String nome;
    private double valor;
    private Date dataMeta;
    private String descricao;
    private double vlAtual; // Novo campo

    public Meta() {}

    public Meta(Long id, Long usuarioId, String nome, double valor, Date dataMeta, String descricao, double vlAtual) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.valor = valor;
        this.dataMeta = dataMeta;
        this.descricao = descricao;
        this.vlAtual = vlAtual;
    }

    public Meta(Long id, String nome, java.util.Date dataMeta) {
        this.id = id;
        this.nome = nome;
        this.dataMeta = new Date(dataMeta.getTime());
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public Date getDataMeta() { return dataMeta; }
    public void setDataMeta(Date dataMeta) { this.dataMeta = dataMeta; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getVlAtual() { return vlAtual; }
    public void setVlAtual(double vlAtual) { this.vlAtual = vlAtual; }

    public String getDataMetaFormatada() {
        if (dataMeta != null) {
            return new SimpleDateFormat("dd/MM/yyyy").format(dataMeta);
        }
        return "";
    }

    @Override
    public String toString() {
        return "Meta{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", dataMeta=" + dataMeta +
                ", descricao='" + descricao + '\'' +
                ", vlAtual=" + vlAtual +
                '}';
    }
}

