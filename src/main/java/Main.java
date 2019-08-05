import com.google.gson.Gson;
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
import java.util.*;

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

        document.getDocumentElement().normalize();
        System.out.println("Коренной элемент: " + document.getDocumentElement().getNodeName()+"\n");

        //вариант 1: формируем лист
        NodeList modelNodeList = document.getElementsByTagName("Model");
        List<Model> modelsList = new ArrayList<>();

        //вариант 2: формируем мапу
        Map<String, List<Model>> modelsMap = new HashMap<>();

        for (int i = 0; i < modelNodeList.getLength(); i++) {
            if (modelNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

                Element modelElement = null;
                try {
                    modelElement = (Element) modelNodeList.item(i);
                } catch (ClassCastException e){
                    e.printStackTrace();
                }
                //формируем модель
                Model model = new Model();
                model.setCategory(modelElement.getAttribute("Category"));
                model.setMake(modelElement.getAttribute("Make"));
                model.setModel(modelElement.getAttribute("Model"));

                //вариант 1: просто заполняем лист
                modelsList.add(model);
                System.out.println(modelsList.get(i).toString());

                //вариант 2: заполняем мапу
                if (!modelsMap.containsKey(model.getMake())){
                    List<Model> list = new ArrayList<>();
                    list.add(model);

                    modelsMap.put(model.getMake(), list);
                } else {
                    modelsMap.get(model.getMake()).add(model);
                }
            }
        }
        //вариант 1:
        System.out.println("\nформируем json из обыкновенного листа, в который последовательно складываются объекты");
        String jsonOutputObject = new Gson().toJson(modelsList);
        System.out.println(jsonOutputObject);

        //вариант 2:
        System.out.println("\nформируем json из мапы с ключом Ford");
        String fordJson = new Gson().toJson(modelsMap.get("Ford"));
        System.out.println(fordJson);

        //вариант 3
        System.out.println("\nформируем json из мапы, модули группируются по брендам");
        String mapJson = new Gson().toJson(modelsMap);
        System.out.println(mapJson);
    }
}
