package by.epam.task02.service;

import by.epam.task02.domain.AttributeImp;
import by.epam.task02.domain.DocumentImp;
import by.epam.task02.domain.ElementImp;
import by.epam.task02.domain.TextImp;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OwnDOMParser {

    private final static char tagStart = '<';
    private final static char tagEnd = '>';
    private final static char tagClose = '/';
    private final static char quotes = '"';
    private final static char space = ' ';
    private final static char equal = '=';
    private final static char question = '?';

    private FileReader fileReader;
    private char currentSymbol;

    private DocumentImp documentImp;

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

    public DocumentImp parse(){
        while (readSymbol() != 0) {
            readTag(null);
        }
        
        return documentImp;
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

    private void readTag(ElementImp parentElement) {
        ElementImp element = new ElementImp();
        boolean closedTag = false;
        boolean singleTag = false;
        boolean declaration = false;
        StringBuilder tagName = new StringBuilder();
        StringBuilder textBetweenTags = new StringBuilder();

        while (currentSymbol != tagStart && currentSymbol != 0)
            textBetweenTags.append(readSymbol());
        if (currentSymbol == 0)
            return;

        while (readSymbol() != tagEnd) {
            if (currentSymbol == tagClose) {
                if (tagName.length() == 0)
                    closedTag = true;
                else
                    singleTag = true;
                break;
            }
          
            if(currentSymbol == space)
                readAttribute(element);
            else
                tagName.append(currentSymbol);
        }

        if (parentElement != null) {
            textBetweenTags.deleteCharAt(textBetweenTags.length() - 1);
            parentElement.setTextContent(new TextImp(textBetweenTags.toString()));
        }

        element.setTagName(tagName.toString());

        if (tagName.length() != 0)
            if (tagName.charAt(0) == question && tagName.charAt(tagName.length() - 1) == question)
                declaration = true;

        if (!closedTag && !declaration){
            element.setParentElement(parentElement);
            if (parentElement != null)
                parentElement.addChildElement(element);
            else
                documentImp = new DocumentImp(element);
        }

        if (!closedTag && !singleTag && !declaration) {
            readTag(element);
        }
        
        else if (!closedTag)
            readTag(parentElement);
       else
            readTag((ElementImp) parentElement.getParentElement());

    }

    private void readAttribute(ElementImp element) {
        AttributeImp attribute = new AttributeImp();
        StringBuilder attributeName = new StringBuilder();
        StringBuilder attributeValue = new StringBuilder();

        while (readSymbol() != equal)
            attributeName.append(currentSymbol);

        readSymbol();
        
        while (readSymbol() != quotes)
            attributeValue.append(currentSymbol);

        attribute.setName(attributeName.toString());
        attribute.setValue(attributeValue.toString());
        element.addAttribute(attribute);
    }

}