### rinetd项目跨域问题

后台设置了允许 localhost的跨域请求 @CrossOrigin("http://localhost:2334")

+ 前台使用localhost访问ok

+ 使用本机iphttp://192.168.1.9/访问失败

设置@CrossOrigin("*")

+ localhost和192.168.1.9都可访问

不能设置cloudFlare https代理，还需清除cloudFlare和浏览器缓存

设置nginx反向代理 分别代理 server 和 front （都不设置https）