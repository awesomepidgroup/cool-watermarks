package process;
import java.io.File;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.ImageCalculator;

public class ImageMethods {
	
	private String originalPath;
	private String originalName;
	
	private String roName;
	private String sbName;
	private String icName;
	private String feName;
	private String mbName;
	private String mName;
	
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
	
	private String removeOutliers() {
		ImagePlus image = IJ.openImage(originalPath);
		
		String command = "Remove Outliers...";
		String options = "radius=20 threshold=3 which=Dark";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "png", roName);
		image.close();
		
		return roName;
	}
	
	private String subtractBackground() {
		ImagePlus image = IJ.openImage(roName);
		
		String command = "Subtract Background...";
		String options = "rolling=50.0 create";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "png", sbName);
		image.close();
		
		return sbName;
	}
	
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
	
	private String findEdges() {
		ImagePlus image = IJ.openImage(icName);
		
		String command = "Find Edges";
		String options = "";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "png", feName);
		image.close();
		
		return feName;
	}
	
	private String makeBinary() {
		ImagePlus image = IJ.openImage(feName);
		
		String command = "Make Binary";
		String options = "";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "png", mbName);
		image.close();
		
		return mbName;
	}
	
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
