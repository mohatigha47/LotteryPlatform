import java.util.ArrayList;
import java.util.Random;

public class ServeurBillet {
    private ArrayList<LotteryObserver> observers = new ArrayList<>();

    public void addObserver(LotteryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LotteryObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(ArrayList<Integer> winningNumbers) {
        for (LotteryObserver observer : observers) {
            observer.updateWinningNumbers(winningNumbers);
        }
    }

    public void vendreBillet(JoueurLottery joueurLottery) {
        addObserver(joueurLottery);
        new Thread(new AchatBillet(joueurLottery)).start();
    }

    public ArrayList<Integer> getNumerosGagnants() {
        Random random = new Random();
        ArrayList<Integer> numeros = new ArrayList<>();
        int i = 0;
        while (i < Consts.K) {
            int numero = Consts.numbersSet[random.nextInt(Consts.numbersSet.length)];
            if (!numeros.contains(numero)) {
                numeros.add(numero);
                i++;
            }
        }
        notifyObservers(numeros);
        return numeros;
    }
}

class AchatBillet implements Runnable {
    JoueurLottery joueurLottery;

    public AchatBillet(JoueurLottery joueurLottery) {
        this.joueurLottery = joueurLottery;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < (random.nextInt(3) + 1); i++) {
            Billet billet = new Billet();
            joueurLottery.acheterBillet(billet);
        }
        System.out.println(joueurLottery._toString());
    }
}



