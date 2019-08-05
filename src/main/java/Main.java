import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        File file = new File("Models.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document document = null;
        try {
            document = builder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NodeList modelNodeList = document.getElementsByTagName("Model");
        List<Model> modelsList = new ArrayList<Model>();

        for (int i = 0; i < modelNodeList.getLength(); i++) {
            if (modelNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

                Element modelElement = null;
                try {
                    modelElement = (Element) modelNodeList.item(i);
                } catch (ClassCastException e){
                    e.printStackTrace();
                }

                Model model = new Model();
                model.setCategory(modelElement.getAttribute("Category"));
                model.setMake(modelElement.getAttribute("Make"));
                model.setModel(modelElement.getAttribute("Model"));

                modelsList.add(model);
                System.out.println(modelsList.get(i).toString());
            }
        }

        System.out.println("\nАвтомобили Ford:\n");
        for (int i = 0; i < modelsList.size(); i++) {
            Model model = modelsList.get(i);
            if (model.getMake().equals("Ford")){
                System.out.println(model.toString());
            }
        }
    }
}
