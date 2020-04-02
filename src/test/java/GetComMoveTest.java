package test.java;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.testthree.java.GameDriver;
import com.testthree.java.GetCompMove;

/**
 * @author Administrator
 *
 */
@RunWith(Parameterized.class)
public class GetComMoveTest {	
	private int numMoves;
	private int oppCell;
	private int[][] map;
	private int[] expectedResultArray;
	
	/**
	 * 
	 * @param numMoves
	 * @param oppCell
	 * @param map
	 * @param expectedResultArray
	 */
	public GetComMoveTest(int numMoves,int oppCell,int[][] map,int[] expectedResultArray) {
		// TODO Auto-generated constructor stub
		this.numMoves=numMoves;
		this.oppCell=oppCell;
		GameDriver.mat=map;
		this.expectedResultArray=expectedResultArray;
	}
	
	 @Parameterized.Parameters(name = "{index}:getComMoveTest[{0},{1}]={3}")
	    public static Collection testDataSet(){
	        return Arrays.asList(
	                new Object[][]{
	                        //第二步玩家下在边上的情况
	                        {3, 2, new int[][]{{1, 2, 0}, {0, 0, 0}, {0, 0, 0}}, new int[]{1,1}},
	                        {3, 4, new int[][]{{1, 0, 0}, {2, 0, 0}, {0, 0, 0}}, new int[]{1,1}},
	                        {3, 6, new int[][]{{1, 0, 0}, {0, 0, 2}, {0, 0, 0}}, new int[]{1,1}},
	                        {3, 8, new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 2, 0}}, new int[]{1,1}},
	                        //第二步玩家下在四角，且为3号位的情况
	                        {3, 3, new int[][]{{1, 0, 2}, {0, 0, 0}, {0, 0, 0}}, new int[]{2,2}},
	                        //第二步玩家下在四角，但不是3号位的情况
	                        {3, 7, new int[][]{{1, 0, 0}, {0, 0, 0}, {2, 0, 0}}, new int[]{2,2}},
	                        {3, 9, new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 2}}, new int[]{0,2}},
	                        //第二步玩家下在中央的情况
	                        {3, 5, new int[][]{{1, 0, 0}, {0, 2, 0}, {0, 0, 0}}, new int[]{2,2}},
	                        //电脑有机会获胜的情况
	                        {5, 3, new int[][]{{1, 2, 2}, {0, 1, 0}, {0, 0, 0}}, new int[]{2,2}},
	                        //玩家有机会获胜的情况
	                        {5, 4, new int[][]{{1, 0, 0}, {2, 2, 0}, {0, 0, 1}}, new int[]{1,2}}    
	                }
	        );
	    }
	@Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
	@Test
	public void getComMoveTest() {
		assertArrayEquals(this.expectedResultArray, GetCompMove.getCompMove(numMoves,oppCell));
	}
}	
