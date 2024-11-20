package com.oasis.acquiesce.util;

import com.oasis.acquiesce.domain.Attach;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class UpDownUtil {

    private final String UPLOAD = "c:\\upload";

    public List<Attach> upload(MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return new ArrayList<>();
        }

        ArrayList<Attach> attachList = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            String saveFileName = uuid + "_" + fileName;

            //jpg,gif,png,bmp
            String suffix = fileName.substring(fileName.lastIndexOf(".")+1);

            String regExp = "^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)";

            if(!suffix.matches(regExp)){
                continue;
            }

            try (InputStream in = file.getInputStream();
                 OutputStream out = new FileOutputStream(UPLOAD + File.separator + saveFileName))
            {
                FileCopyUtils.copy(in, out);
                log.info("File uploaded successfully to " + UPLOAD + File.separator + saveFileName);

                Thumbnails.of(new File(UPLOAD + File.separator + saveFileName))
                        .size(200, 200)
                        .toFile(UPLOAD + File.separator + "s_" + saveFileName);

            } catch (Exception e) {
                log.error("Failed to upload file: " + e.getMessage());
            }

            Attach attach = new Attach();
            attach.setUuid(uuid);
            attach.setFileName(fileName);

            attachList.add(attach);

        }// 파일 업로드 처리

        return attachList;
    }

    public void deleteFiles(String[] fileNames) {

        if (fileNames == null || fileNames.length == 0) {
            return;
        }

        for (String fileName : fileNames) {
            File originalFile = new File(UPLOAD + File.separator + fileName);
            File thumbFile = new File(UPLOAD + File.separator + "s_" + fileName);

            originalFile.delete();
            thumbFile.delete();
        }
    }
}
