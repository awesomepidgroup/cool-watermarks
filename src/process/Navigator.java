package process;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import util.Strings;

/**
 * Class that works with all the logic of the application
 * between the classes MainWindow and ImageMethods.
 * 
 * @author	Camacho Sosa, José Manuel
 * @author	Muñoz Ríos, Gabriel
 * @author	Pozo Nuñez, José Antonio
 * @version	1.0, 10 Jan 2013
 */
public class Navigator {
	
	/**
	 * Attribute with the index value of the current step.
	 */
	private int index;
	
	/**
	 * Attribute with the index value of the last step.
	 */
	private int lastIndex;
	
	/**
	 * Attribute which associates an index with the appropriate
	 * NavigatorResponse object.
	 */
	private Map<Integer, NavigatorResponse> stepList;
	
	/**
	 * Attribute which contains the original path of an image.
	 */
	private String originalPath;
	
	/**
	 * Attribute which contains the path of the temporal folder.
	 */
	private String tempFolder;
	
	/**
	 * Object of the class ImageMethods. It is used to invoke 
	 * the methods of that class.
	 */
	private ImageMethods methods;
	
	/**
	 * Default constructor.
	 * 
	 * @param	path	the path of the original image
	 */
	public Navigator(String path) {
		this.index = 0;
		this.lastIndex = 6;
		this.stepList = new HashMap<Integer, NavigatorResponse>(lastIndex + 1);
		this.originalPath = path;
		
		tempFolder = "temp";
		File folder = new File(tempFolder);
		if(folder.exists()) {
			for(File f : folder.listFiles()) {
				// We assume that it is always a file.
				f.delete();
			}
			folder.delete();
		}
		folder.mkdir();
		
		this.methods = new ImageMethods(originalPath, tempFolder + File.separator);
	}
	
	/**
	 * Opens an image file.
	 * 
	 * @return	a NavigatorResponse object which the appropriate result
	 */
	public NavigatorResponse open(){
		String explanation = Strings.getExplanation(index);
		NavigatorResponse response = new NavigatorResponse("", false, false, true, true, explanation);
		
		stepList.put(index, response);
		
		return response;
	}
	
	/**
	 * Saves an image file.
	 * 
	 * @param	path	the path for save the image
	 */
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
	
	/**
	 * Goes to the next step if exists.
	 * 
	 * @return	a NavigatorResponse object with the appropriate result
	 */
	public NavigatorResponse next(){
		NavigatorResponse response = null;
		index++;
		
		response = stepList.get(index);
		
		if(response == null) {
			String filePath = methods.invoke(index);
			String explanation = Strings.getExplanation(index);
			if(index == lastIndex) {
				response = new NavigatorResponse(filePath, true, true, false, false, explanation);
			} else {
				response = new NavigatorResponse(filePath, true, true, true, true, explanation);
			}
			stepList.put(index, response);
		}
		
		return response;
	}
	
	/**
	 * Goes to the previous step if exists.
	 * 
	 * @return	a NavigatorResponse object with the appropriate result
	 */
	public NavigatorResponse previous(){
		index--;
		NavigatorResponse response = stepList.get(index);

		return response;
	}
	
	/**
	 * Goes to the last step.
	 * 
	 * @return	a NavigatorResponse object with the appropriate result
	 */
	public NavigatorResponse toEnd(){
		NavigatorResponse response = null;
		
		for(int i = index; i < lastIndex; i++) {
			response = next();
		}
		
		return response;
	}
		
}
