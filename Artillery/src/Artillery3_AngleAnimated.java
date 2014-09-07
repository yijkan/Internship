import processing.core.*;

public class Artillery3_AngleAnimated extends PApplet {
	float range, speed, angle;
	float velX, velY;
	float t, pX, pY, ppX, ppY;

	public void setup() {
		size(800, 500);
		background(255);
	}

	public void setVars(float r, float s) {
		range = r;
		speed = s;
		// range = (speed*cos(angle))*(speed*sin(angle))/(0.5*9.8)
		// range = (0.5)*sin(2*angle)*speed*speed/(0.5*9.8)
		// sin(2*angle) = (9.8)*range/(speed*speed)
		// 2*angle = asin((9.8)*range/(speed*speed))
		angle = (float) Math.asin((9.8) * range / (speed * speed)) / 2;
		// initial x-velocity = speed*cos(angle)
		// initial y-velocity = speed*sin(angle)
		velX = speed * cos(angle);
		velY = speed * sin(angle);

		t = (float) 0.1;
		pX = velX * t;
		pY = velY * t - (float) 0.5 * (float) 9.8 * t * t;
		// x-position = vt
		// y-position = vt - (0.5)(9.8)t^2
		ppX = 0;
		ppY = 0;

		background(255);
	}

	public void draw() {
		fill(0);
		textSize(20);
		text("Range = " + range, 50, 50);
		text("Speed = " + speed, 50, 75);
		// Show range and speed on the screen.

		if (range > (speed * speed) / 9.8) {
			fill(0, 0, 255);
			text("Inputted range is greater than maximum range possible for inputted speed",
					50, 100);
		} else {
			text("Angle = " + angle, 50, 100);
		}

		translate(50, 450);
		scale(1, -1);
		line(-50, 0, 750, 0);
		fill(255, 0, 0);
		ellipse(range, 0, 10, 10);
		// Draw a line at y = 0 and a red circle to show the target.

		fill(0);
		if (pY > 0) {
			line(ppX, ppY, pX, pY);
			ppX = pX;
			ppY = pY;
			t += 0.1;
			pX = velX * t;
			pY = velY * t - (float) 0.5 * (float) 9.8 * t * t;
		}
	}
}
