import java.util.HashMap;
import java.applet.*;
import java.awt.Toolkit;
public class Sons {
    HashMap<String,AudioClip> sons;
    public Sons (){
        AudioClip win = null;
        AudioClip explosao = null;
        try {
        	win = Applet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/win.wav"));
        	explosao = Applet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/explosion.wav"));
        } 
        catch (Exception e) {
        	e.printStackTrace();
        	System.exit(-1);
        }
        sons = new HashMap<String, AudioClip>(2);
        sons.put("explosao", explosao);
        sons.put("vitoria", win);
    }
    public void Tocar(String nome){
    	sons.get(nome).play();
    }
}