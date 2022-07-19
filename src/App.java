import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os filmes
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient(); //criação do cliente
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair => título, poster, classificação
        var parser = new JsonParser();
        List<Map<String, String>> listMovies = parser.parse(body);    

        // exibir e manipular os dados 
        var generate = new CreateStickers();
        for (Map<String,String> movie : listMovies) {

            String urlImage = movie.get("image");
            String title = movie.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = title + ".png";

            generate.create(inputStream, fileName);

            System.out.println(title);
            System.out.println();
        }
    }
}
