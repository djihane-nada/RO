import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;


public class Probleme_2 {
	public static void solvelle(int n ){
		double[] xPos = new double [n];
		double[] yPos = new double [n];
		for(int i=0; i<n; i++){
			xPos[i]= Math.random()*100;
			yPos[i]= Math.random()*100;

		}
      double[][] p = new double[n][n];
      for(int i=0; i<n; i++){
    	  for(int j=0; j<n; j++){
    		  p[i][j]=Math.sqrt(Math.pow(xPos[i]-xPos[j],2)+Math.pow(yPos[i]-yPos[j], 2));
    		  
    	  }
      }
		try{
			IloCplex cplex = new IloCplex();
			
			//variables
			IloNumVar[][] x = new IloNumVar[n][];
			for(int i=0; i<n; i++){
				x[i]= cplex.boolVarArray(n);
			}
			IloLinearNumExpr obj = cplex.linearNumExpr();
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					if(i==1 && j==1){
						obj.addTerm(p[i][j], x[i][j]);
					}
				}
			}
			// declaration de la fonction objectif
	    cplex.addMaximize(obj);
	    
	    //contrainte
	    for(int j=0; j<n; j++){
	    	IloLinearNumExpr expr = cplex.linearNumExpr();
	    	for(int i=0; i<n; i++){
	    		if(j==1){
	    			expr.addTerm(1.0, x[i][j]);
	    		}
	    	}
	    	cplex.addEq(expr, 1.0);
	    }
	    for( int i=0; i<n; i++){
	    	IloLinearNumExpr expr = cplex.linearNumExpr();
	    	for(int j=0; j<n; j++){
	    		if(i==1){
	    			expr.addTerm(1.0, x[i][j]);
	    		}
	    	}
	    	cplex.addEq(expr, 1.0);
	    }
	     
	    
	        cplex.solve();
	        cplex.end();
	   		}catch(IloException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
 

