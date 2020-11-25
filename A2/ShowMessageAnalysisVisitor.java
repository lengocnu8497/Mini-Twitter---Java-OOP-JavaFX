package A2;

import java.util.HashSet;
import java.util.Set;

public class ShowMessageAnalysisVisitor implements Visitor {

    private Set<String> posWords = new HashSet<String>() {
        {
            add("absolutely");add("beautiful");add("beneficial");add("peace");add("believe");add("celebrated");add("certain");add("champion");
            add("delight");add("distinguished");add("divine");add("easy");add("effective");add("fabulous");add("famous");add("beautiful");
            add("cool");add("cute");add("gorgeous");add("honest");add("impressive");add("kind");add("lovely");add("nice");
        }
    };
    
    @Override
    public int visit(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int visit(UserGroup userGroup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int visit(MessageAnalysis analysis) {
        AdminController admin = AdminController.getInstance();
        int totalMsg = 0;
        int totalPosMsg = 0;
        
        for(User user : admin.getUsers().values()) {
            totalMsg += user.getUserMessages().size();
            System.out.println("totalmsg " + totalMsg);
            for(String msg : user.getUserMessages()) {
                System.out.println("msg " + msg);
                for(String word : msg.split(" ")) {
                    System.out.println("word: " + word);
                    if(posWords.contains(word)) {
                        totalPosMsg++;
                        System.out.println("posword: " + totalPosMsg);
                        break;
                    }
                }
            }
        }   
        
        double percentage = totalMsg == 0 ? 0 : ((double)totalPosMsg/totalMsg)*100;
 
        return (int) percentage;
    }
    
}
