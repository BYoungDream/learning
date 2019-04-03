<template>
    <div>
        <el-row class="left-row">
            <el-select size="small" v-model="paramItem.LeftOnevalue" placeholder="请选择" @change="leftOneChange()">
                <el-option
                v-for="item in SLTData.LeftoptionsOne"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
            </el-select>
            <el-select size="small" v-model="paramItem.LeftTwovalue" placeholder="请选择" @change="leftTwoChange()">
                <el-option
                v-for="item in SLTData.LeftoptionsTwo"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
            </el-select>
            <el-button type="primary" icon="el-icon-search" size="small" @click="leftButton"></el-button>
        </el-row>

        <el-row class="right-row">
            <el-collapse v-model="collname" accordion>
                <el-collapse-item name="1">
                    <template slot="title">
                        <div class="card-title">
                            <i class="el-icon-location i-location"></i>
                            <div>{{this.rightTile}}</div>
                        </div>
                    </template>
                    <el-card class="box-card el-box-card">
                        <div class="card-context" style="margin-top:-10px;">
                            <el-tabs v-model="rightActiveName" type="border-card" @tab-click="righthandleClick">
                                <el-tab-pane v-for="item in SLTData.RightData" :key="item.name" :label="item.title" :name="item.name">
                                    <div style="overflow: hidden;">
                                        <span class="card-span-icon-left" v-if="item.context.length >4" @click="cardspanleftevent(item.context.length)">
                                            <i class="card-img-icon-left"></i>
                                        </span>
                                        <span class="card-span-icon-right" v-if="item.context.length >4" @click="cardspanrightevent(item.context.length)">
                                            <i class="card-img-icon-right"></i>
                                        </span>
                                        <div class="card-tab-pane" :style="cardTabPane">
                                            <div v-for="(itemimg,index) in item.context" :key="index" 
                                            @mouseover="rightCardImgHoverIndex = index" 
                                            @mouseleave="rightCardImgHoverIndex = -1" 
                                            @click="rightCardImgClick(index,itemimg)"
                                            :class="{'card-tab-pane-list':true,'card-tab-pane-list-over':index == rightCardImgHoverIndex,'card-tab-pane-list-selected':index == paramItem.rightCardImgClickIndex}">
                                                <img :src="itemimg.imgsrc" class="card-tab-pane-list-img" >
                                                <img :src="itemimg.iconShow === true ? require('../assets/sltimg/icon-selected.png') : require('../assets/sltimg/icon-unselected.png')" class="card-tab-icon-img" v-if="itemimg.iconEnable" @click.stop="rightCardIconClick(itemimg)" >
                                                <span>{{itemimg.imgname}}</span>
                                            </div>
                                        </div>
                                    </div>
                                    
                                </el-tab-pane>
                                <el-table
                                        :data="SLTData.rightTableData"
                                        @row-click="tableRowClick"
                                        height="332"
                                        style="width: 100%; margin-top:-15px;">
                                        <el-table-column
                                            v-for="(itemtablefield,fieldindex) in rightTableField"
                                            :key="fieldindex"
                                            :prop="itemtablefield.prop"
                                            :label="itemtablefield.label"
                                            :width="itemtablefield.width"
                                            :header-align="itemtablefield.headalign"
                                            :align="itemtablefield.align"
                                            style="background-color:#E4E7ED;">
                                        </el-table-column>
                                    </el-table>
                            </el-tabs>
                        </div>
                    </el-card>
                </el-collapse-item>
            </el-collapse>
        </el-row>
        
    </div>
