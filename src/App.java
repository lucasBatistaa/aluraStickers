import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os filmes
        
        //String url = "https://alura-filmes.herokuapp.com/conteudos";
        //ContentExtractor extractor = new ImdbContentExtractor();

        String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        ContentExtractor extractor = new NasaContentExtractor();

        var http = new ClientHttp();
        String json = http.searchData(url);   

        // exibir e manipular os dados 
        List<Content> contents = extractor.extractContents(json);

        var generate = new CreateStickers();

        for (int i = 0; i < 3; i++) {

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String fileName = content.getTitle() + ".png";

            generate.create(inputStream, fileName);

            System.out.println("\u001b[34mTítulo: " + content.getTitle());
            System.out.println();
        }
    }
}
