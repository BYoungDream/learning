<template>
  <div id="app">
    <SLT :SLTData="SLTData"
    v-on:listenLeftOneChange="leftOneChange"
    v-on:listenSearchClick="searchClick"
    v-on:listenTabsClick="tabsClick"
    v-on:listenRightImgClick="rightImgClick"
    v-on:listenTableRowClick="tableRowClick"
    v-on:listenIconClick="iconClick"/>
  </div>
</template>

<script>
import RightData from './SLTConfig.js';
import SLT from './components/Slt';


export default {
  name: 'App',
  data(){
    return{
      SLTData:{
        //左侧第一个下拉列表数据
        LeftoptionsOne: [{
                value: '1',
                label: '北京'
                }, {
                value: '2',
                label: '上海'
                }, {
                value: '3',
                label: '河北'
            }],
   
        //右侧数据
        RightData:RightData,
        //左侧第二个下拉列表数据
        LeftoptionsTwo: [],
        //右侧表格的数据
        rightTableData:[]
      }
      

    }
  },
  components: {
    SLT:SLT
  },
  methods: {
    leftOneChange(LeftOnevalue){
      //第一个下拉列表change事件,LeftOnevalue是选中的值
       if(LeftOnevalue === "1"){
            this.SLTData.LeftoptionsTwo=[
                {value:'1',label:'朝阳'},
                {value:'2',label:'海淀'},
                {value:'3',label:'石景山'},
                {value:'4',label:'丰台'}
            ];
        }else if(LeftOnevalue === "2"){
            this.SLTData.LeftoptionsTwo=[
                {value:'1',label:'黄埔路'},
                {value:'2',label:'长春路'},
                {value:'3',label:'天津路'}
            ];
        }else if(LeftOnevalue ==="3"){
            this.SLTData.LeftoptionsTwo=[
                {value:'1',label:'石家庄'},
                {value:'2',label:'邯郸'},
                {value:'3',label:'唐山'}
            ];
        }
      
    },
    iconClick(item){
        //监听右侧图片上的Icon点击事件
        //paramItem.LeftOnevalue(左侧第一个默认下拉列表的值)
        //paramItem.LeftTwovalue(左侧第二个默认下拉列表的值)
        //paramItem.rightActiveIndex(右侧选中的tabs的index索引) 根据此索引可以查询信息
        //paramItem.rightCardImgClickIndex(右侧选中的img的index索引) 根据此索引可以查询信息
    },
    searchClick(paramItem){
        //左侧点击查询按钮事件
        //paramItem.LeftOnevalue(左侧第一个默认下拉列表的值)
        //paramItem.LeftTwovalue(左侧第二个默认下拉列表的值)
        //paramItem.rightActiveIndex(右侧选中的tabs的index索引) 根据此索引可以查询信息
        //paramItem.rightCardImgClickIndex(右侧选中的img的index索引) 根据此索引可以查询信息
        this.SLTData.rightTableData=[
            {
                name:"张三",
                age:"21",
                sex:"男",
                native:"北京"
            },{
                name:"李四",
                age:"18",
                sex:"男",
                native:"上海"
            },{
                name:"赵武",
                age:"23",
                sex:"女",
                native:"河南"
            }
        ]
        
    },
    tabsClick(paramItem){
        //右侧tabs点击事件
        //paramItem.LeftOnevalue(左侧第一个默认下拉列表的值)
        //paramItem.LeftTwovalue(左侧第二个默认下拉列表的值)
        //paramItem.rightActiveIndex(右侧选中的tabs的index索引) 根据此索引可以查询信息
        //paramItem.rightCardImgClickIndex(右侧选中的img的index索引) 根据此索引可以查询信息

    },
    rightImgClick(paramItem){
        //右侧Img点击事件
        //paramItem.LeftOnevalue(左侧第一个默认下拉列表的值)
        //paramItem.LeftTwovalue(左侧第二个默认下拉列表的值)
        //paramItem.rightActiveIndex(右侧选中的tabs的index索引) 根据此索引可以查询信息
        //paramItem.rightCardImgClickIndex(右侧选中的img的index索引) 根据此索引可以查询信息

    },
    tableRowClick(row){
        //右侧table行的点击事件
        //row是返回当前点击的行的数据
    }
  },
  mounted(){
      
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
