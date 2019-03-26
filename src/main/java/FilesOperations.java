import java.io.File;

public class FilesOperations {

    public static void main(String[] args) {
        String sourceZip = "D:\\GZ\\Workspace\\IdeaProjects\\src.zip";
        String targetZip = "D:\\GZ\\Workspace\\IdeaProjects\\Test\\src.zip";
        String outDir = "D:\\GZ\\Workspace\\IdeaProjects\\Test\\src";

        System.out.println("Moving src.zip file...");
        long time = System.currentTimeMillis();

        File source = new File(sourceZip);
        if (source.isFile()) {
            if (source.renameTo(new File(targetZip))) {
                System.out.println("OK");
            } else {
                System.out.println("False");
            }
        } else {
            System.out.println(sourceZip + " is not a file");
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Moving time: " + time / 1000.0 + "s");

        System.out.println("Unzipping src.zip file...");
        time = System.currentTimeMillis();
        GZip.extract(targetZip, outDir);
        time = System.currentTimeMillis() - time;
        System.out.println("Unzipping time: " + time / 1000.0 + "s");

        SeekJavaFiles.seek(outDir);

    }
}
