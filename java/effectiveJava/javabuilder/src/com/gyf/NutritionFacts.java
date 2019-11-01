package com.gyf;

/**
 * created by guoyanfei on 2019/11/1
 * 营养成分实体类
 * 构造方法的参数过多时，请使用builder模式
 */
public class NutritionFacts {
    //供给大小
    private final int servingSize;
    //等级
    private final int servings;
    //卡路里
    private final int calories;
    //脂肪
    private final int fat;
    //钠
    private final int sodium;
    //碳水化合物
    private final int carbohydrate;

    public static class Builder{
        //必须项
        private final int servingSize;
        private final int servings;

        //可选项
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private  int carbohydrate = 0;

        public Builder(int servingSize,int servings){
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val){
            calories = val;
            return this;
        }
        public Builder fat(int val){
            fat = val;
            return this;
        }
        public Builder sodium(int val){
            sodium = val;
            return this;
        }
        public Builder carbohydrate(int val){
            carbohydrate = val;
            return this;
        }
        public NutritionFacts build(){
            return new NutritionFacts(this);
        }

    }

    private NutritionFacts(Builder builder){
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

}
