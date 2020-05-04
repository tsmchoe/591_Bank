import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;

public class DBConnector{ 
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    private ResultSet resultSet = null;

    public DBConnector(){
        this.connect = this.getConnection();
    }

    private Connection getConnection(){
        Connection myConnection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            myConnection = DriverManager.getConnection("jdbc:mysql://cs591-bank.c6rbpssqfeyd.us-east-2.rds.amazonaws.com:"+
                                                    "3305/" + //port number
                                                    "CS591-bank?" + //schema name
                                                    "user=admin&" + //username
                                                    "password=adminbank"); // password
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            System.out.println("done");
        }
        return myConnection;
    }

    public ArrayList<Loan> getAllLoans(int userID){
        ArrayList<Loan> ret = new ArrayList<Loan>();
        try{
            //this.getConnection(); //connect to the database
            this.statement = this.connect.createStatement();
            this.resultSet = statement.executeQuery("select * from `CS591-bank`.Loan WHERE Loan.userID=" + userID);

            while(resultSet.next()){
                int loan_id = resultSet.getInt("loanID");
                int user_id = resultSet.getInt("userID");
                String collateral = resultSet.getString("collateral");
                Date loan_date = resultSet.getDate("loan_date");
                Date payment_date = resultSet.getDate("payment_date");
                double amount = resultSet.getDouble("amount");

                /* System.out.println("loan_id: " + loan_id);
                System.out.println("user_id: " + user_id);
                System.out.println("collateral: " + collateral);
                System.out.println("loan_date: " + loan_date);
                System.out.println("payment_date: " + payment_date);
                System.out.println("amount: " + amount); */

                Loan userLoan = new Loan(loan_id,user_id,amount,collateral,loan_date.toString(),payment_date.toString());
                ret.add(userLoan);

            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            System.out.println("done");
        }

        return ret;


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
        dbc.getAllLoans(12);
    }


}