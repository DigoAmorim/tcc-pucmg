package br.com.tcc.puc.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
		
public class Utilidade {
	
	/**
	 * 
	 * Método para obtenção dos valores no message.properties.
	 * 
	 */
	public static String getMessage(String key, Object params[]) {

        String text;
        ResourceBundle bundle = ResourceBundle.getBundle("message");

        try {
            text = bundle.getString(key);
        } catch (MissingResourceException e) {
            text = "?? key " + key + " not found ??";
        }

        if (params != null) {
            MessageFormat mf = new MessageFormat(text);
            text = mf.format(params, new StringBuffer(), null).toString();
        }

        return text;
    }
	
	/**
	 * Méotodo que adiciona mensagens de retorno ao view.
	 * 
	 * @param mensagem:   Texto da mensagem
	 * @param severidade: Tipo da severidade
	 */
	public static void retornarMensagem(String mensagem, Severity severidade) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if (severidade.equals(FacesMessage.SEVERITY_INFO)) {
			contexto.addMessage(null, new FacesMessage(severidade, mensagem, ""));
		} else if (severidade.equals(FacesMessage.SEVERITY_WARN)) {
			contexto.addMessage(null, new FacesMessage(severidade, mensagem, ""));
		} else {
			contexto.addMessage(null, new FacesMessage(severidade, mensagem, ""));
		}
	}
}