</template>
<script>
import { 
  Select,
  Option,
  OptionGroup,
  Button,
  ButtonGroup,
  Row,
  Card,
  RadioGroup,
  RadioButton,
  Tabs,
  TabPane,
  Table,
  TableColumn,
  Collapse,
  CollapseItem
} from 'element-ui';
export default {
    name: "Slt",
    props:{
        SLTData:Object
    },
    data(){
        return {
            paramItem:{
                //左侧第一个下拉列表值
                LeftOnevalue:"",
                //左侧第二个下拉列表值
                LeftTwovalue:"",
                //右侧当前tabs的index索引
                rightActiveIndex:"0",
                //右侧当前img的index索引
                rightCardImgClickIndex:"0",
                //选中的Icon列表
                iconList:[]
            },
            //右侧tabs的name值
            rightActiveName:"",
            rightTableField:[],
            LeftOneLabel: '',
            LeftTwoLabel: '',
            rightTile:'',
            rightCardImgHoverIndex:-1,
            cardTabPane:'transform:translateX(0px)',
            panevalue:0,
            iconImgCount:4,
            iconImgWidth:73,
            collname:"1"
        }
    },
    methods:{
        leftOneChange(){//左侧数据绑定
            let obj={};
            obj =this.SLTData.LeftoptionsOne.find((item)=>{
                return item.value === this.paramItem.LeftOnevalue;
            });
            this.LeftOneLabel = obj.label;
            this.paramItem.LeftTwovalue = '';
           
            this.$emit("listenLeftOneChange",this.paramItem.LeftOnevalue);
            this.paramItem.LeftTwovalue = this.SLTData.LeftoptionsTwo[0].value;
            this.leftTwoChange();
        },
        leftTwoChange(){//左侧二级菜单改变事件
            let obj={};
            obj =this.SLTData.LeftoptionsTwo.find((item)=>{
                return item.value === this.paramItem.LeftTwovalue;
            });
            this.LeftTwoLabel = obj.label;
        },
        leftButton(){//左侧按钮点击事件
            this.rightTile=this.LeftOneLabel+"-"+this.LeftTwoLabel;
            this.rightCardImgClick(this.paramItem.rightCardImgClickIndex,this.SLTData.RightData[this.paramItem.rightActiveIndex].context[this.paramItem.rightCardImgClickIndex]);
            this.$emit("listenSearchClick",this.paramItem);
        },
        righthandleClick(tab,event){//右侧tabs点击事件
            this.rightTableField =  [];
            this.SLTData.rightTableData = [];
            this.paramItem.rightActiveIndex = tab.index;
            this.paramItem.iconList=[];
            this.paramItem.rightCardImgClickIndex=0;
            for(var i=0;i<this.SLTData.RightData[this.paramItem.rightActiveIndex].context.length;i++){
                if(this.SLTData.RightData[this.paramItem.rightActiveIndex].context[i].iconShow === true && 
                  this.SLTData.RightData[this.paramItem.rightActiveIndex].context[i].iconEnable === true){
                    this.paramItem.iconList.push(this.SLTData.RightData[this.paramItem.rightActiveIndex].context[i].imgcode);
                }
            }
            this.rightCardImgClick(0,this.SLTData.RightData[this.paramItem.rightActiveIndex].context[this.paramItem.rightCardImgClickIndex]);
            this.$emit("listenTabsClick",this.paramItem);
        },
        rightCardImgClick(index,itemimg){//右侧图片点击事件
            this.paramItem.rightCardImgClickIndex = index;
            this.rightTableField =  itemimg.tableField;

            itemimg.iconShow = true;
            if(this.paramItem.iconList.indexOf(itemimg.imgcode) === -1 && 
              itemimg.iconEnable === true){
                this.paramItem.iconList.push(itemimg.imgcode);
            }
            
            this.$emit("listenRightImgClick",this.paramItem);
        },
        rightCardIconClick(itemimg){//右侧点击Icon事件
            itemimg.iconShow = !itemimg.iconShow;
            
            if(itemimg.iconShow === true && itemimg.iconEnable === true){
                if(this.paramItem.iconList.indexOf(itemimg.imgcode) === -1){
                    this.paramItem.iconList.push(itemimg.imgcode);
                }
            }else{
                this.paramItem.iconList.splice(this.arrayContains(this.paramItem.iconList,itemimg.imgcode),1);
            }
            
            this.$emit("listenIconClick",this.paramItem);
        },
        arrayContains(arrobj,val){//获取数组对应的值的索引
            var i = arrobj.length;
            while(i--){
                if(arrobj[i] == val){
                    return i;
                }
            }
            return false;
        },
        tableRowClick(row,event,column){//右侧表格数据点击事件
            this.$emit("listenTableRowClick",row);
        },
        cardspanleftevent(itemcount){            

            if(Math.floor(Math.abs(this.panevalue) / this.iconImgCount) > 0){
                this.panevalue = this.panevalue + this.iconImgCount;
            } else{
                this.panevalue=0;
            }
            this.cardTabPane='transform:translateX('+(this.panevalue * this.iconImgWidth)+'px)';
        },
        cardspanrightevent(itemcount){
            var xlength = itemcount - this.iconImgCount - Math.abs(this.panevalue);
            if(Math.floor(xlength / this.iconImgCount) > 0){
                this.panevalue=this.panevalue - this.iconImgCount;
            }else{
                this.panevalue=this.panevalue-xlength;
            }
            this.cardTabPane='transform:translateX('+(this.panevalue * this.iconImgWidth)+'px)';
        }
        
    },
    components: {
        ElSelect: Select,
        ElOption: Option,
        ElButton: Button,
        ElRow: Row,
        ElCard: Card,
        ElRadioGroup: RadioGroup,
        ElRadioButton: RadioButton,
        ElTabs: Tabs,
        ElTabPane: TabPane,
        ElTable: Table,
        ElTableColumn: TableColumn,
        ElCollapse: Collapse,
        ElCollapseItem: CollapseItem
    },
    mounted(){
        this.paramItem.LeftOnevalue=this.SLTData.LeftoptionsOne[0].value;
        this.leftOneChange();
        this.rightActiveName = this.SLTData.RightData[this.paramItem.rightActiveIndex].name;
        this.leftButton();
    }
}
</script>

