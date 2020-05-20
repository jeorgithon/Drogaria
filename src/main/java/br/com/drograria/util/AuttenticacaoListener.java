/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drograria.util;

import br.com.drograria.bean.AutenticacaoBean;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.omnifaces.util.Faces;

/**
 * https://balusc.omnifaces.org/2006/09/debug-jsf-lifecycle.html
 * https://www.youtube.com/watch?v=oHG04WIZLIk&list=PL_GwGUsBlNyfI0W3ggfffhBdJUqB4981Z&index=158
 * @author jeor_
 */
public class AuttenticacaoListener implements PhaseListener{

    /*
    depois da fase
    */
    @Override
    public void afterPhase(PhaseEvent event) {
        String paginaAtual = Faces.getViewId();
        
        boolean hePaginaDeAutenticacao = paginaAtual.contains("index.xhtml");
        if(!hePaginaDeAutenticacao){
            AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("MBAutenticacao");
            if(autenticacaoBean == null){
                Faces.navigate("/index.xhtml");
            }
            else if(autenticacaoBean.getUsuarioLogin() == null ){
                Faces.navigate("/index.xhtml");
            }
        }
//        System.out.println("AutenticaçãoBean: " + autenticacaoBean);
//        System.out.println("Página Atual: "+paginaAtual);
    }

    /*
    ante da fase
    */
    @Override
    public void beforePhase(PhaseEvent event) {
    }

    /*
    fase atual de autntcação
    */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}