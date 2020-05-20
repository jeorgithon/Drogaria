
package br.com.drograria.bean;


import br.com.drograria.dao.FabricanteDAO;
import br.com.drograria.dao.ProdutoDAO;
import br.com.drograria.model.Fabricante;
import br.com.drograria.model.Produto;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author jeor_
 */

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean implements Serializable{
    private Produto produto;
    private List<Produto> listaDeProdutos;
    private List<Produto> listaDeProdutoFiltrados;
    private List<Fabricante> listaDeFabricante;
    
    // inicia o objeto, é chamado pelo butão novo.
    public void novo(){
        produto = new Produto();
        try{
            listaDeFabricante = new FabricanteDAO().listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalFatal("Erro ao Listar Fabricantes");
        }
    }
    
    public void editar(ActionEvent evento){
        produto = (Produto) evento.getComponent().getAttributes().get("selecaoLinhaEditar");
        try {
            listaDeFabricante = new FabricanteDAO().listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalFatal("Erro ao Listar Fabricantes");
        }
    }
   
    
    public void salvar(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            if(produto.getCaminhoDoArquivoTemp() == null){
                Messages.addGlobalError("Campo obrigatóroi - foto.");
                return;
            }
            /*
            o merge foi editado para retornar um produto, assim
            podemos pegar o id do produto salvo/editado e salvar 
            a imagem correpondente com nome igual ao id do produto.
            */
            Produto produtoRetorno = produtoDAO.merge(produto);
            
            /*
            pega o caminho temporário
            */
            Path origem = Paths.get(produto.getCaminhoDoArquivoTemp());
            /*
            pega o caminho de destino informado.
            */
            Path destino = Paths.get("D:\\OneDrive - ufrpe.br\\"
                    + "CC UFRPE\\Projeto Java Web\\Projeto 9BPM\\"
                    + "projeto\\Drograria\\imagensSalvas\\UploadsProduto\\"
                    + produtoRetorno.getCodigo() +".png");
            
            /*
            copia o arquivo da pasta temporária para a pasta definitiva
            */
            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
            Messages.addGlobalInfo("Produto Cadastrado com sucesso!");
            listaDeProdutos = produtoDAO.listar();
        } catch (Exception erro) {
            Messages.addGlobalError("Erro ao cadastrar Cidade!");
            erro.printStackTrace();
        } finally {
            novo();
        }
    } 
    
    @PostConstruct
    public void listar(){
        try{
            ProdutoDAO produtoDAO = new ProdutoDAO();
            listaDeProdutos = produtoDAO.listar();
        }  catch (Exception erro) {
            Messages.addGlobalError("Erro ao listar Cidade!");
           // erro.printStackTrace();
        }
    }  
    
    public void excluir(ActionEvent evento){
        produto = (Produto) evento.getComponent().getAttributes().get("selecaoLinhaExcluir");
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            produtoDAO.excluir(produto);
            Path arquivo = Paths.get("D:\\OneDrive - ufrpe.br\\"
                    + "CC UFRPE\\Projeto Java Web\\Projeto 9BPM\\"
                    + "projeto\\Drograria\\imagensSalvas\\UploadsProduto\\"
                    + produto.getCodigo()+".png");
            Files.deleteIfExists(arquivo);
            listaDeProdutos = produtoDAO.listar();
            Messages.addGlobalInfo("Produto excluído com sucesso!");
        } catch (Exception erro) {
            Messages.addGlobalError("O Produto não pode ser excluída!");
            erro.printStackTrace();
        }
    }
    
    
    public  void uploadDeArquivo(FileUploadEvent event){
//        pega o arquivo de upload da pela.
        UploadedFile arquivoUpload =event.getFile();
        try {
//            cria um arquivo temporário.
            Path arquivoTemp =  Files.createTempFile(null, null);
//            copia o arquivo capiturado para o arquivo temporário.
            Files.copy(arquivoUpload.getInputstream(),
                    arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
//            guarda o caminho do arquivo temporário.
            produto.setCaminhoDoArquivoTemp(arquivoTemp.toString());
            
            
            System.err.println(produto.getCaminhoDoArquivoTemp());
        } catch (IOException erro) {
            Messages.addGlobalError("Erro no upload do arquivo");
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public void setListaDeProdutos(List<Produto> listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }

    public List<Produto> getListaDeProdutoFiltrados() {
        return listaDeProdutoFiltrados;
    }

    public void setListaDeProdutoFiltrados(List<Produto> listaDeProdutoFiltrados) {
        this.listaDeProdutoFiltrados = listaDeProdutoFiltrados;
    }

    public List<Fabricante> getListaDeFabricante() {
        return listaDeFabricante;
    }

    public void setListaDeFabricante(List<Fabricante> listaDeFabricante) {
        this.listaDeFabricante = listaDeFabricante;
    }

    

    
}
