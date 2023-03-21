package com.jw.james.comm;

import net.jpountz.lz4.LZ4BlockInputStream;
import net.jpountz.lz4.LZ4BlockOutputStream;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * tax_complex_entrance com.ly.ic.taxbag.standard.util
 *
 * @Description: com.ly.ic.taxbag.standard.util.compress.Lz4Util
 * @Author: guoyiyong/james
 * @Date: 2019-08-18 00:23
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 TCLY All rights reserved.
 */
public class Lz4Util {
    private final LZ4Compressor compressor;
    private final LZ4FastDecompressor decompressor;

    private Lz4Util() {
        LZ4Factory factory = LZ4Factory.fastestInstance();
        compressor = factory.fastCompressor();
        decompressor = factory.fastDecompressor();
    }

    public static Lz4Util instance() {
        return Singlon.INSTANCE;
    }

    public byte[] compress(byte[] src) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        LZ4BlockOutputStream bos = null;
        try {
            bos = new LZ4BlockOutputStream(os, 65536, compressor);
            bos.write(src);
        } finally {
            bos.close();
            os.close();
        }
        return os.toByteArray();
    }

    public byte[] uncompress(byte[] dest) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ByteArrayInputStream is = null;
        LZ4BlockInputStream bis = null;
        try {
            is = new ByteArrayInputStream(dest);
            bis = new LZ4BlockInputStream(is, decompressor);
            byte[] b = new byte[8192];
            while (true) {
                int i = bis.read(b);
                if (i <= -1) {
                    break;
                }
                os.write(b, 0, i);
                os.flush();
            }
        } finally {
            is.close();
            bis.close();
            os.close();
        }
        return os.toByteArray();
    }

    private static class Singlon {
        private static final Lz4Util INSTANCE = new Lz4Util();
    }

}
