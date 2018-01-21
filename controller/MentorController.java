package queststore.controller;

import queststore.view.MentorView;
import queststore.model.StudentModel;
import queststore.model.WalletModel;
import queststore.controller.InputController;
import queststore.model.GroupModel;
import queststore.model.ItemModel;
import queststore.model.QuestModel;
import queststore.model.ArtifactModel;

import java.util.List;
import java.util.ArrayList;


public class MentorController {

    private MentorView view;
    private InputController inputController;

    public MentorController() {
        view = new MentorView();
        inputController = new InputController();
    }

    public void controlMenuOptions() {
        boolean exit = false;
        while (!exit) {
            view.displayMentorMenu();
            int userChoice = inputController.getIntInput("SELECT AN OPTION: ");
            switch (userChoice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    createQuest();
                    break;
                case 3:
                    createArtifact();
                    break;
                case 4:
                    changePriceOfItem();
                    break;
                case 5:
                    changePriceOfItem();
                    break; 
                case 6:
                    // Mark student's achieved quests\n"
                    break;  
                case 7:
                    // Mark student's bought artifacts\n"
                    break;    
                case 8:
                    // See student's wallet\n"
                    break;                                                            
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Wrong number!");
            }
        }
    }

    public GroupModel selectGroup() { //HARDCODED
        return new GroupModel("A");
    }

    public void createStudent() {
        String studentName = inputController.getStringInput("Enter student name: ");
        String studentLastName = inputController.getStringInput("Enter student last name: ");
        String studentEmail = inputController.getStringInput("Enter student email: ");
        String studentPassword = inputController.getStringInput("Enter student password: ");
        GroupModel group = selectGroup();
        WalletModel wallet = new WalletModel();
        StudentModel newStudent = new StudentModel(studentName, studentLastName, studentEmail, studentPassword, group, wallet);
    }

    public void createQuest() {
        String questName = inputController.getStringInput("Enter quest name: ");
        String questDescription = inputController.getStringInput("Enter quest description: ");
        int questValue = inputController.getIntInput("Enter quest value: ");
        QuestModel newQuest = new QuestModel("Quest", questName, questDescription, questValue);        
    }

    public void createArtifact() {
        String artifactName = inputController.getStringInput("Enter artifact name: ");
        String artifactDescription = inputController.getStringInput("Enter artifact description: ");
        int artifactValue = inputController.getIntInput("Enter artifact value: ");
        ArtifactModel newArtifact = new ArtifactModel("Artifact", artifactName, artifactDescription, artifactValue);        
    }

    private ItemModel selectItem() {
        List<ItemModel> itemCollection = ItemModel.getItemCollection();
        view.displayItemCollection(itemCollection);
        String chosenType = inputController.getStringInput("Enter Type of item: ");
        String chosenName = inputController.getStringInput("Enter name of item: ");
        ItemModel matchedItem = null;
        for (ItemModel item: itemCollection) 
            if (item.getType().equals(chosenType) && item.getName().equals(chosenName))
                matchedItem = item;
        return matchedItem;
    }

    public void changePriceOfItem() {
        ItemModel item = selectItem();
        int newPrice = inputController.getIntInput("Enter new price: ");
        item.setValue(newPrice);
    }   
}