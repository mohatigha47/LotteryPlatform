import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
    public void writeToFile(String filePath, String data) {
        Path path = Paths.get(filePath);
        try {
            // Check if the file exists
            if (!Files.exists(path)) {
                // If the file does not exist, create it
                Files.createFile(path);
            }
            synchronized (path) {
                // Append the data with a newline to the file
                Files.write(path, (data + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            }
            System.out.println("Data successfully written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < (random.nextInt(3) + 1); i++) {
            Billet billet = new Billet();
            joueurLottery.acheterBillet(billet);
            writeToFile("LotteryData.txt", billet.getID()+" "+joueurLottery.getID());
        }
        System.out.println(joueurLottery._toString());
    }
}



