import java.text.SimpleDateFormat;
import java.util.Date;

public class FileSystem 
{
    private Directory root;
    private Directory current;
    private long totalSpace = 100_000_000;
    private long freeSpace = totalSpace;

    public FileSystem() 
    {
        root = new Directory("TesOS", null);
        current = root;
    }

    public void mkdir(String name) 
    {
        if (current.hasChild(name)) 
        {
            System.out.println("Directory already exists");
        } 
        else 
        {
            Directory newDir = new Directory(name, current);
            current.addChild(name, newDir);
        }
    }

    public void cd(String name) 
    {
        if ("/".equals(name)) 
        {
            current = root;
        } 
        else if ("..".equals(name)) 
        {
            if (current.getParent() != null) 
            {
                current = current.getParent();
            } 
            else 
            {
                System.out.println("Already at root directory");
            }
        } 
        else if (current.hasChild(name)) 
        {
            current = current.getChild(name);
        } 
        else 
        {
            System.out.println("Directory not found");
        }
    }

    public void ls() 
    {
        for (String dirName : current.getChildren().keySet()) 
        {
            System.out.println(dirName + "      <DIR>");
        }
        for (String fileName : current.getFiles().keySet()) 
        {
            System.out.println(fileName + "     <FILE>");
        }
    }

    public void createFile(String name) 
    {
        if (current.hasFile(name)) 
        {
            System.out.println("File already exists");
            return;
        }

        int size = (int) (Math.random() * 2048) + 1; // Generate a random file size between 1 and 2048 bytes
        if (size > freeSpace) 
        {
            System.out.println("Not enough space to create the file");
            return;
        }

        File newFile = new File(name, size);
        current.addFile(name, newFile);
        freeSpace -= size;
    }

    public void dir() 
    {
        System.out.println(" Volume in drive TesOS is New Volume");
        System.out.println(" Volume Serial Number is 111B-222D");
        System.out.println("\n Directory of " + current.getFullPath());
        System.out.println();

        System.out.printf("%-20s <DIR>            .\n", current.getFormattedCreationDate());
        System.out.printf("%-20s <DIR>            ..\n", current.getParent() != null ? current.getParent().getFormattedCreationDate() : current.getFormattedCreationDate());

        for (Directory child : current.getChildren().values()) 
        {
            System.out.printf("%-20s <DIR>            %s\n", child.getFormattedCreationDate(), child.getName());
        }

        for (File file : current.getFiles().values()) 
        {
            System.out.printf("%-20s          %7d %s\n", file.getFormattedCreationDate(), file.getSize(), file.getName());
        }

        int totalFiles = current.getFiles().size();
        int totalSize = current.getFiles().values().stream().mapToInt(File::getSize).sum();

        System.out.printf("               %d File(s)     %8d bytes\n", totalFiles, totalSize);
        System.out.printf("               %d Dir(s) %13d bytes free\n", current.getChildren().size() + 2, freeSpace);
    }

    public void rm(String name)
    {
        if (current.hasFile(name)) 
        {
            File file = current.getFiles().remove(name);
            freeSpace += file.getSize();
            System.out.println("File deleted");
        } 
        else 
        {
            System.out.println("File not found");
        }
    }

    public void rmdir(String name)
    {
        if (current.hasChild(name))
        {
            current.getChildren().remove(name);
            System.out.println("Directory deleted");
        } 
        else
        {
            System.out.println("Directory not found");
        }
    }

    public void pwd() {
        System.out.println(current.getFullPath());
    }

    public void cat(String name)
    {
        if (current.hasFile(name))
        {
            System.out.println("Displaying contents of file: " + name);
            System.out.println("Content of " + name);
        }
        else
        {
            System.out.println("File not found");
        }
    }

    public void help()
    {
        System.out.println("Available commands:");
        System.out.println("MKDIR <name>    Create a new directory");
        System.out.println("CD <name>       Change directory");
        System.out.println("LS              List directories and files");
        System.out.println("DIR             Detailed list of directories and files");
        System.out.println("CREATE <name>   Create a new file");
        System.out.println("RM <name>       Remove a file");
        System.out.println("RMDIR <name>    Remove a directory");
        System.out.println("PWD             Print working directory");
        System.out.println("CAT <name>      Display file contents");
        System.out.println("HELP            Display this help message");
        System.out.println("EXIT            Exit the file system");
        System.out.println();
    }

    public void prompt()
    {
        System.out.print(current.getFullPath() + ">");
    }
}
