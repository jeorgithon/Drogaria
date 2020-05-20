package br.com.drograria.dao;

import br.com.drograria.model.Funcionario;
import br.com.drograria.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author jeor_
 */
public class FuncionarioDAO extends GenericDAO<Funcionario>{
    public List<Funcionario> listarOrdenado(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Criteria consulta = session.createCriteria(Funcionario.class);
            consulta.createAlias("pessoa", "p");
            consulta.addOrder(Order.asc("p.nome"));
            List<Funcionario> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro){
            throw erro;
        }
        finally{
            session.close();
        }
    }
}
