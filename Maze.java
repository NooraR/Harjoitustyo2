/*
* Harjoitustyö 2
*
* Lausekielinen ohjelmointi II, syksy 2015
*
* Noora Rintamäki (rintamaki.noora.m@student.uta.fi)
*
* Ohjelma kutsuu valmista Generator-luokkaa, saa tältä randomoidun taulukon (labyrintin)
* ja ratkaisee labyrintin käyttäen oikean käden sääntöä. Käyttäjä päättää ratkaistaanko
* taulukko vaihe vaiheelta, kerrallaan vai keskeytetäänkö ohjelma.
*
*/

import java.util.Scanner;

public class Maze {

   /*
   * Globaalit muuttujat
   */
   
   // Muuttujaan tallennetaan taulukko, joka saadaan syötteenä Generator-luokalta
   private static char[][] Maze;
   
   // Kertoo sen hetkisen rivin
   private static int CURRENTLOCATIONROW = 0;
   
   // Kertoo sen hetkisen sarakkeen
   private static int CURRENTLOCATIONCOL = 1;
   
   // Kertoo mikä oli edellinen rivi eli rivi ennen liikkumista
   private static int PREVIOUSLOCATIONROW;
   
   // Kertoo mikä oli edellinen sarake eli sarake ennen liikkumista
   private static int PREVIOUSLOCATIONCOL;
   
   // Edellisen ja nykyisen sijainnin avulla määritettävä sen hetkinen liikkumissuunta
   // Käytetään määrittämään, missä on oikea ja vasen
   private static String DIRECTION;
   
   // Kertoo montako riviä taulukosta löytyy
   // Käytetään tulostuksen ja lopun tarkistuksen apuna
   private static int NROWS;
   
   // Kertoo montako saraketta taulukosta löytyy
   // Käytetään tulostuksen ja lopun tarkistuksen apuna
   private static int NCOLS;
   
   /*
   * Metodi määrittää edellistä ja nykyistä sijaintia hyödyntäen mihin suuntaan ollaan liikkumassa.
   */
   public static void checkDirection() {
      // lähtötilanne eli suunta on alas
      if( CURRENTLOCATIONROW == 0 && CURRENTLOCATIONCOL == 1 ) {
         DIRECTION = "south";
      }
      // suunta on alas
      else if( PREVIOUSLOCATIONROW < CURRENTLOCATIONROW && PREVIOUSLOCATIONCOL == CURRENTLOCATIONCOL ) {
         DIRECTION = "south";
      } 
      // suunta on ylös
      else if( PREVIOUSLOCATIONROW > CURRENTLOCATIONROW && PREVIOUSLOCATIONCOL == CURRENTLOCATIONCOL ) {
         DIRECTION = "north";
      } 
      // suunta on oikealle
      else if( PREVIOUSLOCATIONROW == CURRENTLOCATIONROW && PREVIOUSLOCATIONCOL < CURRENTLOCATIONCOL ) {
         DIRECTION = "east";
      } 
      // suunta on vasemmalle
      else if( PREVIOUSLOCATIONROW == CURRENTLOCATIONROW && PREVIOUSLOCATIONCOL > CURRENTLOCATIONCOL ) {
         DIRECTION = "west";
      }
   }

