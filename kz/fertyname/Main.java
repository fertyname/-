package kz.fertyname;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import kz.fertyname.util.Util;
import kz.fertyname.work1.Cipher;
import kz.fertyname.work3.Manager;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		Util.m_Print("default", "1 -  Асимметричное шифрование", 20);
		Util.m_Print("default", "2 -  Шифрование цезаря ", 20);
		Util.m_Print("default", "3 -  Формы авторизации ", 20);
		Util.m_Print("default", "Выберите действие: ", 20);
		String req = scanner.nextLine();
		switch(req) {
		case "1":
			Util.m_Print("default", "Строка для шифрования: ", 20);
			String data = scanner.nextLine();
			try {
				Cipher.manager(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "2":
			Util.m_Print("default", "Строка для шифрования: ", 20);
			String data1 = scanner.nextLine();
			Util.m_Print("default", "На сколько шифр: ", 20);
			int shift = scanner.nextInt();
			try {
				kz.fertyname.work2.Cipher.manager(data1,shift);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "3":
			try {
		    	SwingUtilities.invokeLater(()->{
		    		Manager manager = new Manager();
		    		manager.setVisible(true);
		    	});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
}
