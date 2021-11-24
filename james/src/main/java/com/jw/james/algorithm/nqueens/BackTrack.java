package com.jw.james.algorithm.nqueens;

import com.google.common.collect.Lists;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.collections4.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * guoyy com.jw.algorithm.nqueens
 * <p>
 * 在n×n格的国际象棋上摆放n个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 *
 * @Description: com.jw.algorithm.nqueens.BackTrack
 * @Author: guoyiyong/james
 * @Date: 2020-07-11 13:01
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class BackTrack {
    private final List<int[][]> rets = Lists.newArrayList();
    /**
     * 浅拷贝
     */
    private final BeanCopier copier = BeanCopier.create(int[][].class, int[][].class, false);
    private int n;

    private final MapperFacade mapperFacade;

    private BackTrack(int n) {
        this.n = n;

        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(int[][].class, int[][].class).byDefault().register();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public static void main(String[] args) {
        LocalDateTime t1 = LocalDateTime.now();
        final int n = 13;
        BackTrack backTrack = new BackTrack(n);
        int[][] location = new int[n][n];

        backTrack.backTrack(location, 0);

        if (CollectionUtils.isNotEmpty(backTrack.rets)) {
            for (int k = 0, len = backTrack.rets.size(); k < len; k++) {
                int[][] ret = backTrack.rets.get(k);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(ret[i][j] + ",");
                    }
                    System.out.println();
                }
                System.out.println(String.format("%20d", k));
            }
        }
        LocalDateTime t2 = LocalDateTime.now();

        System.out.println(String.format("estimated time: %s(ms)", Duration.between(t1, t2).toMillis()));
    }

    public boolean backTrack(int[][] location, int row) {
        // 退出条件
        if (row == n) {
            int[][] ret = new int[n][n];
            for (int i = 0; i < n; i++) {
                ret[i] = location[i].clone();
            }
            rets.add(ret);
            return false;
        }
        // 按行遍历
        for (int col = 0; col < n; col++) {
            // 排除不合法选择
            boolean valid = isValid(location, row, col);
            if (!valid) {
                continue;
            }
            // 做选择
            location[row][col] = 1;
            // 进入下一行决策
            boolean stop = backTrack(location, row + 1);
            if (stop) {
                break;
            }
            // 撤销选择
            location[row][col] = 0;
        }
        return false;
    }

    /**
     * 任意两个皇后都不能处于同一行、同一列或同一斜线上
     */
    private boolean isValid(int[][] location, int row, int col) {
        // 检查列是否有皇后互相冲突
        for (int j = 0; j < n; j++) {
            if (location[j][col] == 1) {
                return false;
            }
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (location[i][j] == 1) {
                return false;
            }
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (location[i][j] == 1) {
                return false;
            }
        }
        // 检查右下方是否有皇后互相冲突
        for (int i = row + 1, j = col + 1; i < n && j < n; i++, j++) {
            if (location[i][j] == 1) {
                return false;
            }
        }
        // 检查左下方是否有皇后互相冲突
        for (int i = row + 1, j = col - 1; i < n && j >= 0; i++, j--) {
            if (location[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

}
