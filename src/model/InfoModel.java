package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entities.Info;

public class InfoModel extends AbstractTableModel{

    private List<Info> data = new ArrayList<>();
    private String[] colum = {"Word","Key"};
    private int line; 

    @Override
    public String getColumnName(int column) {
        return colum[column]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return colum.length;
    }

    public int getLines() {
        return this.line;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {

        switch(coluna){
            case 0:
                return data.get(linha).getWord();
            case 1:
                return data.get(linha).getKey();
        }

        return null;

    }


    public void addRow(Info p){
        line++;
        this.data.add(p);
        this.fireTableDataChanged();
    }

    public void removeRow(int linha){
        line--;
        this.data.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public void clearTable() {
        int size = getRowCount();
        this.data.clear();
        fireTableRowsDeleted(0,size);
    }

}