package br.com.fiap.fintech.model;

import java.util.Date;

public class Transacao {
	private int idTransacao;
	private int idUsuario;
	private int idConta;
	private double vlTransacao;
	private String nmTransacao;
	private String dsTransacao;
	private String nmSegundaPessoa;
	private int nrContaSegPessoa;
	private Date dtTransacao;
	private int idRecorrencia;
	private String tpTransacao;

	public int getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(int idTransacao) {
		this.idTransacao = idTransacao;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdConta() {
		return idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}

	public double getVlTransacao() {
		return vlTransacao;
	}

	public void setVlTransacao(double vlTransacao) {
		this.vlTransacao = vlTransacao;
	}

	public String getNmTransacao() {
		return nmTransacao;
	}

	public void setNmTransacao(String nmTransacao) {
		this.nmTransacao = nmTransacao;
	}

	public String getDsTransacao() {
		return dsTransacao;
	}

	public void setDsTransacao(String dsTransacao) {
		this.dsTransacao = dsTransacao;
	}

	public String getNmSegundaPessoa() {
		return nmSegundaPessoa;
	}

	public void setNmSegundaPessoa(String nmSegundaPessoa) {
		this.nmSegundaPessoa = nmSegundaPessoa;
	}

	public int getNrContaSegPessoa() {
		return nrContaSegPessoa;
	}

	public void setNrContaSegPessoa(int nrContaSegPessoa) {
		this.nrContaSegPessoa = nrContaSegPessoa;
	}

	public Date getDtTransacao() {
		return dtTransacao;
	}

	public void setDtTransacao(Date dtTransacao) {
		this.dtTransacao = dtTransacao;
	}

	public int getIdRecorrencia() {
		return idRecorrencia;
	}

	public void setIdRecorrencia(int idRecorrencia) {
		this.idRecorrencia = idRecorrencia;
	}

	public String getTpTransacao() {
		return tpTransacao;
	}

	public void setTpTransacao(String tpTransacao) {
		this.tpTransacao = tpTransacao;
	}
}
