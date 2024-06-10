import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        welcomeMessage();
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            fileSystem.prompt();
            String input = scanner.nextLine().trim();
            if (input.equals("exit"))
            {
                break;
            } 
            else if (input.startsWith("mkdir "))
            {
                fileSystem.mkdir(input.substring(6).trim());
            } 
            else if (input.startsWith("cd "))
            {
                fileSystem.cd(input.substring(3).trim());
            } 
            else if (input.equals("ls"))
            {
                fileSystem.ls();
            }
            else if (input.equals("dir"))
            {
                fileSystem.dir();
            }
            else if (input.startsWith("create "))
            {
                fileSystem.createFile(input.substring(7).trim());
            }
            else if (input.startsWith("rm "))
            {
                fileSystem.rm(input.substring(3).trim());
            }
            else if (input.startsWith("rmdir "))
            {
                fileSystem.rmdir(input.substring(6).trim());
            }
            else if (input.equals("pwd"))
            {
                fileSystem.pwd();
            }
            else if (input.startsWith("cat "))
            {
                fileSystem.cat(input.substring(4).trim());
            }
            else if (input.equals("help"))
            {
                fileSystem.help();
            }
            else
            {
                System.out.println("Invalid command");
            }
        }

        scanner.close();
    }

    private static void welcomeMessage()
    {
        System.out.println();
        System.out.println("Kelompok 1 Custom OS [Version 1.0.0]");
        System.out.println("(c) 2024. All rights reserved.");
        System.out.println();
    }
}
