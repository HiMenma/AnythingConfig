package top.himenma.rinetdconfig.service.impl;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.RuntimeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.himenma.rinetdconfig.common.api.CommonResult;
import top.himenma.rinetdconfig.service.MainService;

import java.util.Map;

@Service
public class MainServiceImpl implements MainService {

    @Value("${rinetd.command.pkill}")
    private String pkill;

    @Value("${rinetd.command.start}")
    private String start;

    @Value("${rinetd.command.pwd}")
    private String pwd;


    @Override
    public CommonResult readConfig(String path) {
        FileReader reader = new FileReader(path);
        // List<String> lines = reader.readLines();
        String content = reader.readString();
        // HashMap<String, String> map = new HashMap<>();
        // for (int i = 0; i < lines.size(); i++) {
        //     map.put(String.valueOf(i), lines.get(i));
        // }
        return CommonResult.success(content);
    }

    @Override
    public CommonResult updateConfig(String path, Map<String, String> data) {
        FileWriter writer = new FileWriter(path);
        writer.write(""); // 先将文件清空，后面再重新写入 (优化点：区分覆盖（全量 和 部分 覆盖 如何实现？）和追加)
        data.forEach((k, v) -> {
            if ("data".equals(k)) {
                writer.append(v + '\n');
            }
        });
        return CommonResult.success("文件更新成功!");
    }

    @Override
    public CommonResult exeShell(String command) {
        if ("pwd".equals(command)) {
            command = pwd;
        } else if ("pkill".equals(command)) {
            command = pkill;
        } else if ("start".equals(command)) {
            command = start;
        } else {
            return CommonResult.failed("命令有误！");
        }
        String execForStr = RuntimeUtil.execForStr(command);
        return CommonResult.success(execForStr, "命令执行成功!");
    }
}
