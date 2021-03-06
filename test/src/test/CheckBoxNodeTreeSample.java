package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import sun.security.jca.GetInstance;

public class CheckBoxNodeTreeSample extends JTree {
  public static void main(String args[]) {
    JFrame frame = new JFrame("CheckBox Tree");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
	//JPanel JTreePanel = new JPanel();
	
//    MyCheckBoxNode MyNode1[] = {
//        new MyCheckBoxNode("entry 1.2, true", true) };
//    
//    MyCheckBoxNode MyNode2[] = {
//        new MyCheckBoxNode("entry 2.1, true", true),
//        new MyCheckBoxNode("entry 2.2, false", false),
//        new MyCheckBoxNode("entry 2.3, true", true) };
    
    MyCheckBoxNode test = new MyCheckBoxNode("enroll", false);
   
//    Vector MyVector01 = new NamedVector("Kursname 01", MyNode1);
//    Vector MyVector02 = new NamedVector("Kursname 02", MyNode2);
    //Vector MyVectorTest = new NamedVector("Kursname 03", test);

    NamedVector MyNamedVectorArray[] = new NamedVector[20];
    for (int i = 0; i < MyNamedVectorArray.length; i++) {
		MyNamedVectorArray[i] = new NamedVector("Kursname "+i, test);
	}

    //Object rootNodes[] = { MyVector01, MyVector02 };
    Object rootNodes[] = MyNamedVectorArray;

    Vector rootVector = new NamedVector("Root", rootNodes);
    
    JTree MyTree = new JTree(rootVector);

    CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
    MyTree.setCellRenderer(renderer);

    MyTree.setCellEditor(new CheckBoxNodeEditor(MyTree));
    MyTree.setEditable(true);

    JScrollPane scrollPane = new JScrollPane(MyTree);
    //frame.add(JTreePanel);
    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    frame.setSize(300,400);
    frame.setVisible(true);
    //JTreePanel.add(scrollPane);
  }
}

class CheckBoxNodeRenderer implements TreeCellRenderer {
  private JCheckBox leafRenderer = new JCheckBox();

  private DefaultTreeCellRenderer nonLeafRenderer = new DefaultTreeCellRenderer();

  Color selectionBorderColor, selectionForeground, selectionBackground,
      textForeground, textBackground;

  protected JCheckBox getLeafRenderer() {
    return leafRenderer;
  }

  public CheckBoxNodeRenderer() {
    Font fontValue;
    fontValue = UIManager.getFont("Tree.font");
    if (fontValue != null) {
      leafRenderer.setFont(fontValue);
    }
    Boolean booleanValue = (Boolean) UIManager
        .get("Tree.drawsFocusBorderAroundIcon");
    leafRenderer.setFocusPainted((booleanValue != null)
        && (booleanValue.booleanValue()));

    selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
    selectionForeground = UIManager.getColor("Tree.selectionForeground");
    selectionBackground = UIManager.getColor("Tree.selectionBackground");
    textForeground = UIManager.getColor("Tree.textForeground");
    textBackground = UIManager.getColor("Tree.textBackground");
  }

  public Component getTreeCellRendererComponent(JTree tree, Object value,
      boolean selected, boolean expanded, boolean leaf, int row,
      boolean hasFocus) {

    Component returnValue;
    if (leaf) {

      String stringValue = tree.convertValueToText(value, selected,
          expanded, leaf, row, false);
      leafRenderer.setText(stringValue);
      leafRenderer.setSelected(false);

      leafRenderer.setEnabled(tree.isEnabled());

      if (selected) {
        leafRenderer.setForeground(selectionForeground);
        leafRenderer.setBackground(selectionBackground);
      } else {
        leafRenderer.setForeground(textForeground);
        leafRenderer.setBackground(textBackground);
      }

      if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
        Object userObject = ((DefaultMutableTreeNode) value)
            .getUserObject();
        if (userObject instanceof MyCheckBoxNode) {
          MyCheckBoxNode node = (MyCheckBoxNode) userObject;
          leafRenderer.setText(node.getText());
          leafRenderer.setSelected(node.isSelected());
        }
      }
      returnValue = leafRenderer;
    } else {
      returnValue = nonLeafRenderer.getTreeCellRendererComponent(tree,
          value, selected, expanded, leaf, row, hasFocus);
    }
    return returnValue;
  }
}

class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {

  CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();

  ChangeEvent changeEvent = null;

  JTree tree;

  public CheckBoxNodeEditor(JTree tree) {
    this.tree = tree;
  }

  public Object getCellEditorValue() {
    JCheckBox checkbox = renderer.getLeafRenderer();
    MyCheckBoxNode checkBoxNode = new MyCheckBoxNode(checkbox.getText(),
        checkbox.isSelected());
    return checkBoxNode;
  }

  public boolean isCellEditable(EventObject event) {
    boolean returnValue = false;
    if (event instanceof MouseEvent) {
      MouseEvent mouseEvent = (MouseEvent) event;
      TreePath path = tree.getPathForLocation(mouseEvent.getX(),
          mouseEvent.getY());
      if (path != null) {
        Object node = path.getLastPathComponent();
        if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
          DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
          Object userObject = treeNode.getUserObject();
          returnValue = ((treeNode.isLeaf()) && (userObject instanceof MyCheckBoxNode));
        }
      }
    }
    return returnValue;
  }

  public Component getTreeCellEditorComponent(JTree tree, Object value,
      boolean selected, boolean expanded, boolean leaf, int row) {

    Component editor = renderer.getTreeCellRendererComponent(tree, value,
        true, expanded, leaf, row, true);

    // editor always selected / focused
    ItemListener itemListener = new ItemListener() {
      public void itemStateChanged(ItemEvent itemEvent) {
        if (stopCellEditing()) {
          fireEditingStopped();
        }
      }
    };
    if (editor instanceof JCheckBox) {
      ((JCheckBox) editor).addItemListener(itemListener);
    }

    return editor;
  }
}

class MyCheckBoxNode {
  String text;
  boolean selected;

  public MyCheckBoxNode(String text, boolean selected) {
    this.text = text;
    this.selected = selected;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean newValue) {
    selected = newValue;
  }

  public String getText() {
    return text;
  }

  public void setText(String newValue) {
    text = newValue;
  }

  public String toString() {
    return getClass().getName() + "[" + text + "/" + selected + "]";
  }
}



//public Category(int max){ . . . }
//Category [] categories = new Category[4];
//categories[0] = new Category(10)


class NamedVector extends Vector {
  String name;

  public NamedVector(String name) {
    this.name = name;
  }

  public NamedVector(String name, Object elements[]) {
	  this.name = name;
	  for (int i = 0, n = elements.length; i < n; i++) {
      add(elements[i]);
    }
  }

  public NamedVector(String name, Object element) {
	    this.name = name;
	    add(element);
	  }
  
  public String toString() {
    return "[" + name + "]";
  }
}