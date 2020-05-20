
package br.com.drograria.dao;

import br.com.drograria.model.Cidade;
import br.com.drograria.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author jeor_
 */
public class CidadeDAO extends GenericDAO<Cidade>{
   
    public List<Cidade> pesquisarPorEstado(Long estadoCodigo){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Criteria consulta = session.createCriteria(Cidade.class);
            consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
            consulta.addOrder(Order.asc("nome"));
            List<Cidade> listaDeCidade = consulta.list();
            return listaDeCidade;
        } catch (RuntimeException erro){
            throw erro;
        }
        finally{
            session.close();
        }
    }
}
