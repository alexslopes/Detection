package detection;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class TesteOpenCV {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println(Core.VERSION);

        Mat imagemColorida = imread("src/detection/opencv_java.jpg", CV_LOAD_IMAGE_COLOR);
        Utilities utilities = new Utilities();
        utilities.mostraImagem(utilities.convertMatToImage(imagemColorida));
    }
}
