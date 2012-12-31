import java.awt.image.BufferedImage;

import ij.*;
import ij.gui.Roi;
import ij.plugin.filter.BackgroundSubtracter;
import ij.plugin.filter.RankFilters;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

public class Principal {
	
	public static void main(String[] args) {
		
		//Obetenemos la imagen de la ruta
		ImagePlus imp = IJ.openImage("C:/Users/Antonio/Desktop/prueba.jpeg");
		
		
		//Creamos un imageprocessor desde el imageplus
		ImageProcessor ip = imp.getProcessor();  
		

		//******** Remove Outliers *********
		
		RankFilters rf=new RankFilters();
		rf.rank(ip, 20, rf.OUTLIERS, 1, 3);
		
		//BufferedImage bf=ip.getBufferedImage(); En cada fase nos podemos crear un BufferedImage para guardar
		//y mostrar el resultado de cada proceso.
		
		//actualizamos el imageplus
		imp.updateImage();
		
		//Mostramos la imagen procesado 
		imp.show();  
		
		
		//******** Subtract Background *********
	
		BackgroundSubtracter bs= new BackgroundSubtracter();		
		bs.rollingBallBackground(ip, 50, true, false, false, false, false);
		
		imp.updateImage();
		imp.show(); 
	}
}	