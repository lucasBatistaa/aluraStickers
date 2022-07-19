import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class CreateStickers {

    public void create(InputStream inputStream, String fileName) throws Exception {

        //leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("images/movie.jpg"));
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);
        
        // cria nova imagem em memória com transparência e com tamanho novo
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        
        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // configurar a fonte
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.RED);
        graphics.setFont(font);

        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 0, newHeight - 100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File("exit/" + fileName));
    } 
}
