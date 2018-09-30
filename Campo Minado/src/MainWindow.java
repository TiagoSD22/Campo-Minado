import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.SwingUtilities;

public class MainWindow {

	private JFrame frame;
			JFrame fim;
	private JLabel info;
			JButton sair;
			JButton novojogo;
			JButton voltarmenu;
	
	public Tabuleiro tab;
		   int n_bandeiras = 0;
		   int n_movimentos = 0;
		   boolean matriz_bandeiras[][];
		   int n_linhas,n_colunas;
		   int n_minas;
		   int largura,altura;
		   Sons som;
		   
	private JLabel Painel;
	        JLabel bandeiras;
	        JLabel Jogadas;
	        JButton novo;
	        JButton botoes[][]; 
	        
	private boolean blocosDesenhados[][];
		   
	public void Recomecar(){
		fim.setVisible(false);
		//Sons.init();
		Painel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/alive.png")));
		n_bandeiras = 0;
		n_movimentos = 0;
		bandeiras.setText("Bandeiras: 0/" + String.valueOf(n_minas));
		Jogadas.setText("Jogadas: 0");
		for(int i = 0;i < n_linhas; i++){
			for(int j = 0; j < n_colunas; j++){
				matriz_bandeiras[i][j] = false;
			}
		}
		tab = new Tabuleiro(n_linhas,n_colunas,n_minas);
		Iniciar_Tabuleiro();
	}
		   
	public void Iniciar_Tabuleiro(){
		for(int i = 0; i < n_linhas; i++){
			for(int j = 0; j < n_colunas; j++){
				botoes[i][j].setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/hide.jpg")));
				blocosDesenhados[i][j] = false;
				RepaintBlock(botoes[i][j]);
			}
		}
	}
	
	public void Mostrar_Mina(int i, int j, JButton botao){
		if(tab.matriz[i][j] == -1){
			botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/mine.png")));
			RepaintBlock(botao);
		}
	}
	
	public void Mostrar_Minas(){
		for(int i = 0; i < n_linhas; i++){
			for(int j = 0; j < n_colunas; j++){
				Mostrar_Mina(i,j,botoes[i][j]);
			}
		}
	}
	
	public void Desenhar_Bloco(int i, int j, JButton botao){
		if(tab.matriz[i][j] != -10 && tab.matriz[i][j] != -1){
			if(!blocosDesenhados[i][j]){
	    		if(matriz_bandeiras[i][j]){
	    			n_bandeiras--;
	    			matriz_bandeiras[i][j] = false;
	    		}
	    		if(tab.matriz[i][j] == 0){
	    			botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/vazio.jpg")));
	    		}
	    		else{
	    			if(tab.matriz[i][j] == 1){
	    				botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/um.jpg")));
	    			}
	    			if(tab.matriz[i][j] == 2){
	    				botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/dois.jpg")));
	    			}
	    			if(tab.matriz[i][j] == 3){
	    				botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/tres.jpg")));
	    			}
	    			if(tab.matriz[i][j] == 4){
	    				botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/quatro.jpg")));
	    			}
	    			if(tab.matriz[i][j] == 5){
	    				botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/cinco.jpg")));
	    			}
	    			if(tab.matriz[i][j] == 6){
	    				botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/seis.jpg")));
	    			}
	    			if(tab.matriz[i][j] == 7){
	    				botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/sete.jpg")));
	    			}
	    			if(tab.matriz[i][j] == 8){
	    				botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/oito.jpg")));
	    			}
	    		}
	    		blocosDesenhados[i][j] = true;
	    		RepaintBlock(botao);
			}
    	}
	}
	
