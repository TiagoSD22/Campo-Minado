import java.awt.Toolkit;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tabuleiro{
	
	public int matriz[][];
	       int linhas;
	       int colunas;
	       boolean Explodiu = false;
	       boolean Venceu = false;
	
	public Tabuleiro(int linhas, int colunas,int qt){
		this();
		this.linhas = linhas;
		this.colunas = colunas;
		Inicializar_Tabuleiro();
		Distribuir_Minas(qt);
	}

	public Tabuleiro(){}

	public void Inicializar_Tabuleiro(){
		matriz = new int[linhas][colunas];
		int i,j;
		for(i = 0; i < linhas; i++){
			for(j = 0; j < colunas; j++){
				matriz[i][j] = -10;
			}
		}
	}

	public void Distribuir_Mina(){
		int i,j;
		Random rand = new Random();
		i = rand.nextInt(linhas);
		j = rand.nextInt(colunas);
		if(matriz[i][j] == -1){
			do{
				i = rand.nextInt(linhas);
				j = rand.nextInt(colunas);
			}while(matriz[i][j] == -1);
		}
		matriz[i][j] = -1;
	} 

	public void Distribuir_Minas(int qt){
		int Quantidade;
		for(Quantidade = 0; Quantidade < qt; Quantidade++){
			Distribuir_Mina();
		}
	}

	public int Numero_de_Minas_na_Vizinhanca(int i,int j){
		int x,y,n_minas = 0;
		for(x = -1; x <= 1; x++){
			for(y = -1; y <= 1; y++){
				if(i + x >= 0 && i + x < linhas && j + y >= 0 && j + y < colunas){
					if(matriz[i+x][j+y] == -1){
						n_minas++;
					}
				}
			}
		}
		return n_minas;
	}
	
	public void Abrir_Vizinhanca(int i, int j){
		int x,y;
		for(x = -1; x <= 1; x++){
			for(y = -1; y <= 1; y++){
				if(i + x >= 0 && i + x < linhas && j + y >= 0 && j + y < colunas){
					if(matriz[i+x][j+y] == -10){
						if(Numero_de_Minas_na_Vizinhanca(i+x,j+y) == 0){
							matriz[i+x][j+y] = 0; 
							Abrir_Vizinhanca(i+x,j+y);
						}
						else{
							matriz[i+x][j+y] = Numero_de_Minas_na_Vizinhanca(i+x,j+y);
						}
					}
				}
			}
		}
	}
	
	public void Processar_Jogada(int i, int j, JButton botao){
		if(matriz[i][j] == -1){
			botao.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/explosion.png")));
			matriz[i][j] = -2;
			Explodiu = true;
		}
		else{
			if(matriz[i][j] == -10){
				if(Numero_de_Minas_na_Vizinhanca(i,j) == 0){
					Abrir_Vizinhanca(i,j);
				}
				else{
					matriz[i][j] = Numero_de_Minas_na_Vizinhanca(i,j);
				}
			}
		}
	}

	public void Venceu(){
		int i,j,campos_vazios = 0;
		for(i = 0; i < linhas; i++){
			for(j = 0; j < colunas; j++){
				if(matriz[i][j] == -10){
					campos_vazios++;
				}
			}
		}
		if(campos_vazios == 0){
			Venceu = true;
		}
	}
}