import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GZip {

    private static final int BUFFER_SIZE = 1024;

    public static void extract(String fileZip, String outDir) {
        byte[] buffer = new byte[BUFFER_SIZE];

        // Создаем каталог, куда будут распакованы файлы
        final File dstDir = new File(outDir);
        if (!dstDir.exists()) {
            dstDir.mkdir();
        }

        try {
            // Получаем содержимое ZIP архива
            final ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry ze = zis.getNextEntry();
            String nextFileName;

            while (ze != null) {
                nextFileName = ze.getName();
                File nextFile = new File(outDir + File.separator + nextFileName);
//                System.out.println("Распаковываем: " + nextFile.getAbsolutePath());
                // Если мы имеем дело с каталогом - надо его создать. Если
                // этого не сделать, то не будут созданы пустые каталоги архива
                if (ze.isDirectory()) {
                    nextFile.mkdir();
                } else {
                    // Создаем все родительские каталоги
                    new File(nextFile.getParent()).mkdirs();
                    // Записываем содержимое файла
                    try (FileOutputStream fos = new FileOutputStream(nextFile)) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
