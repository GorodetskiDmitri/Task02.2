package by.epam.task02.parser;

public interface Node {
    short getNodeType();
    
    String getNodeValue();
    Node getParentNode();

    Node getFirstChild();
    Node getLastChild();
}