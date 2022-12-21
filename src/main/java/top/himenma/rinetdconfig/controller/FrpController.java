package top.himenma.rinetdconfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.himenma.rinetdconfig.common.annotation.LogHandler;
import top.himenma.rinetdconfig.common.api.CommonResult;
import top.himenma.rinetdconfig.service.FrpConfigService;

@RestController
@RequestMapping("frp")
@CrossOrigin("*")
public class FrpController {

    @Autowired
    private FrpConfigService frpService;

    @LogHandler("获取frpc配置")
    @RequestMapping("/getFrpcConfig")
    public CommonResult getFrpcConfig() {
        return frpService.getFrpcConfig();
    }

    @LogHandler("获取frps配置")
    @RequestMapping("/getFrpsConfig")
    public CommonResult getFrpsConfig() {
        return frpService.getFrpsConfig();
    }

    @LogHandler("更新frpc配置")
    @RequestMapping("/upFrpcConfig")
    public CommonResult upFrpcConfig(String content) {
        return frpService.upFrpcConfig(content);
    }

    @LogHandler("更新frps配置")
    @RequestMapping("/upFrpsConfig")
    public CommonResult upFrpsConfig(String content) {
        return frpService.upFrpsConfig(content);
    }

    @LogHandler("启动frps")
    @RequestMapping("/startFrps")
    public CommonResult startFrps() {
        return frpService.startFrps();
    }

    @LogHandler("启动frpc")
    @RequestMapping("/startFrpc")
    public CommonResult startFrpc() {
        return frpService.startFrpc();
    }

}
