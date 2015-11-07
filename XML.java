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
        //create document
        try {
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
     * set root of document, add increment counter
     *
     * @param root root name
     * @return element of root
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
     * give auto-increment to an element attribute id
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
     * add child NODE to an element
     *
     * @param parent parent element of the child
     * @param child name of child NODE to be appended to parent
     * @return
     */
    public Element addNewChild(Element parent, String child) {
        Element c = this.doc.createElement(child);
        parent.appendChild(c);
        return c;
    }

    /**
     * add text content to an element
     *
     * @param element
     * @param content
     */
    public void addContent(Element element, String content) {
        element.appendChild(doc.createTextNode(content));
    }

    /**
     * add child NODE with content to a parent element
     *
     * @param parentNode
     * @param elementName
     * @param elementContent
     */
    public void addElement(Element parentNode, String elementName, String elementContent) {
        Element e = this.doc.createElement(elementName);
        parentNode.appendChild(e);
        e.appendChild(doc.createTextNode(elementContent));
    }

    /**
     * get a node element in a node list
     *
     * @param list list of items in xml
     * @param index index of the element in the list
     * @return element of NodeList
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
     * get the content of a node element
     *
     * @param element parent element
     * @param nodeName name of node in the parent element
     * @return content of node
     */
    public String getNodeContent(Element element, String nodeName) {
        return element.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    /**
     * write content to file
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
