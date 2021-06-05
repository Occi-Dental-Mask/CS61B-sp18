public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP,double yP, double xV, double yV, double m, String img){
    	xxPos = xP;
    	yyPos = yP;
    	xxVel = xV;
    	yyVel = yV;
    	mass = m;
    	imgFileName = img;
    }
    public Planet(Planet p){
    	this.xxPos = p.xxPos;
    	this.yyPos = p.yyPos;
    	this.xxVel = p.xxVel;
    	this.yyVel = p.yyVel;
    	this.mass = p.mass;
    	this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet rocinante){
    	return Math.sqrt((this.xxPos - rocinante.xxPos) * (this.xxPos - rocinante.xxPos) + (this.yyPos - rocinante.yyPos)*(this.yyPos - rocinante.yyPos));
    }

    public double  calcForceExertedBy(Planet rocinante){
    	double r_2 = this.calcDistance(rocinante) * this.calcDistance(rocinante);
    	double m1 = this.mass;
    	double m2 = rocinante.mass;
    	return 6.67e-11 * m1 * m2 /r_2;
    }

    public double calcForceExertedByX(Planet rocinante){
    	double F = this.calcForceExertedBy(rocinante);
    	return F * (rocinante.xxPos - this.xxPos)/this.calcDistance(rocinante);
    }

    public double calcForceExertedByY(Planet rocinante){
    	double F = this.calcForceExertedBy(rocinante);
    	return F * (rocinante.yyPos - this.yyPos)/this.calcDistance(rocinante);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
    	int i;
    	double netx = 0.0;
    	for (i = 0;i < allPlanets.length;i++){
    		if (this.equals(allPlanets[i])){
    			continue;
    		}
    		netx += this.calcForceExertedByX(allPlanets[i]);
    	}
    	return netx;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
    	int i;
    	double nety = 0.0;
    	for (i = 0;i < allPlanets.length;i++){
    		if (this.equals(allPlanets[i])){
    			continue;
    		}
    		nety += this.calcForceExertedByY(allPlanets[i]);
    	}
    	return nety;
    }

    public void update(double dt, double fx, double fy){
    	double ax = fx/this.mass;
    	double ay = fy/this.mass;
    	this.xxVel += dt * ax;
    	this.yyVel += dt * ay;
    	this.xxPos += dt * this.xxVel;
    	this.yyPos += dt * this.yyVel;
    }

    public void draw(){
    	StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }
}