package hibernate;
// Generated 18/11/2018 05:40:19 PM by Hibernate Tools 4.3.1



/**
 * FriendList generated by hbm2java
 */
public class FriendList  implements java.io.Serializable {


     private Integer id;
     private Users usersByIdUser2;
     private Users usersByIdUser1;

    public FriendList() {
    }

    public FriendList(Users usersByIdUser2, Users usersByIdUser1) {
       this.usersByIdUser2 = usersByIdUser2;
       this.usersByIdUser1 = usersByIdUser1;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Users getUsersByIdUser2() {
        return this.usersByIdUser2;
    }
    
    public void setUsersByIdUser2(Users usersByIdUser2) {
        this.usersByIdUser2 = usersByIdUser2;
    }
    public Users getUsersByIdUser1() {
        return this.usersByIdUser1;
    }
    
    public void setUsersByIdUser1(Users usersByIdUser1) {
        this.usersByIdUser1 = usersByIdUser1;
    }




}

