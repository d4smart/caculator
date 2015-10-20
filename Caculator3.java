package caculator;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Caculator3 extends JFrame implements ActionListener {
	private JTextField text;
	private String output = "";
	static boolean firstFlag = true;
	static boolean secondFlag = false;
	static int len = 0;
	static float op1 = 0;
	static float op2 = 0;
	static String opp = "";

	// 定义字符串数组，为按钮的显示文本赋值
	String str[] = { "Inf", "00","←", "C", "7", "8", "9", "÷", "4", "5", "6",
			"x", "1", "2", "3", "-", "0", ".", "=", "+" };

	public Caculator3() {
		super("简易计算器"); // 继承父类的构造方法

		// 初始化容器
		Container c = this.getContentPane();
		// 设置边界布局
		BorderLayout borderLayout = (BorderLayout) c.getLayout();
		borderLayout.setHgap(40);
		borderLayout.setVgap(20);

		c.setLayout(borderLayout);

		// 设置文本域外观
		text = new JTextField();
		text.setHorizontalAlignment(SwingConstants.TRAILING);
		text.setPreferredSize(new Dimension(12, 50));
		text.setColumns(10);
		text.setEditable(false);
		// c.add(text, BorderLayout.NORTH);

		// 定义面板，并设置为网格布局，5行4列，组件水平、垂直间距均为5
		JPanel table = new JPanel();
		table.setLayout(new GridLayout(5, 4, 5, 5));
		table.setBackground(Color.pink);// 添加颜色
		setLayout(new BorderLayout()); // 定义窗体布局为边界布局
		// 循环定义按钮，并添加到面板中
		for (int i = 0; i < str.length; i++) {
			JButton button = new JButton(str[i]);
			button.addActionListener(this);
			table.add(button);
		}
		
		//将组件添加到容器中
		c.add(text, BorderLayout.NORTH);
		c.add(table, BorderLayout.CENTER);
		
		//外观及功能设置
		setSize(242,304);// 定义大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); // 让窗口居中显示
		// this.setResizable(false);// 不能改变窗体大小
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 设置鼠标形状
		this.setVisible(true);//可见
	}

	public void actionPerformed(ActionEvent e) {
		//按键为数字的判断
		if (e.getActionCommand()=="1" ||   
           e.getActionCommand()=="2" ||   
           e.getActionCommand()=="3" ||   
           e.getActionCommand()=="4" ||   
           e.getActionCommand()=="5" ||   
           e.getActionCommand()=="6" ||   
           e.getActionCommand()=="7" ||   
           e.getActionCommand()=="8" ||   
           e.getActionCommand()=="9" ||   
           e.getActionCommand()=="0" ||   
           e.getActionCommand()=="00" ||   
           e.getActionCommand()==".") {
			output += e.getActionCommand();
			text.setText(output);
		}
		
		//按键为计算符号的判断，同时获取前一个操作数
		if (firstFlag && 
			  (e.getActionCommand() == "+" ||   
		       e.getActionCommand() == "-" ||   
		       e.getActionCommand() == "x" ||   
		       e.getActionCommand() == "÷")) {
			op1 = Float.parseFloat(text.getText());   
            len = text.getText().length();   
            opp = e.getActionCommand();   
            output += e.getActionCommand();   
            text.setText(output);
            firstFlag = false;
            secondFlag = true;
		}
		
		//按键为“=”的判断，同时获取后一个操作数
		if (secondFlag && e.getActionCommand()=="=") {
			op2 = Float.parseFloat(text.getText().substring(len+1));  
			switch (opp) {
			case "+": add(); 		break;
			case "-": minus(); 		break;
			case "x": mutiply(); 	break;
			case "÷": divide();		break;
			}
			text.setText(output);
			op1 = Float.parseFloat(output);
			firstFlag = true;
			secondFlag = false;
		}
		
		//重置按钮
		if (e.getActionCommand()=="C") {
			output = "";
			text.setText(output);
			firstFlag = true;
			secondFlag = false;
			len = 0;
			op1 = 0;
			op2 = 0;
			opp = "";
		}
		
		//回删按钮
		if (e.getActionCommand()=="←") {
			String content = text.getText();
            int remain = content.length(); 
            String last = content.substring(remain-1);
            //回删内容为计算符号的处理
            if (last.equals("+") ||
            	last.equals("-") ||
            	last.equals("x") ||
            	last.equals("÷")) {
            	firstFlag = true;
    			secondFlag = false;
    			len = 0;
    			op1 = 0;
    			op2 = 0;
    			opp = "";
            }
			output = output.substring(0, remain-1);
			text.setText(output);
		}
		
		//作者的相关信息
		if (e.getActionCommand()=="Inf") {
			output = "华中科技大学软件学院1402班 Lv xd";
			text.setText(output);
		}
	}
	
	//加减乘除的操作
	public void add() {
		output = (op1 + op2)+"";
	}
	public void minus() {
		output = (op1 - op2)+"";
	}
	public void mutiply() {
		output = (op1 * op2)+"";
	}
	public void divide() {
		output = (op1 / op2)+"";
	}

	//主动类
	public static void main(String[] args) {
		//加载UI模块
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		//创建实例
		Caculator3 caculator = new Caculator3();
	}
}
