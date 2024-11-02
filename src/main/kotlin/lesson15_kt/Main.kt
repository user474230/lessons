package lesson15_kt

import java.nio.ByteBuffer
import java.io.File
import java.io.RandomAccessFile
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import kotlin.io.path.fileSize

fun main() {
    case1("qwerty");
}

fun case1(str : String) {
    var result = 0
    val seq = str.toByteArray()
    RandomAccessFile("1.txt", "rw").use { file ->
        file.channel.use { channel ->
            val buffer = ByteBuffer.allocate(seq.size)
            var size = channel.read(buffer)
            while(size > 0) {
                buffer.flip()
                var equals = true
                for (char in seq) {
                    if (!buffer.hasRemaining() || char != buffer.get()) {
                        equals = false
                    }
                }
                if (equals) {
                    result++
                    buffer.clear()
                    size = channel.read(buffer)
                } else {
                    buffer.rewind()
                    buffer.get()
                    buffer.compact()
                    size = channel.read(buffer)
                }
            }
        }
    }
    println(result)
}

fun case2() {
    RandomAccessFile("dst.txt", "rw").use { dstFile ->
        dstFile.channel.use { dstChannel ->
            var dir = File("texts")
            for (file in dir.listFiles{file -> file.name.endsWith(".txt")}) {
                RandomAccessFile(file.absolutePath, "rw").use { srcFile ->
                    srcFile.channel.use { srcChannel ->
                        dstChannel.transferFrom(srcChannel, dstChannel.size(), srcChannel.size())
                    }
                }
            }
        }
    }
}

fun case3() {
    val list = mutableListOf<Path>()
    Files.walkFileTree(Paths.get("target"), object : SimpleFileVisitor<Path>() {
        override fun visitFile(file: Path?, attrs: BasicFileAttributes?): FileVisitResult? {
            if (file!!.fileSize() > 102400L)
                list.add(file)
            return FileVisitResult.CONTINUE;
        }
    })
    println(list)
}