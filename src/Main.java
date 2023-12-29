import java.util.ArrayList;

import static java.lang.Thread.sleep;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JoueurLottery joueur1 = new JoueurLottery(1,"Mohamed");
        JoueurLottery joueur2 = new JoueurLottery(2,"Cherif");
        JoueurLottery joueur3 = new JoueurLottery(3,"Farid");
        ServeurBillet serveurBillet = new ServeurBillet();

        serveurBillet.vendreBillet(joueur1);
        serveurBillet.vendreBillet(joueur2);
        serveurBillet.vendreBillet(joueur3);

        // Wait for a period
        try {
            Thread.sleep(2000);
            System.out.println("------------------------Sales ended !----------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ArrayList<Integer> numerosGagnants = serveurBillet.getNumerosGagnants();
        System.out.println("Numeros gagnants: " + numerosGagnants);
        System.out.println(joueur1.getName() + " " + joueur1.isGagnant(numerosGagnants));
        System.out.println(joueur2.getName() + " " + joueur2.isGagnant(numerosGagnants));
        System.out.println(joueur3.getName() + " " + joueur3.isGagnant(numerosGagnants));

    }
}