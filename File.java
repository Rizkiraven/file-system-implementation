import java.text.SimpleDateFormat;
import java.util.Date;

public class File {
    private String name;
    private int size;
    private Date creationDate;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
        this.creationDate = new Date();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getFormattedCreationDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(creationDate);
    }
}
