package com.mh.expensemanager.service;

import com.mh.expensemanager.dto.ExpenseDTO;
import com.mh.expensemanager.entity.Expense;
import com.mh.expensemanager.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // this annotation creates the constructor injection
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    public List<ExpenseDTO> getAllExpenses() {
        List<Expense> entityExpenses = expenseRepository.findAll();
        // it's a best practice to convert Entity Objects into DTOs when returning them to clients
        return entityExpenses.stream().map(this::convertExpenseToExpenseDTO).toList();
    }

    private ExpenseDTO convertExpenseToExpenseDTO(Expense expense) {
        return modelMapper.map(expense, ExpenseDTO.class);
    }
}
