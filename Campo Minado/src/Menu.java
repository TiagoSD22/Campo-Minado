import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.UIManager;

public class Menu {

	private JFrame frame;
		   JButton op1,op2,op3,op4,op5,play;
		   JSpinner linhas,colunas,minas;
		   JLabel lblLinhas,lblColunas,lblMinas;
	private int lp = 2,cp = 2,mp = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame("Campo Minado Menu");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Fontes/Main.png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setContentPane(new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/menu.jpg"))));
		JLabel texto = new JLabel("ESCOLHA UM MODO DE JOGO");
		texto.setHorizontalAlignment(SwingConstants.CENTER);
		texto.setForeground(Color.DARK_GRAY);
		texto.setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		texto.setOpaque(true);
		texto.setBounds((frame.getWidth() - 1)/2 - 100,25,200,20);
		frame.getContentPane().add(texto);
		
		op1 = new JButton("8x8 10 Minas");
		op1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		op1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new MainWindow(8,8,10);
			}
		});
		op1.setBounds(frame.getWidth()/2 - 5 - 140, 68, 140, 25);
		frame.getContentPane().add(op1);
		
		op2 = new JButton("16x16 40 Minas");
		op2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		op2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new MainWindow(16,16,40);
			}
		});
		op2.setBounds(frame.getWidth()/2 + 5, 68, 140, 25);
		frame.getContentPane().add(op2);
		
		op3 = new JButton("16x30 99 Minas");
		op3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		op3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new MainWindow(16,30,99);
			}
		});
		op3.setBounds(frame.getWidth()/2 - 5 - 140, 103, 140, 25);
		frame.getContentPane().add(op3);
		
		op5 = new JButton("16x36 150 Minas");
		op5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		op5.setFont(new Font("Dialog", Font.BOLD, 10));
		op5.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				frame.dispose();
				new MainWindow(16,36,150);
			}
		});
		op5.setBounds(230, 103, 140, 25);
		frame.getContentPane().add(op5);
		
		op4 = new JButton("Personalizado");
		op4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		op4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				linhas.setVisible(true);
				colunas.setVisible(true);
				minas.setVisible(true);
				play.setVisible(true);
				lblLinhas.setVisible(true);
				lblColunas.setVisible(true);
				lblMinas.setVisible(true);
			}
		});
		op4.setBounds(155, 167, 140, 25);
		frame.getContentPane().add(op4);
		
		linhas = new JSpinner();
		linhas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lp = (int)linhas.getValue();
				minas.setModel(new SpinnerNumberModel(mp,1,lp*cp,1));
			}
		});
		linhas.setModel(new SpinnerNumberModel(2,2,50,1));
		linhas.setBounds(114, 215, 72, 20);
		linhas.setVisible(false);
		frame.getContentPane().add(linhas);
		
		colunas = new JSpinner();
		colunas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				cp = (int)colunas.getValue();
				minas.setModel(new SpinnerNumberModel(mp,1,lp*cp,1));
			}
		});
		colunas.setModel(new SpinnerNumberModel(2,2,50,1));
		colunas.setBounds(266, 215, 72, 20);
		colunas.setVisible(false);
		frame.getContentPane().add(colunas);
		
		minas = new JSpinner();
		minas.setModel(new SpinnerNumberModel(mp,1,lp*cp,1));
		minas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				mp = (int)minas.getValue();
			}
		});
		minas.setBounds(114, 240, 72, 20);
		minas.setVisible(false);
		frame.getContentPane().add(minas);
		
		play = new JButton("Jogar");
		play.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		play.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new MainWindow(lp,cp,mp);
			}
		});
		play.setBounds(266, 240, 72, 20);
		play.setVisible(false);
		frame.getContentPane().add(play);
		
		lblLinhas = new JLabel("Linhas");
		lblLinhas.setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		lblLinhas.setForeground(Color.DARK_GRAY);
		lblLinhas.setOpaque(true);
		lblLinhas.setBounds(33, 217, 70, 15);
		lblLinhas.setVisible(false);
		frame.getContentPane().add(lblLinhas);
		
		lblMinas = new JLabel("Minas");
		lblMinas.setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		lblMinas.setForeground(Color.DARK_GRAY);
		lblMinas.setOpaque(true);
		lblMinas.setBounds(33, 240, 70, 15);
		lblMinas.setVisible(false);
		frame.getContentPane().add(lblMinas);
		
		lblColunas = new JLabel("Colunas");
		lblColunas.setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		lblColunas.setForeground(Color.DARK_GRAY);
		lblColunas.setOpaque(true);
		lblColunas.setBounds(356, 215, 70, 15);
		lblColunas.setVisible(false);
		frame.getContentPane().add(lblColunas);
	}
}
