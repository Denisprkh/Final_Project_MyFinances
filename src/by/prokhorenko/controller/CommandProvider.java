package by.prokhorenko.controller;

import by.prokhorenko.controller.command.CommandName;
import by.prokhorenko.controller.command.Command;
import by.prokhorenko.controller.command.impl.*;
import by.prokhorenko.controller.exception.ControllerException;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.ADD_TRANSACTION, new AddTransaction());
        repository.put(CommandName.GET_ALL_TRANSACTIONS_OF_CERTAIN_TYPE,new GetAllTransactionsOfCertainType());
        repository.put(CommandName.GET_SUM_OF_TRANSACTIONS_OF_CERTAIN_TYPE, new GetSumOfTransactionsOfCertainType());
        repository.put(CommandName.GET_ALL_TRANSACTIONS, new GetAllTransactions());
        repository.put(CommandName.GET_ALL_TRANSACTIONS_IN_A_PERIOD, new GetAllTransactionsInAPeriod());
        repository.put(CommandName.GET_ALL_TRANSACTIONS_OF_CERTAIN_TYPE_IN_A_PERIOD,
                new GetAllTransactionsOfCertainTypeInAPeriod());
        repository.put(CommandName.LOG_OUT,new LogOut());
        repository.put(CommandName.GET_BALANCE, new GetBalance());
    }

    public Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (Exception e) {
            String mes = "Getting command error";

        }
        return command;
    }
}
