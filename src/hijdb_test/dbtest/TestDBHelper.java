package hijdb_test.dbtest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import HiJDB.DBHelper;
import HiJDB.DBOperate;
import HiJDB.ICreator;
import HiJDB.Creator.MySQLCreator;
import HiJUtil.HiTypeHelper;
import HiJUtil.Generic.IEventRet;
import HiJUtil.Generic.IEventRet8Param;
import HiJUtil.Generic.IResult;
import HiJUtil.Generic.HiResult;

public class TestDBHelper {
	String url = "jdbc:mysql://localhost:3306/information_schema";
	public TestDBHelper() {
		DBOperate.AddDBCreator(DBOperate.MySQL, new IEventRet<ICreator>(){

			@Override
			public ICreator OnEvent() {
				// TODO Auto-generated method stub
				return new MySQLCreator();
			}
			
		});
	}
	@Test
	public void Test_DBHelper_ExecuteScalar_String() {
		DBOperate db = new DBOperate(url, "root", "root", DBOperate.MySQL);
        String obj;
		try {
			obj = db.ExecuteScalar(String.class, "select table_name from tables limit 1");
	        Assert.assertEquals(obj, "CHARACTER_SETS");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void Test_DBHelper_ExecuteScalar_int() {
		DBOperate db = new DBOperate(url, "root", "root", DBOperate.MySQL);
        int obj;
		try {
			obj = db.ExecuteScalar(int.class, "select Count(1) from tables limit 1");
	        Assert.assertTrue(obj > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void Test_DBHelper_ExecuteScalar_Integer() {
		DBOperate db = new DBOperate(url, "root", "root", DBOperate.MySQL);
        Integer obj;
		try {
			obj = db.ExecuteScalar(int.class, "select Count(1) from tables limit 1");
	        Assert.assertTrue(obj > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void Test_DBHelper_ExecuteScalar_String2() {
		DBOperate db = new DBOperate(url, "root", "root", DBOperate.MySQL);
        int obj;
		try {
			obj = db.ExecuteScalar(int.class, "select table_name from tables where 1 != 1");
	        Assert.assertEquals(obj, -1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void Test_DBHelper_ExecuteScalar_int_null() {
		DBOperate db = new DBOperate(url, "root", "root", DBOperate.MySQL);
        String obj;
		try {
			obj = db.ExecuteScalar(String.class, "select Count(1) from tables limit 1");
	        Assert.assertTrue(Integer.parseInt(obj) > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void Test_DBHelper_ExecuteQuery() {

		DBOperate db = new DBOperate(url, "root", "root", DBOperate.MySQL);
		try {
			final HiResult<List<CharactersetsPO>> result = new HiResult<List<CharactersetsPO>>();
			boolean ret = db.ExecuteQuery("SELECT  CHARACTER_SET_NAME,  DEFAULT_COLLATE_NAME,  DESCRIPTION,  MAXLEN FROM Character_sets ", new IEventRet8Param<Boolean, ResultSet>(){

				@Override
				public Boolean OnEvent(ResultSet v) {
					List<CharactersetsPO> lst = null;
					try {
						lst = DBHelper.GetResultsList(CharactersetsPO.class, v);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
					result.Set(lst);
					return lst != null;
				}
				
			});
			Assert.assertTrue(ret);
			Assert.assertTrue(result.Get() != null);
			for (int i = 0; i < result.Get().size(); i++) {
				String str = HiTypeHelper.ToString(CharactersetsPO.class, result.Get().get(i));
				System.out.print(str);
				System.out.print("\r\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
