package hibernate;
// Generated 18/11/2018 05:40:19 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class Users  implements java.io.Serializable {


     private Integer id;
     private String email;
     private String firstName;
     private String lastName;
     private String alias;
     private String pass;
     private Set<Exchanges> exchangeses = new HashSet<Exchanges>(0);
     private Set<FriendList> friendListsForIdUser2 = new HashSet<FriendList>(0);
     private Set<ParticipantList> participantLists = new HashSet<ParticipantList>(0);
     private Set<FriendList> friendListsForIdUser1 = new HashSet<FriendList>(0);
     private Set<Pairs> pairses = new HashSet<Pairs>(0);
     private Set<Pairs> pairses_1 = new HashSet<Pairs>(0);

    public Users() {
    }

	
    public Users(String email, String firstName, String lastName, String alias, String pass) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.pass = pass;
    }
    public Users(String email, String firstName, String lastName, String alias, String pass, Set<Exchanges> exchangeses, Set<FriendList> friendListsForIdUser2, Set<ParticipantList> participantLists, Set<FriendList> friendListsForIdUser1, Set<Pairs> pairses, Set<Pairs> pairses_1) {
       this.email = email;
       this.firstName = firstName;
       this.lastName = lastName;
       this.alias = alias;
       this.pass = pass;
       this.exchangeses = exchangeses;
       this.friendListsForIdUser2 = friendListsForIdUser2;
       this.participantLists = participantLists;
       this.friendListsForIdUser1 = friendListsForIdUser1;
       this.pairses = pairses;
       this.pairses_1 = pairses_1;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getPass() {
        return this.pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    public Set<Exchanges> getExchangeses() {
        return this.exchangeses;
    }
    
    public void setExchangeses(Set<Exchanges> exchangeses) {
        this.exchangeses = exchangeses;
    }
    public Set<FriendList> getFriendListsForIdUser2() {
        return this.friendListsForIdUser2;
    }
    
    public void setFriendListsForIdUser2(Set<FriendList> friendListsForIdUser2) {
        this.friendListsForIdUser2 = friendListsForIdUser2;
    }
    public Set<ParticipantList> getParticipantLists() {
        return this.participantLists;
    }
    
    public void setParticipantLists(Set<ParticipantList> participantLists) {
        this.participantLists = participantLists;
    }
    public Set<FriendList> getFriendListsForIdUser1() {
        return this.friendListsForIdUser1;
    }
    
    public void setFriendListsForIdUser1(Set<FriendList> friendListsForIdUser1) {
        this.friendListsForIdUser1 = friendListsForIdUser1;
    }
    public Set<Pairs> getPairses() {
        return this.pairses;
    }
    
    public void setPairses(Set<Pairs> pairses) {
        this.pairses = pairses;
    }
    public Set<Pairs> getPairses_1() {
        return this.pairses_1;
    }
    
    public void setPairses_1(Set<Pairs> pairses_1) {
        this.pairses_1 = pairses_1;
    }




}

