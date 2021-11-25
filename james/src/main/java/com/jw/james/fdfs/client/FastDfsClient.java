package com.jw.james.fdfs.client;

import com.alibaba.fastjson.JSON;
import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Set;

/**
 * Description: guoyy
 * com.jw.fdfs.client.FastDfsClient
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/11/23 17:54
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Service
public class FastDfsClient {
    @Resource
    private FastFileStorageClient fastFileStorageClient;
    @Resource
    private FdfsWebServer fdfsWebServer;

    @Nullable
    public String getMetadata(String groupName, String path) {
        Set<MetaData> metadata = fastFileStorageClient.getMetadata(groupName, path);
        return JSON.toJSONString(metadata);
    }

    public String uploadFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            StorePath storePath = fastFileStorageClient.uploadFile(fis, file.length(),
                    FilenameUtils.getExtension(file.getName()), null);
            return storePath.getFullPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String uploadFile(byte[] data, String fileExtension) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        StorePath storePath = fastFileStorageClient.uploadFile(bais, data.length, fileExtension, null);
        return storePath.getFullPath();
    }

    public boolean downloadFile(String fileUrl, File file) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            byte[] data = fastFileStorageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
            fos.write(data);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteFile(String fileUrl) {
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getResAccessUrl(String path) {
        return fdfsWebServer.getWebServerUrl() + path;
    }

}
