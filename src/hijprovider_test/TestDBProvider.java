package hijprovider_test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import HiJDB.DBOperate;
import HiJDB.ICreator;
import HiJDB.Creator.MySQLCreator;
import HiJProvider.DataProvider;
import HiJProvider.ProviderConfig;
import HiJUtil.HiTypeHelper;
import HiJUtil.Generic.IEventRet;
import HiJUtil.Generic.IEventRet8Param;
import hijdb_test.dbtest.CharactersetsPO;

public class TestDBProvider {
	String url = "jdbc:mysql://localhost:3306/information_schema";
	public TestDBProvider() {
		ProviderConfig.RegistDB(DBOperate.MySQL, new IEventRet<ICreator>(){
			@Override
			public ICreator OnEvent() {
				// TODO Auto-generated method stub
				return new MySQLCreator();
			}			
		});
		String path = getCurrentPath();
		try {
			ProviderConfig.setSQLFolder(path + "provider_xml");
			ProviderConfig.setConnStr(url);
			ProviderConfig.setUser("root");
			ProviderConfig.setPwd("root");
			ProviderConfig.setDbType(DBOperate.MySQL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void Test_ExecuteQuery_list_on_param() {
		List<CharactersetsPO> lst = DataProvider.ExecuteQuery(CharactersetsPO.class, "Data.Character_sets.Query", 
				new  IEventRet8Param<String, String>(){

					@Override
					public String OnEvent(String v) {

						switch (v) {
						case "CHARACTER_SET_NAME":
						{
							return "CHARACTER_SET_NAME";
						}
						case "DEFAULT_COLLATE_NAME":
						{
							return "DEFAULT_COLLATE_NAME";
						}
						case "DESCRIPTION":
						{
							return "DESCRIPTION";
						}
						case "MAXLEN":
						{
							return "MAXLEN";
						}
						case "Count":
						{
							return "8";
						}
						default:
						{
							return null;
						}
						}
					}
			
		});
		Assert.assertTrue(lst!= null);
		for (int i = 0; i < lst.size(); i++) {
			String str = HiTypeHelper.ToString(CharactersetsPO.class, lst.get(i));
			System.out.print(str);
			System.out.print("\r\n");
		}
	}
	@Test
	public void Test_ExecuteQuery_list_using_param() {
		List<CharactersetsPO> lst = DataProvider.ExecuteQuery(CharactersetsPO.class, "Data.Character_sets.Query2");
		Assert.assertTrue(lst!= null);
		for (int i = 0; i < lst.size(); i++) {
			String str = HiTypeHelper.ToString(CharactersetsPO.class, lst.get(i));
			System.out.print(str);
			System.out.print("\r\n");
		}
	}
	@Test
	public void Test_ExecuteQuery_Single() {
		CharactersetsPO ret =  DataProvider.ExecuteQuerySingle(CharactersetsPO.class, "Data.Character_sets.Query_Single", 
				new  IEventRet8Param<String, String>(){

			@Override
			public String OnEvent(String v) {

				switch (v) {
				case "CHARACTER_SET_NAME":
				{
					return "CHARACTER_SET_NAME";
				}
				case "DEFAULT_COLLATE_NAME":
				{
					return "DEFAULT_COLLATE_NAME";
				}
				case "DESCRIPTION":
				{
					return "DESCRIPTION";
				}
				case "MAXLEN":
				{
					return "MAXLEN";
				}
				default:
				{
					return null;
				}
				}
			}	
		});
		
		Assert.assertTrue(ret!= null);
		String str = HiTypeHelper.ToString(CharactersetsPO.class, ret);
		System.out.print(str);
		System.out.print("\r\n");
	}
	@Test
	public void Test_DBHelper_ExecuteQuery_Single_null() {
		CharactersetsPO ret =  DataProvider.ExecuteQuerySingle(CharactersetsPO.class, "Data.Character_sets.Query_Single_null");
		Assert.assertTrue(ret == null);
		String str = HiTypeHelper.ToString(CharactersetsPO.class, ret);
		Assert.assertEquals(str, "");
	}
	
	private String getCurrentPath(){  
		 //当前目录的上级目录路径  
	       String rootPath=getClass().getResource("/").getPath();  
	       int pos = rootPath.lastIndexOf("/", rootPath.length() - 2);
	          
	       return rootPath.substring(0, pos) + "/"; 
	   
	   }

}
