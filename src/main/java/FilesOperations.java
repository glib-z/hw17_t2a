import java.io.File;

public class FilesOperations {

    public static void main(String[] args) {
        String sourceZip = "D:\\GZ\\Workspace\\IdeaProjects\\src.zip";
        String targetZip = "D:\\GZ\\Workspace\\IdeaProjects\\Test\\src.zip";
        String outDir = "D:\\GZ\\Workspace\\IdeaProjects\\Test\\src";
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

        GZip.extract(targetZip, outDir);

        SeekJavaFiles.seek(outDir);

    }
}
