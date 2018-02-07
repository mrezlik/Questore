package controller;

import java.util.List;

import dao.ItemDao;
import dao.StudentDao;
import dao.TransactionDao;
import model.ItemModel;
import view.StudentView;
import model.StudentModel;


public class StudentController {

    private StudentView view;
    private InputController inputController;

    protected StudentController() {
        view = new StudentView();
        inputController = new InputController();
    }

    private StudentModel getStudent(int idLogin) {
        StudentDao studentDao = new StudentDao();
        return studentDao.getStudentsCollection().get(0);
    }

    private ItemModel selectArtifact() {
        ItemDao itemDao = new ItemDao();
        List<ItemModel> itemCollection  = itemDao.getItemCollectionByType("Artifact");
        view.displayCollectionOfItem(itemCollection);
        int idArtifact = inputController.getIntInput("Enter artifact id to buy: ");
        ItemModel matchedArtifact = null;
        for (ItemModel artifact: itemCollection)
            if(artifact.getID() == idArtifact)
                matchedArtifact = artifact;
        return matchedArtifact;
    }

    private void buyArtifact(StudentModel student) {
        ItemModel artifact = selectArtifact();
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.insertTransaction(student.getID(), artifact.getID());
    }

    public void controlMenuOptions(int loginId) {
        StudentModel student = getStudent(loginId);
        boolean exit = false;
        while (!exit) {
            view.displayStudentMenu();
            int userChoice = inputController.getIntInput("SELECT AN OPTION: ");
            switch (userChoice) {
                case 1:
                    //view.displayWallet();
                    break;
                case 2:
                    buyArtifact(student);
                    break;
                case 3:
                    //Buy artifact together with teammates; CHYBA TNIEMY
                    break;
                case 4:
                    //4 - See your level of experience\n"; TEŻ MIELIŚMY CIĄĆ
                    break;                                                         
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Wrong number!");
            }
        }
    }
}