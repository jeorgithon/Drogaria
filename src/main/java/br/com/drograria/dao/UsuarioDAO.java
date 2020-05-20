package br.com.drograria.dao;

import br.com.drograria.model.Usuario;
import br.com.drograria.util.HibernateUtil;
import org.apache.shiro.codec.CodecException;
import org.apache.shiro.crypto.UnknownAlgorithmException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author jeor_
 */
public class UsuarioDAO extends GenericDAO<Usuario>{
    public Usuario autenticar(String cpf, String senha){
//       capitura uma sessão
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
//            cria um critério para consulta, equivalente ao where no sql.
            Criteria consulta = sessao.createCriteria(Usuario.class);
           
            /*O alias serve para poder usar composição no criteria,
              não é possivel fazer navegação o objeto, como ex:
            Restrictions.eq("pessoa.cpf", cpf).
            */
            consulta.createAlias("pessoa", "p");
            /*
            verifica se cpf informado é igual ao cpf de pessoa.
            sql: where p.cpf=cpf;
            */
            consulta.add(Restrictions.eq("p.cpf", cpf));
            /*
            ciando uma criptografia tipo md5 e passando a senha para
            ser criptografada.
            */
            SimpleHash hash = new SimpleHash("md5", senha);
            /*
            verifica se a senha senha, já criptografada é igual 
            a senha do banco, que por sua ve tb está criptografada.
            */
            consulta.add(Restrictions.eq("senha", hash.toHex()));
            
            Usuario usuraio = (Usuario) consulta.uniqueResult();
            return usuraio;
        } catch (CodecException erro) {
            throw erro;
        } catch (UnknownAlgorithmException erro) {
            throw erro;
        } catch (HibernateException erro) {
            throw erro;
        } finally{
            sessao.close();
        }
        
    }
    
   
}