	 public ImageIcon scaleImage(ImageIcon icon, int w, int h){
	        int nw = icon.getIconWidth();
	        int nh = icon.getIconHeight();

	        if(icon.getIconWidth() > w)
	        {
	          nw = w;
	          nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
	        }

	        if(nh > h)
	        {
	          nh = h;
	          nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
	        }

	        return new ImageIcon(icon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
	        //return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
	}
	
	public void RepaintBlock(JButton botao){
		ImageIcon icon = (ImageIcon)botao.getIcon();
		ImageIcon img = scaleImage(icon,botao.getWidth(),botao.getHeight());
		botao.setIcon(img);
	}
	
    public void Desenhar_Tabuleiro(){
    	for(int i = 0; i < n_linhas; i++){
    		for(int j = 0; j < n_colunas; j++){
    			Desenhar_Bloco(i,j,botoes[i][j]);
    		}
    	}
    	bandeiras.setText("Bandeiras: " + String.valueOf(n_bandeiras) + "/" + String.valueOf(n_minas));
    	Jogadas.setText("Jogadas: " + String.valueOf(n_movimentos));
    }
    
    public boolean Bandeira(int i,int j,JButton botao,boolean arg){
    	if(n_bandeiras == n_minas && arg == false){
    		return arg;
    	}
    	if(tab.matriz[i][j] == -10 || tab.matriz[i][j] == -1){
    		arg = !arg;
			if(arg){
				if(n_bandeiras < n_minas){
					n_bandeiras++;
					botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/flag.png")));
					RepaintBlock(botao);
				}
			}
			else{
				n_bandeiras--;
				botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/hide.jpg")));
				RepaintBlock(botao);
			}
			bandeiras.setText("Bandeiras: " + String.valueOf(n_bandeiras) + "/" + String.valueOf(n_minas));
			
		}
    	return arg;
    }
    
    public void Fim_de_Jogo(){
    	tab.Venceu();
    	if(tab.Explodiu == true || tab.Venceu == true){
    		fim.setVisible(true);
    		fim.getContentPane().add(sair);
    		if(tab.Explodiu == true){
    			fim.setTitle("VOCÊ MORREU!");
    			som.Tocar("explosao");
    			Mostrar_Minas();
    			Painel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/death.png")));
    			info.setText("Você acertou uma mina!");
    		}
    		else{
    			fim.setTitle("VOCÊ É DO C@#@!%* P*##@");
    			som.Tocar("vitoria");
    			Painel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/win.png")));
    			info.setText("Parabéns! Você venceu em " + String.valueOf(n_movimentos) + " movimentos!");
    		}
    	}
    }  
	/**
	 * Create the application.
	 */
	public MainWindow(int linhas,int colunas, int minas) {
		n_linhas = linhas;
		n_colunas = colunas;
		n_minas = minas;
		if(n_linhas <= 8){
			altura = 50;
		}
		
		else{
			if(n_linhas <= 16){
				altura = 35;
			}
			else{
				if(n_linhas <= 30){
					altura = 20;
				}
				else{
					if(n_linhas <= 40){
						altura = 15;
					}
					else{
						altura = 11;
					}
				}
			}
		}
		
		if(n_colunas <= 8){
			largura = 50;
		}
		else{
			if(n_colunas <= 36){
				largura = 35;
			}
			else{
				largura = 20;
			}
		}
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/Fontes/Main.png")));
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(10, 10,n_colunas - 1 + (largura*(n_colunas+2)),150 + (altura*(n_linhas + 1)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.frame.setVisible(true);
		
		tab = new Tabuleiro(n_linhas,n_colunas,n_minas);
		blocosDesenhados = new boolean[n_linhas][n_colunas];
		matriz_bandeiras = new boolean[n_linhas][n_colunas];
		for(int i = 0; i < n_linhas; i++){
			for(int j = 0; j < n_colunas; j++){
				matriz_bandeiras[i][j] = false;
			}
		}
		frame.setTitle("Campo Minado " + String.valueOf(n_linhas) + "x" + String.valueOf(n_colunas) + " " + String.valueOf(n_minas) + " Minas");
		frame.getContentPane().setLayout(null);
		botoes = new JButton[n_linhas][n_colunas];
		for(int i = 0; i < n_linhas; i++){
			for(int j = 0; j < n_colunas; j++){
				botoes[i][j] = new JButton("");
				botoes[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				botoes[i][j].setBackground(Color.LIGHT_GRAY);
				botoes[i][j].setBounds(j + (largura + largura*j), i + (100 + altura*i), largura, altura);
				frame.getContentPane().add(botoes[i][j]);
			}
		}
		Iniciar_Tabuleiro();
		som = new Sons();
		for(int i = 0; i < n_linhas; i++){
			for(int j = 0; j < n_colunas; j++){
				botoes[i][j].addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e) {
						int x = 0,y = 0;
						while(e.getSource() != botoes[x][y]){
							y++;
							if(y == n_colunas){
								y = 0;
								x++;
							}
						}
						if(!fim.isVisible() && (tab.matriz[x][y] == -10 || tab.matriz[x][y] == -1)){
							if(SwingUtilities.isLeftMouseButton(e)){
								if(!matriz_bandeiras[x][y]){
									n_movimentos++;
									tab.Processar_Jogada(x,y,botoes[x][y]);
									Desenhar_Tabuleiro();
									Fim_de_Jogo();
								}
							}
							else if(SwingUtilities.isRightMouseButton(e)){
								matriz_bandeiras[x][y] = Bandeira(x,y,botoes[x][y],matriz_bandeiras[x][y]);
							}
						}
					}
					public void mousePressed(MouseEvent e) {
						int x = 0,y = 0;
						while(e.getSource() != botoes[x][y]){
							y++;
							if(y == n_colunas){
								y = 0;
								x++;
							}
						}
			    		if(!fim.isVisible() && (SwingUtilities.isLeftMouseButton(e) && (tab.matriz[x][y] == -10 || tab.matriz[x][y] == -1))){
			    			if(!matriz_bandeiras[x][y]){
			    				Painel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/worried.png")));
			    			}
			    		}
					}
					public void mouseReleased(MouseEvent e) {
						if(!fim.isVisible()){
							Painel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/alive.png")));
						}
					}
				});
			}
		}
		
		Painel = new JLabel("");
		Painel.setBounds(frame.getWidth()/2 - 24, 5, 50, 50);
		Painel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Painel.setToolTipText("CLIQUE AQUI PARA VOLTAR AO MENU");
		Painel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/alive.png")));
		Painel.addMouseListener(new MouseAdapter(){
			Icon icon;
			public void mouseClicked(MouseEvent e){
				frame.dispose();
				if(fim.isVisible()){
					fim.dispose();
				}
				new Menu();
			}
			public void mouseEntered(MouseEvent e) {
				icon = Painel.getIcon();
				Painel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/menubt.png")));
			}
			public void mouseExited(MouseEvent e){
				Painel.setIcon(icon);
			}
		});
		frame.getContentPane().add(Painel);
		
		bandeiras = new JLabel("Bandeiras: 0/" + String.valueOf(n_minas));
		bandeiras.setHorizontalAlignment(SwingConstants.CENTER);
		bandeiras.setBounds(5, 27, 200, 15);
		frame.getContentPane().add(bandeiras);
		
		Jogadas = new JLabel("Jogadas: 0");
		Jogadas.setBounds(frame.getWidth() - 150, 27, 104, 15);
		Jogadas.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(Jogadas);
		
		novo = new JButton("");
		novo.setBackground(Color.white);
		novo.setOpaque(true);
		novo.setBorderPainted(true);
		novo.setToolTipText("Recomeçar\n");
		novo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		novo.setBounds(frame.getWidth()/2 - 12, 58, 25, 25);
		novo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/restart.png")));
		frame.getContentPane().add(novo);
		
		novo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Recomecar();
			}
		});	
		
		fim = new JFrame();
		fim.setBounds(frame.getWidth()/2,frame.getHeight()/2,400,200);
		fim.getContentPane().setLayout(null);
		fim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fim.setVisible(false);
		info = new JLabel();
		info.setBounds(((fim.getWidth() - 1)/2) - 165, 10, 330, 20);
		info.setHorizontalAlignment(SwingConstants.CENTER);
		fim.getContentPane().add(info);
		novo = new JButton("NOVO JOGO");
		novo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		novo.setBounds(((fim.getWidth() - 1)/2) - 70, 90, 140, 20);
		novo.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Recomecar();
			}
		});
		fim.getContentPane().add(novo);
		sair = new JButton("SAIR");
		sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sair.setBounds(((fim.getWidth() - 1)/2) - 35, 130, 70, 20);
		sair.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				frame.dispose();
				fim.dispose();
				System.exit(0);
			}
		});
		voltarmenu = new JButton("VOLTAR PARA O MENU");
		voltarmenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		voltarmenu.setBounds(((fim.getWidth() - 1)/2) - 95, 50, 190,20);
		voltarmenu.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				frame.dispose();
				fim.dispose();
				new Menu();
			}
		});
		fim.getContentPane().add(voltarmenu);
	}
}