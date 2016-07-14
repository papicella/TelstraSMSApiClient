package pas.org.pivotal.telstra.client;

public class SmsWithBody
{
    private String to;
    private String body;

    public SmsWithBody()
    {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "SmsWithBody{" +
                "to='" + to + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
