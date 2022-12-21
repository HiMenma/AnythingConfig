package top.himenma.rinetdconfig.service;

import top.himenma.rinetdconfig.common.api.CommonResult;

public interface FrpConfigService {

    CommonResult getFrpcConfig();

    CommonResult getFrpsConfig();

    CommonResult upFrpcConfig(String content);

    CommonResult upFrpsConfig(String content);

    CommonResult startFrps();

    CommonResult startFrpc();
}
