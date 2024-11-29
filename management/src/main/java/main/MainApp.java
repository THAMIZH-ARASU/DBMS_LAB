package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import ui.AuthenticateLogin;
import ui.LoadingScreen;
import ui.SplashScreen;

public class MainApp {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame1 = new SplashScreen(null, true);
					frame1.setVisible(true);
					
					AuthenticateLogin frame = new AuthenticateLogin();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

