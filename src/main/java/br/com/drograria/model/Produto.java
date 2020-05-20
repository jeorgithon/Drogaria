
package br.com.drograria.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author jeor_
 */
@Entity
public class Produto extends CodigoDasEntidades{
    @Column(length = 80, nullable = false)
    private String descricao;
    
    @Column(nullable = false)
    private Short quantidade;
    
    //precision: quantdade de digitos, scale: digitos depois da virgula
    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal preco;
    
    @ManyToOne
    @JoinColumn(nullable = false)        
    Fabricante fabricante;
    
//    Usado para upoade de arquivo
//    armazena o caminho do arquivo temporário.
    @Transient
    private String caminhoDoArquivoTemp;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Short getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Short quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public String getCaminhoDoArquivoTemp() {
        return caminhoDoArquivoTemp;
    }

    public void setCaminhoDoArquivoTemp(String caminhoDoArquivoTemp) {
        this.caminhoDoArquivoTemp = caminhoDoArquivoTemp;
    }
    
    
}
