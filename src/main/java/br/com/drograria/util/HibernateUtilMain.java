
package br.com.drograria.util;

import org.hibernate.Session;

/**
 *
 * @author jeor_
 */
public class HibernateUtilMain {
    public static void main(String[] args) {
        Session sessao= HibernateUtil.getSessionFactory().openSession();
        sessao.close();
        HibernateUtil.getSessionFactory().close();
    }
}
