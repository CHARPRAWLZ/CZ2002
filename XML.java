package CZ2002;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Scanner;
import javax.xml.xpath.*;

/**
 *
 * @author alfiefarhana
 */
public class XML {

    private File file;
    private Document doc;
    private Element root;
    private String elementName;

    /**
     * Constructor for XML
     */
    public XML() {
    }

    /**
     * Constructor for XML
     *
     * @param elementName name of Element (no spaces)
     */
    public XML(String elementName) {
        //create document
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.file = new File("src/CZ2002/xml/" + elementName + ".xml");
            this.doc = dBuilder.newDocument();
            Element eRoot = this.doc.createElement("root");
            if (this.file.exists()) {
                this.doc = dBuilder.parse(file);
                eRoot = this.doc.getDocumentElement();
            } else {
                eRoot.setAttribute("counter", "0");
                this.doc.appendChild(eRoot);
            }
            this.root = eRoot;
            this.elementName = elementName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add element
     *
     * @return element
     */
    public Element addElement() {
        Element e = this.doc.createElement(this.elementName);
        this.root.appendChild(e);
        String c = this.root.getAttribute("counter");
        int x = Integer.parseInt(c) + 1;
        String s = Integer.toString(x);
        this.root.setAttribute("counter", s);
        e.setAttribute("id", s);
        return e;
    }

    /**
     * Add item with content to parent element
     *
     * @param parentNode Parent node
     * @param elementName Child node
     * @param elementContent Child content
     */
    public void addItem(Element parentNode, String elementName, String elementContent) {
        Element e = this.doc.createElement("item");
        parentNode.appendChild(e);
        e.setAttribute("name", elementName);
        e.appendChild(doc.createTextNode(elementContent));
    }

    /**
     * Display all elements with items
     */
    public void displayElement() {
        if (!this.file.exists()) {
            System.out.println("No file found");
        } else {
            NodeList movieList = this.doc.getElementsByTagName(this.elementName);
            for (int i = 0; i < movieList.getLength(); i++) {
                Node listnode = movieList.item(i);
                Element e = this.getNodeElement(movieList, i);
                System.out.println("---------------");
                System.out.println(e.getNodeName() + " id : " + e.getAttribute("id"));
                NodeList items = e.getElementsByTagName("item");
                for (int j = 0; j < items.getLength(); j++) {

                    this.printAttrAndContent(items.item(j), "name");
                }
            }
        }
    }

    /**
     * Display element with item by element id
     *
     * @param id value of element id
     * @return boolean if id exists
     */
    public boolean displayElement(String id) {
        boolean idExists = false;
        if (!this.file.exists()) {
            System.out.println("No file found");
        } else {
            NodeList movieList = doc.getElementsByTagName("movie");
            for (int i = 0; i < movieList.getLength(); i++) {
                Element e = this.getNodeElement(movieList, i);
                if (e.getAttribute("id").equals(id)) {
                    idExists = true;
                    System.out.println("---------------");
                    System.out.println(e.getNodeName() + " id: " + e.getAttribute("id"));
                    NodeList items = e.getElementsByTagName("item");
                    for (int j = 0; j < items.getLength(); j++) {
                        this.printAttrAndContent(items.item(j), "name");
                    }
                    break;
                }
            }
            if (idExists == false) {
                System.out.println("Id not found");
            }
        }
        return idExists;
    }

    /**
     * List elements by item content
     *
     * @param item
     * @param content
     * @return
     */
    public boolean displayElement(String item, String content) {
        boolean itemExists = false;
        if (!this.file.exists()) {
            System.out.println("No file found");
        } else {
            NodeList movieList = doc.getElementsByTagName("movie");
            for (int i = 0; i < movieList.getLength(); i++) {
                Element e = this.getNodeElement(movieList, i);
                System.out.println("---------------");
                System.out.println(e.getNodeName() + " id: " + e.getAttribute("id"));
                NodeList items = e.getElementsByTagName("item");
                for (int j = 0; j < items.getLength(); j++) {
                    this.printAttrAndContent(items.item(j), "name");
                }

            }
            if (itemExists == false) {
                System.out.println("Item not found");
            }
        }
        return itemExists;
    }

    /**
     * Check if item exists in an element
     *
     * @param id value of element id
     * @param item name of item
     * @return boolean if item exists
     */
    public boolean checkItemExists(String id, String item) {
        boolean itemExists = false;
        NodeList movieList = doc.getElementsByTagName("movie");
        for (int i = 0; i < movieList.getLength(); i++) {
            Element e = this.getNodeElement(movieList, i);
            if (e.getAttribute("id").equals(id)) {
                NodeList items = e.getElementsByTagName("item");
                for (int j = 0; j < items.getLength(); j++) {
                    String name = items.item(j).getAttributes().getNamedItem("name").getNodeValue();
                    if (item.equals(name)) {
                        itemExists = true;
                        break;
                    }
                }
                break;
            }
        }
        if (itemExists == false) {
            System.out.println("Item name not found");

        }
        return itemExists;
    }

    /**
     * Edit item content
     *
     * @param id value element id
     * @param item name of item
     * @param content new content
     */
    public void editItem(String id, String item, String content) {
        NodeList movieList = doc.getElementsByTagName("movie");
        for (int i = 0; i < movieList.getLength(); i++) {
            Element e = this.getNodeElement(movieList, i);
            if (e.getAttribute("id").equals(id)) {
                NodeList items = e.getElementsByTagName("item");
                for (int j = 0; j < items.getLength(); j++) {
                    String name = items.item(j).getAttributes().getNamedItem("name").getNodeValue();
                    if (item.equals(name)) {
                        items.item(j).setTextContent(content);
                        break;
                    }
                }
                break;
            }
        }
    }

    /**
     * Delete item
     *
     * @param id item id to be deleted
     */
    public void deleteItem(String id) {
        NodeList movieList = doc.getElementsByTagName("movie");
        for (int i = 0; i < movieList.getLength(); i++) {
            Element e = this.getNodeElement(movieList, i);
            if (e.getAttribute("id").equals(id)) {
                this.root.removeChild(e);
                break;
            }
        }
        // remove unwanted blank new line
        try {
            XPath xp = XPathFactory.newInstance().newXPath();
            NodeList nl = (NodeList) xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

            for (int i = 0; i < nl.getLength(); ++i) {
                Node node = nl.item(i);
                node.getParentNode().removeChild(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * Print Attribute and Content of node
     *
     * @param node Node to print
     * @param attributeName name of attribute to print
     */
    public void printAttrAndContent(Node node, String attributeName) {
        System.out.println(node.getAttributes().getNamedItem(attributeName).getNodeValue()
                + " : " + node.getTextContent());
    }

    /**
     * ***************************************************************************************
     */
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

    public static void main(String argv[]) {
        XML xml = new XML("movie");
        String choice = "-1", id = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("||=============================||");
            System.out.println("|| 1: Create Movie             ||");
            System.out.println("|| 2: List all movies          ||");
            System.out.println("|| 3: List movies by item      ||");
            System.out.println("|| 4: Read movie by movie id   ||");
            System.out.println("|| 5: Update movie by movie id ||");
            System.out.println("|| 6: Delete movie by movie id ||");
            System.out.println("|| 0: Exit                     ||");
            System.out.println("||=============================||");
            System.out.print("Enter choice: ");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("\n[ Enter / to go back ]");
                    System.out.print("Enter title: ");
                    String titleIn = sc.nextLine();
                    if (titleIn.equals("/")) {
                        break;
                    }
                    Element e = xml.addElement();
                    xml.addItem(e, "Title", titleIn);

                    System.out.print("Enter status: ");
                    String statusIn = sc.nextLine();
                    xml.addItem(e, "Status", statusIn);

                    xml.writeContent();
                    System.out.println("- Movie added into database -");

                    break;
                case "2":
                    xml.displayElement();
                    break;
                case "3":
                    xml.displayElement();
                    break;
                case "4":
                    System.out.print("Enter movie id: ");
                    id = sc.nextLine();
                    xml.displayElement(id);
                    break;
                case "5":
                    System.out.print("Enter movie id: ");
                    id = sc.nextLine();
                    if (xml.displayElement(id) == true) {
                        System.out.println("\n[ Enter / to go back ]");
                        System.out.print("Enter item name: ");
                        String name = sc.nextLine();
                        if (name.equals("/")) {
                            break;
                        }
                        if (xml.checkItemExists(id, name) == true) {
                            System.out.println("\n[ Enter / to go back ]");
                            System.out.print("Enter new value: ");
                            String val = sc.nextLine();
                            if (val.equals("/")) {
                                break;
                            }
                            xml.editItem(id, name, val);
                            xml.displayElement(id);
                            xml.writeContent();
                            System.out.println("- Movie has been updated -");
                        }
                    }
                    break;
                case "6":
                    System.out.print("Enter movie id: ");
                    id = sc.nextLine();
                    if (xml.displayElement(id) == true) {
                        xml.deleteItem(id);
                        xml.writeContent();
                        System.out.println("- Movie is deleted -");
                    }
                    break;
                default:
                    break;
            }
            System.out.println();
        } while (!choice.equals("0"));

    }
}
