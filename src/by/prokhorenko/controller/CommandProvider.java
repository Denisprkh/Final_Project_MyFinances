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
        repository.put(CommandName.GET_ALL_EXPENSES, new GetAllExpenses());
        repository.put(CommandName.GET_ALL_INCOMES, new GetAllIncomes());
        repository.put(CommandName.GET_SUM_OF_ALL_EXPENSES, new GetSumOfAllExpenses());
        repository.put(CommandName.GET_ALL_TRANSACTIONS, new GetAllTransactions());
        repository.put(CommandName.GET_SUM_OF_ALL_INCOMES, new GetSumOfAllIncomes());
    }

    public Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (Exception e) {
            String mes = "Getting_command_error";

        }
        return command;
    }
}
