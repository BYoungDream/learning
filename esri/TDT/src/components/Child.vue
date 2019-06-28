<template>
    <div>
        这里是子组件的页面
        <input type="button" value="点击" @click="handleChange">
    </div>
</template>
<script>
export default {
    name:"child",
    methods:{
        handleChange(){
            this.$emit("listenhandleChange","123");
        }
    }
}
</script>

