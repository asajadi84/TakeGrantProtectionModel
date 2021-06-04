package babaey.vahid;

public class MyGraphVertex {

    enum VertexType {
        SUBJECT, OBJECT
    }

    private String vertexName;
    private VertexType vertexType;

    public MyGraphVertex(String vertexName, VertexType vertexType) {
        this.vertexName = vertexName;
        this.vertexType = vertexType;
    }

    public String getVertexName() {
        return vertexName;
    }

    public VertexType getVertexType() {
        return vertexType;
    }
}
