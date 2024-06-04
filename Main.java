import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            fileSystem.prompt();
            String input = scanner.nextLine().trim();
            if (input.equals("exit")) {
                break;
            } else if (input.startsWith("mkdir ")) {
                fileSystem.mkdir(input.substring(6).trim());
            } else if (input.startsWith("cd ")) {
                fileSystem.cd(input.substring(3).trim());
            } else if (input.equals("ls")) {
                fileSystem.ls();
            } else if (input.equals("dir")) {
                fileSystem.dir();
            } else if (input.startsWith("create ")) {
                fileSystem.createFile(input.substring(7).trim());
            } else {
                System.out.println("Invalid command");
            }
        }

        scanner.close();
    }
}