<style>
    /* begin left */
    .left-row{
        top:28px; 
        left:54px;
        position:absolute;
    }
    .el-select {
        width:180px;
        float: left;
        margin-right: 6px;
    }
    .el-button{
        float: left;
    }

    /* end left */

    /* begin right */
    .right-row {
        position: absolute;
        top:90px;
        right: 85px;
        width:348px;
        border:solid 1px #FFF;
        border-radius: 4px;
    }

    .el-card__body{
        padding:10px;
    }
    .i-location {
        color:#66b1ff;
        font-size: 18px;
        margin-top:14px;
    }
    .card-title{
        font-size: 16px;
        display: flex;
        margin-left: 8px;
    }
    .card-context{
        margin-top:10px;
    }
    .card-tab-pane{
        display:flex;
        transition: transform .5s,-webkit-transform .5s;

    }
    .card-tab-pane-list{
        float: left;
        margin-right: 3px;
        cursor: pointer;
        margin-left: 4px;
        width:66px;
        height:98px;
        
    }
    .card-tab-pane-list span{
        font-size: 14px;
        font-family:微软雅黑;
        margin-left: 6px;
    }
    .card-tab-pane-list-over{
        background: url(../assets/sltimg/ComBg02.png);
    }
    .card-tab-pane-list-selected{
        background: url(../assets/sltimg/ComBg02.png);
        
    }
    .card-tab-pane-list-img{
        padding: 7px 7px 0px 7px;
    }
    .card-tab-icon-img{
        width:18px;
        height: 18px;
        position: absolute;
        margin-left: 48px;
        top:0px;
        z-index:10;
    }
    
    .el-tabs__item{
        padding:0 10px;
    }
    .card-span-icon-left{
        position:absolute;
        width:8px;
        height: 92px;
        left:6px;
        cursor: pointer;
    }
    .card-img-icon-left{
         background:url(../assets/sltimg/select-icon-left.png);
         width:8px;
         height: 91px;
         display: block;
    }
    .card-span-icon-right{
        position: absolute;
        width:8px;
        height: 92px;
        right: 6px;
    }
    .card-img-icon-right{
        background:url(../assets/sltimg/select-icon-right.png);
         width:8px;
         height: 91px;
         display: block;
         cursor: pointer;
    }

    .el-tabs--border-card{
        height: 500px;
        border:0px solid #DCDFE6 !important;
        -webkit-box-shadow: 0 0px 0px 0 rgba(0,0,0,0), 0 0 0px 0 rgba(0,0,0,0) !important;
            box-shadow: 0 0px 0px 0 rgba(0,0,0,0), 0 0 0px 0 rgba(0,0,0,0) !important;
    }
    .el-table thead{
        color: #303133 !important;
    }
    .el-box-card{
        border:0; 
        box-shadow: 0 0px 0px 0 rgba(0,0,0,.1); 
        -webkit-box-shadow: 0 0px 0px 0 rgba(0,0,0,.1);
    }
    .el-collapse-item__content{
        padding-bottom:0px;
    }
    .el-collapse{
        border-top: 0px solid #EBEEF5;
        border-bottom: 0px solid #EBEEF5;
    }
    .has-gutter tr th{
        background-color: #e3f2fb;
    }
    .hover-row{
        cursor: pointer;
    }
    .el-tabs__nav > .is-active{
        background-color: #66b1ff !important;
        color:#fff !important;
        border-radius: 4px;
    }
    .el-tabs--border-card>.el-tabs__content{
        padding:0px !important;
    }
    .el-tab-pane{
        margin: 15px !important;
    }
    .el-tabs--border-card>.el-tabs__header{
        border:1px solid #E4E7ED; 
    }
    .el-tabs__nav-next, .el-tabs__nav-prev{
        line-height: 34px !important;
    }
    .el-tabs__item{
        height: 34px;
        line-height: 34px;
    }
    .el-table td, .el-table th{
        padding:5px 0 !important;
    }
    .el-table th{
        font-weight: normal !important;
    }
    /* end right */

    /*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
    ::-webkit-scrollbar
    {
        width: 12px;
        height: 12px;
        background-color: #F5F5F5;
    }
    
    /*定义滚动条轨道 内阴影+圆角*/
    ::-webkit-scrollbar-track
    {
        -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.1);
        border-radius: 5px;
        background-color: #F5F5F5;
    }
    
    /*定义滑块 内阴影+圆角*/
    ::-webkit-scrollbar-thumb
    {
        border-radius: 5px;
        -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
        background-color: rgba(0,0,0,.2);
    }

</style>

