package by.epam.task02.domain;

import java.util.List;

public interface Element extends Node {
    String getTagName();
    boolean hasAttribute();
    String getAttribute(String name);
    Attribute getAttributeNode(String name);
    void setAttribute(String name, String value);
    void removeAttribute(String name);
    List<Element> getElementsByTagName(String name);
    String getInnerText();
}