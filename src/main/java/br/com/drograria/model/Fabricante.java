
package br.com.drograria.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author jeor_
 * 
 */
@Entity
public class Fabricante extends CodigoDasEntidades{
    @Column(length = 50, nullable = false)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
