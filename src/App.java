import java.net.URI;
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
        for (Map<String,String> movie : listMovies) {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
            System.out.println();
        }
    }
}