   /*
   * Voidaanko suuntaan liikkua, suuntaan liikkuminen ja merkkien vaihtaminen.
   */
   public static void move() {
      // jos suunta on alas
      if( DIRECTION == "south" ) {
         // jos vasemmalle voi liikkua
         if( Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL - 1] == ' ' || Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL - 1] == 'o') {
            // muuttaa edellisen sijainnin o-merkiksi
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'o';
            
            // tallentaa edellisen sijainnin
            PREVIOUSLOCATIONROW = CURRENTLOCATIONROW;
            PREVIOUSLOCATIONCOL = CURRENTLOCATIONCOL;
            
            // tallentaa nykyisen sijainnin
            CURRENTLOCATIONCOL--;
            
            // tallentaa merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = '<';
         }
         // jos alaspäin voi liikkua
         else if( Maze[CURRENTLOCATIONROW + 1][CURRENTLOCATIONCOL] == ' ' || Maze[CURRENTLOCATIONROW + 1][CURRENTLOCATIONCOL] == 'o' ) {
            // muuttaa edellisen sijainnin o-merkiksi
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'o';
            
            // tallentaa edellisen sijainnin
            PREVIOUSLOCATIONROW = CURRENTLOCATIONROW;
            PREVIOUSLOCATIONCOL = CURRENTLOCATIONCOL;
            
            // tallentaa nykyisen sijainnin
            CURRENTLOCATIONROW++;
            
            // tallentaa merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'V';
         }
         // jos oikealle voi liikkua
         else if( Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL + 1] == ' ' || Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL + 1] == 'o' ) {
            // muuttaa edellisen sijainnin o-merkiksi
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'o';
            
            // tallentaa edellisen sijainnin
            PREVIOUSLOCATIONROW = CURRENTLOCATIONROW;
            PREVIOUSLOCATIONCOL = CURRENTLOCATIONCOL;
            
            // tallentaa nykyisen sijainnin
            CURRENTLOCATIONCOL++;
            
            // tallentaa merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = '>';
         }
         // jos muihin suuntiin ei voi liikkua, kääntyy takaisin tulosuuntaan
         else {
            // tallentaa uuden merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'A';
            // kääntää suunnan tulosuuntaan
            PREVIOUSLOCATIONROW = PREVIOUSLOCATIONROW + 2;
         }
      }

      // jos suunta on vasemmalle
      if( DIRECTION == "west" ) {
         // jos ylöspäin voi liikkua
         if( Maze[CURRENTLOCATIONROW - 1][CURRENTLOCATIONCOL] == ' ' || Maze[CURRENTLOCATIONROW - 1][CURRENTLOCATIONCOL] == 'o' ) {
            // muuttaa edellisen sijainnin o-merkiksi
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'o';
            
            // tallentaa edellisen sijainnin
            PREVIOUSLOCATIONROW = CURRENTLOCATIONROW;
            PREVIOUSLOCATIONCOL = CURRENTLOCATIONCOL;
            
            // tallentaa nykyisen sijainnin
            CURRENTLOCATIONROW--;
            
            // tallentaa merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'A';
         }
         // Jos suunta on vasemmalle
         else if( Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL - 1] == ' ' || Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL - 1] == 'o' ) {
            // muuttaa edellisen sijainnin o-merkiksi
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'o';
            
            // tallentaa edellisen sijainnin
            PREVIOUSLOCATIONROW = CURRENTLOCATIONROW;
            PREVIOUSLOCATIONCOL = CURRENTLOCATIONCOL;
            
            // tallentaa nykyisen sijainnin
            CURRENTLOCATIONCOL--;
            
            // tallentaa merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = '<';
         }
         // jos suunta on alaspäin
         else if( Maze[CURRENTLOCATIONROW + 1][CURRENTLOCATIONCOL] == ' ' || Maze[CURRENTLOCATIONROW + 1][CURRENTLOCATIONCOL] == 'o' ) {
            // muuttaa edellisen sijainnin o-merkiksi
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'o';
            
            // tallentaa edellisen sijainnin
            PREVIOUSLOCATIONROW = CURRENTLOCATIONROW;
            PREVIOUSLOCATIONCOL = CURRENTLOCATIONCOL;
            
            // tallentaa nykyisen sijainnin
            CURRENTLOCATIONROW++;
            
            // tallentaa merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'V';
         }
         // jos suunta ei ole mikään yllä olevista, palaa takaisin tulosuuntaan
         else {
            // tallentaa uuden merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = '>';
            
            // vaihtaa suuntaa
            PREVIOUSLOCATIONCOL = PREVIOUSLOCATIONCOL - 2;
         }
      }

      // jos suunta on ylöspäin
      if( DIRECTION == "north" ) {
         // jos oikealle voi liikkua
         if( Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL + 1] == ' ' || Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL + 1] == 'o' ) {
            // muuttaa edellisen sijainnin o-merkiksi
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'o';
            
            // tallentaa edellisen sijainnin
            PREVIOUSLOCATIONROW = CURRENTLOCATIONROW;
            PREVIOUSLOCATIONCOL = CURRENTLOCATIONCOL;
            
            // tallentaa nykyisen sijainnin
            CURRENTLOCATIONCOL++;
            
            // tallentaa merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = '>';
         } 
         // jos ylöspäin voi liikkua
         else if( Maze[CURRENTLOCATIONROW - 1][CURRENTLOCATIONCOL] == ' ' || Maze[CURRENTLOCATIONROW - 1][CURRENTLOCATIONCOL] == 'o' ) {
            // muuttaa edellisen sijainnin o-merkiksi
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'o';
            
            // tallentaa edellisen sijainnin
            PREVIOUSLOCATIONROW = CURRENTLOCATIONROW;
            PREVIOUSLOCATIONCOL = CURRENTLOCATIONCOL;
            
            // tallentaa nykyisen sijainnin
            CURRENTLOCATIONROW--;
            
            // tallentaa merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'A';
         }
         // jos vasemmalle voi liikkua
         else if( Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL - 1] == ' ' || Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL - 1] == 'o' ) {
            // muuttaa edellisen sijainnin o-merkiksi
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'o';
            
            // tallentaa edellisen sijainnin
            PREVIOUSLOCATIONROW = CURRENTLOCATIONROW;
            PREVIOUSLOCATIONCOL = CURRENTLOCATIONCOL;
            
            // tallentaa nykyisen sijainnin
            CURRENTLOCATIONCOL--;
            
            // tallentaa merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = '<';
         }
         // jos suunta ei ole mikään yllä olevista, palaa takaisin tulosuuntaan
         else {
            // tallentaa uuden merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'V';
            
            // vaihtaa suuntaa
            PREVIOUSLOCATIONROW = PREVIOUSLOCATIONROW - 2;
         }
      }

      // jos suunta on oikealle
      if( DIRECTION == "east" ) {
         // jos alaspäin voi liikkua
         if( Maze[CURRENTLOCATIONROW + 1][CURRENTLOCATIONCOL] == ' ' || Maze[CURRENTLOCATIONROW + 1][CURRENTLOCATIONCOL] == 'o' ) {
               // muuttaa edellisen sijainnin o-merkiksi
               Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'o';
               
               // tallentaa edellisen sijainnin
               PREVIOUSLOCATIONROW = CURRENTLOCATIONROW;
               PREVIOUSLOCATIONCOL = CURRENTLOCATIONCOL;
               
               // tallentaa nykyisen sijainnin
               CURRENTLOCATIONROW++;
               
               // tallentaa merkin nykyiseen sijaintiin
               Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'V';
         }
         // jos oikealle voi liikkua
         else if( Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL + 1] == ' ' || Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL + 1] == 'o' ) {
            // muuttaa edellisen sijainnin o-merkiksi
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'o';
            
            // tallentaa edellisen sijainnin
            PREVIOUSLOCATIONROW = CURRENTLOCATIONROW;
            PREVIOUSLOCATIONCOL = CURRENTLOCATIONCOL;
            
            // tallentaa nykyisen sijainnin
            CURRENTLOCATIONCOL++;
            
            // tallentaa merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = '>';
         }
         // jos ylöspäin voi liikkua
         else if( Maze[CURRENTLOCATIONROW - 1][CURRENTLOCATIONCOL] == ' ' || Maze[CURRENTLOCATIONROW - 1][CURRENTLOCATIONCOL] == 'o' ) {
            // muuttaa edellisen sijainnin o-merkiksi
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'o';
            
            // tallentaa edellisen sijainnin
            PREVIOUSLOCATIONROW = CURRENTLOCATIONROW;
            PREVIOUSLOCATIONCOL = CURRENTLOCATIONCOL;
            
            // tallentaa nykyisen sijainnin
            CURRENTLOCATIONROW--;
            
            // tallentaa merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = 'A';
         }
         // jos suunta ei ole mikään yllä olevista, palaa takaisin tulosuuntaan
         else {
            // tallentaa uuden merkin nykyiseen sijaintiin
            Maze[CURRENTLOCATIONROW][CURRENTLOCATIONCOL] = '<';
            
            // vaihtaa suuntaa
            PREVIOUSLOCATIONCOL = PREVIOUSLOCATIONCOL + 2;
         }
      }
   }
   
   // tarkistaa onko loppu saavutettu
   public static boolean checkEnd() {
      // jos nykyinen sijainti on päätepiste, palauttaa truen ja lopettaa ohjelman pyörittämisen
      if( CURRENTLOCATIONROW == NROWS - 1 && CURRENTLOCATIONCOL == NCOLS - 2 ) {
         return true;
      }
      // jos nykyinen sijainti ei ole päätepiste, palauttaa falsen
      else {
         return false;
      }
   }
   
   // tulostaa kentän
   public static void printMaze() {
      // tulostaa rivit
      for(int i = 0; i < NROWS; i++) {
         // tulostaa sarakkeet
         for(int j = 0; j < NCOLS; j++) {
            System.out.print(Maze[i][j]);
         }
         // vaihtaa riviä
         System.out.println();
      }
   }
   
   /*
   * Main-metodi, joka pyörittää ohjelmaa
   */
   public static void main(String[] args) {
      // Scannerin luonti
      Scanner input = new Scanner(System.in);
      
      // Alkutervehdyksen tulostus
      System.out.println("-----------\n| M A Z E |\n-----------");
      
      // Vastaanotetaan komentorivisyötteet ja tallennetaan ne muuttujiin
      String str1 = args[0];
      String str2 = args[1];
      String str3 = args[2];

      try {
         // Muuttaa String-tyyppiset komentorivisyötteet int-tyyppisiksi
         int seed = Integer.parseInt(str1);
         NROWS = Integer.parseInt(str2);
         NCOLS = Integer.parseInt(str3);

         // Kutsuu Generator luokkaa
         Generator.initialise(seed);
         
         // Kutsuu Generator-luokkaa, antaa sille parametreina rivien ja sarakkeiden määrän
         // Saa paluuarvona kaksiulotteisen taulukon, joka sisältää sokkelon
         Maze = Generator.generate(NROWS, NCOLS);

         // Tarkistaa, onko käyttäjä päässyt sokkelon loppuun
         boolean end = checkEnd();
         
         // Asettaa käyttäjän aloituskohtaan
         Maze[0][1] = 'V';
         printMaze();

         // Pyörittää peliä, kunnes loppu (tietty taulukon piste) on saavutettu
         while(!end) {
            // tulostaa taulukon
            
               
            // answer-muuttujan esittely ja alustus
            char answer = 'k';
            
            // Kysyy käyttäjältä jatkotoimenpiteen (tarkistaa onko syöte sallittu)
            System.out.println("m/move, s/solve, q/quit?");

            // Vastaanottaa käyttäjän syötteen
            answer = (input.nextLine()).char.At(0);
                        
            /*
            * Vertailee käyttäjän syötettä ja tekee määritellyn toimenpiteen.
            */
            if( answer == 'm') {
               // tarkistaa mihin suuntaan ollaan liikkumassa
               checkDirection();
               // tarkistaa mihin suuntaan on mahdollista liikkua ja liikkuu siihen suuntaan
               move();
               // tarkistaa onko sokkelon loppu/maali saavutettu
               end = checkEnd();
               printMaze();
            }
            else if( answer == 's') {
               // Jatkaa metodien suorittamista, kunnes sokkelon loppu on saavutettu
               while(!end) {
                  // tarkistaa, mihin suuntaan ollaan liikkumassa
                  checkDirection();
                  // tarkistaa mihin suuntaan on mahdollista liikkua ja liikkuu siihen suuntaan
                  move();
                  // tarkistaa, onko sokkelon loppu/maali saavutettu
                  end = checkEnd();
                  
               }
               printMaze();
            }
            else if (answer == 'q') {
               // Muuttaa boolean-muuttujan arvon trueksi ja lopettaa luupin
               end = true;
            }
            else {
               printMaze();
            }
         }
      }
      // jos java antaa virhesyötteen, try-catch poimii sen ja tulostaa käyttäjän määrittämän virheilmoituksen
      catch (Exception e) {
         System.out.println("Invalid command-line argument!");
      }
      
      // Ohjelman päätyttyä tulostaa tekstin.
      System.out.println("Bye, see you soon.");
   }   
}
