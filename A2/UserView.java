package A2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class UserView {
    private GridPane grid;
    private User user;
    private AdminController admin = AdminController.getInstance();
    
    public UserView(User user) {
        this.grid = new GridPane();
        this.user = user;
        process();
    }
    public GridPane getPane() {    
        return grid;
    }
    
    public void process() {
        
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        //Defining the UID text field
        TextField uid = new TextField();
        uid.setPromptText("Enter User ID");
        GridPane.setConstraints(uid, 0, 0);
        grid.getChildren().add(uid);
        
        //Defining the Follow User button
        Button followUser = new Button("Follow User");
        GridPane.setConstraints(followUser, 1, 0);
        grid.getChildren().add(followUser);
        
        // Define listView of Following
        ListView following = new ListView();
        following.getItems().add("Current Following\n"); 
        GridPane.setConstraints(following, 2, 0);
        grid.getChildren().add(following);
        
        followUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
               String newID = uid.getText();
               //Creating a dialog
               Dialog<String> dialog = new Dialog<String>();
               //Setting the title
               dialog.setTitle("Notice");
               ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
               
               if(newID.isEmpty() || !admin.getUsers().containsKey(newID)) {
                   dialog.setContentText("Please enter an existing User ID.");
                  
               }
               else if (newID.equalsIgnoreCase(user.getUID()))
                   dialog.setContentText("You can't follow yourself.");
               else {
                   User queriedUser = admin.getUsers().get(newID);
                   // successfully follow queriedUser
                   if(user.follow(queriedUser)) {
                      following.getItems().add(newID);
                      dialog.setContentText("You are now following " + newID);
                   }
                   // unsuccessful follow
                   else {
                       dialog.setContentText("You've already followed this User.");
                   }
               }
               
               dialog.getDialogPane().getButtonTypes().add(type);
               dialog.showAndWait();
               uid.clear();
            }
        });
        
        
        
        //Defining the message text field
        TextField message = new TextField();
        message.setPromptText("Enter message");
        GridPane.setConstraints(message, 0, 1);
        grid.getChildren().add(message);
        
        //Defining the Post Tweet button
        Button postTweet = new Button("Post Tweet");
        GridPane.setConstraints(postTweet, 1, 1);
        grid.getChildren().add(postTweet);
        
        postTweet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
               String tweet = message.getText();
               //Creating a dialog
               Dialog<String> dialog = new Dialog<String>();
               //Setting the title
               dialog.setTitle("Notice");
               ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
               
               if(tweet.isEmpty()) {
                   dialog.setContentText("You cannot post empty tweet.");  
               }
               else {
                  user.addMessage(tweet);
                  user.notifyObserver();
                  dialog.setContentText("Your tweet has now been posted."); 
                 
               }
               
               dialog.getDialogPane().getButtonTypes().add(type);
               dialog.showAndWait();
               message.clear();
            }
        });
        
         // Define listView for following messgae
        ListView<String> followingMsg = new ListView<>();
        followingMsg.setItems(user.getFollowingMessages());
           
        GridPane.setConstraints(followingMsg, 2, 1);
        grid.getChildren().add(followingMsg);
    }
}
