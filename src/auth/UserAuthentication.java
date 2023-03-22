package auth;

 public class UserAuthentication {
     private UserAuthentication(){

     }
    static public boolean isAdmin(String token){
        return true;
    }
    static public boolean isMember(String token){
        return true;
    }
}
