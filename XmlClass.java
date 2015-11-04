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
            //create document
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            File f = new File("xml/allmovies.xml");
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("allmovies");

            if (f.exists()) {
                System.out.println("File existed");
                //load existing file, get root
                doc = dBuilder.parse(f);
                rootElement = doc.getDocumentElement();
            } else {
                // create a counter, append root to doc
                Attr counter = doc.createAttribute("counter");
                counter.setValue("0");
                rootElement.setAttributeNode(counter);
                doc.appendChild(rootElement);
            }

            //  movie element
            Element movie = doc.createElement("movie");
            rootElement.appendChild(movie);

            // setting attribute to element
            String s = incCounter(rootElement);
            rootElement.setAttribute("counter", s);
            Attr attr = doc.createAttribute("id");
            attr.setValue(s);
            movie.setAttributeNode(attr);
            
            /*
             * NOTE: the section above can be part of a constructor
             * The following section below is input from user
             */
            // title element
            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode("Lord Of The Rings"));
            movie.appendChild(title);
            
            //status element
            Element status = doc.createElement("status");
            status.appendChild(doc.createTextNode("Coming Soon"));
            movie.appendChild(status);

            writeContent(doc, f);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String incCounter(Element rootElement) {
        String c = rootElement.getAttribute("counter");
        int x = Integer.parseInt(c) + 1;
        String s = Integer.toString(x);
        return s;
    }

    public static void writeContent(Document doc, File file) {
        try {
            //write the content into xml file
            Transformer t = TransformerFactory.newInstance().newTransformer();
            //indent
            //t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            //transform doc into xml
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            t.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            t.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
