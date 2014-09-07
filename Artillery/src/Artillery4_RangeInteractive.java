import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class Artillery4_RangeInteractive extends JFrame {
	private JLabel angleL, speedL;
	private JScrollBar angle, speed;
	private JButton button;
	private Artillery3_RangeAnimated ani;
	
	public Artillery4_RangeInteractive() {
		super("Artillery");
		setSize(800, 650);
		setResizable(false);
		
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
		c.setBackground(new Color(255,255,255));
		
		ani = new Artillery3_RangeAnimated();
		c.add(ani, BorderLayout.NORTH);
		
		angle = new JScrollBar(JScrollBar.HORIZONTAL,0,0,0,700);
		angleL = new JLabel("Angle: " + angle.getValue());
		c.add(angleL);
		c.add(angle);
		angle.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				angleL.setText("Angle: " + angle.getValue());
			}
		});
		
		speed = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 100);
		speedL = new JLabel("Speed: " + speed.getValue());
		c.add(speedL);
		c.add(speed);
		speed.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				speedL.setText("Speed: " + speed.getValue());
			}
		});
		
		button = new JButton("GO!");
		c.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button) {
					ani.setVars(angle.getValue(), speed.getValue());
				}
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		ani.init();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Artillery4_RangeInteractive();
	}
}
