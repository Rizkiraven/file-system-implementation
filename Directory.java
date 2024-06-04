import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Directory {
    private String name;
    private Directory parent;
    private Map<String, Directory> children;
    private Map<String, File> files;
    private Date creationDate;

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.children = new HashMap<>();
        this.files = new HashMap<>();
        this.creationDate = new Date();
    }

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public Map<String, Directory> getChildren() {
        return children;
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void addChild(String name, Directory child) {
        children.put(name, child);
    }

    public Directory getChild(String name) {
        return children.get(name);
    }

    public boolean hasChild(String name) {
        return children.containsKey(name);
    }

    public void addFile(String name, File file) {
        files.put(name, file);
    }

    public boolean hasFile(String name) {
        return files.containsKey(name);
    }

    public String getFullPath() {
        if (parent == null) {
            return name + ":";
        }
        return parent.getFullPath() + "\\" + name;
    }

    public String getFormattedCreationDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(creationDate);
    }
}
