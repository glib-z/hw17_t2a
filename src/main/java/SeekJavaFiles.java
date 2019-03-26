import java.io.*;

public class SeekJavaFiles {
    private static int javaFilesCounter = 0;
    private static int totalFilesCounter = 0;
    private static int annotationCounter = 0;

    public static void seek (String path) {
        //String path = "D:\\GZ\\Workspace\\IdeaProjects\\src";
        //String path = "/home/glib/IdeaProjects/src";
        System.out.println("Looking for *.java files in the \"" + path + "\" path");
        File homeDir = new File(path);
        if (homeDir.exists() && homeDir.isDirectory()) {
            System.out.println("Reading files...");
            long time = System.currentTimeMillis();
            countJavaFiles(homeDir.getAbsolutePath());
            time = System.currentTimeMillis() - time;
            System.out.println("Handling time: " + time / 1000.0 + "s");
        } else {
            System.out.println("The specified path is not directory.");
            return;
        }
        System.out.printf("The specified directory has %d *.java files (total %d files)\n",
                javaFilesCounter,
                totalFilesCounter);

        System.out.printf("Quantity of files with annotation @FunctionalInterface is: %d\n", annotationCounter);
    }

    private static void countJavaFiles(String filepath) {
        File homeDir = new File(filepath);
        File[] files = homeDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    countJavaFiles(file.getAbsolutePath());
                } else {
                    totalFilesCounter++;
                    if (file.getName().contains(".java")) {
                        javaFilesCounter++;
                        try {
                            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
                            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                            String s;
                            while ((s = br.readLine()) != null) {
                                if (s.contains("@FunctionalInterface")) {
                                    annotationCounter++;
                                    System.out.println("Contains annotation \"@FunctionalInterface\": " +
                                            file.getAbsolutePath());
                                }
                            }
                            fstream.close();
                        } catch (IOException e) {
                            System.out.println("FileInputStream error");
                        }
                    }
                }
            }
        }
    }

}
