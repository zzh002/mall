package com.mall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZZH
 * @date 2018/10/2 0002 16:41
 **/
public interface IFileService {

    String upload(MultipartFile file, String path);
}
