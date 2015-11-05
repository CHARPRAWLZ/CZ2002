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
import java.util.Scanner;

public class XML {

    private File file;
    private Document doc;
    private Element root;

    public XML() {
    }

    public XML(String f) {
        try {
            //create document
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.file = new File(f);
            this.doc = dBuilder.newDocument();
            if (this.file.exists()) {
                this.doc = dBuilder.parse(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String argv[]) {
        XML xml = new XML("src/CZ2002/allmovies.xml");
        Element root = xml.setRoot("allmovies");
        String choice = "-1";
        Scanner sc = new Scanner(System.in);
        try {
            // setTitle()
            do {
                System.out.println("1: Input Movie");
                System.out.println("0: Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        Element movie = xml.addNewChild(root, "movie");
                        xml.setIncId(movie);

                        System.out.print("Enter title: ");
                        String titleIn = sc.nextLine();
                        xml.addElement(movie, "title", titleIn);

                        System.out.print("Enter status: ");
                        String statusIn = sc.nextLine();
                        xml.addElement(movie, "status", statusIn);

                        xml.writeContent();

                        break;
                    case "0":
                        break;
                }

            } while (!choice.equals("0"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Element setRoot(String r) {
        Element eRoot = this.doc.createElement(r);
        if (this.file.exists()) {
            eRoot = this.doc.getDocumentElement();
        } else {
            eRoot.setAttribute("counter", "0");
            this.doc.appendChild(eRoot);
        }
        this.root = eRoot;
        return this.root;
    }

    public Element addNewChild(Element parent, String child) {
        Element c = this.doc.createElement(child);
        parent.appendChild(c);
        return c;
    }

    public void addContent(Element e, String nodeName) {
        e.appendChild(doc.createTextNode(nodeName));
    }

    public void setIncId(Element e) {
        String c = this.root.getAttribute("counter");
        int x = Integer.parseInt(c) + 1;
        String s = Integer.toString(x);
        this.root.setAttribute("counter", s);
        e.setAttribute("id", s);
    }

    public void addElement(Element parentNode, String elementName, String elementContent) {
        Element e = this.doc.createElement(elementName);
        parentNode.appendChild(e);
        e.appendChild(doc.createTextNode(elementContent));
    }

    public void writeContent() {
        try {
            //write the content into xml file
            Transformer t = TransformerFactory.newInstance().newTransformer();
            //indent
            //t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            //transform doc into xml
            DOMSource source = new DOMSource(this.doc);
            StreamResult result = new StreamResult(this.file);
            t.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            t.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
