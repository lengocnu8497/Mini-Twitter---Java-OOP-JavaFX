package A2;

import java.util.HashSet;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TreeViewHandler {
    private static HashSet<String> ids = new HashSet<>();
    private static TreeItem<String> rootNode = null; 
    private static TreeView<String> treeView = null;
    private Image userIcon = 
        new Image(getClass().getResourceAsStream("userIcon.png"));
    
    
    private TreeViewHandler() {}
    
    public static TreeItem<String> getInstance() {
        if(rootNode == null) {
            rootNode = new TreeItem<>("Root");
        }
        return rootNode;
    }
    
    public static boolean addUserComponent(UserComponent newUserComponent, 
                                            TreeItem<String> selectedItem) 
    {
        String newId;
        boolean isUserGroup = false;
        // TODO case its a user added in a group
        if(isLeaf(newUserComponent)) {
            User user = (User) newUserComponent;
            newId = user.getUID();
        }
        else {
            UserGroup user = (UserGroup) newUserComponent;
            newId = user.getGID();
            isUserGroup = true;
        }
            
        if(ids.add(newId)) {
            rootNode.setExpanded(true);
            TreeItem<String> leaf = isUserGroup? 
                    new TreeItem<String>(newId.toUpperCase()) : new TreeItem<String>(newId);
            leaf.setExpanded(true);
            if(selectedItem == null || selectedItem.equals(rootNode)){
                rootNode.getChildren().add(leaf);
            }
            else {
                for(TreeItem<String> child : rootNode.getChildren()) {
                    if(selectedItem.equals(child)) {
                        selectedItem.getChildren().add(leaf);
                        selectedItem.setExpanded(true);
                        break;
                    }
                }
            }      
            return true;
        }
        
        return false;
    }
    
    public static TreeView<String> getTreeView() {
        if(treeView == null)
            treeView = new TreeView<>(rootNode);
        
        return treeView;
    }
    
    public static boolean isLeaf(UserComponent newUserComponent){
        if(newUserComponent instanceof User) 
            return true;
        return false;
    }
}
