package com.jw.enums;

import org.apache.commons.lang3.StringUtils;

public enum FileTypeEnum {
    // 行李
    FOO("OPT", "行李文件"),
    // 税费
    YQYR("SER", "燃油文件"),
    FPP("PFCSUBS", "XF税文件"),
    XTAX("TAX", "税费文件"),
    // 运价
    G16("G16", "G16");

    private String key;
    private String desc;

    /**
     * @param key
     * @param desc
     */
    FileTypeEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static boolean isTaxfeeFile(String fileTypeKey) {
        if (StringUtils.isNotEmpty(fileTypeKey)) {
            if (YQYR.key.equals(fileTypeKey)) {
                return true;
            }
            if (FPP.key.equals(fileTypeKey)) {
                return true;
            }
            if (XTAX.key.equals(fileTypeKey)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isG16File(String fileTypeKey) {
        if (StringUtils.isNotEmpty(fileTypeKey)) {
            if (G16.key.equals(fileTypeKey)) {
                return true;
            }
        }
        return false;
    }
}