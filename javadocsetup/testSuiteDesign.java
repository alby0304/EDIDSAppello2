import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.UnknownBlockTagTree;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Element;
import jdk.javadoc.doclet.Taglet;

public class testSuiteDesign  implements Taglet {

    @Override
    public Set<Location> getAllowedLocations() {
        return EnumSet.allOf(Location.class);
    }

    @Override
    public boolean isInlineTag() {
        return false;
    }

    @Override
    public String getName() {
        return "Test Suite Design";
    }

    @Override
    public String toString(List<? extends DocTree> tags, Element element) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Test Suite Design:</h1><br>");
        for (DocTree tag : tags) {
            if (tag instanceof UnknownBlockTagTree) {
                sb.append(((UnknownBlockTagTree) tag).getContent()).append("<br>");
            }
        }
        return sb.toString();
    }
}
