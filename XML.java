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
    private String incCounter;
    public XML() {}
    public XML(String f) {
        try {
            //create document
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.file = new File(f);
            this.doc = dBuilder.newDocument();
            this.root = doc.createElement("allmovies");
            if (file.exists()) {
                //load existing file, get root
                doc = dBuilder.parse(file);
                this.root = doc.getDocumentElement();
            } else {
                // create a counter, append root to doc
                //Attr counter = doc.createAttribute("counter");
                //counter.setValue("0");
                this.root.setAttribute("counter","0");
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
            //Attr attr = doc.createAttribute("id");
            //attr.setValue(s);
            movie.setAttribute("id",s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String argv[]) {
        XML xml = new XML();
        String choice = "0";
        Scanner sc = new Scanner(System.in);
        try {
            // setTitle()
            do {
                System.out.println("1: Input Movie");
                System.out.println("2: Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        System.out.print("Enter title: ");
                        String titleInput = sc.nextLine();
                        System.out.print("Enter status: ");
                        String statusInput = sc.nextLine();

                        xml.appendTitle(titleInput);
                        xml.appendStatus(statusInput);
                        break;
                    case "2":
                        break;
                }

            } while (!choice.equals("2"));

            xml.writeContent();

        } catch (Exception e) {
            e.printStackTrace();
        }

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
