import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Runner {
    static String address = "https://www.lenta.ru";
    private static String IMAGE_DESTINATION_FOLDER = "C:\\Users\\123\\IdeaProjects\\ImgParser\\src\\Images";

    public static void main(String[] args) throws IOException {
        //Document doc = Jsoup.connect(address).maxBodySize(0).get();
        //System.out.println(doc);
        System.out.println();
        Document document = Jsoup
                .connect(address)
                .userAgent("Mozilla/5.0")
                .timeout(10 * 1000)
                .get();


        Elements imageElements = document.select("img");


        for (Element imageElement : imageElements) {

            String strImageURL = imageElement.attr("abs:src");
            downloadImage(strImageURL);
        }
    }
    private static void downloadImage(String strImageURL){

        String strImageName =
                strImageURL.substring( strImageURL.lastIndexOf("/") + 1 );

        System.out.println("Saving: " + strImageName + ", from: " + strImageURL);

        try {

            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[5555];
            int n = -1;

            OutputStream os =
                    new FileOutputStream( IMAGE_DESTINATION_FOLDER + "/" + strImageName );

            while ( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }
            
            os.close();

            System.out.println("Image saved");

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

    }

}









