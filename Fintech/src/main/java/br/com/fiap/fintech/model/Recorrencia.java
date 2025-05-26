package br.com.fiap.fintech.model;

import java.time.LocalDate;

/**
 * Classe que representa uma recorrência de transação financeira.
 * Pode ser um parcelamento ou uma transação recorrente.
 */
public class Recorrencia {
    private int idRecorrencia;
    private char tipoRecorrencia; // 'P' (Parcelado) ou 'R' (Recorrente)
    private Integer totalParcelas; // Pode ser null para recorrências sem fim
    private int intervaloMeses;
    private LocalDate dataInicio;
    private LocalDate dataFim; // Pode ser null para recorrências sem fim definido
    
    /**
     * Construtor padrão
     */
    public Recorrencia() {
        this.intervaloMeses = 1; // Por padrão, recorrência mensal
    }
    
    /**
     * Construtor para recorrência parcelada
     */
    public Recorrencia(char tipoRecorrencia, int totalParcelas, int intervaloMeses, LocalDate dataInicio) {
        this.tipoRecorrencia = tipoRecorrencia;
        this.totalParcelas = totalParcelas;
        this.intervaloMeses = intervaloMeses;
        this.dataInicio = dataInicio;
        
        // Se for parcelado, calcula a data fim baseada no número de parcelas e intervalo
        if (tipoRecorrencia == 'P' && totalParcelas > 0) {
            this.dataFim = dataInicio.plusMonths((long) (totalParcelas - 1) * intervaloMeses);
        }
    }
    
    /**
     * Construtor para recorrência sem fim definido
     */
    public Recorrencia(char tipoRecorrencia, int intervaloMeses, LocalDate dataInicio) {
        this.tipoRecorrencia = tipoRecorrencia;
        this.intervaloMeses = intervaloMeses;
        this.dataInicio = dataInicio;
        this.dataFim = null; // Sem data fim definida
    }
    
    /**
     * Construtor completo
     */
    public Recorrencia(int idRecorrencia, char tipoRecorrencia, Integer totalParcelas, 
                      int intervaloMeses, LocalDate dataInicio, LocalDate dataFim) {
        this.idRecorrencia = idRecorrencia;
        this.tipoRecorrencia = tipoRecorrencia;
        this.totalParcelas = totalParcelas;
        this.intervaloMeses = intervaloMeses;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters
    public int getIdRecorrencia() {
        return idRecorrencia;
    }

    public void setIdRecorrencia(int idRecorrencia) {
        this.idRecorrencia = idRecorrencia;
    }

    public char getTipoRecorrencia() {
        return tipoRecorrencia;
    }

    public void setTipoRecorrencia(char tipoRecorrencia) {
        this.tipoRecorrencia = tipoRecorrencia;
    }

    public Integer getTotalParcelas() {
        return totalParcelas;
    }

    public void setTotalParcelas(Integer totalParcelas) {
        this.totalParcelas = totalParcelas;
    }

    public int getIntervaloMeses() {
        return intervaloMeses;
    }

    public void setIntervaloMeses(int intervaloMeses) {
        this.intervaloMeses = intervaloMeses;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
    
    /**
     * Verifica se a recorrência está ativa na data especificada
     */
    public boolean isAtivaEm(LocalDate data) {
        if (data == null || dataInicio == null) {
            return false;
        }
        
        // Verifica se a data está após o início
        boolean aposInicio = !data.isBefore(dataInicio);
        
        // Verifica se a data está antes do fim (se houver fim definido)
        boolean antesFim = dataFim == null || !data.isAfter(dataFim);
        
        return aposInicio && antesFim;
    }
    
    /**
     * Calcula a próxima data de ocorrência após a data especificada
     */
    public LocalDate proximaOcorrenciaApos(LocalDate data) {
        if (data == null || dataInicio == null) {
            return null;
        }
        
        // Se a data for anterior à data de início, a próxima ocorrência é a data de início
        if (data.isBefore(dataInicio)) {
            return dataInicio;
        }
        
        // Se a data for posterior à data de fim (se houver), não há próxima ocorrência
        if (dataFim != null && data.isAfter(dataFim)) {
            return null;
        }
        
        // Calcula quantos intervalos se passaram desde o início
        long mesesDesdeInicio = java.time.Period.between(dataInicio, data).toTotalMonths();
        long intervalosCompletos = mesesDesdeInicio / intervaloMeses;
        
        // Calcula a data da próxima ocorrência
        LocalDate proximaData = dataInicio.plusMonths((intervalosCompletos + 1) * intervaloMeses);
        
        // Verifica se a próxima data está dentro do período válido
        if (dataFim != null && proximaData.isAfter(dataFim)) {
            return null;
        }
        
        return proximaData;
    }

    @Override
    public String toString() {
        return "Recorrencia [idRecorrencia=" + idRecorrencia + 
               ", tipoRecorrencia=" + tipoRecorrencia + 
               ", totalParcelas=" + totalParcelas + 
               ", intervaloMeses=" + intervaloMeses + 
               ", dataInicio=" + dataInicio + 
               ", dataFim=" + dataFim + "]";
    }
}