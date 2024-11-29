package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoadingScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadingScreen frame = new LoadingScreen();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoadingScreen() {
		
		setAlwaysOnTop(true);
		setUndecorated(true);
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(9, 11, 94));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 198, 198));
		progressBar.setString("30%");
		progressBar.setBounds(10, 60, 430, 29);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel = new JLabel("Loading..");
		lblNewLabel.setFont(new Font("Noto Sans Georgian", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel.setBackground(new Color(9, 11, 94));
		lblNewLabel.setForeground(new Color(147, 255, 255));
		lblNewLabel.setBounds(10, 11, 284, 38);
		contentPane.add(lblNewLabel);
		
		 // Simulate loading process
		timer = new Timer(20, new ActionListener() {
            int progress = 0;

            public void actionPerformed(ActionEvent e) {
                if (progress >= 100) {
                    timer.stop();
                    // Loading complete, do something here
                    System.out.println("Loading complete!");
                    dispose();
                } else {
                    progress++;
                    progressBar.setValue(progress);
                }
            }
        });

        timer.start();
	}
}
