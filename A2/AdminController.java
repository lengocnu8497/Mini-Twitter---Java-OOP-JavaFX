package A2;

import java.util.HashSet;
import java.util.Hashtable;

public class AdminController {
   private static AdminController firstInstance = null; 
   static boolean firstThread = true;
   private static Hashtable<String, User> users = new Hashtable<>();
   private static HashSet<UserGroup> userGroup = new HashSet<>();
   
   private AdminController() {}
   
   public static AdminController getInstance() {
       if(firstInstance == null) {
           
         if(firstThread) {
            firstThread = false;
             
            try {
               Thread.currentThread();
               Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         } 
         
         synchronized(AdminController.class) {
             if(firstInstance == null) {
                 firstInstance = new AdminController();
             }
         }
       }
       
       return firstInstance;
   }
   
   public static boolean addUser(User user) {
        users.put(user.getUID(), user);
        return true;
   }
   
   public static boolean addGroup(UserGroup group) {
        userGroup.add(group);
        return true;
   }
   
   public static Hashtable<String,User> getUsers() {
       return users;
   }
   
   public static HashSet<UserGroup> getGroups() {
       return userGroup;
   }
   
}
