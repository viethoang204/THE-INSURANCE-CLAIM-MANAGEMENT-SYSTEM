/**
 * @author <Duong Viet Hoang - S3962514>
 */

package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Claim {
    private String id; // Format: f-xxxxxxxxxx
    private Date claimDate;
    private Customer insuredPerson;
    private InsuranceCard cardNumber;
    private Date examDate;
    private List<String> documents;
    private double claimAmount;
    private String status; // New, Processing, Done
    private BankingInfo receiverBankingInfo; // Bank – Name – Number

    public Claim(String id, Date claimDate, Customer insuredPerson, InsuranceCard cardNumber, Date examDate, List<String> documents, double claimAmount, String status, BankingInfo receiverBankingInfo) {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
    }

    public InsuranceCard getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(InsuranceCard cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public Customer getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(Customer insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BankingInfo getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    @Override
    public String toString() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    return "Claim{" +
            "id='" + id + '\'' +
            ", claimDate=" + dateFormat.format(claimDate) +
            ", insuredPersonId='" + insuredPerson +
            ", cardNumber='" + cardNumber +
            ", examDate=" + dateFormat.format(examDate) +
            ", documents=" + documents +
            ", claimAmount=" + claimAmount +
            ", status='" + status + '\'' +
            ", receiverBankingInfo='" + receiverBankingInfo + '\'' +
            '}';
}
}