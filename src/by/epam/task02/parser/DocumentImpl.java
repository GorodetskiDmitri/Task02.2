package by.epam.task02.parser;

public class DocumentImpl implements Document {
    private Element documentElement;

    public DocumentImpl(){
    }

    public DocumentImpl(Element documentElement){
        this.documentElement = documentElement;
    }

    @Override
    public Element getDocumentElement() {
        return documentElement;
    }

    @Override
    public short getNodeType() {
        return 0;
    }

    @Override
    public String getNodeValue() {
        return null;
    }

    @Override
    public Node getParentNode() {
        return null;
    }

    @Override
    public Node getFirstChild() {
        return documentElement;
    }

    @Override
    public Node getLastChild() {
        return documentElement;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentImpl documentImp = (DocumentImpl) o;

        return !(documentElement != null ? !documentElement.equals(documentImp.documentElement) : documentImp.documentElement != null);

    }

    @Override
    public int hashCode() {
        return documentElement != null ? documentElement.hashCode() : 0;
    }

    @Override
    public String toString() {
    	return getClass().getName() + "@" + "documentElement : " + documentElement;
    }
}