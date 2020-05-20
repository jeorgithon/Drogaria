
package br.com.drograria.bean;


import br.com.drograria.dao.FuncionarioDAO;
import br.com.drograria.dao.PessoaDAO;
import br.com.drograria.model.Funcionario;
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

@ManagedBean(name = "MBFuncionario")
@ViewScoped
public class FuncionarioBean implements Serializable{
    private Funcionario funcionario;
    private List<Funcionario> listaDeFunconarios;
    private List<Funcionario> listaDeFuncionariosFiltrados;
    private List<Pessoa>  listaDePessoas;
    
    // inicia o objeto, é chamado pelo butão novo.
    public void novo(){
        funcionario = new Funcionario();
        try{
            listaDePessoas = new PessoaDAO().listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalFatal("Erro ao listar Pessas");
        }
    }
    
    public void prepararEditar(ActionEvent evento){
        funcionario = (Funcionario) evento.getComponent().getAttributes().get("selecaoLinhaEditar");
        try{
            listaDePessoas = new PessoaDAO().listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalFatal("Erro ao listar Pessas");
        }
    }
   
    
    public void salvar(){
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        try {
            funcionarioDAO.merge(funcionario);
            Messages.addGlobalInfo("Funcionário Cadastrado com sucesso!");
            listaDeFunconarios = funcionarioDAO.listarOrdenado();
        } catch (Exception erro) {
            Messages.addGlobalFatal("Erro ao cadastrar Funcionário!");
        } finally {
            novo();
        }
    } 
    
    @PostConstruct
    public void listar(){
        try{
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            listaDeFunconarios = funcionarioDAO.listarOrdenado();
        }  catch (Exception erro) {
            Messages.addGlobalError("Erro ao listar Funcionário!");
        }
    }  
    
    public void excluir(ActionEvent evento){
        funcionario = (Funcionario) evento.getComponent().getAttributes().get("selecaoLinhaExcluir");
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        try {
            funcionarioDAO.excluir(funcionario);
            listaDeFunconarios = funcionarioDAO.listarOrdenado();
            Messages.addGlobalInfo("Funcionário excluído com sucesso!");
        } catch (Exception erro) {
            Messages.addGlobalError("O funcioário não pode ser excluído!");
        }
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getListaDeFunconarios() {
        return listaDeFunconarios;
    }

    public void setListaDeFunconarios(List<Funcionario> listaDeFunconarios) {
        this.listaDeFunconarios = listaDeFunconarios;
    }

    public List<Funcionario> getListaDeFuncionariosFiltrados() {
        return listaDeFuncionariosFiltrados;
    }

    public void setListaDeFuncionariosFiltrados(List<Funcionario> listaDeFuncionariosFiltrados) {
        this.listaDeFuncionariosFiltrados = listaDeFuncionariosFiltrados;
    }

    public List<Pessoa> getListaDePessoas() {
        return listaDePessoas;
    }

    public void setListaDePessoas(List<Pessoa> listaDePessoas) {
        this.listaDePessoas = listaDePessoas;
    }

    
}
