<template>
    <div>
        <h1>命名视图</h1>
        <el-button type="primary" @click="gethide()">隐藏命名视图</el-button>
        <el-button type="info" @click="getshow()">显示命名视图</el-button>
        <router-view />
        <router-view name="jve"/>
    </div>
</template>
<script>
export default {
    methods:{
        getshow(){
            this.$router.push({path: '/jjfz/jview1'});
        },
        gethide(){
            this.$router.push({path: '/jjfz'});
        }
    }
}
</script>

