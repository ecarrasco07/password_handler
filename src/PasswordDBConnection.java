import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class PasswordDBConnection{

    private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = ""; //url for local host
	private static final String user = ""; //username for sql
	private static final String pswd = ""; //password for sql server


public void addPassword(String sql) throws Exception{
    try{
        Class.forName(driver);

        Connection con = DriverManager.getConnection(url, user, pswd);

        Statement state = con.createStatement();

        state.executeUpdate(sql);
        System.out.println("insert complete");
    }
    catch(Exception e){
        System.out.println(e);
    }

}

public void updateEntry(String id, String update, int field) throws Exception{
    String fields[] = {"websiteName", "email", "password", "username"};
    try{
        Class.forName(driver);

        Connection con = DriverManager.getConnection(url, user, pswd);

        Statement state = con.createStatement();

        String sql = "update passwords "+
        "set " + fields[field-=1]+ "='"+update+"' "+
        "where id=" + id;

        //System.out.println(sql);
        state.executeUpdate(sql);
        System.out.println("Update Complete");
    } catch(Exception e){
        System.out.println(e);
    }
}

public void deleteEntry(String id) throws Exception{
    try{
        Class.forName(driver);

        Connection con = DriverManager.getConnection(url, user, pswd);

        Statement state = con.createStatement();

        String sql = "delete from passwords "+
        "where id=" + id;
        //System.out.println(sql);
        state.executeUpdate(sql);
        System.out.println("Deletion Complete");
    } catch(Exception e){
        System.out.println(e);
    }
    
    
}

public String[][] viewAll() throws Exception{
    int rowNum = getRowNum();
    String[][] data = new String[rowNum][];
    String query = "select * from passwords";

    try{
        Class.forName(driver);

        Connection con = DriverManager.getConnection(url, user, pswd);

        Statement state = con.createStatement();

        ResultSet result = state.executeQuery(query);
        
        int counter = 0;
        while(result.next()){
            String dataString = "";
            for(int i = 1; i < 6; i++){
                dataString += result.getString(i) + ":";
            }
            data[counter] = dataString.split(":");
            counter++;
        }
    } catch(Exception e){System.out.println(e);}

    return data;
}

//Gets the number of rows in the databse
public int getRowNum() throws Exception{
    String query = "select count(*) from passwords";
    int rowNum = 0;
    try{
        Class.forName(driver);

        Connection con = DriverManager.getConnection(url, user, pswd);

        Statement state = con.createStatement();
        ResultSet result = state.executeQuery(query);
        result.next();

        rowNum = result.getInt(1);
        
    }catch(Exception e){System.out.println(e);}

    return rowNum;
}

}
