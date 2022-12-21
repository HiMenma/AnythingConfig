package top.himenma.rinetdconfig.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.himenma.rinetdconfig.common.api.CommonResult;
import top.himenma.rinetdconfig.service.FrpConfigService;


@Service
public class FrpConfigServiceImpl implements FrpConfigService {

    @Value("${frp.command.frpc.start}")
    private String frpcStart;
    @Value("${frp.command.frps.start}")
    private String frpsStart;
    @Value("${frp.config.frpc}")
    private String frpcConfig;
    @Value("${frp.config.frps}")
    private String frpsConfig;

    @Override
    public CommonResult getFrpcConfig() {
        return CommonResult.success(FileUtil.readUtf8String(frpcConfig));
    }

    @Override
    public CommonResult getFrpsConfig() {
        return CommonResult.success(FileUtil.readUtf8String(frpsConfig));
    }

    @Override
    public CommonResult upFrpcConfig(String content) {
        if (StrUtil.isEmpty(content)) {
            return CommonResult.failed("更新文件内容为空！");
        }
        return CommonResult.success(FileUtil.writeUtf8String(content, frpcConfig));
    }

    @Override
    public CommonResult upFrpsConfig(String content) {
        if (StrUtil.isEmpty(content)) {
            return CommonResult.failed("更新文件内容为空！");
        }
        return CommonResult.success(FileUtil.writeUtf8String(content, frpsConfig));
    }


    @Override
    public CommonResult startFrps() {
        return CommonResult.success(RuntimeUtil.execForStr(frpsStart));
    }

    @Override
    public CommonResult startFrpc() {
        return CommonResult.success(RuntimeUtil.execForStr(frpcStart));
    }
}
