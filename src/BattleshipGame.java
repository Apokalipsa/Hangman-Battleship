import java.util.Random;
import java.util.Scanner;

public class BattleshipGame {

    public static void main(String[] args) {
        int[][] board = new int[5][5];               // kreiramo tabelu matrix 5*5
        int[][] ships = new int[3][2];              // kreiramo brodic
        int[] shoot = new int[2];                  // kreiramo niz od 2 mjesta za pogodak
        int attempts=0,                           // memoriski prostor za broj pokusaja
            shotHit=0;                           //  memoriski prostor za brojac pogodaka
        
        initBoard(board);                      // pozivamo prethodno kreiranu metodu za ispis tabele u konzoli
        initShips(ships);                     // pozivamo prethodno kreiranu metodu za upis brodica u tabelu
        
        System.out.println();
        
        do{
            showBoard(board);                          // pozivamo metodu za ispis polja sa svim elementima
            shoot(shoot);                             // pozivamo metodu za gadjanje brodica
            attempts++;                              // broimo pogodke
            
            if(hit(shoot,ships)){                  // ukoliko  brodic nije  pogodjen
                hint(shoot,ships,attempts);       // prikazujemo gdje se igrac nalazi i koliko je bilo pogodaka
                shotHit++;
            }                
            else
                hint(shoot,ships,attempts);    // ukoliko je brodic pogodjen
            
            changeboard(shoot,ships,board);   // mijenjamo novo polje
            

        }while(shotHit!=3);                 // sve dok igrac ne bude imao 3 pogodjena brodica
        
        System.out.println("\n\n\nBattleship Java game finished! You hit 3 ships in "+attempts+" attempts");
        showBoard(board);
    }
    
    public static void initBoard(int[][] board){               // metoda koja crta tabelu
        for(int row=0 ; row < 5 ; row++ )
            for(int column=0 ; column < 5 ; column++ )
                board[row][column]=-1;
    }
    
    public static void showBoard(int[][] board){                // metoda koja printa tabelu i popunjava je sadrzajem
        System.out.println("\t1 \t2 \t3 \t4 \t5");
        System.out.println();
        
        for(int row=0 ; row < 5 ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < 5 ; column++ ){
                if(board[row][column]==-1){                   // postavljamo uslov a zatim
                    System.out.print("\t"+"~");              // popunjavamo matrix datim znakom (brodicem)
                }else if(board[row][column]==0){
                    System.out.print("\t"+"*");             // postavljamo uslov za promjenu prethodnog znaka
                }else if(board[row][column]==1){
                    System.out.print("\t"+"X");            // postavljamo jos jedan uslov za promjenu prethodnih znakova
                }
                
            }
            System.out.println();
        }

    }

    public static void initShips(int[][] ships){         // metoda koja inicijalizuje brodice
        Random random = new Random();                   // pozivamo se na random izbor brojeva iz javine klase
        
        for(int ship=0 ; ship < 3 ; ship++){
            ships[ship][0]=random.nextInt(5);
            ships[ship][1]=random.nextInt(5);
            
             
            
            for(int last=0 ; last < ship ; last++){        //provjeravamo da li je pogodak bio vec upucen na trazenu poziciju
                if( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) )
                    do{                                   // ukoliko je vec bio trazen pogodak prelazimo na do while petlju koja ponovo trazi random
                        ships[ship][0]=random.nextInt(5);
                        ships[ship][1]=random.nextInt(5);
                    }while( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) );
            }
            
        }
    }

    public static void shoot(int[] shoot){                        // metoda koja trazi odabran unos za redove i kolone u tabeli
        Scanner input = new Scanner(System.in);
        
        System.out.print("Row: ");
        shoot[0] = input.nextInt();
        shoot[0]--;
        
        System.out.print("Column: ");
        shoot[1] = input.nextInt();
        shoot[1]--;
        
    }
    
    public static boolean hit(int[] shoot, int[][] ships){           // metoda koja vraca da li je brodic pogodjen ili ne
        
        for(int ship=0 ; ship<ships.length ; ship++){
            if( shoot[0]==ships[ship][0] && shoot[1]==ships[ship][1]){
                System.out.printf("You hit a ship located in (%d,%d)\n",shoot[0]+1,shoot[1]+1);
                return true;
            }
        }
        return false;
    }

    public static void hint(int[] shoot, int[][] ships, int attempt){     // metoda koja vraca tacnu trenutnu poziciju u igri na tabeli
        int row=0,
            column=0;
        
        for(int line=0 ; line < ships.length ; line++){
            if(ships[line][0]==shoot[0])
                row++;
            if(ships[line][1]==shoot[1])
                column++;
        }
        
        System.out.printf("\nHint %d: \nRow %d -> %d ships\n" +
                                 "Column %d -> %d ships\n",attempt,shoot[0]+1,row,shoot[1]+1,column);
    }

    public static void changeboard(int[] shoot, int[][] ships, int[][] board){   // metoda koja mijenja novu tabelu ukoliko je brodic pogodjen
        if(hit(shoot,ships))
            board[shoot[0]][shoot[1]]=1;
        else
            board[shoot[0]][shoot[1]]=0;
    }
}


