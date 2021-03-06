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
	g.getHashFunction().addActionListener(e->changeHash());
	g.getCheckChaining().addActionListener(e->changeChaining());
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
                "Position", "Key", "Chain1", "Chain2", "Chain3"
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
	// String hash = g.getHashFunction().getSelectedItem().toString();
        String input = g.getInput().getText();
        
        int[] index = m.add(input);// this must be where it hashes
        
        System.out.println("Index add: " + index[0] + " " + index[1]);
        if (index[0] == -1 || index[1] == -1){
	    JOptionPane.showMessageDialog(g, "That hash is full", "Error", JOptionPane.INFORMATION_MESSAGE);
	    return;
	}
	Object valueAtIndex = g.getTable().getModel().getValueAt(index[0], index[1]+1);
        if(input.equals("")){
            JOptionPane.showMessageDialog(g, "Please enter a value to add!" , "Error", JOptionPane.INFORMATION_MESSAGE);
        } else if(valueAtIndex != null){
	    System.err.println(valueAtIndex);
	    JOptionPane.showMessageDialog(g, "Hashed to the same place!", "Error", JOptionPane.INFORMATION_MESSAGE);
	    
	} else {
	    g.getTable().setValueAt(input, index[0], index[1]+1);
	}
		
		
	/*

	else if(g.getCheckChaining().isSelected()){
            boolean check = true;
            int column = 1;
	    index[1] = column;
            while(index[1] < 5){
                Object valueAtIndex = g.getTable().getModel().getValueAt(index[0], index[1]);
                if(valueAtIndex == null){
                    g.getTable().setValueAt(input, index[0], index[1]);
                    check = false;
                    break;
                }else{
                    index[1]++;
                }
            }
            
            if(check){
                JOptionPane.showMessageDialog(g, "Out of space to chain at index: " + String.valueOf(index) , "Warning", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }else{
            Object valueAtIndex = g.getTable().getModel().getValueAt(index[0], index[1]+1);
	    System.err.println(valueAtIndex);
            if(valueAtIndex == null){
                g.getTable().setValueAt(input, index[0], index[1]+1);
            }else{
                JOptionPane.showMessageDialog(g, "Hashed to the same place!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            }
        }*/
    }
    
    private void remove(){
	// String hash = g.getHashFunction().getSelectedItem().toString();
        String input = g.getInput().getText();

        int[] index = m.remove(input);
        System.out.println("Index remove: " + index[0] + " " + index[1]);
	
        if (index[0] == -1 || index[1] == -1){
	    JOptionPane.showMessageDialog(g, "Value '"+input+"' does not exist in the hashtable", "Error", JOptionPane.INFORMATION_MESSAGE);
	    return;
	}
	
	Object valueAtIndex = g.getTable().getModel().getValueAt(index[0], index[1]+1);
	
        if(input.equals("")){
            JOptionPane.showMessageDialog(g, "Please enter a value to remove!" , "Error", JOptionPane.INFORMATION_MESSAGE);
        } else if(valueAtIndex != null){
	     g.getTable().setValueAt(null, index[0], index[1]+1);
	    System.err.println(valueAtIndex);
	    
	    
	} else {
	    JOptionPane.showMessageDialog(g, "Value '" +input+ "' does not exist in hash table", "Error", JOptionPane.INFORMATION_MESSAGE);
	}
        /*if (index[0] == -1 || index[1] == -1){
	    JOptionPane.showMessageDialog(g, "Value is not present in table!", "Warning", JOptionPane.INFORMATION_MESSAGE);
	    return;
	}
        if(input.equals("")){
            JOptionPane.showMessageDialog(g, "Please enter a value" , "Warning", JOptionPane.INFORMATION_MESSAGE);
        }else if(g.getCheckChaining().isSelected()){
            boolean check = true;
            index[1] = 1;
            while(index[1] < 5){
                Object valueAtIndex = g.getTable().getModel().getValueAt(index[0], index[1]);
                if(valueAtIndex.equals(input)){
                    g.getTable().setValueAt(null, index[0], index[1]);
                    check = false;
                    break;
                }else{
                    index[1]++;
                }
            }
            
            if(check){
                JOptionPane.showMessageDialog(g, "Value not found in table!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }else{
            Object valueAtIndex = g.getTable().getModel().getValueAt(index[0], index[1]+1);
            if(valueAtIndex != null && valueAtIndex.equals(input)){
               g.getTable().setValueAt(null, index[0], index[1]+1); 
            }else{
                JOptionPane.showMessageDialog(g, "Value is not present in table!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            }
        } */
    }
    //need a listner for the chaining
    //need a listner for the drop down
    public void changeHash(){
        String option = (String) g.getHashFunction().getSelectedItem();
	m.changeHashing(option);
	System.err.print("Hash function changed to ");
	System.err.println(option);	
	
    }
    public void changeChaining(){
	m.toggleChaining();
	System.err.println(m.isChaining());


    }


    
}
