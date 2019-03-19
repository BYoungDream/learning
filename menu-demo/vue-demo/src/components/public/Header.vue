<template>
    <div class="head">
        <el-menu
          :default-active="activeIndex2"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-menu-item index="1">
            <router-link to="/">首页</router-link>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">路由(vue router)</template>
            <el-menu-item index="2-1">
              <router-link to="/cpzx/张三">产品中心</router-link>
            </el-menu-item>
            <el-menu-item index="2-2">
              <router-link to="/jjfz">解决方案</router-link>
            </el-menu-item>
            <el-menu-item index="2-3">
              <router-link to="/xxzx">新闻中心</router-link>
            </el-menu-item>
          </el-submenu>
          <el-menu-item index="3">
            <router-link to="/gywm">关于我们</router-link>
          </el-menu-item>
        </el-menu>
    </div>
</template>
<script>
  export default {
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>
<style>
.head {
    margin-bottom: 20px;
}
</style>
