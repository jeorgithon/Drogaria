package br.com.drograria.dao;

import br.com.drograria.model.Estado;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author jeor_
 */
public class EstadoDAOTest {
    @Test
    @Ignore
    public void salvar(){
        Estado estado = new Estado();
        estado.setNome("Alagoas");
        estado.setSigla("AL");
        EstadoDAO estadoDAO = new EstadoDAO();
        estadoDAO.salvar(estado);
    }
    @Test
    @Ignore
    public  void listar(){
        EstadoDAO estadoDAO = new EstadoDAO();
        List<Estado> listaDeEstado = estadoDAO.listar();
    
    }
    @Test
    @Ignore
    public void pesquisar(){
        Long codigo = 1L;
        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.pesquisar(codigo);
        
        System.out.println(estado.getNome());
    }
    @Test
    @Ignore
    public void excluir(){
        Estado estado = new Estado();
        EstadoDAO estadoDAO = new EstadoDAO();
        estado = estadoDAO.pesquisar(5L);
        estadoDAO.excluir(estado);
                
    }
    @Test
    @Ignore
    public void editar(){
        Estado estado = new Estado();
        EstadoDAO estadoDAO = new EstadoDAO();
        estado = estadoDAO.pesquisar(6L);
        estado.setSigla("AC");
        estadoDAO.editar(estado);
    }
}
