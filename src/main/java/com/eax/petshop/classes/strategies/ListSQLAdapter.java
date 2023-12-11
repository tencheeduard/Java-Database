package com.eax.petshop.classes.strategies;

import com.eax.petshop.classes.base.Table;

public class ListSQLAdapter extends ListStrategy{

    public MySQLStrategy sqlStrategy;

    public ListSQLAdapter(MySQLStrategy sqlStrategy) throws Exception {
        this.sqlStrategy = sqlStrategy;
        Table[] tables = sqlStrategy.getAll();
        for(Table table: tables)
            add(table);
    }

}
