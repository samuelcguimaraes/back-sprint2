package br.com.rchlo.domain;

import java.time.YearMonth;

public class DadosCartao {

    private String nomeCliente;
    private String numero;
    private YearMonth expiracao;
    private String codigoVerificacao;

    public DadosCartao(String nomeCliente, String numero, YearMonth expiracao, String codigoVerificacao) {
        this.nomeCliente = nomeCliente;
        this.numero = numero;
        this.expiracao = expiracao;
        this.codigoVerificacao = codigoVerificacao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getNumero() {
        return numero;
    }

    public YearMonth getExpiracao() {
        return expiracao;
    }

    public String getCodigoVerificacao() {
        return codigoVerificacao;
    }
}
