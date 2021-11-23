package com.oop.moneymanager;

public class AppConst {
    public static class CATEGORY_TYPE{
        public static Integer EXPENSE = 0;
        public static Integer INCOME = 1;
    }

//    public static class RANGE_TIME{
//        public static Integer DAY = 0;
////        public static Integer WEEK = 1;
//        public static Integer MONTH = 1;
//        public static Integer YEAR = 2;
//        public static Integer ALL = 3;
//    }

    public static enum RANGE_TIME{
        DAY,MONTH,YEAR,ALL
    }
    public static class ACCOUNT_MODE{
        public static Integer ADD = 0;
        public static Integer DEL = 1;
    }
}
