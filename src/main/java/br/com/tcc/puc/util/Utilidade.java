package br.com.tcc.puc.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
		
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

}
