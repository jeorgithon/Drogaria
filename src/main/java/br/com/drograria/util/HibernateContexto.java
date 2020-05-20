package br.com.drograria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Essa classe faz o hibernate inicializar junto com
 * o tomcat para melhorar a performace. Deve ser configurado 
 * no arquivo web.xml da seguinte forma:
 * <listener>
        <listener-class>br.com.drograria.util.HibernateContexto</listener-class>  
    </listener>
 * <listener-class> pede o endereço completo da classe.
 * @author jeor_
 */
public class HibernateContexto implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent evento) {
        HibernateUtil.getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent evento) {
        HibernateUtil.getSessionFactory().close();
    }
    
}
