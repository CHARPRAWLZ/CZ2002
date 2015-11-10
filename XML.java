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

    /**
     * Constructor for XML
     */
    public XML() {
    }

    /**
     * Constructor for XML
     *
     * @param file File name of XML
     */
    public XML(String file) {
        //create document
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.file = new File(file);
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
        do {
            System.out.println("||==========================||");
            System.out.println("|| 1: Input Movie           ||");
            System.out.println("|| 2: Display all movies    ||");
            System.out.println("|| 3: Get movie by movie id ||");
            System.out.println("|| 0: Exit                  ||");
            System.out.println("||==========================||");
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
                    System.out.println("- Movie added into database -");

                    break;
                case "2":
                    if (!xml.getFile().exists()) {
                        System.out.println("no file found");
                    } else {
                        Document doc = xml.getDoc();
                        NodeList movieList = doc.getElementsByTagName("movie");
                        for (int i = 0; i < movieList.getLength(); i++) {
                            Element e = xml.getNodeElement(movieList, i);
                            System.out.println("---------------");
                            System.out.println(e.getNodeName() + " id: " + e.getAttribute("id"));
                            System.out.println("Title   : " + xml.getNodeContent(e, "title"));
                            System.out.println("Status  : " + xml.getNodeContent(e, "status"));

                        }

                    }
                    break;
                case "3":
                    System.out.print("Enter movie id: ");
                    String id = sc.nextLine();
                    boolean idExists = false;
                    if (!xml.getFile().exists()) {
                        System.out.println("no file found");
                    } else {
                        Document doc = xml.getDoc();
                        NodeList movieList = doc.getElementsByTagName("movie");
                        for (int i = 0; i < movieList.getLength(); i++) {
                            Element e = xml.getNodeElement(movieList, i);
                            if (e.getAttribute("id").equals(id)) {
                                idExists = true;
                                System.out.println("---------------");
                                System.out.println(e.getNodeName() + " id: " + e.getAttribute("id"));
                                System.out.println("Title   : " + xml.getNodeContent(e, "title"));
                                System.out.println("Status  : " + xml.getNodeContent(e, "status"));
                            }

                        }
                        if (idExists == false) {
                            System.out.println("Id not found");
                        }

                    }
                    break;

                case "0":
                    break;
            }
            System.out.println();
        } while (!choice.equals("0"));

    }

    /**
     * Set root of document, add increment counter
     *
     * @param root Root name
     * @return Element of root
     */
    public Element setRoot(String root) {
        Element eRoot = this.doc.createElement(root);
        if (this.file.exists()) {
            eRoot = this.doc.getDocumentElement();
        } else {
            eRoot.setAttribute("counter", "0");
            this.doc.appendChild(eRoot);
        }
        this.root = eRoot;
        return this.root;
    }

    /**
     * Give auto-increment to an element attribute id
     *
     * @param element Element that has element attribute id increment
     */
    public void setIncId(Element element) {
        String c = this.root.getAttribute("counter");
        int x = Integer.parseInt(c) + 1;
        String s = Integer.toString(x);
        this.root.setAttribute("counter", s);
        element.setAttribute("id", s);
    }

    /**
     * Add child NODE to an element
     *
     * @param parent Parent element of the child
     * @param child Name of child NODE to be appended to parent
     * @return
     */
    public Element addNewChild(Element parent, String child) {
        Element c = this.doc.createElement(child);
        parent.appendChild(c);
        return c;
    }

    /**
     * Add text content to an element
     *
     * @param element Element of NODE
     * @param content Text content to be added to element
     */
    public void addContent(Element element, String content) {
        element.appendChild(doc.createTextNode(content));
    }

    /**
     * Add child NODE with content to a parent element
     *
     * @param parentNode Parent node
     * @param elementName Child node
     * @param elementContent Child content
     */
    public void addElement(Element parentNode, String elementName, String elementContent) {
        Element e = this.doc.createElement(elementName);
        parentNode.appendChild(e);
        e.appendChild(doc.createTextNode(elementContent));
    }

    /**
     * Get a node element in a node list
     *
     * @param list List of items in XML
     * @param index Index of the element in the list
     * @return Element of NodeList
     */
    public Element getNodeElement(NodeList list, int index) {
        Node listnode = list.item(index);
        Element e = null;
        if (listnode.getNodeType() == Node.ELEMENT_NODE) {
            e = (Element) listnode;
        }
        return e;
    }

    /**
     * Get the content of a node element
     *
     * @param element Parent element
     * @param nodeName Name of node in the parent element
     * @return Content of node
     */
    public String getNodeContent(Element element, String nodeName) {
        return element.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    /**
     * Write content to file
     */
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
            //StreamResult consoleResult = new StreamResult(System.out);
            //t.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get xml file
     *
     * @return xml file
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Get xml doc
     *
     * @return xml doc
     */
    public Document getDoc() {
        return this.doc;
    }
}
