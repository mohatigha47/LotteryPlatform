import java.util.ArrayList;

public class JoueurLottery implements LotteryObserver {
    private String name;
    private ArrayList<Billet> billets = new ArrayList<>();
    public JoueurLottery(String name){
        this.name = name;
    }

    public void acheterBillet(Billet billet) {
        this.billets.add(billet);
    }

    public boolean isGagnant(ArrayList<Integer> numeroGagnants){
        int compteurBilletsGagnants = 0;
        for(Billet billet : this.billets){
            int compteurNumeroGagnantsParBillet = 0;
            for(Integer numero : billet.numbers){
                if(numeroGagnants.contains(numero)){
                    compteurNumeroGagnantsParBillet++;
                }
            }
            if(compteurNumeroGagnantsParBillet >= Consts.T){
                compteurBilletsGagnants++;
            }
        }
        if(compteurBilletsGagnants >=1) return true; else return false;
    }

    public String _toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Player: ").append(this.getName()).append(" has these tickets: ");
        for (Billet billet : this.billets) {
            sb.append(billet.numbers).append(", ");
        }
        // Remove the trailing comma and space
        if (!this.billets.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Billet> getBillets() {
        return billets;
    }

    public void setBillets(ArrayList<Billet> billets) {
        this.billets = billets;
    }


    @Override
    public void updateWinningNumbers(ArrayList<Integer> winningNumbers) {
        // Implement how a player reacts to the update in winning numbers
        System.out.println(name + " received winning numbers update: " + winningNumbers);
        // Check if the player has a winning ticket and take appropriate action
        if (isGagnant(winningNumbers)) {
            System.out.println(name + " is a winner!");
            // Perform any actions for winning, such as awarding prizes
        }
    }
}
