package by.epam.task02.parser;

import java.util.ArrayList;
import java.util.List;

public class ElementImpl implements Element {
    private String tagName;
    private Element parentElement;
    private List<Element> childElements;
    private List<Attribute> attributes;
    private Text textContent;

    public ElementImpl() {
        childElements = new ArrayList<>();
        attributes = new ArrayList<>();
    }

    public ElementImpl(String tagName, Text textContent, List<Attribute> attributes, List<Element> childElements, Element parentElement) {
        this.tagName = tagName;
        this.textContent = textContent;
        this.attributes = attributes;
        this.childElements = childElements;
        this.parentElement = parentElement;
    }

    @Override
    public boolean hasAttribute() {
        return !attributes.isEmpty();
    }

    @Override
    public String getAttribute(String name) {
        for(Attribute attribute : attributes){
            if(attribute.getName().equals(name))
                return attribute.getValue();
        }

        return null;
    }

    @Override
    public Attribute getAttributeNode(String name) {
        for(Attribute attribute : attributes){
            if(attribute.getName().equals(name))
                return attribute;
        }

        return null;
    }

    @Override
    public void setAttribute(String name, String value) {
        for(Attribute attribute : attributes){
            if(attribute.getName().equals(name))
                attribute.setValue(value);
        }
    }

    @Override
    public void removeAttribute(String name) {
        for(Attribute attribute : attributes){
            if(attribute.getName().equals(name))
                attributes.remove(attribute);
        }
    }

    @Override
    public List<Element> getElementsByTagName(String name) {
        List<Element> searchingElements = new ArrayList<>();

        for(Element element : childElements) {
            if (element.getTagName().equals(name))
                searchingElements.add(element);

            searchingElements.addAll(element.getElementsByTagName(name));
        }
        return searchingElements;
    }

    @Override
    public String getInnerText() {
        return textContent.getWholeText();
    }
    
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Element getParentElement() {
        return parentElement;
    }

    public void setParentElement(Element parentElement) {
        this.parentElement = parentElement;
    }

    public List<Element> getChildElements() {
        return childElements;
    }

    public void setChildElements(List<Element> childElements) {
        this.childElements = childElements;
    }

    public void addChildElement(Element childElement) {
        this.childElements.add(childElement);
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
    }

    public Text getTextContent() {
        return textContent;
    }

    public void setTextContent(Text textContent) {
        this.textContent = textContent;
    }

    @Override
    public short getNodeType() {
        return 1;
    }

    @Override
    public String getNodeValue() {
        return null;
    }

    @Override
    public Node getParentNode() {
        return parentElement;
    }

    @Override
    public Node getFirstChild() {
        return childElements.get(0);
    }

    @Override
    public Node getLastChild() {
        return childElements.get(childElements.size() - 1);
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElementImpl that = (ElementImpl) o;

        if (parentElement != null ? !parentElement.equals(that.parentElement) : that.parentElement != null)
            return false;
        if (childElements != null ? !childElements.equals(that.childElements) : that.childElements != null)
            return false;
        return !(textContent != null ? !textContent.equals(that.textContent) : that.textContent != null);

    }

    @Override
    public int hashCode() {
        int result = parentElement != null ? parentElement.hashCode() : 0;
        result = 31 * result + (childElements != null ? childElements.hashCode() : 0);
        result = 31 * result + (textContent != null ? textContent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
    	return getClass().getName() + "@" + "parentElement : " + parentElement +
    			", childElements : " + childElements +
    			", textContent : " + textContent;
    }

}