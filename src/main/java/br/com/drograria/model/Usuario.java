
package br.com.drograria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author jeor_
 */
@Entity
public class Usuario extends CodigoDasEntidades{
    @Column(length = 32, nullable = false)
    private String senha;
    
    @Transient
    private String senhaSemCriptogradia;
    
    @Column(nullable = false)
    private Character tipo;
    
    @Column(nullable = false)
    private Boolean ativo;
    
    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private Pessoa pessoa;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public String getTipoFormatado(){
        if(tipo == 'A'){
            return "Administarado";
        } else if(tipo == 'G'){
            return "Gerente";
        } else if (tipo == 'B'){
            return "Balconista";
        }
        return null;
    }
    
    public String getAtivoFormatado(){
        if(ativo){
            return "Ativo";
        }
        return "Bloqueado";
    }

    public String getSenhaSemCriptogradia() {
        return senhaSemCriptogradia;
    }

    public void setSenhaSemCriptogradia(String senhaSemCriptogradia) {
        this.senhaSemCriptogradia = senhaSemCriptogradia;
    }
    
    
}
