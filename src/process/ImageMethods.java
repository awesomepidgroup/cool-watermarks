package process;

import java.io.File;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.ImageCalculator;

/**
 * Class which contains the calls of the used methods.
 * We have used the ImageJ macro calls for a simple and
 * easy implementation.
 * 
 * @author	Camacho Sosa, José Manuel
 * @author	Muñoz Ríos, Gabriel
 * @author	Pozo Nuñez, José Antonio
 * @version	1.0, 10 Jan 2013
 */
public class ImageMethods {
	
	/**
	 * Attribute which contains the original path of the image.
	 */
	private String originalPath;
	
	/**
	 * Attribute which contains the original name of the image.
	 */
	private String originalName;
	
	/**
	 * Attribute used to name the result image in the
	 * first step, "remove outliers". 
	 */
	private String roName;
	
	/**
	 * Attribute used to name the result image in the
	 * second step, "subtract background".
	 */
	private String sbName;
	
	/**
	 * Attribute used to name the result image in the
	 * third step, "image calculator".
	 */
	private String icName;
	
	/**
	 * Attribute used to name the result image in the
	 * fourth step, "find edges".
	 */
	private String feName;
	
	/**
	 * Attribute used to name the result image in the
	 * fifth step, "make binary".
	 */
	private String mbName;
	
	/**
	 * Attribute used to name the result image in the
	 * sixth step, "Median".
	 */
	private String mName;
	
	/**
	 * Default constructor.
	 * 
	 * @param	originalPath	the original path of the image
	 * @param	tempFolder		the path of the temporal folder
	 */
	public ImageMethods(String originalPath, String tempFolder) {
		this.originalPath = originalPath;
		
		String[] splitedPath = originalPath.split("\\" + File.separator);
		this.originalName = splitedPath[splitedPath.length - 1];
		
		this.roName = tempFolder + originalName + "1ro.png";
		this.sbName = tempFolder + originalName + "2sb.png";
		this.icName = tempFolder + originalName + "3im.png";
		this.feName = tempFolder + originalName + "4fe.png";
		this.mbName = tempFolder + originalName + "5mb.png";
		this.mName = tempFolder + originalName + "6m.png";
	}
	
	/**
	 * Method used to call the appropriate ImageJ method by index.
	 * 
	 * @param	index	the index that indicates the step and the ImageJ method to call
	 * @return			the string with the path of the appropriate image of the method
	 */
	public String invoke(Integer index) {
		switch(index) {
		case 1:
			return removeOutliers();
		case 2:
			return subtractBackground();
		case 3:
			return imageCalculator();
		case 4:
			return findEdges();
		case 5:
			return makeBinary();
		case 6:
			return median();
		default:
			return "";
		}
	}
	
	/**
	 * Method which runs ImageJ macro, "remove outliers".
	 * 
	 * @return	the string with the path of the image appropriate to the method
	 */
	private String removeOutliers() {
		ImagePlus image = IJ.openImage(originalPath);
		
		String command = "Remove Outliers...";
		String options = "radius=20 threshold=3 which=Dark";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "png", roName);
		image.close();
		
		return roName;
	}
	
	/**
	 * Method which runs ImageJ macro, "subtract background".
	 * 
	 * @return the string with the path of the image appropriate to the method
	 */
	private String subtractBackground() {
		ImagePlus image = IJ.openImage(roName);
		
		String command = "Subtract Background...";
		String options = "rolling=50.0 create";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "png", sbName);
		image.close();
		
		return sbName;
	}
	
	/**
	 * Method which runs ImageJ macro, "image calculator".
	 * 
	 * @return	the string with the path of the image appropriate to the method
	 */
	private String imageCalculator() {
		ImagePlus image1 = IJ.openImage(roName);
		
		ImagePlus image2 = IJ.openImage(sbName);
		
		String command = "Subtract create";
		
		ImageCalculator ic = new ImageCalculator();
		ImagePlus image3 = ic.run(command, image1, image2);
		
		IJ.saveAs(image3, "png", icName);
		
		image1.close();
		image2.close();
		image3.close();
		
		return icName;
	}
	
	/**
	 * Method which runs ImageJ macro, "find edges".
	 * 
	 * @return the string with the path of the image appropriate to the method
	 */
	private String findEdges() {
		ImagePlus image = IJ.openImage(icName);
		
		String command = "Find Edges";
		String options = "";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "png", feName);
		image.close();
		
		return feName;
	}
	
	/**
	 * Method which runs ImageJ macro, "make binary".
	 * 
	 * @return	the string with the path of the image appropriate to the method
	 */
	private String makeBinary() {
		ImagePlus image = IJ.openImage(feName);
		
		String command = "Make Binary";
		String options = "";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "png", mbName);
		image.close();
		
		return mbName;
	}
	
	/**
	 * Method which runs ImageJ macro, "median".
	 * 
	 * @return	the string with the path of the image appropriate to the method
	 */
	private String median() {
		ImagePlus image = IJ.openImage(mbName);
		
		String command = "Median...";
		String options = "radius=2";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "png", mName);
		image.close();
		
		return mName;
	}
	
}
