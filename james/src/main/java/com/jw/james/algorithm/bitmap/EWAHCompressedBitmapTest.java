package com.jw.james.algorithm.bitmap;

import com.googlecode.javaewah.EWAHCompressedBitmap;
import com.googlecode.javaewah.datastructure.BitSet;
import com.googlecode.javaewah.datastructure.ImmutableBitSet;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * guoyy com.jw.algorithm.bitmap
 *
 * @Description: com.jw.algorithm.bitmap.EWAHCompressedBitmapTest
 * @Author: guoyiyong/james
 * @Date: 2020-07-08 21:05
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class EWAHCompressedBitmapTest {

    public static void main(String[] args) throws IOException {
        EWAHCompressedBitmap bitmap1 = EWAHCompressedBitmap.bitmapOf(0, 2, 55, 64, 1 << 30);
        EWAHCompressedBitmap bitmap2 = EWAHCompressedBitmap.bitmapOf(1, 2, 64, 1 << 30);

        System.out.println(bitmap1);
        System.out.println(bitmap2);

        // or
        EWAHCompressedBitmap bitmap_or = bitmap1.or(bitmap2);
        System.out.println("bitmap_or: " + bitmap_or);
        System.out.println("memory usage(bytes): " + bitmap_or.sizeInBytes());

        // and
        EWAHCompressedBitmap bitmap_and = bitmap1.and(bitmap2);
        System.out.println("bitmap_and: " + bitmap_and);
        System.out.println("memory usage(bytes): " + bitmap_and.sizeInBytes());

        // xor
        EWAHCompressedBitmap bitmap_xor = bitmap1.xor(bitmap2);
        System.out.println("bitmap_xor: " + bitmap_xor);
        System.out.println("memory usage(bytes): " + bitmap_xor.sizeInBytes());

        EWAHCompressedBitmap bitmap3 = EWAHCompressedBitmap.bitmapOf(5, 55, 1 << 30);
        EWAHCompressedBitmap bitmap4 = EWAHCompressedBitmap.bitmapOf(4, 66, 1 << 30);

        EWAHCompressedBitmap andbitmap = EWAHCompressedBitmap.and(bitmap1, bitmap2, bitmap3, bitmap4);
        System.out.println("b1 AND b2 AND b3 AND b4: " + andbitmap);

        File tmpfile = new File("tmpfile");
        final FileOutputStream fos = new FileOutputStream(tmpfile);
        BitSet bitmap = BitSet.bitmapOf(0, 2, 55, 64, 512);
        bitmap.serialize(new DataOutputStream(fos));
        RandomAccessFile memoryMappedFile = new RandomAccessFile(tmpfile, "r");
        ByteBuffer bb = memoryMappedFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, tmpfile.length());
        ImmutableBitSet mapped = new ImmutableBitSet(bb.asLongBuffer());
        System.out.println("mapped: " + mapped);
    }

}
