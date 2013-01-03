package process;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.ImageCalculator;

public class ImageMethods {
	
	public static void removeOutliers(String inPath, String outPath) {
		ImagePlus image = IJ.openImage(inPath);
		
		String command = "Remove Outliers...";
		String options = "radius=20 threshold=3 which=Dark";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "jpg", outPath);
		image.close();
	}
	
	public static void subtractBackground(String inPath, String outPath) {
		ImagePlus image = IJ.openImage("1.jpg");
		
		String command = "Subtract Background...";
		String options = "rolling=50.0 create";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "jpg", "2");
		image.close();
	}
	
	public static void imageCalculator(String inPath1, String inPath2, String outPath) {
		ImagePlus image1 = IJ.openImage(inPath1);
		
		ImagePlus image2 = IJ.openImage(inPath2);
		
		String command = "Subtract create";
		
		ImageCalculator ic = new ImageCalculator();
		ImagePlus image3 = ic.run(command, image1, image2);
		
		IJ.saveAs(image3, "jpg", outPath);
		
		image1.close();
		image2.close();
		image3.close();
	}
	
	public static void findEdges(String inPath, String outPath) {
		ImagePlus image = IJ.openImage(inPath);
		
		String command = "Find Edges";
		String options = "";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "jpg", outPath);
		image.close();
	}
	
	public static void makeBinary(String inPath, String outPath) {
		ImagePlus image = IJ.openImage(inPath);
		
		String command = "Make Binary";
		String options = "";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "jpg", outPath);
		image.close();
	}
	
	public static void median(String inPath, String outPath) {
		ImagePlus image = IJ.openImage(inPath);
		
		String command = "Median...";
		String options = "radius=2";
		IJ.run(image, command, options);
		
		IJ.saveAs(image, "jpg", outPath);
		image.close();
	}
	
}
