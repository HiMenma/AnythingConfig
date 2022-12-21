package top.himenma.rinetdconfig.service;

import top.himenma.rinetdconfig.common.api.CommonResult;

import java.util.Map;


public interface MainService {
    CommonResult readConfig(String path);

    CommonResult updateConfig(String path, Map<String, String> data);

    CommonResult exeShell(String command);

}
