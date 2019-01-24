import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Controller {
    //private Model m;
    private GuiHashTable g;
    private Model m;
    
    public Controller(GuiHashTable g, Model m){
        this.g = g;
        this.m = m;
        g.getResetButton().addActionListener(e->reset());
        g.getAddButton().addActionListener(e->add());
        g.getRemoveButton().addActionListener(e->remove());
    }
    
    private void reset(){
        JTable tableView = g.getTable();

        tableView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {0, null, null, null, null},
                {1, null, null, null, null},
                {2, null, null, null, null},
                {3, null, null, null, null},
                {4, null, null, null, null},
                {5, null, null, null, null},
                {6, null, null, null, null},
                {7, null, null, null, null},
                {8, null, null, null, null},
                {9, null, null, null, null},
                {10, null, null, null, null},
                {11, null, null, null, null},
                {12, null, null, null, null},
                {13, null, null, null, null},
                {14, null, null, null, null},
                {15, null, null, null, null},
                {16, null, null, null, null},
                {17, null, null, null, null},
                {18, null, null, null, null},
                {19, null, null, null, null}
            },
            new String [] {
                "Position", "Key1", "Key2", "Key3", "Key4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
    tableView.getColumn("Position").setCellRenderer( rightRenderer );
    tableView.getTableHeader().setReorderingAllowed(false);

    g.getPane().setViewportView(tableView);
    }
    
    private void add(){
        String hash = g.getHashFunction().getSelectedItem().toString();
        String input = g.getInput().getText();
        
        int index = m.getIndex(input, hash);
        System.out.println("Index add: " + index);
        if(input.equals("")){
            JOptionPane.showMessageDialog(g, "Please enter a value" , "Warning", JOptionPane.INFORMATION_MESSAGE);
        }else if(g.getCheckChaining().isSelected()){
            boolean check = true;
            int column = 1;
            while(column < 5){
                Object valueAtIndex = g.getTable().getModel().getValueAt(index, column);
                if(valueAtIndex == null){
                    g.getTable().setValueAt(input, index, column);
                    check = false;
                    break;
                }else{
                    column++;
                }
            }
            
            if(check){
                JOptionPane.showMessageDialog(g, "Out of space to chain at index: " + String.valueOf(index) , "Warning", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }else{
            Object valueAtIndex = g.getTable().getModel().getValueAt(index, 1);
            if(valueAtIndex == null){
                g.getTable().setValueAt(input, index, 1);
            }else{
                JOptionPane.showMessageDialog(g, "Hashed to the same place!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    private void remove(){
        String hash = g.getHashFunction().getSelectedItem().toString();
        String input = g.getInput().getText();
        
        int index = m.getIndex(input, hash);
        System.out.println("Index remove: " + index);
        
        if(input.equals("")){
            JOptionPane.showMessageDialog(g, "Please enter a value" , "Warning", JOptionPane.INFORMATION_MESSAGE);
        }else if(g.getCheckChaining().isSelected()){
            boolean check = true;
            int column = 1;
            while(column < 5){
                Object valueAtIndex = g.getTable().getModel().getValueAt(index, column);
                if(valueAtIndex.equals(input)){
                    g.getTable().setValueAt(null, index, column);
                    check = false;
                    break;
                }else{
                    column++;
                }
            }
            
            if(check){
                JOptionPane.showMessageDialog(g, "Value not found in table!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }else{
            Object valueAtIndex = g.getTable().getModel().getValueAt(index, 1);
            if(valueAtIndex != null && valueAtIndex.equals(input)){
               g.getTable().setValueAt(null, index, 1); 
            }else{
                JOptionPane.showMessageDialog(g, "Value is not present in table!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
