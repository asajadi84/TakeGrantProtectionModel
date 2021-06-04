package babaey.vahid;

import org.jgrapht.graph.DefaultEdge;

public class MyGraphEdge extends DefaultEdge {

    enum EdgeRule {
        TAKE, GRANT, R
    }

    private EdgeRule edgeRule;

    public MyGraphEdge(EdgeRule edgeRule) {
        this.edgeRule = edgeRule;
    }

    public EdgeRule getEdgeRule() {
        return edgeRule;
    }

    public String getEdgeRuleString() {
        switch (edgeRule){
            case TAKE:
                return "take";

            case GRANT:
                return "grant";

            case R:
                return "r";

            default:
                return "none";
        }
    }
}
