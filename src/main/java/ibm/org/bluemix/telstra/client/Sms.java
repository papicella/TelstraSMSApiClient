package ibm.org.bluemix.telstra.client;

/**
 * Created by pas on 20/07/15.
 */
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
