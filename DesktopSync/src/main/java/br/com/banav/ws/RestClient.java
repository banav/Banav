package br.com.banav.ws;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public abstract class RestClient {

    private Properties properties;

    private HashMap<String, String> parameters;

    private Boolean prod = true;

    protected RestClient() {
        try {
            String computerName = InetAddress.getLocalHost().getHostName();
            if(computerName != null && computerName.equalsIgnoreCase("gilson-note")) {
                prod = false;
            }
        } catch (UnknownHostException e) {

        }
    }

    protected String getBaseURL()  {
        if(prod) {
            return "http://173.230.137.47:8080/bmexpress"; // PRODUÇÃO
        } else {
            return "http://localhost:8080/bmexpress"; // DESENVOLVIMENTO
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> T get(String serviceUrl, JAXBContext jaxbContext) throws IOException, JAXBException {
        URL url = getUrlWithParameters(getBaseURL() + serviceUrl);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object obj = unmarshaller.unmarshal(bufferedReader);

        bufferedReader.close();

        return (T) obj;
    }

    protected <T> T getResult(InputStream in, JAXBContext jaxbContext) throws IOException, JAXBException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        try{
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object obj = unmarshaller.unmarshal(bufferedReader);

        return (T) obj;
        }
        finally {
            if(bufferedReader !=  null)
                bufferedReader.close();
        }
    }

    protected void limparParametros() {
        parameters = new HashMap<String, String>();
    }

    protected void addParametro(String chave, String valor) {
        if(parameters == null) {
            limparParametros();
        }

        if(valor != null) {
            parameters.put(chave, valor);
        }
    }

    private URL getUrlWithParameters(String serviceUrl) throws MalformedURLException, UnsupportedEncodingException {
        StringBuffer url = new StringBuffer(serviceUrl);

        if(parameters != null && parameters.size() > 0) {
            url.append("?");

            Set<String> keySet = parameters.keySet();
            for (String key : keySet) {
                url.append(String.format("%s=%s&", key, parameters.get(key)));
            }

            url.append(String.format("%s=%s", "time", Calendar.getInstance().getTimeInMillis()));
        }

        return new URL(url.toString());
    }
}