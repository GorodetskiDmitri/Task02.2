package by.epam.task02.domain;

public class TextImp implements Text {
    private String value;

    public TextImp() {
    }

    public TextImp(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextImp textImp = (TextImp) o;

        return !(value != null ? !value.equals(textImp.value) : textImp.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TextImp{" +
                "value='" + value + '\'' +
                '}';
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
}