import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date; 

public class DBConnector{ 
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    private ResultSet resultSet = null;

    public DBConnector(){
    }

    private void readDataBase(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://cs591-bank.c6rbpssqfeyd.us-east-2.rds.amazonaws.com:"+
                                                    "3305/" + //port number
                                                    "CS591-bank?" + //schema name
                                                    "user=admin&" + //username
                                                    "password=adminbank"); // password
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from `CS591-bank`.Users");
            writeResultSet(resultSet);
            }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            System.out.println("done");
        }

    }

    private void writeResultSet(ResultSet resultSet) throws SQLException{
        while(resultSet.next()){
            int userID = resultSet.getInt("userID");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            System.out.println("UserID: " + userID);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        }
    }

    public static void main(String[] args){
        DBConnector dbc = new DBConnector();
        dbc.readDataBase();
    }


}