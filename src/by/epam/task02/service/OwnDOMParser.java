package by.epam.task02.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import by.epam.task02.parser.AttributeImpl;
import by.epam.task02.parser.DocumentImpl;
import by.epam.task02.parser.ElementImpl;
import by.epam.task02.parser.TextImpl;

public class OwnDOMParser {

    private final static char TAG_START = '<';
    private final static char TAG_END = '>';
    private final static char TAG_CLOSE = '/';
    private final static char QUOTE = '"';
    private final static char SPACE = ' ';
    private final static char EQUAL = '=';
    private final static char QUESTION = '?';

    private FileReader fileReader;
    private char currentSymbol;

    private DocumentImpl documentImpl;

    public OwnDOMParser() {
    }

    public OwnDOMParser(String xmlFileURI) {
        File xmlFile = new File(xmlFileURI);
        try {
            fileReader = new FileReader(xmlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DocumentImpl parse() {
        while (readSymbol() != 0) {
            readTag(null);
        }
        return documentImpl;
    }

    private char readSymbol() {
        int readSymbol = 0;
        try {
            readSymbol = fileReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (readSymbol != -1)
            currentSymbol = (char)readSymbol;
        else
            currentSymbol = 0;

        return currentSymbol;
    }

    private void readTag(ElementImpl parentElement) {
        ElementImpl element = new ElementImpl();
        boolean closedTag = false;
        boolean singleTag = false;
        boolean declaration = false;
        StringBuilder tagName = new StringBuilder();
        StringBuilder textBetweenTags = new StringBuilder();

        while (currentSymbol != TAG_START && currentSymbol != 0)
            textBetweenTags.append(readSymbol());
        if (currentSymbol == 0)
            return;

        while (readSymbol() != TAG_END) {
            if (currentSymbol == TAG_CLOSE) {
                if (tagName.length() == 0)
                    closedTag = true;
                else
                    singleTag = true;
                break;
            }
          
            if (currentSymbol == SPACE)
                readAttribute(element);
            else
                tagName.append(currentSymbol);
        }

        if (parentElement != null) {
            textBetweenTags.deleteCharAt(textBetweenTags.length() - 1);
            parentElement.setTextContent(new TextImpl(textBetweenTags.toString()));
        }

        element.setTagName(tagName.toString());

        if (tagName.length() != 0)
            if (tagName.charAt(0) == QUESTION && tagName.charAt(tagName.length() - 1) == QUESTION)
                declaration = true;

        if (!closedTag && !declaration){
            element.setParentElement(parentElement);
            if (parentElement != null)
                parentElement.addChildElement(element);
            else
                documentImpl = new DocumentImpl(element);
        }

        if (!closedTag && !singleTag && !declaration) {
            readTag(element);
        }
        
        else if (!closedTag)
            readTag(parentElement);
       else
            readTag((ElementImpl) parentElement.getParentElement());

    }

    private void readAttribute(ElementImpl element) {
        AttributeImpl attribute = new AttributeImpl();
        StringBuilder attributeName = new StringBuilder();
        StringBuilder attributeValue = new StringBuilder();

        while (readSymbol() != EQUAL)
            attributeName.append(currentSymbol);

        readSymbol();
        
        while (readSymbol() != QUOTE)
            attributeValue.append(currentSymbol);

        attribute.setName(attributeName.toString());
        attribute.setValue(attributeValue.toString());
        element.addAttribute(attribute);
    }

}