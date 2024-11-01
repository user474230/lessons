package lesson15;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        case2();
    }

    public static void case1(String src) throws IOException {
        var res = 0;
        var seq = src.getBytes();
        var file = new RandomAccessFile("1.txt", "rw");
        var channel = file.getChannel();
        var buffer = ByteBuffer.allocate(seq.length);
        var size = channel.read(buffer);
        while (size >= 0) {
            buffer.flip();
            boolean equals = true;
            for (int i = 0; i < seq.length; ++i) {
                var curr = buffer.get();
                if (curr != seq[i]) {
                    equals = false;
                    break;
                }
            }
            if (equals) {
                res++;
                buffer.clear();
                size = channel.read(buffer);
            } else {
                buffer.rewind();
                buffer.get();
                buffer.compact();
                size = channel.read(buffer);
            }
        }

        System.out.println(res);
    }

    public static void case2() throws FileNotFoundException {
        var dir = new File("texts");
        if (dir.isDirectory()) {
            try (var dst = new RandomAccessFile("out.txt", "rw");
                 var dstChannel = dst.getChannel()) {
                for (var file : dir.listFiles()) {
                    if (!file.isDirectory() && file.getName().endsWith(".txt")) {
                        try (var src = new RandomAccessFile(file.getAbsolutePath(), "rw");
                             var srcChannel = src.getChannel()) {
                            dstChannel.transferFrom(srcChannel, dstChannel.size(), srcChannel.size());
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public static void case3() throws IOException {
        List<File> result = new ArrayList<>();
        Files.walkFileTree(Path.of("target"), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toFile().length() > 102400) {
                    System.out.println(file.getFileName());
                }
                return super.visitFile(file, attrs);
            }
        });
    }
}
