
package br.com.drograria.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jeor_
 */
@Entity
public class Funcionario extends CodigoDasEntidades{
    
    @Column(length = 16, nullable = false)
    private String carteiraDeTrabalho;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAdmissao;
    
    @OneToOne
    @JoinColumn(nullable = false)
    private Pessoa pessoa;

    public String getCarteiraDeTrabalho() {
        return carteiraDeTrabalho;
    }

    public void setCarteiraDeTrabalho(String carteiraDeTrabalho) {
        this.carteiraDeTrabalho = carteiraDeTrabalho;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
}
