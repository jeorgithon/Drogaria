
package br.com.drograria.bean;


import br.com.drograria.dao.ClienteDAO;
import br.com.drograria.dao.FuncionarioDAO;
import br.com.drograria.dao.ProdutoDAO;
import br.com.drograria.dao.VendaDAO;
import br.com.drograria.model.Cliente;
import br.com.drograria.model.Funcionario;
import br.com.drograria.model.ItemVenda;
import br.com.drograria.model.Produto;
import br.com.drograria.model.Venda;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

@ManagedBean(name = "MBVenda")
@ViewScoped
public class VendaBean implements Serializable{
    private List<Produto> listaDeProdutos;
    private List<ItemVenda> listaDeItensVenda;
    private List<Cliente> listaDeCliente;
    private List<Funcionario> listaDeFuncionario;
    private Venda venda;
    
    @PostConstruct
    public void novo(){
        try{
            ProdutoDAO produtoDAO = new ProdutoDAO();
            listaDeProdutos = produtoDAO.listar("descricao");
            listaDeItensVenda = new ArrayList<ItemVenda>();
            venda = new Venda();
            venda.setPrecoTotal(new BigDecimal("0.00"));
        }  catch (Exception erro) {
            Messages.addGlobalError("Erro ao carregar a tela de vendas.");
           // erro.printStackTrace();
        }
    }  
    
    public void salvar(){
        if(venda.getPrecoTotal().signum() == 0){
            Messages.addGlobalError("Carrinho de compra fazio.");
            return;
        }
        try {
            VendaDAO vendaDAO = new VendaDAO();
            vendaDAO.salvar(venda, listaDeItensVenda);
            Messages.addGlobalInfo("venda salva com sucesso.");
            
        } catch (Exception e) {
            Messages.addGlobalError("Erro ao salvar a vendas.");
        }
        finally {
            novo();
        }
        
    }
    
     public void adicionar(ActionEvent evento){
        Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
        
        int achou = -1;
        for(int posicao = 0; posicao < listaDeItensVenda.size(); posicao++){
            if(listaDeItensVenda.get(posicao).getProduto().equals(produto)){
               achou = posicao; 
            }
        }
        
        if(achou == -1){
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProduto(produto);
            itemVenda.setValorParcial(produto.getPreco());
            itemVenda.setQuantidade(new Short("1"));

            listaDeItensVenda.add(itemVenda);
        } else {
            ItemVenda itemVenda = listaDeItensVenda.get(achou);
            /*
            se somar os valores o java transforma em int, por isso é
            preciso dar um new Short no resultado para transformar novamente 
            em short. Como o new Short recebe uma string, foi preciso concatenas
            com aspas vazia para o java transformar o reltado em string e depois 
            em short.
            */
            itemVenda.setQuantidade(new Short(itemVenda.getQuantidade()+1+""));
            /*
            Se o valor fosse double era só multiplicar preço*quantidade, mas o BigDecimal
            não aceita essa operação, para isso temos o multipy, porém, por sua vez, só multiplica
            dois BigDecimal. por isso a quantidade foi transformada em BigDecimal.
            */
            itemVenda.setValorParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
            
        }
        calcularPrecoTotal();
     }
     
     public void remover(ActionEvent evento){
         ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");
         
         int achou = -1;
         for(int posicao = 0; posicao < listaDeItensVenda.size(); posicao++){
             if(listaDeItensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())){
                 achou = posicao;
             }
         }
         
         if(achou > -1){
             listaDeItensVenda.remove(achou);
        } 
         calcularPrecoTotal();
     }
     
     public void calcularPrecoTotal(){
         venda.setPrecoTotal(new BigDecimal("0.00"));
         ItemVenda itemVenda = new ItemVenda();
         for(int posicao= 0; posicao < listaDeItensVenda.size(); posicao++){
             itemVenda = listaDeItensVenda.get(posicao);
             venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getValorParcial()));
         }
     }
     
     public void finalizar(){
         venda.setHorario(new Date());
         venda.setCliente(null);
         venda.setFuncionario(null);
         try {
             listaDeFuncionario = new FuncionarioDAO().listarOrdenado();
             listaDeCliente = new ClienteDAO().listarOrdenado();
         } catch (Exception e) {
             Messages.addGlobalError("Erro ao carregar os clientes e funcionários.");
         }
     }
     
    public List<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public void setListaDeProdutos(List<Produto> listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }
    
    public List<ItemVenda> getListaDeItensVenda() {
        return listaDeItensVenda;
    }

    public void setListaDeItensVenda(List<ItemVenda> listaDeItensVenda) {
        this.listaDeItensVenda = listaDeItensVenda;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Cliente> getListaDeCliente() {
        return listaDeCliente;
    }

    public void setListaDeCliente(List<Cliente> listaDeCliente) {
        this.listaDeCliente = listaDeCliente;
    }

    public List<Funcionario> getListaDeFuncionario() {
        return listaDeFuncionario;
    }

    public void setListaDeFuncionario(List<Funcionario> listaDeFuncionario) {
        this.listaDeFuncionario = listaDeFuncionario;
    }
 
    
}
