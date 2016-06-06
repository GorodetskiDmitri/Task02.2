package by.epam.task02.parser;

public class TextImpl implements Text {
    private String value;

    public TextImpl() {
    }

    public TextImpl(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getWholeText() {
        return value;
    }

    @Override
    public void replaceWholeText(String content) {
        value = content;
    }

    @Override
    public short getNodeType() {
        return 3;
    }

    @Override
    public String getNodeValue() {
        return value;
    }

    @Override
    public Node getParentNode() {
        return null;
    }

    @Override
    public Node getFirstChild() {
        return null;
    }

    @Override
    public Node getLastChild() {
        return null;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextImpl textImp = (TextImpl) o;

        return !(value != null ? !value.equals(textImp.value) : textImp.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
    	return getClass().getName() + "@" + "value : " + value;
    }

}