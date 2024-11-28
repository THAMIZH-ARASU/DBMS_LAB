package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import ui.AuthenticateLogin;
import ui.LoadingScreen;

public class MainApp {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadingScreen frame1 = new LoadingScreen();
					frame1.setLocationRelativeTo(null);
					frame1.setVisible(true);
					
					new Timer(3500, new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	((Timer) e.getSource()).stop();
			            	
			            	AuthenticateLogin frame = new AuthenticateLogin();
							frame.setVisible(true);
							frame.setLocationRelativeTo(null);
			            }
			        }).start();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

