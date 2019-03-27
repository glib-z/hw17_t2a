import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GZip {

    private static final int BUFFER_SIZE = 1024;

    public static int extract(String fileZip, String outDir) {
        byte[] buffer = new byte[BUFFER_SIZE];

        System.out.print("Unzipping " + fileZip + " file...");
        long time = System.currentTimeMillis();

        final File dstDir = new File(outDir);   // Makes the directory for file unzipping
        if (!dstDir.exists()) {
            if (!dstDir.mkdir()) {
                System.out.println("Destination directory is not created!");
                return -1;
            }
        }

        try {
            final ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                File nextFile = new File(outDir + File.separator + ze.getName());
                if (ze.isDirectory()) {
                    nextFile.mkdir();                                               // Makes a directory
                } else {
                    new File(nextFile.getParent()).mkdirs();                        // Make all parent directories
                    try (FileOutputStream fos = new FileOutputStream(nextFile)) {   // Writing file from the zip file
                        int length;
                        while((length = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, length);
                        }
                    }
                }
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            time = System.currentTimeMillis() - time;
            System.out.println("OK\nUnzipping time: " + time / 1000.0 + "s");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
