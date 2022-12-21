package top.himenma.rinetdconfig.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.himenma.rinetdconfig.common.annotation.LogHandler;
import top.himenma.rinetdconfig.common.api.CommonResult;
import top.himenma.rinetdconfig.service.MainService;

import java.util.Map;

@RestController
@RequestMapping("/rinetd")
// @CrossOrigin("http://localhost:2334")
@CrossOrigin("*")
// @RequiredArgsConstructor
public class MainController {

    @Autowired
    private MainService mainService;

    @Value("${rinetd.configPath}")
    private String rinetdConfigPath;

    @Value("${rinetd.hostPath}")
    private String rinetdHostPath;

    @LogHandler(title = "读取配置文件")
    @GetMapping("/readConfig")
    public CommonResult readConfig(@RequestParam("path") String path) {
        if (path.equals("r")) {
            path = rinetdConfigPath;
        } else if (path.equals("h")) {
            path = rinetdHostPath;
        } else {
            return CommonResult.failed("配置文件路径不正确！");
        }
        return mainService.readConfig(path);
    }

    @LogHandler(title = "更新配置文件")
    @PostMapping("/updateConfig")
    public CommonResult updateConfig(@RequestBody Map<String, String> data) {
        String path = data.get("path");
        if (StrUtil.isEmpty(path)) {
            return CommonResult.failed("path 不能为空");
        }
        if (path.equals("r")) {
            path = rinetdConfigPath;
        } else if (path.equals("h")) {
            path = rinetdHostPath;
        } else {
            return CommonResult.failed("配置文件路径不正确！");
        }
        return mainService.updateConfig(path, data);
    }

    @LogHandler(title = "执行shell命令")
    @GetMapping("/exeShell")
    public CommonResult exeShell(@RequestParam("command") String command) {
        return mainService.exeShell(command);
    }
}
