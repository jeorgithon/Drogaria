
package br.com.drograria.bean;


import br.com.drograria.dao.ClienteDAO;
import br.com.drograria.dao.PessoaDAO;
import br.com.drograria.model.Cliente;
import br.com.drograria.model.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

/**
 *
 * @author jeor_
 */

@ManagedBean(name = "MBCliente")
@ViewScoped
public class ClienteBean implements Serializable{
    private Cliente cliente;
    private List<Cliente> listaDeClientes;
    private List<Cliente> listaDeClientesFiltradas;
    private List<Pessoa> listaDePesoas;
   
    /*
    inicia o objeto, é chamado pelo butão novo.
    */
    public void novo(){
        cliente = new Cliente();
        try{
            //inicia a lista vazia.
            listaDePesoas = new PessoaDAO().listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalFatal("Erro ao Listar Pessas");
        }
    }
    
    public void editar(ActionEvent evento){
        cliente = (Cliente) evento.getComponent().getAttributes().get("selecaoLinhaEditar");
        try {
            listaDePesoas = new PessoaDAO().listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalFatal("Erro ao Listar Pessoas");
        }
    }
   
    
    public void salvar(){
        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            clienteDAO.merge(cliente);
            Messages.addGlobalInfo("Cliente Cadastrado com sucesso!");
            listaDeClientes = clienteDAO.listar();
        } catch (Exception erro) {
            Messages.addGlobalFatal("Erro ao cadastrar cliente!");
        } finally {
            novo();
        }
    } 
    
    @PostConstruct
    public void listar(){
        try{
            ClienteDAO clienteDAO = new ClienteDAO();
            listaDeClientes = clienteDAO.listar();
        }  catch (Exception erro) {
            Messages.addGlobalError("Erro ao listar Cliente!");
            erro.printStackTrace();
        }
    }  
    
    public void excluir(ActionEvent evento){
        cliente = (Cliente) evento.getComponent().getAttributes().get("selecaoLinhaExcluir");
        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            clienteDAO.excluir(cliente);
            listaDeClientes = clienteDAO.listar();
            Messages.addGlobalInfo("Cliente excluído com sucesso!");
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

    public void setListaDeClientes(List<Cliente> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    public List<Cliente> getListaDeClientesFiltradas() {
        return listaDeClientesFiltradas;
    }

    public void setListaDeClientesFiltradas(List<Cliente> listaDeClientesFiltradas) {
        this.listaDeClientesFiltradas = listaDeClientesFiltradas;
    }

    public List<Pessoa> getListaDePesoas() {
        return listaDePesoas;
    }

    public void setListaDePesoas(List<Pessoa> listaDePesoas) {
        this.listaDePesoas = listaDePesoas;
    }

   
    
}
