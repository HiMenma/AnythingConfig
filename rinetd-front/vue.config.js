const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: '/',
  outputDir: 'dist',
  assetsDir:"static",
  indexPath:'index.html',
  pwa: {
    iconPaths: {
      favicon32: 'public/综合.png',
      favicon16: 'public/综合.png',
      appleTouchIcon: 'public/综合.png',
      maskIcon: 'public/综合.png',
      msTileImage: 'public/综合.png'
    }
  },
  devServer:{
    port: 2334,
    // // Paths
    // assetsSubDirectory: 'static',
    // assetsPublicPath: '/',
    // proxyTable: { // 配置跨域
    //   '/api':{
    //     target:`http://localhost`, //请求后台接口
    //     changeOrigin:true, // 允许跨域
    //     pathRewrite:{
    //       '^/api' : '' // 重写请求
    //     }
    //   }
    // }
  }
})
