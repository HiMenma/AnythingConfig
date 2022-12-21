<template>
  <el-form :inline="true">
    <el-form-item>
      <el-select  v-model="path" @change="readConfig" class="m-2" placeholder="Select" size="mini">
        <el-option
            v-for="item in paths"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="执行命令">
      <el-select  v-model="command"  class="m-2" placeholder="Select" size="mini">
        <el-option
            v-for="item in commands"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        />
      </el-select>
    </el-form-item>
    <el-form-item >
      <el-button type="primary" @click="exeShell">执行</el-button>
    </el-form-item>
  </el-form>
<textarea rows="40"  style="border: 1px solid black;width: 700px;margin: 0 auto;white-space: pre-line;" v-model="data"></textarea>
  <br>
  <el-button type="primary" @click="updateConfig" style="margin-left: 620px">提交修改</el-button>
</template>
<script>
import { ElMessage } from 'element-plus'
export default {
  name: "indexPage",
  data()  {
    return {
      data: '',
      path: '',
      command: '',
      // apiPrefix:'http://127.0.0.1:2333',
      apiPrefix:'http://rinetd.himenma.top',
      paths: [
        {
          key:'0',
          label: '全部',
          value: ''
        },
        {
          key:'1',
          label: 'hosts',
          value: 'h'
        },
        {
          key:'2',
          label: 'rinetd',
          value: 'r'
        }
      ] ,
      commands: [
        {
          key:'0',
          label: '命令',
          value: ''
        },
        {
          key:'1',
          label: 'rinet-start',
          value: 'start'
        },
        {
          key:'2',
          label: 'rinet-close',
          value: 'pkill'
        },
        {
          key:'3',
          label: 'pwd',
          value: 'pwd'
        }
      ]
    }
  },
  // activated() {
  //   this.readConfig()
  // },
  methods: {
    readConfig() {
      const path = this.path
      this.getRequest(this.apiPrefix +'/rinetd/readConfig?path=' + path).then(resp =>{
        this.data = resp.data.data
        ElMessage({
          message: resp.data.message,
          type: '',
        })
      })
    },
    updateConfig() {
      const params = {'data':this.data,'path': this.path}

      console.log(params)
      this.postRequest(this.apiPrefix + '/rinetd/updateConfig',params).then(resp =>{
       if (resp.data.code === 200) {
         ElMessage({
           message: resp.data.data,
           type: 'success',
         })
         this.readConfig()
       }
      })
    } ,
    exeShell() {
      const command = this.command
      this.getRequest(this.apiPrefix + '/rinetd/exeShell?command=' + command).then(resp =>{
       if (resp.data.code === 200) {
         ElMessage({
           message: resp.data.data,
           type: 'success',
         })
       }
      })
    }
  }
}
</script>

<style>
</style>
