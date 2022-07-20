import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImdbContentExtractor implements ContentExtractor{
    
    public List<Content> extractContents(String json) {
        
        // extrair => título, poster, classificação
        var parser = new JsonParser();
        List<Map<String, String>> atributteList = parser.parse(json); 

        List<Content> contents = new ArrayList<>();

        //popular a lista de conteudos
        for (Map<String, String> atributte : atributteList) {

            String title = atributte.get("title");
            String urlImage = atributte.get("image")
                .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            
            var content = new Content(title, urlImage);

            contents.add(content);
        }

        return contents;
    }
}