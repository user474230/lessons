package lesson14;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main implements Serializable {

    public static void main(String[] args) {
        case3();
    }

    public static void case1(String src) {
        int res = 0;
        byte[] seq = src.getBytes();
        try (var fis = new FileInputStream("1.txt");
             var bis = new BufferedInputStream(fis)) {
            byte[] bytes = bis.readAllBytes();
            for (int i = 0; i < (bytes.length - seq.length); ++i) {
                boolean equals = true;
                for (int j = 0; j < seq.length; ++j) {
                    if (bytes[i + j] != seq[j]) {
                        equals = false;
                        break;
                    }
                }
                if (equals) {
                    res++;
                }
            }
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void case2() {
        try (var fos = new BufferedOutputStream(new FileOutputStream("out.txt"))) {
            File dir = new File("texts");
            if (dir.isDirectory()) {
                var files = dir.listFiles();
                for (var file : files) {
                    try (var fis = new BufferedInputStream(new FileInputStream(file))) {
                        byte[] buffer = new byte[1024];
                        int len = 0;
                        while (0 < (len = fis.read(buffer))) {
                            fos.write(buffer, 0, len);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void case3() {
        var dir = new File("toDelete");
        if (dir.isDirectory()) {
            List<File> list = new ArrayList<File>();
            List<File> dirsToDelete = new ArrayList<File>();
            list.add(dir);
            while (!list.isEmpty()) {
                var lastInd = list.size() - 1;
                var currDir = list.get(lastInd);
                list.remove(lastInd);
                for (var file : currDir.listFiles()) {
                    if (file.isDirectory()) {
                        list.add(file);
                    } else {
                        file.delete();
                    }
                }
                dirsToDelete.add(currDir);
            }
            while (!dirsToDelete.isEmpty()) {
                var lastInd = dirsToDelete.size() - 1;
                dirsToDelete.get(lastInd).delete();
                dirsToDelete.remove(lastInd);
            }
        }
    }
}

































