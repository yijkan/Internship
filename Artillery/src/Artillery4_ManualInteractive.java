import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Artillery4_ManualInteractive extends JFrame {
	private JLabel rangeL, angleL, speedL;
	private JScrollBar range, angle, speed;
	private JButton button;
	private Artillery3_ManualAnimated ani;
	
	public Artillery4_ManualInteractive() {
		super("Artillery");
		setSize(800, 650);
		setResizable(false);
		
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
		c.setBackground(new Color(255,255,255));
		
		ani = new Artillery3_ManualAnimated();
		c.add(ani, BorderLayout.NORTH);
		
		
		range = new JScrollBar(JScrollBar.HORIZONTAL,0,0,0,700);
		rangeL = new JLabel("Target distance: " + range.getValue());
		c.add(rangeL);
		c.add(range);
		range.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				rangeL.setText("Target distance: " + range.getValue());
			}
		});
		
		angle = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 90);
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
					ani.setVars(range.getValue(), angle.getValue(), speed.getValue());
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
		new Artillery4_ManualInteractive();
	}
}
