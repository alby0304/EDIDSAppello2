import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.UnknownBlockTagTree;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Element;
import jdk.javadoc.doclet.Taglet;

public class expectedResults  implements Taglet {

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
        return "Test Case Design";
    }

    @Override
    public String toString(List<? extends DocTree> tags, Element element) {
        StringBuilder sb = new StringBuilder();
        sb.append("<b>Test Case Design:</b><br>");
        for (DocTree tag : tags) {
            if (tag instanceof UnknownBlockTagTree) {
                sb.append(((UnknownBlockTagTree) tag).getContent()).append("<br>");
            }
        }
        return sb.toString();
    }
}
