package process;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import util.Strings;

public class Navigator {
	
	private Map<Integer, NavigatorResponse> stepList;
	private int index;
	private String originalPath;
	private String tempFolder;
	private ImageMethods methods;
	
	public Navigator(String path) {
		this.stepList = new HashMap<Integer, NavigatorResponse>(7);
		this.index = 0;
		this.originalPath = path;
		
		tempFolder = "temp";
		File folder = new File(tempFolder);
		boolean exists = folder.exists();
		if(!exists) {
			folder.mkdir();
		}
		
		this.methods = new ImageMethods(originalPath, tempFolder + File.separator);
	}
	
	public NavigatorResponse open(){
		String explanation = Strings.getExplanation(index);
		NavigatorResponse response = new NavigatorResponse("", false, false, true, true, explanation);
		
		stepList.put(index, response);
		
		return response;
	}
	
	
	public void save(String path){
		NavigatorResponse response = stepList.get(index);

		try {
		
			FileOutputStream saveFile = new FileOutputStream(path);
			ObjectOutputStream save = ObjectOutputStream(saveFile);
			File image = new File(path);

			save.writeObject(image);

			save.close();

		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public NavigatorResponse next(){
		NavigatorResponse response = null;
		
		index++;
		response = stepList.get(index);
		
		if(response == null) {
			if(index == 6) {
				String filePath = methods.invoke(index);
				String explanation = Strings.getExplanation(index);
				response = new NavigatorResponse(filePath, true, true, false, false, explanation);
			} else {
				String filePath = methods.invoke(index);
				String explanation = Strings.getExplanation(index);
				response = new NavigatorResponse(filePath, true, true, true, true, explanation);
			}
		}
		
		return response;
	}
	
	
	public NavigatorResponse previous(){
		NavigatorResponse response = null;

		index--;
		response = stepList.get(index);

		return reponse;
	}
	
	public NavigatorResponse toEnd(){
		NavigatorResponse response = null;

		for(int = 0; i < 7; i++) {
			response = next();
		}

		return response;
	}
		
}
