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

    public ArrayList<Loan> getAllUserLoans(int userID){
        ArrayList<Loan> ret = new ArrayList<Loan>();
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("select * from `CS591-bank`.Loan WHERE Loan.userID=" + userID);

            while(this.resultSet.next()){
                int loan_id = this.resultSet.getInt("loanID");
                int user_id = this.resultSet.getInt("userID");
                String collateral = this.resultSet.getString("collateral");
                Date loan_date = this.resultSet.getDate("loan_date");
                Date payment_date = this.resultSet.getDate("payment_date");
                double amount = this.resultSet.getDouble("amount");

                System.out.println("loan_id: " + loan_id);
                System.out.println("user_id: " + user_id);
                System.out.println("collateral: " + collateral);
                System.out.println("loan_date: " + loan_date);
                System.out.println("payment_date: " + payment_date);
                System.out.println("amount: " + amount);

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

    public void insertNewLoan(Loan loan){
        try{
            String query = "INSERT INTO Loan(loanID,userID,collateral,loan_date,payment_date,amount) "
            + "VALUES(?,?,?,?,?,?)";

            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setInt(1,loan.getLoanID());
            this.preparedStatement.setInt(2,loan.getUser());
            this.preparedStatement.setString(3,loan.getCollateral());
            Date loan_date = Date.valueOf(loan.getLoanDate());
            this.preparedStatement.setDate(4,loan_date);
            Date payment_date = Date.valueOf(loan.getPaymentDate());
            this.preparedStatement.setDate(5,payment_date);
            this.preparedStatement.setDouble(6,loan.getAmount());
            //this.statement = this.connect.createStatement();
            //this.statement.executeUpdate("insert into `CS591-bank`.Loan" + 
              //                              "VALUES(2,12, 'house', '2020-5-04', '2022-5-04', 5000.0");
            this.preparedStatement.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            System.out.println("done");
        }
    }

    public ArrayList<Transaction> getUserTransactions_Date(int user_id,String date){
        ArrayList<Transaction> ret = new ArrayList<Transaction>();
        try{
            
            this.statement = this.connect.createStatement();
            Date transactionDate = Date.valueOf(date);
            this.resultSet = this.statement.executeQuery("Select * FROM `CS591-bank`.Transactions WHERE (Transactions.userID=" + user_id + " AND Transactions.transaction_date='" + transactionDate +"')");

            while(this.resultSet.next()){
                int transactionID  = this.resultSet.getInt("transactionID");
                int userID = this.resultSet.getInt("userID");
                int accountID = this.resultSet.getInt("accountID");
                Date transaction_date = this.resultSet.getDate("transaction_date");
                double amount = this.resultSet.getDouble("amount");
                String currency = this.resultSet.getString("currency");
                String transactionType = this.resultSet.getString("transactionType");
                int transferAccountID = this.resultSet.getInt("transferAccountID");
                
                Transaction userTransactionByDate;
                if(transactionType.equals("deposit")){
                    userTransactionByDate = new Deposit(transactionID, userID, accountID, amount, currency,
                    transaction_date.toString());
                    ret.add(userTransactionByDate);
                }
                else if(transactionType.equals("withdrawl")){
                    userTransactionByDate = new Withdraw(transactionID, userID, accountID, amount, currency,
                    transaction_date.toString());
                    ret.add(userTransactionByDate);
                }
                else if(transactionType.equals("transfer")){
                    userTransactionByDate = new Transfer(transactionID, userID, accountID, amount, currency,
                    transaction_date.toString(), transferAccountID);
                    ret.add(userTransactionByDate);
                }

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
    
    public boolean checkUserByUsername(String user_name){
        boolean ret = false;

        int userID = -1;

        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("Select * FROM `CS591-bank`.Users WHERE Users.username=" + "'" + user_name + "'");

            while(this.resultSet.next()){
                userID = this.resultSet.getInt("userID");
                if(userID > 0){
                    ret = true;
                }
            }
            System.out.println(ret);
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            System.out.println("checkUserByUsername for " + user_name + "complete");
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
        //dbc.readDataBase();
        //dbc.getAllUserLoans(12);
        //Loan testLoan = new Loan(3,12,10000.0,"test","2020-5-30","2024-5-30");
        //dbc.insertNewLoan(testLoan);
        //dbc.getAllUserLoans(12);
        //dbc.getUserTransactions_Date(12,"2020-05-04");
        dbc.checkUserByUsername("firstUser");
    }


}