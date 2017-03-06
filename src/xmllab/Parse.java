package xmllab;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.util.Scanner;

//This will parse one ID input at a time
//
public class Parse {

    
    public static void main(String[] args) 
    {
        try {
            
            Scanner userInputID = new Scanner(System.in);
            System.out.println("Type in ID");
            int inputID = userInputID.nextInt();
            URL myURL = new URL("http://129.32.92.49/xml/grade.php?id=" + inputID);
            
            new Parse().start(myURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void start(URL myURL) throws Exception
    {
      
        URLConnection connection = myURL.openConnection();

        Document doc = Parse(connection.getInputStream());
        
        NodeList name = doc.getElementsByTagName("name");
        NodeList grade = doc.getElementsByTagName("grade");

        for(int i=0; i<name.getLength();i++)
        {
            System.out.println(name.item(i).getTextContent());
            System.out.println(grade.item(i).getTextContent());
        }
    }
    
    private Document Parse(InputStream stream) throws Exception {
        
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try
        {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        }
        catch(Exception ex)
        {
            throw ex;
        }       

        return doc;
    }
    
}
//      try {
//          
//         URL myURL = new URL("http://129.32.92.49/xml/grade.php?id=100");
//         
//         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//         
//         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        
//         Document doc = dBuilder.parse(new File(myURL));
//         
//         doc.getDocumentElement().normalize();
//         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//         
//         NodeList nList = doc.getElementsByTagName("result");
//         System.out.println("----------------------------");
//         
//         
//         for (int temp = 0; temp < nList.getLength(); temp++) {
//            Node nNode = nList.item(temp);
//            System.out.println("\nCurrent Element :" + nNode.getNodeName());
//            
//            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//               Element eElement = (Element) nNode;
//
//               System.out.println("Name : " 
//               + eElement
//                  .getElementsByTagName("name")
//                  .item(0)
//                  .getTextContent());
//
//               System.out.println("Grade : " 
//               + eElement
//                  .getElementsByTagName("grade")
//                  .item(0)
//                  .getTextContent());
//                }
//            }
//        } catch (Exception e) {
//          
//         e.printStackTrace();
//         
//      }
//   }

   