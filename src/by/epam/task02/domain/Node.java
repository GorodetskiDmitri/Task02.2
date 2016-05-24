package by.epam.task02.domain;

public interface Node {
    short getNodeType();
    
    String getNodeValue();
    Node getParentNode();

    Node getFirstChild();
    Node getLastChild();
}