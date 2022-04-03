package detection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class TesteOpenCV {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println(Core.VERSION);

        Mat imagemColorida = imread("src/detection/opencv_java.jpg", CV_LOAD_IMAGE_COLOR);//Ler imagem colorida
        Utilities utilities = new Utilities();
        utilities.mostraImagem(utilities.convertMatToImage(imagemColorida));

        Mat imagemCinza = new Mat();

        Imgproc.cvtColor(imagemColorida, imagemCinza, Imgproc.COLOR_BGR2GRAY);//converte imagem de colorida para cinza
        utilities.mostraImagem(utilities.convertMatToImage(imagemCinza));
    }
}
