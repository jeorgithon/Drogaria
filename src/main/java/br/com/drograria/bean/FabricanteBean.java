
package br.com.drograria.bean;


import br.com.drograria.dao.CidadeDAO;
import br.com.drograria.dao.FabricanteDAO;
import br.com.drograria.model.Cidade;
import br.com.drograria.model.Fabricante;
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

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean implements Serializable{
    private Fabricante fabricante;
    private List<Fabricante> listaDeFabricante;
    private List<Fabricante> listaDeFabricanteFiltrados;
    
    // inicia o objeto, é chamado pelo butão novo.
    public void init(){
        fabricante = new Fabricante();
    }
    
    public void prepararEditar(ActionEvent evento){
        fabricante = (Fabricante) evento.getComponent().getAttributes().get("selecaoLinhaEditar");
    }
   
    
    public void salvar(){
            FabricanteDAO fabricanteDAO = new FabricanteDAO();
        try {
            fabricanteDAO.merge(fabricante);
            Messages.addGlobalInfo("Fabricante Cadastrado com sucesso!");
            listaDeFabricante = fabricanteDAO.listar("descricao");
        } catch (Exception erro) {
            Messages.addGlobalFatal("Erro ao cadastrar Cidade!");
        } finally {
            init();
        }
    } 
    
    @PostConstruct
    public void listar(){
        try{
            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            listaDeFabricante = fabricanteDAO.listar("descricao");
        }  catch (Exception erro) {
            Messages.addGlobalError("Erro ao listar Cidade!");
        }
    }  
    
    public void excluir(ActionEvent evento){
        fabricante = (Fabricante) evento.getComponent().getAttributes().get("selecaoLinhaExcluir");
        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        try {
            fabricanteDAO.excluir(fabricante);
            listaDeFabricante = fabricanteDAO.listar("descricao");
            Messages.addGlobalInfo("Cidade excluído com sucesso!");
        } catch (Exception erro) {
            Messages.addGlobalError("A cidade não pode ser excluída!");
        }
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public List<Fabricante> getListaDeFabricante() {
        return listaDeFabricante;
    }

    public void setListaDeFabricante(List<Fabricante> listaDeFabricante) {
        this.listaDeFabricante = listaDeFabricante;
    }

    public List<Fabricante> getListaDeFabricanteFiltrados() {
        return listaDeFabricanteFiltrados;
    }

    public void setListaDeFabricanteFiltrados(List<Fabricante> listaDeFabricanteFiltrados) {
        this.listaDeFabricanteFiltrados = listaDeFabricanteFiltrados;
    }

    

    
}
