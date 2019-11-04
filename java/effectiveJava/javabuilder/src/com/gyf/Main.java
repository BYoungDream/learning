package com.gyf;

public class Main {

    public static void main(String[] args) {
	    //构造方法参数过多时，请使用builder模式
        NutritionFacts cocaCola =
                new NutritionFacts.Builder(240,8)
                .calories(2).sodium(1).carbohydrate(34).build();

    }
}
