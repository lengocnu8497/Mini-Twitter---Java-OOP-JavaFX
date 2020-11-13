package A2;


import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminPane {
    
    public static SplitPane createPane() {
        
        SplitPane sp = new SplitPane();
        
        BorderPane adminPane = setRightPane();  
        
        TreeView<String> treeView = setLeftPane();
        
        sp.getItems().addAll(treeView, adminPane);
        
        return sp;
    }
    
     public static BorderPane setRightPane() {
        
        BorderPane bp = new BorderPane();
        
        AdminController admin = AdminController.getInstance();
         
        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        //Defining the UID text field
        final TextField UID = new TextField();
        UID.setPromptText("Enter User ID");
        GridPane.setConstraints(UID, 0, 0);
        grid.getChildren().add(UID);
        
        
        //Defining the Group ID text field
        final TextField GROUPID = new TextField();
        GROUPID.setPromptText("Enter Group ID");
        GridPane.setConstraints(GROUPID, 0, 1);
        grid.getChildren().add(GROUPID);
        
        //Defining the Add User button
        Button addUser = new Button("Add User");
        GridPane.setConstraints(addUser, 1, 0);
        grid.getChildren().add(addUser);
        
        //Setting an action for the addUser button
        addUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //Creating a dialog
                Dialog<String> dialog = new Dialog<String>();
                //Setting the title
                dialog.setTitle("Notice");
                ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                
                if(UID.getText().isEmpty()) {
                    dialog.setContentText("Please input ID value.");
                } else {
                    User newUser = new User(UID.getText());
                    TreeItem<String> selectedItem = TreeViewHandler.getTreeView()
                            .getSelectionModel().getSelectedItem();
                    // only select group to create nested tree item
                    if(selectedItem != null && admin.getUsers().containsKey(selectedItem.getValue()))
                        dialog.setContentText("Please select a User Group instead.");
                    else {
                         if(!admin.addUser(newUser)) {
                            //Setting the content of the dialog
                            dialog.setContentText("User with this ID has been created");
                        } 
                        else {
                            // add User to TreeView
                            TreeViewHandler.addUserComponent(newUser, selectedItem);
                            dialog.setContentText("User created successfully!");
                        }
                    }
                }
               
               //Adding buttons to the dialog pane
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
                UID.clear();
            }
        });
        
        //Defining the Add Group button
        Button addGroup = new Button("Add Group");
        GridPane.setConstraints(addGroup, 1, 1);
        grid.getChildren().add(addGroup);
        
        //Setting an action for the addGroup button
        addGroup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //Creating a dialog
                Dialog<String> dialog = new Dialog<String>();
                //Setting the title
                dialog.setTitle("Notice");
                ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                
                if(GROUPID.getText().isEmpty()) {
                    dialog.setContentText("Please input ID value.");
                } else {
                    UserGroup newGroup = new UserGroup(GROUPID.getText());
                    TreeItem<String> selectedItem = TreeViewHandler.getTreeView()
                            .getSelectionModel().getSelectedItem();
                    // only select group to create nested tree item
                    if(selectedItem != null && admin.getUsers().containsKey(selectedItem.getValue()))
                        dialog.setContentText("Please select a User Group instead.");
                    else {
                         if(!admin.addGroup(newGroup)) {
                            //Setting the content of the dialog
                            dialog.setContentText("User Group with this ID has been created");
                        } 
                        else {
                            // add User to TreeView
                            TreeViewHandler.addUserComponent(newGroup, selectedItem);
                            dialog.setContentText("User Group created successfully!");
                        }
                    }
                }
                
               //Adding buttons to the dialog pane
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
                GROUPID.clear();
            }
        });
        
        //Defining the Add Group button
        Button openUserView = new Button("Open User View");
        openUserView.setWrapText(true);
        GridPane.setConstraints(openUserView, 0, 2);    
        grid.getChildren().add(openUserView);
        
        //Setting an action for the openUserView button
        openUserView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                //Creating a dialog
                Dialog<String> dialog = new Dialog<String>();
                //Setting the title
                dialog.setTitle("Notice");
                ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                
                TreeItem<String> selectedItem = TreeViewHandler.getTreeView()
                            .getSelectionModel().getSelectedItem();
                if(selectedItem == null || !admin.getUsers().containsKey(selectedItem.getValue())) {
                    dialog.setContentText("Please select a User to open User View Window.");
                    dialog.getDialogPane().getButtonTypes().add(type);
                    dialog.showAndWait();
                } else {
                    User newUser = admin.getUsers().get(selectedItem.getValue());
                    UserView userWindow = new UserView(newUser); 
                    Scene scene = new Scene(userWindow.getPane(),600,550);
                    Stage stage = new Stage();
                    
                    stage.setTitle("User View");
                    stage.setScene(scene);
                    //Fill stage with content
                    stage.show();               
                }
                
            }
        });
        
         // Bottom btton groups
        HBox hbButtons1 = new HBox();
        hbButtons1.setPadding(new Insets(10, 10, 10, 10));
        hbButtons1.setSpacing(20);
        Button showUserTotal = new Button("Show User Total");
        Button showGroupTotal = new Button("Show Group Total");
        hbButtons1.getChildren().addAll(showUserTotal, showGroupTotal);
        hbButtons1.setAlignment(Pos.CENTER_LEFT);

        showUserTotal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                //Creating a dialog
                Dialog<String> dialog = new Dialog<String>();
                //Setting the title
                dialog.setTitle("Notice");
                ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                
                int sum = 0;
                ShowUserTotalVisitor visitor = new ShowUserTotalVisitor();
                        
                for(User user : admin.getUsers().values()) 
                    sum += user.accept(visitor);
               
                dialog.setContentText("Total number of user(s):   " + sum);
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            }
        });
        
        showGroupTotal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                //Creating a dialog
                Dialog<String> dialog = new Dialog<String>();
                //Setting the title
                dialog.setTitle("Notice");
                ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                
                int sum = 0;
                ShowGroupTotalVisitor visitor = new ShowGroupTotalVisitor();
                Iterator iterator = admin.getGroups().iterator();
                
                while(iterator.hasNext()) {
                    UserGroup group = (UserGroup) iterator.next();
                      sum += group.accept(visitor);   
                }
               
                dialog.setContentText("Total number of group(s):   " + sum);
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            }
        });
 
        HBox hbButtons2 = new HBox();
        hbButtons2.setPadding(new Insets(10, 10, 10, 10));
        hbButtons2.setSpacing(20);
        Button showMsgTotal = new Button("Show Messages Total");
        
        showMsgTotal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                //Creating a dialog
                Dialog<String> dialog = new Dialog<String>();
                //Setting the title
                dialog.setTitle("Notice");
                ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                
                int sum = 0;
                
                for(User user : admin.getUsers().values()) {
                    sum += user.getUserMessages().size();
                }
               
                dialog.setContentText("Total number of message(s):   " + sum);
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            }
        });
        
        Button showPos = new Button("Show Posititve Percentage");
        hbButtons2.getChildren().addAll(showMsgTotal, showPos);
        hbButtons2.setAlignment(Pos.CENTER_LEFT);

        
        VBox vbButtons = new VBox();
        vbButtons.getChildren().addAll(hbButtons1, hbButtons2);
        bp.setBottom(vbButtons); 
       

        bp.setLeft(grid); 
        
        
        return bp;
     }
    
    public static TreeView<String> setLeftPane() {
        TreeView<String> groupTreeView ;
        TreeItem<String> root = TreeViewHandler.getInstance();
       
        root.setExpanded(true);
        
        groupTreeView = TreeViewHandler.getTreeView();
        groupTreeView.setShowRoot(true);
        
        return groupTreeView;
    }
    
    
}
