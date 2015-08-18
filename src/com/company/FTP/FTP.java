package com.company.FTP;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
import java.util.LinkedList;

/**
 * Created by compaurum on 01.07.2015.
 */

public class FTP {
        static final int STEP = 100;
        public static void main(String[] args) throws IOException, InterruptedException {
                URL url = null;
                URLConnection connection = null;
                try {
                        url = new URL("ftp://ftp.ripe.net/ripe/stats/delegated-ripencc-latest");
                }
                catch (Exception e){

                }
                DataInputStream stream = new DataInputStream(url.openStream());
                String currentLine;
                LinkedList<String> list = new LinkedList<String>();
                LinkedList<String> validAdresses = new LinkedList<String>();

                while ((currentLine = stream.readLine()) != null){
                        String [] line = currentLine.split("\\|");
                        if (line[2].equals("ipv4"))
                                list.add(line[3]);
                }
                stream.close();

                LinkedList<newThread> threads = new LinkedList();
                for (int i = 0; i <= list.size()/STEP; i++) {
                        int start = i * STEP;
                        int interval = (start+STEP > list.size()) ? list.size()-start : STEP;
                        newThread thread = new newThread(start, interval, list, validAdresses);
                        threads.add(thread);
                        System.out.println( start + "  " + interval);
                        thread.start();
                        System.out.println(i);
                }

                for (newThread temp : threads){
                        temp.join();
                }
                System.out.println("\n\n\n Valid adresses are:\n ");
                for (String adr : validAdresses){
                        System.out.println(adr);
                }
        }
}

class newThread extends Thread {

        LinkedList list;
        int step;
        int i;
        int finish;
        LinkedList validAdresses;
        public newThread(int i, int step, LinkedList list, LinkedList validAdresses){
                this.i = i;
                this.step = step;
                this.validAdresses = validAdresses;
                this.list = list;
                this.finish = i + step;
        }

        @Override
        public void run() {
                for (; i < finish; i++) {
                        URL url = null;
                        try {
                                url = new URL("http://" + list.get(i));
                                URLConnection connection = url.openConnection();
                                connection.setConnectTimeout(300);
                                connection.connect();
                                // connection.
                                validAdresses.add(list.get(i));
                                System.out.println(list.indexOf(list.get(i)) + " Valid host: " + url);
                        } catch (UnknownHostException e) {
                                System.out.println(list.indexOf(list.get(i)) + " Unknown host: " + url);
                        } catch (ConnectException e) {
                                System.out.println(list.indexOf(list.get(i)) + " Time is out: " + url);
                        } catch (SocketTimeoutException e) {
                                System.out.println(list.indexOf(list.get(i)) + " Time is out: " + url);
                        } catch (ProtocolException e) {
                                System.out.println(list.indexOf(list.get(i)) + " Protocol Exception: " + url);
                        } catch (Exception e) {
                                e.printStackTrace();
                        } finally {
                                //connection = null;
                        }

                }
                System.out.println(this.isAlive() + "Thread ended");
        }


}
