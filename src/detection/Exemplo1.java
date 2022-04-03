package detection;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import static org.opencv.imgcodecs.Imgcodecs.imread;

public class Exemplo1 {
    public static void main (String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat imagemColorida = imread("pessoas/pessoas3.jpg");
        Mat imagemCinza = new Mat();
        Imgproc.cvtColor(imagemColorida, imagemCinza, Imgproc.COLOR_BGR2GRAY);

        //Obter classificador de rosto
        CascadeClassifier classifier = new CascadeClassifier("cascades/haarcascade_frontalface_default.xml");

        //Armazena todas as faces detectadas
        MatOfRect facesDetectadas = new MatOfRect();
        //Obtem as faces e coloca na variável facesDetectadas
        classifier.detectMultiScale(imagemCinza, facesDetectadas,
                1.01,//scale factore: Especifica quanto o tamanho da imagem é reduzido em cada escala (1.1 é a escala mínima)
                1,//minNeighbors
                0,//flags
                new Size(30,30),//minSize
                new Size(100,100));//maxSize

        System.out.println(facesDetectadas.toArray().length);
        for(Rect rect : facesDetectadas.toArray()) {
            //Exibe a localização da face na plano cartesiano e a largura e altura
            System.out.println(rect.x + " " + rect.y  + " " + rect.width + " " + rect.height);
            Imgproc.rectangle(imagemColorida, new Point(rect.x, rect.y),//Desenha o retangulo no ponto inicial (x e y): inicio do rosto no plano cartesiano.....
                    new Point(rect.x + rect.width, rect.y + rect.height)//Desenha o retangulo no ponto final: inicio do rosto + largura.....
                    , new Scalar(0, 0, 255), 2);//Defini a cor e a largura da borda
        }

        Utilities utilities = new Utilities();
        utilities.mostraImagem(utilities.convertMatToImage(imagemColorida));
    }
}
