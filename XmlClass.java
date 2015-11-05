package CZ2002;

/* Document and Element are subinterface of Node, thus both shares the method
 appendChild()

 Node appendChild(Node newChild):
 Adds the node newChild to the end of the list of children of this node. If
 the newChild is already in the tree, it is first removed.
 */
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
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();   // creates DocumentBuilder object
            File f = new File("xml/allmovies.xml");     // creates a new File instance
            Document doc = dBuilder.newDocument();      // creates an abstract Document object
            
            Element rootElement = doc.createElement("allmovies");   // create Element node

            if (f.exists()) {
                System.out.println("File existed");
                //load existing file, get root
                doc = dBuilder.parse(f);                // parse existing file into abstract Document object
                rootElement = doc.getDocumentElement(); // returns document element to Element node
            } else {
                // create a counter, append root to doc
                Attr counter = doc.createAttribute("counter");  // create Attribute node
                counter.setValue("0");                          // set Attribute value
                rootElement.setAttributeNode(counter);          // attach Attribute to Element node
                doc.appendChild(rootElement);                   // append new node to Document node
            }

            //  movie element
            Element movie = doc.createElement("movie");     // create Element node
            rootElement.appendChild(movie);                 // append new Element node to root Element node

            // setting attribute to element
            //set movie id to auto-increment based on root's counter
            //ensures id will always be unique even if movie is deleted
            String s = incCounter(rootElement);
            rootElement.setAttribute("counter", s);
            Attr attr = doc.createAttribute("id");
            attr.setValue(s);
            movie.setAttributeNode(attr);

            /*
             * NOTE: the section above can be part of a constructor
             * The following section below is input from user, can put as methods
             */
            // setTitle()
            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode("Lord Of The Rings"));
            movie.appendChild(title);

            //setStatus()
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
