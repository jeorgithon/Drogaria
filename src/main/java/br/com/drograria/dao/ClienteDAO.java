
package br.com.drograria.dao;

import br.com.drograria.model.Cliente;
import br.com.drograria.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author jeor_
 */
public class ClienteDAO extends GenericDAO<Cliente>{
     public List<Cliente> listarOrdenado(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Criteria consulta = session.createCriteria(Cliente.class);
            consulta.createAlias("pessoa", "p");
            consulta.addOrder(Order.asc("p.nome"));
            List<Cliente> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro){
            throw erro;
        }
        finally{
            session.close();
        }
    }
}
