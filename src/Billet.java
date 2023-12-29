import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Billet {
    private String ID;
    private ArrayList<Integer> numbers = new ArrayList<>();
    private int category;

    public Billet() {
        this.ID = java.util.UUID.randomUUID().toString();
        Random random = new Random();
        int i = 0;
        while(i<Consts.K){
            int number = (random.nextInt(Consts.N))+1;
            if(!this.numbers.contains(number)){
                this.numbers.add(number);
                i++;
            }
        }
        this.category = random.nextInt(2)+1;
    }



    public String _toString(){
        return "ID: "+this.ID+" Numbers : "+this.numbers+" Category : "+this.category;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
