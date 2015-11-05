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

public class XmlClass {

    private File file;
    private Document doc;
    private Element root, movie;
    private String incCounter;

    public XmlClass() {

    }

    public XmlClass(String titleIn, String statusIn) {
        try {
            //create document
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.file = new File("src/CZ2002/allmovies.xml");
            this.doc = dBuilder.newDocument();
            this.root = doc.createElement("allmovies");
            if (file.exists()) {
                //load existing file, get root
                doc = dBuilder.parse(file);
                this.root = doc.getDocumentElement();
            } else {
                // create a counter, append root to doc
                Attr counter = doc.createAttribute("counter");
                counter.setValue("0");
                this.root.setAttributeNode(counter);
                doc.appendChild(this.root);
            }

            //  movie element
            this.movie = doc.createElement("movie");
            this.root.appendChild(movie);
            // setting attribute to element
            //set movie id to auto-increment based on root's counter
            //ensures id will always be unique even if movie is deleted
            String c = this.root.getAttribute("counter");
            int x = Integer.parseInt(c) + 1;
            String s = Integer.toString(x);
            this.incCounter = s;
            this.root.setAttribute("counter", s);
            Attr attr = doc.createAttribute("id");
            attr.setValue(s);
            movie.setAttributeNode(attr);

            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode(titleIn));
            movie.appendChild(title);

            Element status = doc.createElement("status");
            status.appendChild(doc.createTextNode(statusIn));
            movie.appendChild(status);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String argv[]) {
        Scanner sc = new Scanner(System.in);
        try {
            // setTitle()
            System.out.print("Enter title: ");
            String titleInput = sc.nextLine();

            //setStatus()
            System.out.print("Enter status: ");
            String statusInput = sc.nextLine();

            XmlClass xml = new XmlClass(titleInput, statusInput);
            xml.writeContent();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setFile(File f) {
        this.file = f;
    }

    public File getFile() {
        return this.file;
    }

    public void setDoc(Document d) {
        this.doc = d;
    }

    public Document getDoc() {
        return this.doc;
    }

    public void setRoot(Element r) {
        this.root = r;
    }

    public Element getRoot() {
        return this.root;
    }

    public void setMovie(Element m) {
        this.movie = m;
    }

    public Element getMovie() {
        return this.movie;
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
