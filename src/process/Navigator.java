package process;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import util.Strings;

public class Navigator {
	
	private int index;
	private int lastIndex;
	private Map<Integer, NavigatorResponse> stepList;
	private String originalPath;
	private String tempFolder;
	private ImageMethods methods;
	
	public Navigator(String path) {
		this.index = 0;
		this.lastIndex = 6;
		this.stepList = new HashMap<Integer, NavigatorResponse>(lastIndex + 1);
		this.originalPath = path;
		
		tempFolder = "temp";
		File folder = new File(tempFolder);
		if(folder.exists()) {
			for(File f : folder.listFiles()) {
				f.delete(); //We suppose that always is a file
			}
			folder.delete();
		}
		folder.mkdir();
		
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
			InputStream input = new FileInputStream(response.getImagePath());
			OutputStream output = new FileOutputStream(path);
			
			byte[] buffer = new byte[1024];
			int length;
			
			while ((length = input.read(buffer)) > 0) {
			   output.write(buffer, 0, length);
			}
			
			input.close();
			output.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public NavigatorResponse next(){
		NavigatorResponse response = null;
		index++;
		
		response = stepList.get(index);
		
		if(response == null) {
			if(index == lastIndex) {
				String filePath = methods.invoke(index);
				String explanation = Strings.getExplanation(index);
				response = new NavigatorResponse(filePath, true, true, false, false, explanation);
				stepList.put(index, response);
			} else {
				String filePath = methods.invoke(index);
				String explanation = Strings.getExplanation(index);
				response = new NavigatorResponse(filePath, true, true, true, true, explanation);
				stepList.put(index, response);
			}
		}
		
		return response;
	}
	
	
	public NavigatorResponse previous(){
		index--;
		NavigatorResponse response = stepList.get(index);

		return response;
	}
	
	public NavigatorResponse toEnd(){
		NavigatorResponse response = null;
		
		for(int i = index; i < lastIndex; i++) {
			response = next();
		}
		
		return response;
	}
		
}
