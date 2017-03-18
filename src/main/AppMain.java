package main;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import View.ViewChecklist;

public class AppMain {
	public static String HOST; 
	public static void main(String args[]) {
		
		try {
			setUIFont(new javax.swing.plaf.FontUIResource("Tahoma", Font.PLAIN,
					12));
//			Properties prop = new Properties();
//			InputStream input = new FileInputStream("config.properties");
//			prop.load(input);
			HOST = //"https://api.metamug.com";
				JOptionPane.showInputDialog("Enter the server url. e.g. http://192.168.1.102");
			//prop.getProperty("host");
		} catch (Exception e) {
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ViewChecklist vcl = new ViewChecklist("Checklist");
				vcl.setVisible(true);
			}
		});
		// System.out.println(new CheckListService().getTasks());;
	}

	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource)
				UIManager.put(key, f);
		}
	}

}
