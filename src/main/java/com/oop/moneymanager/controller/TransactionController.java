package com.oop.moneymanager.controller;

import com.oop.moneymanager.AppConst;
import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.model.Category;
import com.oop.moneymanager.model.Transaction;
import com.oop.moneymanager.model.dao.ITransactionDAO;
import com.oop.moneymanager.model.dao.MysqlImp.TransactionDAO;
import javafx.scene.chart.PieChart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionController {
    private final ITransactionDAO transactionDAO;
    private Account account;
    private CategoryController categoryController;
    private List<Transaction> transactions;
    public TransactionController(){
        this.transactionDAO = new TransactionDAO();
    }
    public TransactionController(Account account){
        this.transactionDAO = new TransactionDAO();
        this.setAccount(account);
        this.categoryController = new CategoryController();
    }

    public List<Transaction> getAll(){
        return transactionDAO.getByAccountId(this.account.getId());
    }

    public void setAccount(Account account){
        this.account = account;
        if(this.account !=null) {
            this.transactions = transactionDAO.getByAccountId(this.account.getId());
        }
    }

    public void add(Transaction transaction){
        transaction.setAccount(this.account);
        transactionDAO.save(transaction);
        this.transactions.add(transaction);
    }
    public void delete(Transaction transaction){
        transaction.setAccount(this.account);
        transactionDAO.delete(transaction);
        this.transactions.remove(transaction);
    }
    public void update(Transaction transaction){
        transaction.setAccount(this.account);
        transactionDAO.update(transaction);
        Transaction transactionToUpdate = this.transactions.stream().filter(t->t.equals(transaction)).toList().get(0);
        if (transactionToUpdate!=null) transactionToUpdate.copy(transaction);
    }

    public List<Transaction> getByRangeTime(LocalDate startTime, LocalDate endTime){

        return null;
    }
    public List<Transaction> listTransactionsFilter(AppConst.RANGE_TIME rangeTime,LocalDate currentTime){
        List<Transaction> list = new ArrayList<Transaction>();
        for(Transaction transaction : transactions){
            LocalDate time = transaction.getTime().toLocalDate();
            if(rangeTime.equals(AppConst.RANGE_TIME.ALL)){
                return transactions;
            }else if(rangeTime.equals(AppConst.RANGE_TIME.DAY)){
                if(currentTime.equals(time)){
                    list.add(transaction);
                }
            }else if(rangeTime.equals(AppConst.RANGE_TIME.MONTH)){
                if(currentTime.getMonth().equals(time.getMonth())){
                    if(currentTime.getYear()== time.getYear()){
                        list.add(transaction);
                    }
                }
            }else if(rangeTime.equals(AppConst.RANGE_TIME.YEAR)){
                if(currentTime.getYear()== time.getYear()){
                    list.add(transaction);
                }
            }
        }
        return list;
    }

    public Integer sumTransaction(Integer mode,AppConst.RANGE_TIME rangeTime,LocalDate currentTime){
        int sum = 0;
        List<Transaction> list = listTransactionsFilter(rangeTime,currentTime);
        for(Transaction transaction : list){
                if(transaction.getType().equals(mode)) {
                    sum += transaction.getAmount();
                }
        }
        return sum;
    }
    public PieChart.Data getDataByCategory(Category category,List<Transaction> list){
        int sum = 0;
        for(Transaction transaction : list){
            if(transaction.getCategory().equals(category)){
                sum+= transaction.getAmount();
            }
        }
        return new PieChart.Data(category.getName(),sum);
    }

    public List<PieChart.Data> ListPieChartData(Integer mode,AppConst.RANGE_TIME rangeTime,LocalDate currentTime){
        List<Transaction> transactions = this.listTransactionsFilter(rangeTime,currentTime);
        List<PieChart.Data> list = new ArrayList<PieChart.Data>();
        List<Category> categories = this.categoryController.getByType(mode);
        for(Category category : categories){
            list.add(getDataByCategory(category,transactions));
        }
        return list;
    }
}
