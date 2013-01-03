package process;

import java.util.LinkedList;
import java.util.List;

import util.Strings;



public class Navigator {

	private List<NavigatorResponse> lista_procesos;
	private int indice;
	private String original_path;
	
	
	
	public String open(String path){
		
		lista_procesos= new LinkedList<NavigatorResponse>();
		indice=1;
		original_path=path;
		String texto=Strings.getExplanation(indice);
		
		return texto;
	}
	
	
	public void save(String path){
		
		
	}
	
	public NavigatorResponse next(){
		
		NavigatorResponse nr = null;
		
		switch (indice) {
		case 1:
			
			ImageMethods.removeOutliers(original_path, "1.jpg");
			String texto=Strings.getExplanation(indice + 1);
			nr= new NavigatorResponse("1.jpg", false, true, true, texto);
			lista_procesos.add(nr);
			
			break;

		default:
			break;
		}
		
		indice++;	
		return nr;
	}
	
	
	public NavigatorResponse previous(){
		
		return null;
	}
	
	public void toEnd(){
		
		
	}
	
	
		
}
