
public class Planet {
	
	/**
	 * initial variables of a planet 
	 * xxPos -> position of a planet in X direction
	 * yyPos -> position of a planet in Y direction
	 * xxVel -> velocity of a planet in X direction
	 * yyVel -> velocity of a planet in Y direction
	 * mass -> Mass of a planet 
	 **/
	
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	
	/**
	 *Constructor of Planet  
	 **/
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	/**
	 *Second Constructor which takes a planet object  
	 **/
	
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	
	/**
	 *Calculating pairwise distance of two planets using r^2=dx^2+dy^2
	 *dx = (p1X - p2X) 
	 *where,
	 *p1X -> Position of Planet 1 in X direction 
	 *p2X -> Position of Planet 2 in X direction 
	 *
	 *dy = (p1Y - p2Y) 
	 *where,
	 *p1Y -> Position of Planet 1 in Y direction 
	 *p2Y -> Position of Planet 2 in Y direction 
	 **/
	public double calcDistance(Planet p) {
		double dis = Math.sqrt( ((xxPos - p.xxPos) * (xxPos - p.xxPos)) + ((yyPos - p.yyPos) * (yyPos - p.yyPos)));
		
		if(dis == 0) return 1;
		else return dis;
	}
	
	/**
	 * Calculating Total force of two planets
	 * By using Newton's gravitational formula
	 * F = (G*m1*m2)/r^2 
	 **/
	
	public double calcForceExertedBy(Planet p) {
		
		final double G = 6.67e-11;
		
		return (G*p.mass*mass)/(calcDistance(p)*calcDistance(p));
		
	}
	
	public double calcForceExertedByX(Planet p) {
		return (calcForceExertedBy(p) * (p.xxPos - xxPos) )/calcDistance(p);
	}
	
	public double calcForceExertedByY(Planet p) {
		return (calcForceExertedBy(p) * (p.yyPos - yyPos) )/calcDistance(p);
	}
	
	public double calcNetForceExertedByX(Planet[] planets) {
		
		double netForceX = 0;
		
		for(Planet planet: planets) {
			netForceX = netForceX + calcForceExertedByX(planet);
		}
		
		return netForceX;
	}
	
	public double calcNetForceExertedByY(Planet[] planets) {
		
		double netForceY = 0;
		
		for(Planet planet: planets) {
			netForceY = netForceY + calcForceExertedByY(planet);
		}
		
		return netForceY;
	}
	
	public void update(double dt, double fX, double fY) {
		
		double aNetX = fX/mass;
		double aNetY = fY/mass;
		
		xxVel = xxVel + (dt * aNetX);
		yyVel = yyVel + (dt * aNetY);
		
		xxPos = xxPos + (dt * xxVel);
		yyPos = yyPos + (dt * yyVel);
		
	}
	
	/**
     * Draw the planet on the screen
     */
    public void draw() {
        String dir = "images/";
        StdDraw.picture(xxPos, yyPos, dir + imgFileName);
    }

}
