package com.alvin.mall.service;

import com.alvin.mall.dto.OssPolicyResult;

/**
 * @Author Alvin
 * Created by Alvin on 2023/5/31.
 */
public interface OssService {

    /**
     * 获取OSS上传文件授权返回的结果
     * @return OssPolicyResult
     */
    OssPolicyResult policy();
}