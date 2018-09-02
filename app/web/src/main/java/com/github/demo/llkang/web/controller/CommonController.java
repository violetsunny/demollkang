/**
 * LY.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.github.demo.llkang.web.controller;

import com.github.demo.llkang.utils.UpLoadUtils;
import com.github.demo.llkang.web.controller.model.FileDTO;
import com.github.demo.llkang.web.controller.model.ResultMsg;
import com.github.demo.llkang.utils.LogUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author kllp0648
 * @version $Id: CommonController, v 0.1 2017/12/8 17:26 kllp0648 Exp $
 */
@Controller
public class CommonController {

    /**
     * 上传图片
     *
     * @param files
     * @return
     */
    @RequestMapping(value = "/img/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg upload(@RequestParam(value = "files", required = false) MultipartFile[] files) {
        ResultMsg resultMsg = new ResultMsg();
        List<FileDTO> fileDtoList = new ArrayList<FileDTO>();
        if (null != files && files.length > 0) {
            for (MultipartFile file : files) {
                InputStream inputStream;
                try {
                    inputStream = file.getInputStream();
                    String fileName = file.getOriginalFilename();
                    String fileKey = UUID.randomUUID().toString();
                    boolean result = UpLoadUtils.putObjByStream(fileKey, inputStream);
                    if (result) {
                        String fileUrl = UpLoadUtils.getURL(fileKey);
                        FileDTO fileDTO = new FileDTO();
                        fileDTO.setFileName(fileName);
                        fileDTO.setFileKey(fileKey);
                        fileDTO.setFileUrl(fileUrl);
                        fileDtoList.add(fileDTO);
                    } else {
                        resultMsg.setResult(false);
                        resultMsg.setMessage("上传失败");
                    }
                } catch (IOException e) {
                    LogUtils.error("上传文件失败,filename={}", "upload", file.getOriginalFilename(), e);
                }
            }

            resultMsg.setResult(true);
            resultMsg.setObj(fileDtoList);
        }
        return resultMsg;
    }
}
