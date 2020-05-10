package TreasureHunt;
// xrisimopoietai o algorithmos quickhull,

//Anastasios Pantzartzis ->apantzar@csd.auth.gr
//AEM->3216

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Mines {

    /**
     * This is the main class of my program. Totally I used 5 classes.
     * Here i read the input from the txt and stored the points to the objects I Made.
     * I have one class for the minefield which except of mines has the starting point and the treasure.
     * The class named QuickHull is the only part of the programm that is not mine(i'll write the url there).
     * One class for the second part of the project named treasure.
     * Finally the class named Mine is the base class of my array lists.
     * 
     * After storing the variables to my objects, main class is doesnt make many things. 
     * 
     * @param args the command line arguments
     * @throws java.io.IOException
     */
	
	 
	
    public static void main(String[] args) throws IOException {
    	
    	
    	
    	
    	final class Treasure {
    	    private int dnum;   //diamond number
    	    private int w;      //number of weightings
    	    
    	    // o kataskeuastis tis klasis Treasure 
    	    
    	    public Treasure(){w=0;}
    	    
    	   
    	    public void setDiaNum(int number){dnum=number;}
    	    
    	   
    	    public int zygos(){               
    	        Random randomGenerator = new Random();
    	        int x = randomGenerator.nextInt(100);         
    	        if (x<34)             
    	            return 1;          
    	        else if (x < 67)             
    	            return 0;          
    	        else             
    	            return -1;  
    	    }             

    	    //  o algorithmos pou vriskei ta vari ton diadromon 
    	    // periptoseis me zugo kai peritto arithmo 
    	     
    	     
    	    public int weightings(){
    	        if (dnum==1)
    	            return w;
    	        else if (dnum==2)
    	            return w+1;
    	        else{
    	            w+=1;
    	            dnum=dnum/3;
    	            if (dnum%3!=0){ 
    	                int mod=dnum%3;
    	                int z=zygos();
    	                if (z==0)
    	                    dnum+= mod;
    	            }
    	            //weightings();
    	        }
    	        return w;
    	    }
    	}
    	
    	
    	// klasi Mine, xrisompoieitai gia na lambvanei tis syntetagmenew tis narkis
    	final class Mine {
     	    private int x;
     	    private int y;
     	    
     	    /**
     	     * Constructor.
     	     * @param x
     	     * @param y
     	     */
     	    public Mine(int x,int y){
     	        this.x=x;
     	        this.y=y;
     	    }
     	    
     	    /**
     	     * getters
     	     * @return
     	     */
     	    public int getX(){return x;}
     	    public int getY(){return y;}
     	}
    	
    	
    	//klasi Minefield, xrisompoieitai gia na kataxorei se disdistato pinaka tis
    	//syntatagmenew pou tha diavazontai sto arxeio txt, diladi ta empodia apo ta opoia den mporei na
    	//perasei kai yparxoyns oi narkes, diladi ta mines
    	
    	final class MineField {
    	    private ArrayList<Mine> Mines, path1, path2;
    	    
    	    /**
    	     * Constructor
    	     */
    	    public MineField(){
    	        Mines= new ArrayList<>();
    	        path1= new ArrayList<>();
    	        path2= new ArrayList<>();
    	    }
    	    
    	    // prosthiki sti mines Apath1 and path 2 a Mine.
    	     
    	    public void add(Mine mine){Mines.add(mine);}
    	    public void add1(Mine mine){path1.add(mine);}
    	    public void add2(Mine mine){path2.add(mine);}
    	    
    	   
    	    public ArrayList<Mine> getMines(){return Mines;}
    	    public ArrayList<Mine> getpath1(){return path1;}
    	    public ArrayList<Mine> getpath2(){return path2;}
    	    
    	    // parakato ginetai h eurersi ton diadromon me ton quickhull
    	    // xrismopoiountai 3 epanalipseis gia na vrethoun oi diadromes
    	    //h metavliti ekk einai gia tin ekkinisi 
    	    // i metavliti tr einai to simeio sto opoio vrisketai o thisauros
    	    
    	    
    	    public void paths(int ekk ,int tr,ArrayList<Mine> hull){
    	        for(int i=ekk; i<=tr;i++){
    	            path1.add(hull.get(i));         //path1
    	        }
    	        for(int i=tr; i<hull.size();i++){   //misi diadromi path2
    	            path2.add(hull.get(i));
    	        }
    	        for(int i=0; i<=ekk;i++){           //alli misi diadromi  path2 
    	            path2.add(hull.get(i));
    	        }
    	       
    	        Collections.reverse(path2);         
    	    }
    	    
    	    // parakato ypologizetai  to kostos tis diadromis
    	    //i metavliti i1 kai i2 einai ta simeia, enoi stin metavliti sum apothikeueta to athroisma
    	    // tou kostoyw ton simeion 
    	    
    	    public double pathDistance(ArrayList<Mine> path){
    	        double sum=0;
    	        int i1=0,i2=1;
    	        while (i2<path.size()){
    	            sum+=calcDistance(path.get(i1).getX(),path.get(i1).getY(),path.get(i2).getX(),path.get(i2).getY());
    	            i1++;
    	            i2++;
    	        }
    	        return sum;
    	    }
    	    
    	    // ypologismos kostoys symfona  me ton typo tis eukleidias apostasis
    	     
    	    public double calcDistance(int x1, int y1, int x2 ,int y2) {
    	        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    	    }
    	    
    	    // kai telika briskoume tin pio suntomi diadromi 
    	    
    	    public void toStr(ArrayList<Mine> path){
    	        System.out.print("The shortest path is:");
    	        for(int i=0;i<path.size();i++){
    	            Mine m=path.get(i);
    	            System.out.print("("+(float)m.getX()+","+(float)m.getY()+")");
    	            if(i!=path.size()-1){System.out.print("-->");}
    	        }
    	        System.out.println();
    	    }
    	}
    	
    	//klasi QuckHull, gia tin efarmogi tou algorithou gia tin euresu tis pio syntomis diadromis

    	final class QuickHull {

    	    
    	    public ArrayList<Mine> quickHull(ArrayList<Mine> points)
    	    {
    	        ArrayList<Mine> convexHull = new ArrayList<Mine>();
    	        if (points.size() < 3)
    	            return (ArrayList) points.clone();  
    	 
    	        int minPoint = -1, maxPoint = -1;
    	        int minX = Integer.MAX_VALUE;
    	        int maxX = Integer.MIN_VALUE;
    	        for (int i = 0; i < points.size(); i++)
    	        {
    	            if (points.get(i).getX() < minX)
    	            {                                    //vriskoume elaxisto kai megisto 
    	                minX = points.get(i).getX();
    	                minPoint = i;
    	            }
    	            if (points.get(i).getX() > maxX)
    	            {
    	                maxX = points.get(i).getX();
    	                maxPoint = i;
    	            }
    	        }
    	        Mine A = points.get(minPoint);                  
    	        Mine B = points.get(maxPoint);
    	        convexHull.add(A);             // prosthetoume ston  hull kai afairoume apo ta simeia
    	        convexHull.add(B);
    	        points.remove(A);
    	        points.remove(B);
    	 
    	        ArrayList<Mine> leftSet = new ArrayList<Mine>();
    	        ArrayList<Mine> rightSet = new ArrayList<Mine>();
    	 
    	        for (int i = 0; i < points.size(); i++)
    	        {
    	            Mine p = points.get(i);
    	            if (pointLocation(A, B, p) == -1)                           
    	                leftSet.add(p);
    	            else if (pointLocation(A, B, p) == 1)
    	                rightSet.add(p);
    	        }
    	        hullSet(A, B, rightSet, convexHull);        
    	        hullSet(B, A, leftSet, convexHull);
    	 
    	        return convexHull;
    	    }
    	 
    	    // ypologismos diadromis 
    	    
    	    public int distance(Mine A, Mine B, Mine C)
    	    {
    	        int ABx = B.getX() - A.getX();
    	        int ABy = B.getY() - A.getY();
    	        int num = ABx * (A.getY() - C.getY()) - ABy * (A.getX() - C.getX());
    	        if (num < 0)
    	            num = -num;
    	        return num;
    	    }
    	            
    	   
    	    public void hullSet(Mine A, Mine B, ArrayList<Mine> set,
    	            ArrayList<Mine> hull)
    	    {
    	        int insertPosition = hull.indexOf(B);
    	        if (set.size() == 0)
    	            return;
    	        if (set.size() == 1)
    	        {
    	            Mine p = set.get(0);
    	            set.remove(p);
    	            hull.add(insertPosition, p);
    	            return;
    	        }
    	        int dist = Integer.MIN_VALUE;
    	        int furthestPoint = -1;                             
    	        for (int i = 0; i < set.size(); i++)
    	        {
    	            Mine p = set.get(i);
    	            int distance = distance(A, B, p);   //euresi tou pio suntomou simeiou
    	            if (distance > dist)
    	            {
    	                dist = distance;
    	                furthestPoint = i;
    	            }
    	        }
    	        Mine P = set.get(furthestPoint);
    	        set.remove(furthestPoint);
    	        hull.add(insertPosition, P);
    	 
    	          // kauoristmos poio einai aristera toy AP 
    	        ArrayList<Mine> leftSetAP = new ArrayList<Mine>();
    	        for (int i = 0; i < set.size(); i++)
    	        {
    	            Mine M = set.get(i);
    	            if (pointLocation(A, P, M) == 1)
    	            {
    	                leftSetAP.add(M);
    	            }
    	        }
    	 
    	        // kauoristmos poio einai aristera toy  PB
    	        ArrayList<Mine> leftSetPB = new ArrayList<Mine>();
    	        for (int i = 0; i < set.size(); i++)
    	        {
    	            Mine M = set.get(i);
    	            if (pointLocation(P, B, M) == 1)
    	            {
    	                leftSetPB.add(M);
    	            }
    	        }
    	        hullSet(A, P, leftSetAP, hull);
    	        hullSet(P, B, leftSetPB, hull);
    	 
    	    }
    	 
    	    //euresi an kapoio simeio anikei sto aristero h deksio synolo
    	     
    	    public int pointLocation(Mine A, Mine B, Mine P)
    	    {
    	        int cp1 = (B.getX() - A.getX()) * (P.getY() - A.getY()) - (B.getY() - A.getY()) * (P.getX() - A.getX());
    	        if (cp1 > 0)
    	            return 1;
    	        else if (cp1 == 0)
    	            return 0;
    	        else
    	            return -1;
    	    }
    	}

    	
    	
        int xekk=0,yekk=0,xtr=0,ytr=0;
        MineField mf=new MineField();  
        //to Minefied apothikeuei simeio ekkinisis, simeio pou vrisketai o thisauros kai syntetagmenew apo ta mines 
        Treasure treasure=new Treasure();
        
        try (BufferedReader in = new BufferedReader( new FileReader("c:/data.txt"));)
            { 
                String l;
                
                int line=1;
                while ((l = in.readLine()) != null)   //Diavasma arxeiou  txt  gia tis syntetagmenes
                    {                                   
                        
                	    //Diaxorismos kathe grammis gia na pairnoume syntetagmenew x kai y gia ta mines ktl.
                        String[] l2=l.split(" ");           
                        int x=Integer.parseInt(l2[0]) , y=Integer.parseInt(l2[1]);
                        
                        Mine mine=new Mine(x,y);  
                        mf.add(mine);       
                        
                        if(line==1){                
                        	//H grammi ena kseroume oti einai oi syntentagmenes x kai y tou simmeiou ekkinisis 
                            xekk=mine.getX();
                            yekk=mine.getY();
                        }
                        if(line==2){
                            xtr=mine.getX();
                            ytr=mine.getY();
                        }
                        
                        line++;
                    }       
            }
      //klisi tou quickhull's kai apothikeusi se lista pinaka  me onoma p
        QuickHull qh = new QuickHull();                                 
        ArrayList<Mine> p = qh.quickHull(mf.getMines());
        
        //simeio ekkinisis kai simeio pou vrisketai o thisauros
        int ekk=0,tr=0;                             
        for (int i=0;i<p.size();i++){
            if(p.get(i).getX()==xekk && p.get(i).getY()==yekk){ekk=i;}
            if(p.get(i).getX()==xtr && p.get(i).getY()==ytr){tr=i;}    
        }
        
        mf.paths(ekk, tr, p);                           
        //stelno tiw   2  diaforetikes diadromes pou odigoun sto thisauro 
        
        if(mf.pathDistance(mf.getpath1())< mf.pathDistance(mf.getpath2())){
            //System.out.println("The shortest distance is "+mf.pathDistance(mf.getpath1()));     
        	System.out.println("The shortest distance is");
        	System.out.format ("%.5f",mf.pathDistance(mf.getpath1()));
        	System.out.println("/n");
        	//kalo tin sunartisi gia tin euresi tis pio syntomis diadromis kai tin ektypoynoume stin othoni
            mf.toStr(mf.getpath1());
        }
        else {
        	System.out.println("The shortest distance is");
            System.out.format ("%.5f",mf.pathDistance(mf.getpath2()));
            mf.toStr(mf.getpath2());
        }
        
       
        
    }
    
    
}