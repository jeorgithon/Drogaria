package br.com.drograria.dao;

import br.com.drograria.model.Cliente;
import br.com.drograria.model.Pessoa;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;


/**
 *
 * @author jeor_
 */
public class ClienteDAOTest {
    
    @Test
    @Ignore
    public void salvar(){
        Pessoa pessoa = new PessoaDAO().pesquisar(2L);
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setDataDoCadastro(
                new Date());
        cliente.setLiberado(false);
        cliente.setPessoa(pessoa);
        
        clienteDAO.salvar(cliente);
        
    }
    
}

       