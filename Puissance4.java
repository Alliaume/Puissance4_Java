public class Puissance4 {
	 public static final String RESET = "\u001B[0m";
	 public static final String RED = "\u001B[41m";
	 public static final String YELLOW = "\u001B[43m";
	 
		//Initialiser le nom des joueurs
	public static String Init_joueur (int a) {
		String b;
		System.out.println("Nom du joueur"+a+" ?");
		b=Lire.S();
		return b;
	}
	
	//Initialiser la matrice vide
	public static String [][] Init_grille (){
		int lignes, colonnes;
		System.out.println("Quelle est la taille de votre grille ? *Ce n'est pas la taille qui compte ;-)");
		System.out.println("Nombre de lignes :");
		lignes=Lire.i();
		System.out.println("Nombre de colonnes :");
		colonnes=Lire.i();
		String [][] grille= new String[lignes][colonnes]; 
		for (int i=0; i<=lignes-1; i++){
			for (int j=0; j<=colonnes-1; j++){
				grille[i][j]=" ";
			}
		}
		return grille;
	}
	
	//Afficher la grille de jeu
	public static void Affichergrille (String[][] grille){
		int lignes=grille.length, colonnes=grille[0].length;
		for (int i=0; i<=lignes-1; i++){
			System.out.print("|");
			for (int j=0; j<=colonnes-1; j++){
				if (grille[i][j]=="X"){
					System.out.print(RED+grille[i][j]+RESET);
				}
				else if (grille[i][j]=="O"){
					System.out.print(YELLOW+grille[i][j]+RESET);
				}
				else{
					System.out.print(grille[i][j]);
				}
				System.out.print("|");
			}
			System.out.println(" ");
		}
		for (int k=0; k<=colonnes-1; k++){
			System.out.print("+-");
		}
		System.out.println("+");
		for (int l=1; l<=colonnes; l++){
			System.out.print(" "+l);
		}
		System.out.println();
	}
	
	//afficher le pion qui tombe petit à petit
	public static void gravité(String[][] grille, int tour, int colonne) throws InterruptedException{
	int casevide=0;
		for (int i=0; i<=grille.length-1; i++){
			if (grille[i][colonne]==" "){
				casevide=i;
			}
		}
		for (int j=0; j<=casevide; j++){
			for (int k=0; k<=100; k++){
				System.out.println();
			}
			grille=affectation(grille, tour, j, colonne);
			Affichergrille(grille);
			Thread.sleep(500);
		}
	}
	
	//vérifier si la colonne jouée est valide ou pas
	//true si la colonne est valide, false sinon
	public static boolean Vérifiercolonne (String[][] grille, int colonne){
		boolean colonnevalide=true;
		if ((colonne<grille[0].length)&&(grille[0][colonne]==" ")){
			colonnevalide=true;
		}
		else{
			colonnevalide=false;
		}
		return colonnevalide;
	}
	
	//affecter un symbole dans la case où le joueur souhaite jouer
	public static String[][] affectation(String[][] grille, int tour, int indice, int colonne){
		if (tour%2==0){
			grille[indice][colonne]="X";
		}
		else{
			grille[indice][colonne]="O";
		}
		if (indice>=1){
			grille[indice-1][colonne]=" ";
		}
		return grille;
	}
		
	
	//saisir la colonne où le joueur souhaite jouer
	public static int saisircolonne(String [][] grille){
		int taille=grille[0].length, colonne;
		boolean colonnevalide=true;
		do{ 
			if (colonnevalide==false){
				System.out.println("Désolé, cette colonne n'est pas valide. Veuillez recommencer.");
			}
			System.out.println("Quelle colonne souhaitez-vous jouer ?");
			colonne=Lire.i()-1;
			colonnevalide=Vérifiercolonne(grille, colonne);
		} while (colonnevalide==false);
		return colonne;
	}
	
	//vérifier si la position jouée est gagnante
        //retourne true si victoire, false sinon
        public static boolean Victoire(String[][]grille){
	        boolean positiongagnante=false;
	        for (int i=0; i<=grille.length-1; i++){
                for (int j=0; j<=grille[0].length-4; j++){
                   if ((grille[i][j]!=" ")&&(grille[i][j]==grille[i][j+1])&&(grille[i][j]==grille[i][j+2])&&(grille[i][j]==grille[i][j+3])){
                        positiongagnante=true;
                    }
                }
	        }
	        for (int i=0; i<=grille.length-4; i++){
                for (int j=0; j<=grille[0].length-1; j++){
            		if ((grille[i][j]!=" ")&&(grille[i][j]==grille[i+1][j])&&(grille[i][j]==grille[i+2][j])&&(grille[i][j]==grille[i+3][j])){
                        positiongagnante=true;
                    }
                }
	        }
	        for (int i=0; i<=grille.length-4; i++){
                for (int j=0; j<=grille[0].length-4; j++){
                    if ((grille[i][j]!=" ")&&(grille[i][j]==grille[i+1][j+1])&&(grille[i][j]==grille[i+2][j+2])&&(grille[i][j]==grille[i+3][j+3])){
                        positiongagnante=true;
                    }
                }
	        }
	        for (int i=0; i<=grille.length-4; i++){
                for (int j=3; j<=grille[0].length-1; j++){
                    if ((grille[i][j]!=" ")&&(grille[i][j]==grille[i+1][j-1])&&(grille[i][j]==grille[i+2][j-2])&&(grille[i][j]==grille[i+3][j-3])){
                        positiongagnante=true;
                    }
                }
	        }
	        return positiongagnante;
		}

		//faire jouer l'ordinateur au hasard
		public static int coupOrdinateur(String[][]grille){
			int colonne=(int)(Math.random()*grille[0].length);
			while (Vérifiercolonne(grille, colonne)==false){
				colonne=(int)(Math.random()*grille[0].length);
			}
			return colonne;
		}

		
	public static void main (String[] args) throws InterruptedException{
		String [][] grille = Init_grille();
		int typejeu;
		System.out.println("Jeu Humain vs ordinateur : tapez 1, Jeu Humain vs Humain : tapez 2. ");
		do { typejeu=Lire.i();
		}while ((typejeu!=1)&&(typejeu!=2));
		int tour=1;
		boolean victoire=false;
		String J1=Init_joueur(1), J2;
		if (typejeu==2){
			J2=Init_joueur(2);
		}
		else{
			J2="Ordinateur";
		}
		Affichergrille(grille);
		while ((tour<=(grille.length*grille[0].length))&&(victoire==false)){
			if (tour%2==0){
				System.out.println("C'est à "+J2+" de jouer.");
				if (J2=="Ordinateur"){
					gravité(grille, tour, coupOrdinateur(grille));
				}
				else{
					gravité(grille, tour, saisircolonne(grille));
				}
			}
			else {
				System.out.println("C'est à "+J1+" de jouer.");
				gravité(grille, tour, saisircolonne(grille));
			}
			victoire=Victoire(grille);
			tour=tour+1;
		}
		if (victoire==true){
			if (tour%2==0){ //la parité des tours s'inverse (si J1 gagne, le tour est pair)
				System.out.println("Bravo "+J1+", vous avez gagné !");
			}
			else if (J2!="Ordinateur"){
				System.out.println("Bravo "+J2+", vous avez gagné !");
			}
			else{
				System.out.println("Vous avez perdu. Essayez encore ! ");
			}
		}
		else {
		System.out.println("Dommage, il n'y a pas de vainqueur...");
		}
	
	}
}
