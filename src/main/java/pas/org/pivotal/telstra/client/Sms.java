package pas.org.pivotal.telstra.client;

public class Sms
{
    private String to;

    public Sms()
    {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "to='" + to + '\'' +
                '}';
    }
}
