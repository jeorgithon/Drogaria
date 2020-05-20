package br.com.drograria.dao;

import br.com.drograria.model.ItemVenda;
import br.com.drograria.model.Venda;
import br.com.drograria.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jeor_
 */
public class VendaDAO extends GenericDAO<Venda>{
    
    public void salvar(Venda venda, List<ItemVenda> listaDeItemVenda){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            /*
            aqui a venda passa a ter codigo, 
            gerado pelo bd ao salvar
            */
            sessao.save(venda);
            
            for(int posicao = 0; posicao < listaDeItemVenda.size(); posicao++){
                ItemVenda itemVenda = listaDeItemVenda.get(posicao);
                itemVenda.setVenda(venda);
                sessao.save(itemVenda);
        }
            
            transacao.commit();
            
        } catch(RuntimeException erro) {
            if(transacao != null){
                transacao.rollback();
            }
            throw erro;
        }
        finally{
            sessao.close();
        }
    }
}
