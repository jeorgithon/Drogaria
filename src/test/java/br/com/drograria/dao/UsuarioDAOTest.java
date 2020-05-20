package br.com.drograria.dao;

import br.com.drograria.model.Pessoa;
import br.com.drograria.model.Usuario;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author jeor_
 */
public class UsuarioDAOTest {
    
    @Test
//    @Ignore
    public void salvar(){
        Pessoa pessoa = new PessoaDAO().pesquisar(1L);
        
        Usuario usuario = new Usuario();
        usuario.setAtivo(true);
        usuario.setSenhaSemCriptogradia("123456");
        
        SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptogradia());
        
        usuario.setSenha(hash.toHex());
        usuario.setTipo('A');
        usuario.setPessoa(pessoa);
        
        new UsuarioDAO().salvar(usuario);
    }
    
    @Test
    @Ignore
    public void autenticar(){
        String cpf = "333.333.333-33";
        String senha = "123";
        Usuario usuario = new UsuarioDAO().autenticar(cpf, senha);
        
        System.out.println(usuario.getPessoa().getNome());
    }   
}
