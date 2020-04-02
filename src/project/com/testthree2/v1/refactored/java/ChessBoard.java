package com.testthree2.v1.refactored.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoard {

    private int[][] mat;//棋盘矩阵，空为0，X为1，O为2
    private int row,col;//记录上一步棋
    private static final int[][] WIN_LIST ={{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};   //这个是常量，忽略代码规范提示即可
    private int three=3;

    public ChessBoard(){
        this.mat = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        this.row = 0;
        this.col = 0;
    }

    /**对应PrintMat.printMat()*/
    public void printMat(){
        System.out.println("  1 2 3");
        for(int i = 0; i < three; i++){
            for(int j = 0; j < three; j++){
                if(this.mat[i][j]==0){
                    if(j==0){
                        if(i==0){
                            System.out.print("1 . ");
                        }
                        else if(i==1){
                            System.out.print("\n2 . ");
                        }
                        else if(i==2){
                            System.out.print("\n3 . ");
                        }
                    }
                    if(j!=0){
                        System.out.print(". ");
                    }
                }
                if(this.mat[i][j]==1){
                    if(j==0){
                        if(i==0){
                            System.out.print("1 x ");
                        }
                        else if(i==1){
                            System.out.print("\n2 x ");
                        }
                        else if(i==2){
                            System.out.print("\n3 x ");
                        }
                    }
                    if(j!=0){
                        System.out.print("x ");
                    }
                }
                if(this.mat[i][j]==2){
                    if(j==0){
                        if(i==0){
                            System.out.print("1 O ");
                        }
                        else if(i==1){
                            System.out.print("\n2 O ");
                        }
                        else if(i==2){
                            System.out.print("\n3 O ");
                        }
                    }
                    if(j!=0){
                        System.out.print("O ");
                    }
                }
            }
        }
    }

    /**
     *  对应TestWayAndWin.testWin()
     */
    public boolean testAnyOneWin(){
        int cellN = row * 3 + col + 1; //格子号
        /*  1 2 3
            4 5 6
            7 8 9   */

        for (int[] tttList : getMyWinlist(cellN)) {
            int numX = testWay(tttList)[0];
            int numO = testWay(tttList)[1];
            if(numX == 3 || numO == 3) {
                return true;
            }
        }
        return false;
    }

    //从TestWayAndWin.testWin()中提取出来
    public List<int[]> getMyWinlist(int cellN){
        List <int[]> myWinlist = new ArrayList<>();
        for(int[] list : WIN_LIST){
            if(Arrays
                    .stream(list)
                    .boxed()
                    .collect(Collectors.toList())
                    .contains(cellN)) {
                myWinlist.add(list);
            }
        }
        return myWinlist;
    }

    //对应TestToWin.testToWin()
    public boolean testToWin(int r, int c){
        for (int[] tttList : getMyWinlist(r * 3 + c + 1)) {
            if(testWay(tttList)[1] == 2) {
                return true;
            }
        }
        return false;
    }

    //对应TestToWin.testToBlock()
    public boolean testToBlock(int r, int c){
        for (int[] tttList : getMyWinlist(r * 3 + c + 1)) {
            if(testWay(tttList)[2] == 2) {
                return true;
            }
        }
        return false;
    }

    //对TestToWin.testToWin()和TestToBlock.testToBlock()进行重构
    public boolean whoToWin(int r, int c, int chessMan){

        for (int[] tttList : getMyWinlist(r * 3 + c + 1)) {
            if(testWay(tttList)[chessMan] == 2) {
                return true;
            }
        }
        return false;
    }

    //对应TestWayAndWin.testWay()
    public int[] testWay(int[] tttList){
        List<Integer> lettersList = new ArrayList<Integer>();
        for(int cellN : tttList){
            int r = (cellN - 1) / 3;
            int c = (cellN - 1) % 3;
            lettersList.add(this.mat[r][c]);
        }
        int numX = Collections.frequency(lettersList,1);
        int numO = Collections.frequency(lettersList,2);
        int numBlank = Collections.frequency(lettersList,0);
        int[] numOfChessman = {numBlank,numX,numO};
        return numOfChessman;
    }

    //落子
    public void changeMat(int row, int col, int chessMan) {
        this.row = row;
        this.col = col;
        this.mat[row][col] = chessMan;
    }

    public int[][] getMat(){
        return this.mat;
    }

    public void setMat(int[][] mat){
        this.mat = mat;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col){
        this.col = col;
    }

}