package by.epam.task02.domain;

public interface Attribute extends Node {
    String getName();
    Element getOwnerElement();
    String getValue();
    void setValue(String value);
    boolean isId();
}