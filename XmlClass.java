package CZ2002;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XmlClass {

    public static void main(String argv[]) {

        try {
	  File f = new File("xml/allmovies.xml");

	  if(f.exists()){
		  System.out.println("File existed");
	  }else{
		  System.out.println("File not found!");
	  }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument(); 
            // root element
            Element rootElement = doc.createElement("allmovies");
            doc.appendChild(rootElement);

            //  movie element
            Element movie = doc.createElement("movie");
            rootElement.appendChild(movie);

            // setting attribute to element
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            movie.setAttributeNode(attr);

            // title element
            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode("Lord Of The Rings"));
            movie.appendChild(title);

            Element status = doc.createElement("status");
            status.appendChild(doc.createTextNode("Coming Soon"));
            movie.appendChild(status);
            
            writeContent(doc,"xml/allmovies.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void writeContent(Document doc,String filename) {
        try {
            // write the content into xml file
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            //indent
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            t.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            t.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
