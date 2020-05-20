package br.com.drograria.dao;

import br.com.drograria.util.HibernateUtil;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author jeor_
 */
public class GenericDAO<Entidade> {
    private Class<Entidade> classe;
    
    public GenericDAO(){
        //capitura o tipo da classe que está chamando o template.
        this.classe = (Class<Entidade>) ((ParameterizedType) 
                getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public void salvar(Entidade entidade){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.save(entidade);
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
    /**
     * O método merge é uma mesclagem dos métodos salvar e editar,
     * o merge perquisa a chave primária do objeto a ser salvo no banco, 
     * se existir ele edita com os novos valores, se nao existir ele salva.
     * Esse método facilita que na tela usesse o mesmo butão para o dois métodos.
     * @param entidade 
     */
    public Entidade merge(Entidade entidade){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            /*foi adicionado o retorno aqui para poder pegar o id do produto
            e definir o nome da imagem do produto igual ao id do produto. isso 
            faciliará a pesquisa da imagem em diretório posteriormente. o autor 
            nã usará persistência da imagem no bd.
            */
            Entidade  retorno = (Entidade) sessao.merge(entidade);
            transacao.commit();
            return retorno;
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
    
    public List<Entidade> listar(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Criteria consulta = session.createCriteria(classe);
            List<Entidade> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro){
            throw erro;
        }
        finally{
            session.close();
        }
    }
    /*
    recebe uma string com o nome de uma coluna e ordena o resultado
    da listagem por esse parâmetro.
    */
    public List<Entidade> listar(String parametro){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Criteria consulta = session.createCriteria(classe);
            consulta.addOrder(Order.asc(parametro));
            List<Entidade> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro){
            throw erro;
        }
        finally{
            session.close();
        }
    }
    
    public Entidade pesquisar(Long codigo){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Criteria consulta = session.createCriteria(classe);
            consulta.add(Restrictions.idEq(codigo));
            Entidade resultado = (Entidade) consulta.uniqueResult();
            return resultado;
        } catch (RuntimeException erro){
            throw erro;
        }
        finally{
            session.close();
        }
    }
    
    public void excluir(Entidade entidade){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(entidade);
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
    
     public void editar(Entidade entidade){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.update(entidade);
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
