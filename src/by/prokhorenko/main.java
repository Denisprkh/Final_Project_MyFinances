package by.prokhorenko;

import by.prokhorenko.bean.transaction.TransactionType;
import by.prokhorenko.controller.Controller;
import by.prokhorenko.controller.command.impl.Registration;
import by.prokhorenko.controller.exception.ControllerException;
import by.prokhorenko.dao.exception.DAOException;
import by.prokhorenko.dao.exception.InvalidFieldException;
import by.prokhorenko.dao.exception.InvalidParameterException;
import by.prokhorenko.service.exception.ServiceException;
import by.prokhorenko.util.exception.UtilException;
import by.prokhorenko.util.scanner.ScannerUtil;

import java.text.ParseException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws ControllerException {

        Controller controller = new Controller();

        String userLogin = null;

        int viewCommand;

        do{
            if(userLogin == null){
                System.out.println("Enter 1 to register \nEnter 2 to sign in\nEnter 0 to finish ");
            }else{
                System.out.println("Enter 3 to add transaction\nEnter 4 to show all expenses\nEnter 5 to show all incomes");
            }

            viewCommand = ScannerUtil.getIntFromConsol();
            switch (viewCommand){
                case 1:
                    System.out.println("Enter login");
                    String loginRegistration = ScannerUtil.getStrFromConsol();
                    System.out.println("Enter password");
                    String passwordRegistration = ScannerUtil.getStrFromConsol();

                    String request = "registration login=" + loginRegistration + " password=" + passwordRegistration;

                        System.out.println(controller.executeTask(request));
                        userLogin = loginRegistration;

                    break;
                case 2:
                    System.out.println("Enter login");
                    String loginSigningIn = ScannerUtil.getStrFromConsol();
                    System.out.println("Enter password");
                    String passwordSigningIn = ScannerUtil.getStrFromConsol();

                    String requestSigningIn = "sign_in login=" + loginSigningIn + " password=" + passwordSigningIn;

                        System.out.println(controller.executeTask(requestSigningIn));
                        userLogin = loginSigningIn;


                    break;
                case 3:
                    System.out.println("Enter type of transaction: income or expense");
                    String type = ScannerUtil.getStrFromConsol();
                    System.out.println("Enter amount");
                    String amount = ScannerUtil.getStrFromConsol();
                    System.out.println("Enter the date of your transaction in format: dd-mm-yyyy");
                    String date = ScannerUtil.getStrFromConsol();
                    System.out.println("Enter the comment");
                    String comment = ScannerUtil.getStrFromConsol();

                    String requestAddTrans = "add_transaction login=" + userLogin + " type=" + type + " amount=" +
                            amount + " date=" + date + " comment=" + comment;

                        System.out.println(controller.executeTask(requestAddTrans));

                    break;
                case 4:
                    String requestExpense = "get_all_expenses login=" + userLogin;
                    System.out.println(controller.executeTask(requestExpense));
                    break;
                case 5:
                    String requestIncomes = "get_all_incomes login=" + userLogin;
                    System.out.println(controller.executeTask(requestIncomes));
                    break;


            }

        }while(viewCommand !=0);


    }

}

