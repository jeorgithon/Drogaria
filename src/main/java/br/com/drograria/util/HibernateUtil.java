/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drograria.util;

import javax.swing.JOptionPane;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author jeor_
 */
public class HibernateUtil {
     private static SessionFactory sessionFactory = criarFabricaDeSessoes();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    private static SessionFactory criarFabricaDeSessoes(){
    
        try {
            Configuration configuracao = new Configuration().configure();
            
            ServiceRegistry registro = 
                    new StandardServiceRegistryBuilder().applySettings(
                            configuracao.getProperties()).build();
           SessionFactory fabrica = configuracao.buildSessionFactory();
//           JOptionPane.showMessageDialog(null,"A Sessão criada com sucesso ");
           return fabrica;
        }
        catch (Throwable ex) {
            JOptionPane.showMessageDialog(null,"A Sessão não pode ser criada: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}
