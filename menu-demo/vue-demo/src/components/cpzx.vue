<template>
    <div>
        <h1>动态路由匹配</h1>
        <div class="ly-param">
            <span class="ly-option">参数值:{{this.$route.params.username}}</span>
            <el-input v-model="inpvalue"></el-input>
            <p :class="item.id==inpvalue?'paramOption':''" v-for="item in user" :key="item.id">
                Id:{{item.id}}------Name:{{item.name}}------Age:{{item.age}}------<span style="cursor:pointer;" @click="getOption(item.id)">选中</span>
            </p>
        </div>
        
        <hr>
        <h1>嵌套路由</h1>
        <el-row>
            <el-button type="primary" @click="goone()">组件一</el-button>
            <el-button type="info" @click="gotwo()">组件二</el-button>
        </el-row>
        <router-view></router-view>
    
        <hr>
    </div>
        
</template>
<script>
export default {
    data() {
        return {
            inpvalue:'1',
            user:[
                {id:1,name:'张三',age:18},
                {id:2,name:'李四',age:18},
                {id:3,name:'王五',age:18},
                {id:4,name:'赵六',age:18},
                {id:5,name:'周一',age:18},
                {id:6,name:'武二',age:18}
            ]
        }
    },
    methods:{
        getOption(id){
            this.inpvalue=id;
        },
        goone(){
            this.$router.push({path: '/cpzx/张三/qtly'});
        },
        gotwo(){
            this.$router.push({path: '/cpzx/张三/qtlyt'});
        }
    },
    mounted() {
        console.log("");
    }
}
</script>

<style scoped>
    .ly-param{
        margin: auto;
        width:500px;
    }
    .ly-option{
        font-weight: bold;
        font-size: 18px;
    }
    .paramOption{
        color:chocolate;
        font-size: 16px;
    }
</style>