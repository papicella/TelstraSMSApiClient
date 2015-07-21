<h1> Telstra Client Side SMS API Consumer </h1>

The following project can be used to consume the Telstra SMS Api from a spring boot application. It presents a simple form to send
an SMS to a phone number.

The manifest.yml file below is consuming the Telstra SMS service created in the catalog

manifest.yml
 
```
applications:
- name: pas-telstrasmsapi-client
  memory: 512M
  instances: 1
  host: pas-telstrasmsapi-client
  domain: mybluemix.net
  path: ./target/TelstraSMSApiClient-0.0.1-SNAPSHOT.jar
  env:
   JBP_CONFIG_IBMJDK: "version: 1.8.+"
  services:
    - TelstraSMS-service
 ```


Pas Apicella [pasapi at au1.ibm.com] is a Bluemix Technical Specialist at IBM Australia