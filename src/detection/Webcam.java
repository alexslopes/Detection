package detection;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Webcam extends javax.swing.JFrame{
    private JPanel panel1;

    public Webcam() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 653, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 420, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Webcam janela = new Webcam();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 500);
        janela.setVisible(true);
        janela.mostraVideo();
    }

    public void mostraVideo() {
        Mat video = new Mat();//Pega e runtime oque esta na webcam
        VideoCapture capture = new VideoCapture(0);//Numero correspondente a camera usada;
        if(capture.isOpened()) {
            while(true) {
                capture.read(video);
                if(!video.empty()) {
                    setSize(video.width() + 50, video.height() + 70);

                    Mat imagemColorida = video;
                    Mat imagemCinza = new Mat();
                    Imgproc.cvtColor(imagemColorida, imagemCinza, Imgproc.COLOR_BGR2GRAY);
                    CascadeClassifier classificador = new CascadeClassifier("cascades/haarcascade_frontalface_default.xml");
                    MatOfRect facesDetectadas = new MatOfRect();
                    classificador.detectMultiScale(imagemCinza, facesDetectadas,
                            1.1,
                            1,
                            0,
                            new Size(50, 50),
                            new Size(500,500 ));

                    for(Rect rect : facesDetectadas.toArray()) {
                        Imgproc.rectangle(imagemColorida, new Point(rect.x, rect.y),
                                new Point(rect.x + rect.width, rect.y + rect.height)
                                , new Scalar(0, 0, 255), 2);
                    }

                    BufferedImage image = new Utilities().convertMatToImage(video);
                    Graphics g = panel1.getGraphics();
                    g.drawImage(image, 10, 10, image.getWidth(), image.getHeight(), null);//Configura as bordas na janela da Webcam
                }
            }
        }
    }

}
