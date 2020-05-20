
package br.com.drograria.bean;


import br.com.drograria.dao.CidadeDAO;
import br.com.drograria.dao.EstadoDAO;
import br.com.drograria.model.Cidade;
import br.com.drograria.model.Estado;
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

@ManagedBean(name = "MBCidade")
@ViewScoped
public class CidadeBean implements Serializable{
    private Cidade cidade;
    private List<Cidade> listaDeCidades;
    private List<Cidade> listaDeCidadesFiltradas;
    private List<Estado> listaDeEstados;
    
    // inicia o objeto, é chamado pelo butão novo.
    public void novo(){
        cidade = new Cidade();
        try {
            listaDeEstados = new EstadoDAO().listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalFatal("Erro ao Listar Estados");
        }
    }
    
    public void editar(ActionEvent evento){
        EstadoDAO estadoDAO = new EstadoDAO();
        try {
            cidade = (Cidade) evento.getComponent().getAttributes().get("selecaoLinhaEditar");
            listaDeEstados = estadoDAO.listar();
        } catch (RuntimeException erro) {
             Messages.addGlobalFatal("Erro ao Editar Cidade!");
            erro.printStackTrace();
        }
    }
   
    
    public void salvar(){
        CidadeDAO cidadeDAO = new CidadeDAO();
        try {
            cidadeDAO.merge(cidade);
            Messages.addGlobalInfo("Cidade Cadastrada com sucesso!");
            listaDeCidades = cidadeDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalFatal("Erro ao cadastrar Cidade!");
            erro.printStackTrace();
        } finally {
            novo();
        }
    } 
    
    @PostConstruct
    public void listar(){
        try{
            CidadeDAO cidadeDAO = new CidadeDAO();
            listaDeCidades = cidadeDAO.listar();
        }  catch (Exception erro) {
            Messages.addGlobalError("Erro ao listar Cidade!");
           // erro.printStackTrace();
        }
    }  
    
    public void excluir(ActionEvent evento){
        cidade = (Cidade) evento.getComponent().getAttributes().get("selecaoLinhaExcluir");
        CidadeDAO cidadeDAO = new CidadeDAO();
        try {
            cidadeDAO.excluir(cidade);
            listaDeCidades = cidadeDAO.listar();
            Messages.addGlobalInfo("Cidade excluído com sucesso!");
        } catch (Exception erro) {
            Messages.addGlobalError("A cidade não pode ser excluída!");
            erro.printStackTrace();
        }
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getListaDeCidades() {
        return listaDeCidades;
    }

    public void setListaDeCidades(List<Cidade> listaDeCidades) {
        this.listaDeCidades = listaDeCidades;
    }

    public List<Cidade> getListaDeCidadesFiltradas() {
        return listaDeCidadesFiltradas;
    }

    public void setListaDeCidadesFiltradas(List<Cidade> listaDeCidadesFiltradas) {
        this.listaDeCidadesFiltradas = listaDeCidadesFiltradas;
    }

    public List<Estado> getListaDeEstados() {
        return listaDeEstados;
    }

    public void setListaDeEstados(List<Estado> listaDeEstados) {
        this.listaDeEstados = listaDeEstados;
    }
    
    

}
