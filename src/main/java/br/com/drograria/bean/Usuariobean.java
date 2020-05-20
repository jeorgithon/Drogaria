
package br.com.drograria.bean;


import br.com.drograria.dao.PessoaDAO;
import br.com.drograria.dao.UsuarioDAO;
import br.com.drograria.model.Pessoa;
import br.com.drograria.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 *
 * @author jeor_
 */

@ManagedBean(name = "MBUsuario")
@ViewScoped
public class Usuariobean implements Serializable{
    private Usuario usuario;
    private List<Usuario> listaDeUsuarios;
    private List<Usuario> listaDeUsuariosFiltradas;
    private List<Pessoa> listaDePesoas;
   
    /*
    inicia o objeto, é chamado pelo butão novo.
    */
    public void novo(){
        usuario = new Usuario();
        try{
            //inicia a lista vazia.
            listaDePesoas = new PessoaDAO().listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalFatal("Erro ao listar Pessas");
        }
    }
    
    public void editar(ActionEvent evento){
        usuario = (Usuario) evento.getComponent().getAttributes().get("selecaoLinhaEditar");
        try {
            listaDePesoas = new PessoaDAO().listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalFatal("Erro ao listar Pessoas");
        }
    }
    
    public void salvar(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptogradia());
            usuario.setSenha(hash.toHex());
            usuarioDAO.merge(usuario);
            Messages.addGlobalInfo("Usuário Cadastrado com sucesso!");
            listaDeUsuarios = usuarioDAO.listar();
        } catch (Exception erro) {
            Messages.addGlobalFatal("Erro ao cadastrar Usuário!");
        } finally {
            novo();
        }
    } 
    
    @PostConstruct
    public void listar(){
        try{
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            listaDeUsuarios = usuarioDAO.listar();
        }  catch (Exception erro) {
            Messages.addGlobalError("Erro ao listar Usuarios.");
        }
    }  
    
    public void excluir(ActionEvent evento){
        usuario = (Usuario) evento.getComponent().getAttributes().get("selecaoLinhaExcluir");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.excluir(usuario);
            listaDeUsuarios = usuarioDAO.listar();
            Messages.addGlobalInfo("Usuário excluído com sucesso!");
        } catch (Exception erro) {
            Messages.addGlobalError("O usuário não pode ser excluído!");
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
        this.listaDeUsuarios = listaDeUsuarios;
    }

    public List<Usuario> getListaDeUsuariosFiltradas() {
        return listaDeUsuariosFiltradas;
    }

    public void setListaDeUsuariosFiltradas(List<Usuario> listaDeUsuariosFiltradas) {
        this.listaDeUsuariosFiltradas = listaDeUsuariosFiltradas;
    }

    public List<Pessoa> getListaDePesoas() {
        return listaDePesoas;
    }

    public void setListaDePesoas(List<Pessoa> listaDePesoas) {
        this.listaDePesoas = listaDePesoas;
    }
    

    
    
}
