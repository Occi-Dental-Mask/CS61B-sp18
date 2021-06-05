/**
	a class to run your simulation
*/
public class NBody{
	public static double readRadius(String file_name){
		In in = new In(file_name);//How to get access to the txt file
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String file_name){
		In in = new In(file_name);
		int num = in.readInt();
		double radius = in.readDouble();
		int i = 0;
		Planet[] planets = new Planet[num];
		while(i < num){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			Planet pl = new Planet(xP, yP, xV, yV, m, img);
			planets[i] = pl;
			i += 1;
		}
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius =  readRadius(filename);
		Planet[] planets = readPlanets(filename);
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(-radius,radius);
		StdDraw.setYscale(-radius,radius);
		double time = 0.0;
		int len = planets.length;
		int i;
		while(time < T){
			double xForces[] = new double[len];
			double yForces[] = new double[len];
			for (i=0;i<len;i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(i=0;i<len;i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0.0,0.0,"images/starfield.jpg");
			for (i=0;i<len;i++){
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            	planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}//final printing
	}
}