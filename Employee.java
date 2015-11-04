package CZ2002;

public class Employee extends User{
    private String userName, password;
    public Employee(String user, String pw, String id, String name, String email,String no){
        super(id,name,email,no);
        this.userName = user;
        this.password = pw;
    }
    
}
