//Class for Request, right now the only request we work with is loan.
public class Request {
    String type;
    Loan loan;
    DBConnector db = new DBConnector();

    //basic constructor for request
    public Request() {}

    //constructor for loan request
    public Request(Loan loan) {
        this.loan = loan;
    }

    public void approveRequest() {
        //If it is a loan request, insert loan in the database
        if(loan != null) {
            db.insertNewLoan(loan);
        }
    }
     
}