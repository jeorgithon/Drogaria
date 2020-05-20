
package br.com.drograria.bean;


import br.com.drograria.dao.EstadoDAO;
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

@ManagedBean(name = "MBEstado")
@ViewScoped
public class EstadoBean implements Serializable{
    private Estado estado;
    private List<Estado> ListaDeEstados;
    private List<Estado> ListaDeEstadosFiltrados;
    
    // inicia o objeto, é chamado pelo butão novo.
    public void init(){
        estado = new Estado();
    }
    
    public void prepararEditar(ActionEvent evento){
        estado = (Estado) evento.getComponent().getAttributes().get("selecaoLinhaEditar");
    }
   
    
    public void salvar(){
        EstadoDAO estadoDAO = new EstadoDAO();
        try {
            estadoDAO.merge(estado);
            Messages.addGlobalInfo("Estado Cadastrado com sucesso!");
            ListaDeEstados = estadoDAO.listar();
        } catch (Exception erro) {
            Messages.addGlobalFatal("Erro ao cadastrar Estado!");
            erro.printStackTrace();
        } finally {
            init();
        }
    } 
    
    @PostConstruct
    public void listar(){
        try{
            ListaDeEstados = new EstadoDAO().listar();
        }  catch (Exception erro) {
            Messages.addGlobalError("Erro ao listar Estado!");
            erro.printStackTrace();
        }
    }  
    
    public void excluir(ActionEvent evento){
        estado = (Estado) evento.getComponent().getAttributes().get("selecaoLinhaExcluir");
//        Messages.addGlobalInfo("Nome: " + estado.getNome()+"\nSigla: "+
//                estado.getSigla());
        EstadoDAO estadoDAO = new EstadoDAO();
        try {
            estadoDAO.excluir(estado);
            ListaDeEstados = estadoDAO.listar();
            Messages.addGlobalInfo("Estado excluído com sucesso!");
        } catch (Exception erro) {
            Messages.addGlobalError("O Estado não pode ser excluído!");
            erro.printStackTrace();
        }
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public List<Estado> getListaDeEstados() {
        return ListaDeEstados;
    }

    public void setListaDeEstados(List<Estado> ListaDeEstados) {
        this.ListaDeEstados = ListaDeEstados;
    }

    public List<Estado> getListaDeEstadosFiltrados() {
        return ListaDeEstadosFiltrados;
    }

    public void setListaDeEstadosFiltrados(List<Estado> ListaDeEstadosFiltrados) {
        this.ListaDeEstadosFiltrados = ListaDeEstadosFiltrados;
    }
    
}
