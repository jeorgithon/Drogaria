
package br.com.drograria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author jeor_
 */

@Entity
public class Cidade extends CodigoDasEntidades{
    @Column(length = 50, nullable = false)
    private String nome;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Estado estado;

     @Override
    public String toString(){
        return this.estado.getNome();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    
}
