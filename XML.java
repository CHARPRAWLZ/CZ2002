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
            NodeList nList = this.doc.getElementsByTagName(this.elementName);
            for (int i = 0; i < nList.getLength(); i++) {
                Element e = this.getNodeElement(nList, i);
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
            NodeList nList = doc.getElementsByTagName(this.elementName);
            for (int i = 0; i < nList.getLength(); i++) {
                Element e = this.getNodeElement(nList, i);
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
     * @param item name of item
     * @param content content that is to be filtered
     */
    public void displayElement(String item, String content) {
        NodeList nList = doc.getElementsByTagName(this.elementName);
        for (int i = 0; i < nList.getLength(); i++) {
            Element e = this.getNodeElement(nList, i);
            NodeList items = e.getElementsByTagName("item");
            for (int j = 0; j < items.getLength(); j++) {
                String name = this.getItemName(items, j);
                String nameContent = this.getItemContent(items, j);
                if (item.toLowerCase().equals(name.toLowerCase())
                        && content.toLowerCase().equals(nameContent.toLowerCase())) {
                    displayElement(e.getAttribute("id"));
                    break;
                }
            }
        }
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
        NodeList nList = doc.getElementsByTagName(this.elementName);
        for (int i = 0; i < nList.getLength(); i++) {
            Element e = this.getNodeElement(nList, i);
            if (e.getAttribute("id").equals(id)) {
                NodeList items = e.getElementsByTagName("item");
                for (int j = 0; j < items.getLength(); j++) {
                    String name = this.getItemName(items, j);
                    if (item.toLowerCase().equals(name.toLowerCase())) {
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
        NodeList nList = doc.getElementsByTagName(this.elementName);
        for (int i = 0; i < nList.getLength(); i++) {
            Element e = this.getNodeElement(nList, i);
            if (e.getAttribute("id").equals(id)) {
                NodeList items = e.getElementsByTagName("item");
                for (int j = 0; j < items.getLength(); j++) {
                    String name = this.getItemName(nList, j);
                    if (item.toLowerCase().equals(name.toLowerCase())) {
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
        NodeList nList = doc.getElementsByTagName(this.elementName);
        for (int i = 0; i < nList.getLength(); i++) {
            Element e = this.getNodeElement(nList, i);
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
     * After creating xml, check how many elements that contain an item content
     * eg. returns number of movies that has item name "status" as "Now Showing"
     *
     * @param itemName Name of item
     * @param itemContent Content of item
     * @return number of elements
     */
    public int getElementCount(String itemName, String itemContent) {
        int count = 0;
        if (!this.file.exists()) {
            System.out.println("No file found");
        } else {
            NodeList nList = doc.getElementsByTagName(this.elementName);
            for (int i = 0; i < nList.getLength(); i++) {
                Element e = this.getNodeElement(nList, i);
                NodeList items = e.getElementsByTagName("item");
                for (int j = 0; j < items.getLength(); j++) {
                    String name = this.getItemName(items, j);
                    String nameContent = this.getItemContent(items, j);
                    if (name.toLowerCase().equals(itemName.toLowerCase())
                            && nameContent.equals(itemContent)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Get item name
     *
     * @param nList Nodelist to get the item name from
     * @param i index of item
     * @return string of item name
     */
    public String getItemName(NodeList nList, int i) {
        return nList.item(i).getAttributes().getNamedItem("name").getNodeValue();
    }

    /**
     * Get item content from a nodelist
     *
     * @param nList Nodelist to get the item content from
     * @param i index of item
     * @return string of item content
     */
    public String getItemContent(NodeList nList, int i) {
        return nList.item(i).getTextContent();
    }
    /**
     * Get item content using element id
     * @param id id of element
     * @param itemName name of item
     * @return string of item content
     */
    public String getItemContent(String id, String itemName) {
        NodeList nList = this.doc.getElementsByTagName(this.elementName);
        for (int i = 0; i < nList.getLength(); i++) {
            Element e = this.getNodeElement(nList, i);
            NodeList items = e.getElementsByTagName("item");
            if (e.getAttribute("id").equals(id)) {
                for (int j = 0; j < items.getLength(); j++) {
                    String name = this.getItemName(items, j);
                    if (name.toLowerCase().equals(itemName.toLowerCase())) return this.getItemContent(items, j);
                }
            }
        }
        return "";
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
    private void printAttrAndContent(Node node, String attributeName) {
        System.out.println(node.getAttributes().getNamedItem(attributeName).getNodeValue()
                + " : " + node.getTextContent());
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

    /**
     * Get element name
     *
     * @return string of element name
     */
    public String getElementName() {
        return this.elementName;
    }
    /*
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
     System.out.println("|| 7: Element count            ||");
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
     System.out.print("Enter item name: ");
     String name2 = sc.nextLine();
     System.out.print("Enter item content: ");
     String content2 = sc.nextLine();
     xml.displayElement(name2, content2);
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
     case "7":
     System.out.print("Enter item name: ");
     String name3 = sc.nextLine();
     System.out.print("Enter item content: ");
     String content3 = sc.nextLine();
     System.out.println(xml.getElementCount(name3, content3));
     default:
     break;
     }
     System.out.println();
     } while (!choice.equals("0"));
     }
     */
}
