
package br.com.drograria.bean;


import br.com.drograria.dao.CidadeDAO;
import br.com.drograria.dao.EstadoDAO;
import br.com.drograria.dao.PessoaDAO;
import br.com.drograria.model.Cidade;
import br.com.drograria.model.Estado;
import br.com.drograria.model.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
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

@ManagedBean(name = "MBPessoa")
@ViewScoped
public class PessoaBean implements Serializable{
    private Pessoa pessoa;
    private Estado estado;
    private List<Pessoa> listaDePessoas;
    private List<Pessoa> listaDePessoasFiltradas;
    private List<Estado> listaDeEstados;
    private List<Cidade> listaDeCidades;
    
    // inicia o objeto, é chamado pelo butão novo.
    public void novo(){
        pessoa = new Pessoa();
        estado = new Estado();
        try{
            //inicia a lista vazia.
            listaDeCidades = new ArrayList<Cidade>();
            listaDeEstados = new EstadoDAO().listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalFatal("Erro ao Listar Estados e Cidades");
        }
    }
    
    public void editar(ActionEvent evento){
        pessoa = (Pessoa) evento.getComponent().getAttributes().get("selecaoLinhaEditar");
        EstadoDAO estadoDAO = new EstadoDAO();
        try {
            estado = pessoa.getCidade().getEstado();
            popular();
            listaDeEstados = estadoDAO.listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalFatal("Erro ao Listar Estados e Cidades");
        }
    }
   
    
    public void salvar(){
        PessoaDAO pessoaDAO = new PessoaDAO();
        try {
            pessoaDAO.merge(pessoa);
            Messages.addGlobalInfo("Produto Cadastrado com sucesso!");
            listaDePessoas = pessoaDAO.listar();
        } catch (Exception erro) {
            Messages.addGlobalFatal("Erro ao cadastrar Cidade!");
            erro.printStackTrace();
        } finally {
            novo();
        }
    } 
    
    @PostConstruct
    public void listar(){
        try{
            PessoaDAO pessoaDAO = new PessoaDAO();
            listaDePessoas = pessoaDAO.listar();
        }  catch (Exception erro) {
            Messages.addGlobalError("Erro ao listar Pessoa!");
            erro.printStackTrace();
        }
    }  
    
    public void excluir(ActionEvent evento){
        pessoa = (Pessoa) evento.getComponent().getAttributes().get("selecaoLinhaExcluir");
        PessoaDAO pessoaDAO = new PessoaDAO();
        try {
            pessoaDAO.excluir(pessoa);
            listaDePessoas = pessoaDAO.listar();
            Messages.addGlobalInfo("Pessoa excluída com sucesso!");
        } catch (Exception erro) {
            Messages.addGlobalError("A passoa não pode ser excluída!");
            erro.printStackTrace();
        }
    }
    
    public void popular(){
        try {
            
            if(estado != null){
                listaDeCidades = new CidadeDAO().pesquisarPorEstado(estado.getCodigo());
            } else{
                listaDeCidades = new ArrayList<Cidade>();
            }
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Erro ao listar as cidades!");
        }
 
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getListaDePessoas() {
        return listaDePessoas;
    }

    public void setListaDePessoas(List<Pessoa> listaDePessoas) {
        this.listaDePessoas = listaDePessoas;
    }

    public List<Pessoa> getListaDePessoasFiltradas() {
        return listaDePessoasFiltradas;
    }

    public void setListaDePessoasFiltradas(List<Pessoa> listaDePessoasFiltradas) {
        this.listaDePessoasFiltradas = listaDePessoasFiltradas;
    }

    public List<Estado> getListaDeEstados() {
        return listaDeEstados;
    }

    public void setListaDeEstados(List<Estado> listaDeEstados) {
        this.listaDeEstados = listaDeEstados;
    }

    public List<Cidade> getListaDeCidades() {
        return listaDeCidades;
    }

    public void setListaDeCidades(List<Cidade> listaDeCidades) {
        this.listaDeCidades = listaDeCidades;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    
}
