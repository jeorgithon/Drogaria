package br.com.drograria.dao;

import br.com.drograria.model.Cidade;
import br.com.drograria.model.Estado;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author jeor_
 */
public class CidadeDAOTest {

    @Test
    @Ignore
    public void salvar() {
        Estado estado = new EstadoDAO().pesquisar(1L);
        Cidade cidade = new Cidade();
        cidade.setNome("Girau do Ponciano");
        cidade.setEstado(estado);
        CidadeDAO cidadeDao = new CidadeDAO();
        cidadeDao.salvar(cidade);
    }

    @Test
    @Ignore
    public void Listar() {
        CidadeDAO cidadeDao = new CidadeDAO();
        List<Cidade> listaDeCidades = cidadeDao.listar();

        for (Cidade cidade : listaDeCidades) {
            System.out.println(cidade.getNome()
                    + " - " + cidade.getEstado().getNome());
        }
    }

    @Test
    @Ignore
    public void pesquisar() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        Cidade cidade = cidadeDAO.pesquisar(1L);

        if (cidade != null) {
            System.out.println(cidade.getNome()
                    + " - " + cidade.getEstado().getNome());
        }
    }

    @Test
    @Ignore
    public void excluir() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        Cidade cidade = cidadeDAO.pesquisar(8L);
        if (cidade != null) {
            cidadeDAO.excluir(cidade);
        }
    }
    
    @Test
    @Ignore
    public void editar(){
        Cidade cidade = new CidadeDAO().pesquisar(9L);
        Estado estado = new EstadoDAO().pesquisar(1L);
        
        if(cidade != null && estado != null){
            cidade.setNome("Arapiraca");
            cidade.setEstado(estado);
            new CidadeDAO().editar(cidade);
        }
    }
    
    @Test
    @Ignore
    public void listarPorEsado() {
        Long esadoCodigo = 4L;
        CidadeDAO cidadeDao = new CidadeDAO();
        List<Cidade> listaDeCidades = cidadeDao.pesquisarPorEstado(esadoCodigo);

        for (Cidade cidade : listaDeCidades) {
            System.out.println(cidade.getNome()
                    + " - " + cidade.getEstado().getNome());
        }
    }
}
