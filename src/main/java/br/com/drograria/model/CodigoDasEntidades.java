package br.com.drograria.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author jeor_
 */
@MappedSuperclass
public class CodigoDasEntidades implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    /*As calasses que salvam no banco chave estrangeira com p:selectOneMenu
    precisam implementar essas três sobreescritas, no caso do toString 
    esse é o formato padronizado pela biblioteca omnifaces. mais detalhes em:
    http://showcase.omnifaces.org/converters/SelectItemsConverter
    */
    @Override
    public String toString() {
        return String.format("%s[codigo=%d]", getClass().getSimpleName(), getCodigo());
    }
    /*
    hashCode e equals são usado para listar chave estrageira na edição.
    */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CodigoDasEntidades other = (CodigoDasEntidades) obj;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }
    
    

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

}
