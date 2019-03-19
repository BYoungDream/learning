<template>
  <div class="hello">
    <h1>Vue指令介绍</h1>
    Vue指令之v-cloak、v-text、v-html  (特点：v-cloak能够解决插值表达式闪烁问题; v-text没有闪烁问题,会覆盖元素中原本的内容; v-html会解析html元素)
    <div :style="styleObj">
        <p v-cloak>{{msg}}----v-cloak</p>
        <h4 v-text="msg+'----v-text'"></h4>
        <div v-html="msg2"></div>
    </div>
    Vue指令之v-bind (特点：v-bing:是Vue中提供的用于绑定属性的指令,可以写合法的JS表达式,简写为:)
    <div :style="styleObj">
        <input type="button" value="按钮" v-bind:title="mytitle">
    </div>
    Vue指令之v-on  (特点：v-on是用于事件绑定事件,简写使用@)
    <div :style="styleObj">
     <input type="button" value="点击按钮" v-on:click="show">
    </div>
    Vue指令之v-model (特点：双向数据绑定,实现表单元素和Model中数据的双向数据绑定，只能用在表单元素中)
    <div :style="styleObj">
        <h4>{{ msg }}</h4>
        <input type="text" v-model="msg" style="width:500px;">
    </div>
    三种设置内联样式的方式
    <div :style="styleObj">
      <h1 :style="styleObj1">{{ msg }}</h1>
      <h2 :style="{color:'red','font-weight':200}">{{ msg }}</h2>
      <h3 :style="[styleObj1,styleObj2]">{{ msg }}</h3>
    </div>

    Vue中v-for和key属性
    <div :style="styleObj">
        <h2 :style="styleObj1">遍历数组</h2>
        <p v-for="(item, i) in list" :key="item"> 索引值：{{i}} --- 值：{{item}}</p>
        <h2 :style="styleObj1">遍历对象</h2>
        <label>Id:
            <input type="text" v-model="id">
        </label>
        <label>Name:
            <input type="text" v-model="name">
        </label>
        <input type="button" value="添加" @click="add">
        <p v-for="user in list1" :key="user.id"> 
          <input type="checkbox"> Id:{{ user.id}} --- name:{{ user.name }}
        </p>
        <h2 :style="styleObj1">遍历对象键值对</h2>
        <p v-for="(val, key, i) in user" :key="key">值是：{{ val }} --- 键是：{{ key }} -- 索引：{{i}}</p>
        <h2 :style="styleObj1">迭代数字</h2>
        <p v-for="count in 10" :key="count"> 这是第 {{count}} 次循环</p>
    </div>

    Vue指令之v-if和v-show  (特点：v-if 的特点：每次都会重新删除或创建元素)
    <div :style="styleObj">
        <input type="button" value="toggle" @click="flag=!flag">
        <h3 v-if="flag">这是用v-if控制的元素</h3>
        <h3 v-show="flag">这是用v-shwo控制的元素</h3>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      msg2: '<h3>我是h3</h3>',
      mytitle: '这是自己定义的title',
      styleObj:{border:'solid 1px #000','margin-bottom':'20px', 'padding':'10px'},
      styleObj1:{color:'red','font-weight':200},
      styleObj2:{'font-size':'30px'},
      list: [1,2,3,4,5,6],
      list1: [
        { id: 1, name: '张一'},
        { id: 2, name: '张二'},
        { id: 3, name: '张三'},
        { id: 4, name: '张四'},
        { id: 5, name: '张五'},
      ],
      user: {
        id: 1,
        name: '小明',
        sex: '男'
      },
      id: '',
      name: '',
      flag: true
    }
  },
  methods: {//定义当前vue实例所有可用的方法
    add() {
      this.list1.push({id: this.id, name: this.name})
    },
    show() {
       alert("Hello!")
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
[v-cloak] {
  display: none;
}
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
