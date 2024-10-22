import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;

/*This class acts a layer between the database and the backend. 
//Here the connection to the database is taken care of and all of the queries are provided.
//The backend utilizes this by constructing an instance of this class.
The methods of this class are then used to run each of the queries.*/
public class DBConnector{ 
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    private ResultSet resultSet = null;

    /*Constructor for DBConnector. As a part of the constructor we connect to the database so that each query
    does not need to make a seperate connection to the database. As a result once an instance is created
    in the backend instance only needs to make one connection. This should help decrease runtime for our
    application.*/
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
            System.out.println("connection found");
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
            System.out.println("getAllUserLoans query complete");
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
            this.preparedStatement.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            System.out.println("insertNewLoan query complete");
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
                
                System.err.println(transactionID);
                Transaction userTransactionByDate;
                if(transactionType.equals("DEPOSIT")){
                    userTransactionByDate = new Deposit(transactionID, userID, accountID, amount, currency,
                    transaction_date.toString());
                    ret.add(userTransactionByDate);
                }
                else if(transactionType.equals("WITHDRAW")){
                    userTransactionByDate = new Withdraw(transactionID, userID, accountID, amount, currency,
                    transaction_date.toString());
                    ret.add(userTransactionByDate);
                }
                else if(transactionType.equals("TRANSFER")){
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
            System.out.println("getUserTransaction_date query complete");
        }
        System.out.println(ret);
        return ret;
    }

    public ArrayList<Transaction> getTransactions_Date(String date){
        ArrayList<Transaction> ret = new ArrayList<Transaction>();
        try{
            
            this.statement = this.connect.createStatement();
            Date transactionDate = Date.valueOf(date);
            this.resultSet = this.statement.executeQuery("Select * FROM `CS591-bank`.Transactions WHERE (Transactions.transaction_date='" + transactionDate +"')");

            while(this.resultSet.next()){
                int transactionID  = this.resultSet.getInt("transactionID");
                int userID = this.resultSet.getInt("userID");
                int accountID = this.resultSet.getInt("accountID");
                Date transaction_date = this.resultSet.getDate("transaction_date");
                double amount = this.resultSet.getDouble("amount");
                String currency = this.resultSet.getString("currency");
                String transactionType = this.resultSet.getString("transactionType");
                int transferAccountID = this.resultSet.getInt("transferAccountID");
                
                System.err.println(transactionID);
                Transaction userTransactionByDate;
                if(transactionType.equals("DEPOSIT")){
                    userTransactionByDate = new Deposit(transactionID, userID, accountID, amount, currency,
                    transaction_date.toString());
                    ret.add(userTransactionByDate);
                }
                else if(transactionType.equals("WITHDRAW")){
                    userTransactionByDate = new Withdraw(transactionID, userID, accountID, amount, currency,
                    transaction_date.toString());
                    ret.add(userTransactionByDate);
                }
                else if(transactionType.equals("TRANSFER")){
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
            System.out.println("getTransactionByDate query complete");
        }
        System.out.println(ret);
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

    public ArrayList<Transaction> getTransactionsByUser(int user_id){
        ArrayList<Transaction> ret = new ArrayList<Transaction>();
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("Select * FROM `CS591-bank`.Transactions WHERE Transactions.userID=" + user_id);
            while(this.resultSet.next()){
                System.out.println("hello");
                int transactionID  = this.resultSet.getInt("transactionID");
                int userID = this.resultSet.getInt("userID");
                int accountID = this.resultSet.getInt("accountID");
                Date transaction_date = this.resultSet.getDate("transaction_date");
                double amount = this.resultSet.getDouble("amount");
                String currency = this.resultSet.getString("currency");
                String transactionType = this.resultSet.getString("transactionType");
                int transferAccountID = this.resultSet.getInt("transferAccountID");
                
                Transaction userTransactionByDate;
                if(transactionType.equals("DEPOSIT")){
                    userTransactionByDate = new Deposit(transactionID, userID, accountID, amount, currency,
                    transaction_date.toString());
                    ret.add(userTransactionByDate);
                }
                else if(transactionType.equals("WITHDRAW")){
                    userTransactionByDate = new Withdraw(transactionID, userID, accountID, amount, currency,
                    transaction_date.toString());
                    ret.add(userTransactionByDate);
                }
                else if(transactionType.equals("TRANSFER")){
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
            System.out.println("getTransactionbyUser complete");
        }
        System.out.println(ret);
        return ret;
    }

    public void updateBalanceCheckings(int accountID, double newBalance){
        try{
            String query = "UPDATE CheckingsAccount set balance= ? WHERE accountID= ?";
            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setDouble(1,newBalance);
            this.preparedStatement.setInt(2,accountID);
            this.preparedStatement.execute();

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            System.out.println("balance update for account " + accountID);
        }
    }

    public void updateBalanceSavings(int accountID, double newBalance){
        try{
            String query = "UPDATE SavingsAccount set balance= ? WHERE accountID= ?";
            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setDouble(1,newBalance);
            this.preparedStatement.setInt(2,accountID);
            this.preparedStatement.execute();

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            System.out.println("balance update for account " + accountID);
        }
    }

    public void insertNewAccount(CheckingsAccount account){
        try{
            String query = "INSERT INTO CheckingsAccount(accountID,balance,userID,currency) " +
        "VALUES(?,?,?,?)";

        this.preparedStatement = this.connect.prepareStatement((query));
        this.preparedStatement.setInt(1,account.getAccountID());
        this.preparedStatement.setDouble(2, account.getBalance());
        this.preparedStatement.setInt(3, account.getUserID());
        this.preparedStatement.setString(4, account.getCurrency());

        this.preparedStatement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("Inserted new CheckingsAccount");
        }
    }

    public void insertNewAccount(SavingsAccount account){
        try{
            String query = "INSERT INTO SavingsAccount(accountID,balance,userID,currency,interestRate) " +
        "VALUES(?,?,?,?,?)";

        this.preparedStatement = this.connect.prepareStatement((query));
        this.preparedStatement.setInt(1,account.getAccountID());
        this.preparedStatement.setDouble(2, account.getBalance());
        this.preparedStatement.setInt(3, account.getUserID());
        this.preparedStatement.setString(4, account.getCurrency());
        this.preparedStatement.setDouble(5, account.getInterest_rate());

        this.preparedStatement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("Inserted new SavingsAccount");
        }
    }

    public void insertNewAccount(SecurityAccount account){
        try{
            String query = "INSERT INTO SecurityAccount(accountID,balance,userID,currency) " +
        "VALUES(?,?,?,?)";

        this.preparedStatement = this.connect.prepareStatement((query));
        this.preparedStatement.setInt(1,account.getAccountID());
        this.preparedStatement.setDouble(2, account.getBalance());
        this.preparedStatement.setInt(3, account.getUserID());
        this.preparedStatement.setString(4, account.getCurrency());

        this.preparedStatement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("Inserted new SecurityAccount");
        }
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
            System.out.println("readDatabase query complete");
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

    public ArrayList<Transaction> getDailyTransactions(String date){
        ArrayList<Transaction> ret = new ArrayList<Transaction>();
        try{
            this.statement = this.connect.createStatement();
            Date transactionDate = Date.valueOf(date);
            this.resultSet = this.statement.executeQuery("Select * FROM `CS591-bank`.Transactions WHERE Transactions.transaction_date='" + transactionDate +"'");

            while(this.resultSet.next()){
                int transactionID  = this.resultSet.getInt("transactionID");
                int userID = this.resultSet.getInt("userID");
                int accountID = this.resultSet.getInt("accountID");
                String transaction_date = this.resultSet.getString("transaction_date");
                double amount = this.resultSet.getDouble("amount");
                String currency = this.resultSet.getString("currency");
                String transactionType = this.resultSet.getString("transactionType");
                int transferAccountID = this.resultSet.getInt("transferAccountID");
                Transaction userTransactionByDate;
                if(transactionType.equals("DEPOSIT")){
                    userTransactionByDate = new Deposit(transactionID, userID, accountID, amount, currency,
                    transaction_date);
                    ret.add(userTransactionByDate);
                }
                else if(transactionType.equals("WITHDRAW")){
                    userTransactionByDate = new Withdraw(transactionID, userID, accountID, amount, currency,
                    transaction_date.toString());
                    ret.add(userTransactionByDate);
                }
                else if(transactionType.equals("TRANSFER")){
                    userTransactionByDate = new Transfer(transactionID, userID, accountID, amount, currency,
                    transaction_date.toString(), transferAccountID);
                    ret.add(userTransactionByDate);
                }

            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("Daily transaction query");
        }
        return ret;
    }

    public ArrayList<CheckingsAccount> getCheckingsAccountByUser(int user_id){
        ArrayList<CheckingsAccount> ret = new ArrayList<CheckingsAccount>();
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM `CS591-bank`.CheckingsAccount WHERE CheckingsAccount.userID=" + user_id);

            while(this.resultSet.next()){
                int accountID = this.resultSet.getInt("accountID");
                double balance = this.resultSet.getDouble("balance");
                int userID = this.resultSet.getInt("userID");
                String currency = this.resultSet.getString("currency");

                CheckingsAccount accountDB = new CheckingsAccount(accountID, balance, userID, new Currency(currency));
                ret.add(accountDB);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("Checkings Account by user query complete");
        }
        return ret;
    }

    public ArrayList<SavingsAccount> getSavingsAccountByUser(int user_id){
        ArrayList<SavingsAccount> ret = new ArrayList<SavingsAccount>();
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM `CS591-bank`.SavingsAccount WHERE SavingsAccount.userID=" + user_id);

            while(this.resultSet.next()){
                int accountID = this.resultSet.getInt("accountID");
                double balance = this.resultSet.getDouble("balance");
                int userID = this.resultSet.getInt("userID");
                String currency = this.resultSet.getString("currency");
                double ir = this.resultSet.getDouble("interestRate");

                SavingsAccount accountDB = new SavingsAccount(accountID, balance, userID, new Currency(currency),ir);
                ret.add(accountDB);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("Savings Account by user query complete");
        }
        return ret;
    }

    public ArrayList<SecurityAccount> getSecurityAccountByUser(int user_id){
        ArrayList<SecurityAccount> ret = new ArrayList<SecurityAccount>();
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM `CS591-bank`.SecurityAccount WHERE SecurityAccount.userID=" + user_id);

            while(this.resultSet.next()){
                int accountID = this.resultSet.getInt("accountID");
                double balance = this.resultSet.getDouble("balance");
                int userID = this.resultSet.getInt("userID");
                String currency = this.resultSet.getString("currency");
                double cash = this.resultSet.getDouble("cash");
                double unrealized = this.resultSet.getDouble("unrealized_profit");

                SecurityAccount accountDB = new SecurityAccount(accountID, balance, userID, new Currency(currency));
                accountDB.setCash(cash);
                accountDB.setUnrealized_profits(unrealized);
                ret.add(accountDB);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("Savings Account by user query complete");
        }
        return ret;
    }

    public ArrayList<Stock> getAllAvailableStocks(){
        ArrayList<Stock> ret = new ArrayList<Stock>();
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM Stock_Market");

            while(this.resultSet.next()){
                int stockID = this.resultSet.getInt("stockID");
                String name = this.resultSet.getString("name");
                double current_price = this.resultSet.getDouble("current_price");
                int available_shares = this.resultSet.getInt("available_shares");

                Stock dbStock = new Stock(stockID, name, current_price, available_shares);
                ret.add(dbStock);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("Found all current stocks in the stock market");
        }
        System.out.println(ret);
        return ret;
    }

    public Stock getAvailableStockByID(int stock_ID){
        Stock ret = new Stock(0, "", 0, 0);
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM Stock_Market WHERE stockID=" + stock_ID);

            this.resultSet.next();
            int stockID = this.resultSet.getInt("stockID");
            int available_shares = this.resultSet.getInt("available_shares");
            String name = this.resultSet.getString("name");
            double current_price = this.resultSet.getDouble("current_price");
            ret = new Stock(stockID, name, current_price, available_shares);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("getAvailalbeStockByID complete");
        }
        return ret;
    }

    public void addStockToStockMarket(Stock stock){
        try{
            String query = "INSERT INTO Stock_Market(stockID,available_shares,name,current_price)"
            + "VALUES(?,?,?,?)";

            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setInt(1,stock.getId());
            this.preparedStatement.setInt(2,stock.getTotal_share());
            this.preparedStatement.setString(3,stock.getName());
            this.preparedStatement.setDouble(4,stock.getCurrentPrice());
            this.preparedStatement.execute();


        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            System.out.println("inserted new Stock into Stock_Market");
        }
    }

    public void updateStockInStock_Market(int stock_id, double new_price, int avail_shares){
        try{
            String query = "UPDATE Stock_Market set available_shares= ?, current_price =? WHERE stockID=?";
            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setInt(1,avail_shares);
            this.preparedStatement.setDouble(2,new_price);
            this.preparedStatement.setInt(3,stock_id);
            this.preparedStatement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("updateStockInStock_Market complete");
        }
    }

    public void insertNewCustomer(Customer cust){
        try{
            String query = "INSERT INTO Users(userID,username,password,userType,firstname,lastname,balance)" + 
            "VALUES(?,?,?,?,?,?,?)";
            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setInt(1,cust.getUserID());
            this.preparedStatement.setString(2,cust.getUsername());
            this.preparedStatement.setString(3, cust.getPassword());
            this.preparedStatement.setString(4, "customer");
            this.preparedStatement.setString(5, cust.getFirstName());
            this.preparedStatement.setString(6, cust.getLastName());
            this.preparedStatement.setDouble(7, cust.getBalance());
            this.preparedStatement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("inserNewCustomer query complete");
        }
    }

    public ArrayList<SecurityAccount> getSecurityAccountByStockID(int stockID){
        ArrayList<SecurityAccount> ret = new ArrayList<SecurityAccount>();
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM Stocks WHERE stockID= " + stockID);

            ArrayList<Integer> accoundIDs = new ArrayList<Integer>();
            while(this.resultSet.next()){
                int accountID = this.resultSet.getInt("accountID");
                accoundIDs.add(accountID);
            }

            String query = "SELECT * FROM SecurityAccount WHERE accountID=?";
            this.preparedStatement = this.connect.prepareStatement(query);
            for(Integer id : accoundIDs){
                this.preparedStatement.setInt(1,id);
                this.resultSet = this.preparedStatement.executeQuery();
                this.resultSet.next();

                double balance = this.resultSet.getDouble("balance");
                int userID = this.resultSet.getInt("userID");
                String currency = this.resultSet.getString("currency");
                double cash = this.resultSet.getDouble("cash");
                double unrealized = this.resultSet.getDouble("unrealized_profit"); 

                SecurityAccount accountDB = new SecurityAccount(id, balance, userID, new Currency(currency));
                accountDB.setCash(cash);
                accountDB.setUnrealized_profits(unrealized);

                ret.add(accountDB);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("Received Security accounts that own the stock");
        }
        return ret;
    }

    public ArrayList<BoughtStock> getStockByAccountID(int account_id){
        ArrayList<BoughtStock> ret = new ArrayList<BoughtStock>();
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM Stocks WHERE accountID= " + account_id);

            while(this.resultSet.next()){
                int stockID = this.resultSet.getInt("stockID");
                double purchasedPrice = this.resultSet.getDouble("purchasedPrice");
                String name = this.resultSet.getString("name");
                int accountID = this.resultSet.getInt("accountID");
                int shares = this.resultSet.getInt("shares");

                BoughtStock stockDB = new BoughtStock(stockID, purchasedPrice,shares,name,accountID);
                ret.add(stockDB);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("getStockByAccountID complete");
        }
        return ret;
    }

    public void deleteStockByAccountStock(int stock_ID, int account_ID){
        try{
            String query = "DELETE FROM Stocks WHERE (stockID=? AND accountID=?)";
            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setInt(1,stock_ID);
            this.preparedStatement.setInt(2,account_ID);
            this.preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("deleteStockByAccountStock");
        }
    }

    public void insertNewBoughtStock(BoughtStock stock){
        try{
            String query = "INSERT INTO Stocks(stockID,name,current_price,accountID,purchasedPrice,shares)" + 
            "VALUES(?,?,?,?,?,?)";
            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setInt(1,stock.getID());
            this.preparedStatement.setString(2,stock.getName());
            this.preparedStatement.setDouble(3,stock.getCurrrent_price());
            this.preparedStatement.setInt(4,stock.getAccount());
            this.preparedStatement.setDouble(5, stock.getAvgCost());
            this.preparedStatement.setInt(6,stock.getShare());

            this.preparedStatement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.print("insertNewBoughtStock query complete");
        }
    }

    public void updateBoughtStock(int stockID, double currentPrice, int newShares){
        try{
            String query = "UPDATE Stocks set current_price=?, shares=? WHERE stockID=?";
            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setDouble(1,currentPrice);
            this.preparedStatement.setInt(2, newShares);
            this.preparedStatement.setInt(3,stockID);
            this.preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("updateBoughtStock query complete");
        }
    }

    public Customer getCustomerByUserID(int user_ID){
        Customer ret = new Customer(0,"","","","",0);
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM Users WHERE userID=" + user_ID + " AND userType='customer'");
            
            while(this.resultSet.next()){
                int userID = this.resultSet.getInt("userID");
                String username = this.resultSet.getString("username");
                String password =this.resultSet.getString("password");
                String firstname =this.resultSet.getString("firstName");
                String lastname = this.resultSet.getString("lastName");
                double balance = this.resultSet.getDouble("balance");

                Customer customerDB = new Customer(userID, firstname, lastname, username, password, balance);

                ret = customerDB;
            }
        }catch(SQLException e){
                e.printStackTrace();
        }finally{
                System.out.println("getCustomerbyUserID query complete");
            }
            return ret;
    }

    public void insertTransaction(Transaction t, String transactionType){
        try{
            String query = "INSERT INTO Transactions(transactionID,userID,accountID,transaction_date,amount,currency,transactionType)" +
            "VALUES(?,?,?,?,?,?,?)";
            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setInt(1,t.getTransactionID());
            this.preparedStatement.setInt(2,t.getUserid());
            this.preparedStatement.setInt(3,t.getAccountId());
            System.out.println(t.getDate());
            Date trans_date = Date.valueOf(t.getDate());
            this.preparedStatement.setDate(4,trans_date);
            this.preparedStatement.setDouble(5, t.getAmount());
            this.preparedStatement.setString(6,t.getCurrency());
            this.preparedStatement.setString(7,transactionType);
            this.preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("insertTransaction query for checkins or savings complete");
        }
    }

    public void insertTransaction(Transfer t){
        try{
            String query = "INSERT INTO Transactions(transactionID,userID,accountID,transaction_date,amount,currency,transactionType,transferAccountID)" +
            "VALUES(?,?,?,?,?,?,?,?)";
            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setInt(1,t.getTransactionID());
            this.preparedStatement.setInt(2,t.getUserid());
            this.preparedStatement.setInt(3,t.getAccountId());
            Date trans_date = Date.valueOf(t.getDate());
            this.preparedStatement.setDate(4,trans_date);
            this.preparedStatement.setDouble(5, t.getAmount());
            this.preparedStatement.setString(6,t.getCurrency());
            this.preparedStatement.setString(7,"transfer");
            this.preparedStatement.setInt(8,t.getTransferAccountID());
            this.preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("insertTransaction query for transfer complete");
        }
    }

    public ArrayList<CheckingsAccount> getCheckingsAccountByAccountID(int account_ID){
        ArrayList<CheckingsAccount> ret = new ArrayList<CheckingsAccount>();
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM CheckingsAccount WHERE accountID=" + account_ID);

            while(this.resultSet.next()){
                int accountID = this.resultSet.getInt("accountID");
                double balance = this.resultSet.getDouble("balance");
                int userID = this.resultSet.getInt("userID");
                String currency = this.resultSet.getString("currency");

                CheckingsAccount accountDB = new CheckingsAccount(accountID, balance, userID, new Currency(currency));
                ret.add(accountDB);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("getCheckinsAccountByAccountID query complete");
        }
        return ret;
    }

    public ArrayList<SavingsAccount> getSavingsAccountByAccountID(int account_ID){
        ArrayList<SavingsAccount> ret = new ArrayList<SavingsAccount>();
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM SavingsAccount WHERE accountID=" + account_ID);

            while(this.resultSet.next()){
                int accountID = this.resultSet.getInt("accountID");
                double balance = this.resultSet.getDouble("balance");
                int userID = this.resultSet.getInt("userID");
                String currency = this.resultSet.getString("currency");
                Double ir = this.resultSet.getDouble("interestRate");

                SavingsAccount accountDB = new SavingsAccount(accountID, balance, userID, new Currency(currency),ir);
                ret.add(accountDB);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("getSavingsAccountByAccountID query complete");
        }
        return ret;
    }

    public ArrayList<SecurityAccount> getSecurityAccountByAccountID(int account_ID){
        ArrayList<SecurityAccount> ret = new ArrayList<SecurityAccount>();
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM SecurityAccount WHERE accountID=" + account_ID);

            while(this.resultSet.next()){
                int accountID = this.resultSet.getInt("accountID");
                double balance = this.resultSet.getDouble("balance");
                int userID = this.resultSet.getInt("userID");
                String currency = this.resultSet.getString("currency");
                double cash = this.resultSet.getDouble("cash");
                double unrealized = this.resultSet.getDouble("unrealized_profit");

                SecurityAccount accountDB = new SecurityAccount(accountID, balance, userID, new Currency(currency));
                accountDB.setCash(cash);
                accountDB.setUnrealized_profits(unrealized);
                ret.add(accountDB);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("getCheckinsAccountByAccountID query complete");
        }
        return ret;
    }

    public BoughtStock getBoughtStockByStockIDAccountID(int stock_ID, int account_ID){
        BoughtStock ret = new BoughtStock(0, 0, 0, "null", 0);
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM Stocks WHERE (stockID=" + stock_ID + " AND accountID=" +account_ID+")");

            while(this.resultSet.next()){
                int stockID = this.resultSet.getInt("stockID");
                String name = this.resultSet.getString("name");
                double current_price = this.resultSet.getDouble("current_price");
                int shares = this.resultSet.getInt("shares");
                int accountID = this.resultSet.getInt("accountID");

                ret = new BoughtStock(stockID, current_price, shares, name, accountID);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("getBoughtStockByStockIDAccountID query complete");
        }
        System.out.println(ret.getName());
        return ret;
    } 

    public void updateBalanceSecurity(int accountID, double newBalance){
        try{
            String query = "UPDATE SecurityAccount SET balance=? WHERE accountID=?";
            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setDouble(1,newBalance);
            this.preparedStatement.setInt(2,accountID);
            this.preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("updateSecurityBalance query complete");
        }
    }

    public double getBankBalance(){
        double ret = 0;
        try{
            this.statement = this.connect.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM BankProfit");

            this.resultSet.next();
            double bank_balance = this.resultSet.getDouble("bank_balance");
            ret = bank_balance;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("getBankBalance query complete");
        }
        return ret;
    }

    public void increaseBankBalance(double amt){
        double curBalance = this.getBankBalance();
        double newBalance = curBalance+amt;
        try{
            String query = "UPDATE BankProfit SET bank_balance=?";
            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setDouble(1, newBalance);
            this.preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("increaseBankBalance query complete");
        }
    }

    public void decreaseBankBalance(double amt){
        double curBalance = this.getBankBalance();
        double newBalance = curBalance-amt;
        try{
            String query = "UPDATE BankProfit SET bank_balance=?";
            this.preparedStatement = this.connect.prepareStatement(query);
            this.preparedStatement.setDouble(1, newBalance);
            this.preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("decreaseBankBalance query complete");
        }
    }
}