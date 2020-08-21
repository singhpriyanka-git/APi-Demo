import com.blobcity.json.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class AssignComparatorImpl{

    public static void readFiles(String loc) throws IOException {
        System.out.println("Reading files"+loc);
        File file=new File(loc);
        Scanner sc=new Scanner(file);
        while(sc.hasNextLine())
        {
            String outputfile1  = getUrlContents(sc.nextLine());
            System.out.println(outputfile1);

        }

    }

    public static void main(String arr[]) throws IOException {
        readFiles("File1.txt");
        readFiles("File2.txt");
        String url="https://reqres.in/api/users/3";
        String url1="https://reqres.in/api/users/7";
        JSON.areEqual(readApiResponse(url),readApiResponse(url1));

    }

    private static String readApiResponse(String url) throws IOException
    {
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
        return inputLine;
    }

    private static String getUrlContents(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        // many of these calls can throw exceptions, so i've just
        // wrapped them all in one try/catch statement.
        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
}
