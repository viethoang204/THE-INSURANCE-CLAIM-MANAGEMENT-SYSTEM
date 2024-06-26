/**
 * @author <Duong Viet Hoang - S3962514>
 */

package Controller;

import Model.Customer;
import Model.InsuranceCard;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class InsuranceCardController {
    public String currentCardOrder = "default";
    private final ClaimController claimController;
    private static InsuranceCardController instance;

    public static InsuranceCardController getInstance(){
        if (instance == null) {
            instance = new InsuranceCardController();
        }
        return instance;
    }

    public List<InsuranceCard> getAll(){
        return new ArrayList<>(this.getListOfInsuranceCards());
    }

    public InsuranceCardController() {
        this.claimController = ClaimController.getInstance();
    }

    public ArrayList<InsuranceCard> getListOfInsuranceCards() {
        return claimController.getListOfInsuranceCards();
    }

    public InsuranceCard getOne(String insuranceCardNumber){
        return claimController.getListOfInsuranceCards().stream().filter(insuranceCard -> insuranceCard.getCardNumber().equals(insuranceCardNumber)).findFirst().orElse(null);
    }

    public boolean delete(String insuranceCardNumber){
        if(claimController.getListOfInsuranceCards().removeIf(insuranceCard -> insuranceCard.getCardNumber().equals(insuranceCardNumber))){
            writeInsuranceCardToFile();
            return true;
        }
        return false;
    }

    public InsuranceCard add(String cardNumber, Customer cardHolder, String policyOwner, Date expirationDate) {
        // Create a new InsuranceCard object
        InsuranceCard newCard = new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDate);
        this.getListOfInsuranceCards().add(newCard);
//        claimController.writeInsuranceCardoFile();
        return newCard;
    }

    public void sortInsuranceCardByDate(boolean ascending) {
        if (ascending) {
            claimController.getListOfInsuranceCards().sort(Comparator.comparing(InsuranceCard::getExpirationDate));
            currentCardOrder = "expiration date from earliest to latest";
        } else {
            // Sort claims in descending order by date (later dates at the top)
            claimController.getListOfInsuranceCards().sort(Comparator.comparing(InsuranceCard::getExpirationDate).reversed());
            currentCardOrder = "expiration date from earliest to latest";
        }
    }

    public void writeInsuranceCardToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("dataFile/insuranceCards.txt"))) {
            // Write the CSV header
            writer.println("Card Number,Card Holder,Policy Owner,Expiration Date");

            // Write claim records
            for (InsuranceCard insurancecard : claimController.getListOfInsuranceCards()) {
                String cardHolderId = insurancecard.getCardHolder() != null ? insurancecard.getCardHolder().getId() : null;
                writer.println(String.format("%s,%s,%s,%s",
                        insurancecard.getCardNumber(),
                        cardHolderId,
                        insurancecard.getPolicyOwner(),
                        claimController.formatDate(insurancecard.getExpirationDate())
                ));
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
}