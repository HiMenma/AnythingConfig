<template>
  <form>
    <label>
      用户名：
      <input type="text" v-model="username" />
    </label>
    <label>
      密码：
      <input type="password" v-model="password" />
    </label>
    <!--<button @click.prevent="submit">登录</button>-->
    <el-button type="primary" round size="large" @click.prevent="submit">登录</el-button>

  </form>
</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name : "loginPage",
  data() {
    return {
      username: '',
      password: '',
      apiPrefix:'http://rinetd.himenma.top',
      // apiPrefix:'http://127.0.0.1:2333',
    };
  },
  methods: {
    submit() {
      if (!this.username || !this.password) {
        // 用户没有输入账号或密码
        ElMessage({
          message: "请输入账号和密码",
          type : 'warning',
        })
        return;
      }
      // 在这里处理登录逻辑
      this.getRequest(this.apiPrefix + '/login/' + this.username + '/' + this.password).then(rsp => {
        console.log("rsp:" + rsp.data.toString())
        if (rsp.data.code === 200) {
            ElMessage({
              message: rsp.data.message,
              type : 'success',
            })
            this.$router.push('./Index')
          } else {
            ElMessage({
              message: rsp.data.message,
              type : 'warning',
            })
          }
      })
    },
  },
};
</script>

