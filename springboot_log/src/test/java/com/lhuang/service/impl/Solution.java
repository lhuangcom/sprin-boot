package com.lhuang.service.impl;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lhunag
 * date 2019/8/8
 */
public class Solution {




    public static List<List<Integer>> threeSum(int[] nums) {

        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);


        List<List<Integer>> listList = new ArrayList<>();

        // 对全是0的情况特殊清理
        if (nums.length > 2 && nums[0] == 0 && nums[nums.length - 1] == 0) {
            listList.add(Arrays.asList(0, 0, 0));
            return listList;
        }

        //将int数组转成Integer集合
        //List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        List<Integer> integerList = new ArrayList<>();
        int length = nums.length;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < length;i++){
            for (int j = i+1;j<length;j++){
                int remainValue = 0 - nums[i]-nums[j];

                //避免复用前面的使用过的数据,这种方法会超时
               // List<Integer> remainList = new ArrayList<>(list.subList(j+1,length));

                if(hashMap.containsKey(remainValue)){
                    listList.add(Arrays.asList(nums[i],nums[j],remainValue));
                }

            }
            //反向取值法，从后往前取
            hashMap.put(nums[i],i);

            //如果直接integerList = list.subList(0,i+1)，integerList的修改会影响到list的变化

        }
        //去重
        HashSet<List<Integer>> hashSet = new HashSet<>(listList);

        listList.clear();
        listList = new ArrayList<>(hashSet);

        return listList;
    }

    public static void main(String[] args) {

       // int nums[] = {-1, 0, 1, 2, -1, -4};
        int nums[] = {-1,  2, 1, -2};
        //int nums[] = {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};

        List<List<Integer>> list = threeSum(nums);
        System.out.println("输出"+list.toString());




    }
}
