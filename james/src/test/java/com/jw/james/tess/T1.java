package com.jw.james.tess;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * guoyy com.jw.tess
 *
 * @Description: com.jw.tess.T1
 * @Author: guoyiyong/james
 * @Date: 2021-01-01 15:39
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class T1 {

    public static void main(String[] args) throws TesseractException {
        ITesseract instance = new Tesseract();

        instance.setDatapath("/usr/local/share/tessdata");
        // 选择字库文件（只需要文件名，不需要后缀名）
        instance.setLanguage("chi_sim");

        File file1 = new File("/Users/james/Pictures/image1.jpeg");
        File file2 = new File("/Users/james/Pictures/image2.png");

        String ret1 = instance.doOCR(file1);
        String ret2 = instance.doOCR(file2);

        System.out.println(ret1);
        System.out.println(ret2);
    }

}
