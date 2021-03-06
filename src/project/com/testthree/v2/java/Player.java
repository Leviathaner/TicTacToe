package com.testthree.v2.java;

import com.testthree.v2.java.ChessBoard;

import java.util.Scanner;

/**
 * @author 123
 */
public class Player {

    private int chessMan;
    /**棋子类型，1为X，2为O*/
    private int row, col;
    //记录玩家的上一步

    public Player(){
        this.chessMan = 2;
        //玩家棋子默认为O
    }

    public Player(int chessMan) { this.chessMan = chessMan; }

    /**对应GetMove.getMove()*/
    public boolean move(ChessBoard cb){

        while (true) {
            String input = this.getInput();
            String[] contentList = input.split(",");
            if (contentList.length >= 1 && "exit".equals(contentList[0]))
                //输入为exit
            {
                System.out.println("Bye now");
                return true;
            }
            else if (this.checkInput(input) == 0)
                //输入位数不对
            {
                System.out.println("Use row,col.Re-enter");
            }
            else {

                if (this.checkInput(input) == 1) {
                    System.out.println("Out of range,Re-enter");
                }
                else if (cb.getMat()[row][col] != 0) {
                    //已有棋子
                    System.out.println("Occupied,re-enter");
                }
                else {
                    cb.changeMat(row, col, chessMan);
                    cb.printMat();
                    break;
                }

            }

        }
        return false;
    }

    /**对应GetInput.getInput()*/
    public String getInput(){
        System.out.print("\nEnter move or  \"exit\" :");
        Scanner input = new Scanner(System.in);
        String content = input.next();
        return content;
    }

    /**对应CheckInput.checkInput()*/
    public int checkInput(String input){
        String[] contentList = input.split(",");
        if(contentList.length!=2){
            return 0;
            //位数不对
        }
        else {
            row = Integer.parseInt(contentList[0])-1;
            //字符串到整形转换
            col = Integer.parseInt(contentList[1])-1;
            if (row<0||row>=3||col<0||col>=3){
                return 1;
                //越界
            }
        }
        return 3;
        //没有以上情况
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }
}
