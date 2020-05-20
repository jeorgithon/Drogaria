
package br.com.drograria.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author jeor_
 */
@Entity
public class ItemVenda extends CodigoDasEntidades{
    @Column(nullable = false)    
    private Short quantidade;
    @Column(nullable = false)
    private BigDecimal ValorParcial;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Venda venda;

    public Short getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Short quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorParcial() {
        return ValorParcial;
    }

    public void setValorParcial(BigDecimal ValorParcial) {
        this.ValorParcial = ValorParcial;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    
}
