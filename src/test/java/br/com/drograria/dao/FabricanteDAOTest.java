
package br.com.drograria.dao;

import br.com.drograria.model.Fabricante;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author jeor_
 */
public class FabricanteDAOTest {
    @Test
    @Ignore
    public void salvar(){
        Fabricante fabricante = new Fabricante();
        fabricante.setDescricao("Bayer");
        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        fabricanteDAO.salvar(fabricante);
    }
}
