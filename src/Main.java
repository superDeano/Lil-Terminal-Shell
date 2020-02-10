import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static String input;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        System.out.println("Sup");
        File file = new File("./");

         goToFolder(file.getAbsolutePath());

    }

    private static void goToFolder(String place) {

        File file = new File(place);
        if (file == null) {
            System.out.println("Location unrecognized, file FUCKED!!!");
        }

        do {
            System.out.println("What do you want to do ?");

            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }


            if (input.contains("return")) {
                System.out.println("Back");
                return;
            } else if (input.contains("ls")) {
                if (input.contains("-f")) {
                    displayListOfFoldersOnlyInCurrentDirectory(file);
                } else {
                    displayListOfFolders(file.list());
                }
            } else if (input.contains("cd")) {
                String secondArg = getSecondArgument(input);
                if (secondArg != null) {
                    goToFolder(file.getAbsolutePath() + secondArg);
                }
            } else if (input.contains("pwd")) {
                System.out.println(file.getAbsolutePath());
            } else {
                System.out.println("Unrecognized Command");
            }

        } while (true);

    }

    private static String getSecondArgument(String cmd) {

        if (cmd.contains("~")) {
            return replaceHomeShortCut(cmd);
        }

        String cmdArray[] = cmd.split("\\s+");
        if (cmdArray.length >= 2) {
            return (cmdArray[1]);
        } else {
            System.out.println("CMD length is less than 2");
            return null;
        }
    }

    private static void displayListOfFolders(String[] list) {
        if (list != null) {
            for (String s : list) {
                System.out.println(s);
            }
        } else {
            System.out.println("Problem with file.list()");
        }
    }

    private static String replaceHomeShortCut(String s) {
        String array[] = s.split("~");

        return "/Users/Deano" + array[1];
    }

    private static void displayListOfFoldersOnlyInCurrentDirectory(File file) {
        // Arrays.stream(file.listFiles()).filter(file1 -> file1.isDirectory()).forEach(file1 -> {
        //   System.out.println(file1.getName());
//        });
        String[] lol = Arrays.stream(file.listFiles()).filter(file1 -> file1.isDirectory()).map(file1 -> file1.getName()).toArray(String[]::new);
        for (String s : lol) {
            System.out.println(s);
        }
    }
}
