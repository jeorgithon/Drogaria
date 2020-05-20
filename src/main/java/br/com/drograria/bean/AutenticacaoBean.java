package br.com.drograria.bean;

import br.com.drograria.dao.UsuarioDAO;
import br.com.drograria.model.Pessoa;
import br.com.drograria.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

/**
 *
 * @author jeor_ SessionScoped deixa o objeto vivo durante toda sessão, logo se
 * mudar de página não vai perder aautenticação.
 */
@ManagedBean(name = "MBAutenticacao")
@SessionScoped
public class AutenticacaoBean implements Serializable {

    private Usuario usuario;
    private Usuario usuarioLogin;
//    private boolean teste = true;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        usuario.setPessoa(new Pessoa());
    }
    
    public boolean permitir(Character... permissoes) {
        for (Character permissao : permissoes) {
            try {
                if(usuarioLogin.getTipo() == permissao){
                    return true;
                }
            } catch (NullPointerException erro) {
                return false;
            }
        }
        return false;
    }
    
    public void logout() {
        usuarioLogin = null;
        try {
            Faces.redirect("/Drograria/index.xhtml");
        } catch (IOException erro) {
            Messages.addGlobalFatal("Erro de redirecionamento");
        }
    }
    
    public void autenticar() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioLogin = usuarioDAO.autenticar(usuario.getPessoa().getCpf(),
                    usuario.getSenha());
            if (usuarioLogin != null && usuarioLogin.getAtivo().equals(true)) {
                Faces.redirect("./pages/home.xhtml");
            } else {
                Messages.addGlobalFatal("Usuário ou senha inválido");
            }
        } catch (IOException erro) {
            Messages.addGlobalFatal("Erro de redirecionamento");
        }

    }

    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

  

}
